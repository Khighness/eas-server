package top.parak.examarrangementsystem.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.parak.examarrangementsystem.config.QiNiuCloudConfigure;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: QiNiuUtils <p>
 * <p> Description: 七牛云文件操作工具类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote 需要注入调用
 */

@Slf4j
@Component
public class QiNiuUtils {

    @Autowired
    private QiNiuCloudConfigure qiNiuCloudConfig;

    @Autowired
    private Auth auth;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    /**
     * <p>获取token</p>
     * @return
     */
    private String getUploadToken() {
        return auth.uploadToken(qiNiuCloudConfig.getBucket());
    }

    /**
     * <p>上传文件</p>
     * @param file     上传文件
     * @param fileName 远程文件名
     * @return true success，false failure
     */
    public boolean uploadFile(File file, String fileName) {
        String name = file.getName();
        log.info("七牛云上传文件 => [{}]", name);
        String mime = FileUtils.getContentType(name);
        try {
            Response response = uploadManager.put(file, fileName, getUploadToken(), null, mime, false);
            for (int i = 0; response.needRetry() && i < qiNiuCloudConfig.getRetryTimes(); i++) {
                response = uploadManager.put(file, fileName, getUploadToken(), null, mime, false);
            }
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (response.isOK()) {
                String fileURL = "http://" + qiNiuCloudConfig.getDomain() + "/" + fileName;
                log.info("七牛云文件路径 => [{}]", fileURL);
                return true;
            }
        } catch (IOException e) {
            log.error("文件上传结果 => [{}]", e.getMessage());
        }
        return false;
    }

    /**
     * <p>上传文件</p>
     * @param inputStream 文件流
     * @param fileName    远程文件名
     * @param mime        文件格式
     * @return true success，false failure
     */
    public boolean uploadFile(InputStream inputStream, String fileName, String mime) {
        log.info("七牛云上传文件流");
        try {
            Response response = uploadManager.put(inputStream, fileName, getUploadToken(), null, mime);
            for (int i = 0; response.needRetry() && i < qiNiuCloudConfig.getRetryTimes(); i++) {
                response = uploadManager.put(inputStream, fileName, getUploadToken(), null, mime);
            }
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (response.isOK()) {
                String fileURL = "http://" + qiNiuCloudConfig.getDomain() + "/" + fileName;
                log.info("七牛云文件路径 => [{}]", fileURL);
                return true;
            }
        } catch (IOException e) {
            log.error("文件上传失败 => [{}]", e.getMessage());
        }
        return false;
    }

    /**
     * <p>下载文件</p>
     * @param fileName 远程文件名
     */
    public void downloadFile(String fileName) {
        try {
            URL url = new URL("http://" + qiNiuCloudConfig.getDomain() + "/" + fileName);
            InputStream inputStream = url.openConnection().getInputStream();
            OutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/main/resources/file/" + fileName));
            byte[] bytes = new byte[1024];
            int temp = 0;
            while ((temp = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, temp);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            log.error("文件下载失败 => [{}]", e.getMessage());
        }
    }

    /**
     * <p>移除文件</p>
     * @param fileName 远程文件名
     * @return
     */
    public boolean removeFile(String fileName) {
        log.info("七牛云删除文件 => [{}]", fileName);
        try {
            Response response = bucketManager.delete(qiNiuCloudConfig.getBucket(), fileName);
            if (response.isOK()) {
                log.error("文件删除结果 => [{}]", fileName);
                return true;
            }
        } catch (QiniuException e) {
            log.error(e.getMessage());
            log.error("文件删除失败 => [{}]", e.getMessage());
        }
        return false;
    }

    /**
     * <p>查看文件</p>
     * @return 文件信息列表
     */
    public List<FileInfo[]> getAllFiles() {
        List<FileInfo[]> list = new LinkedList<>();
        // 文件名前缀
        String prefix = "";
        // 每次迭代的长度限制，最大1000
        int limit = 1000;
        // 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        // 列举文件空间列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiNiuCloudConfig.getBucket(), prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            list.add(fileListIterator.next());
        }
        return list;
    }

}
