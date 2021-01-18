package top.parak.examarrangementsystem.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: FileUtils <p>
 * <p> Description: 文件工具类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/4
 *
 * @apiNote 可以静态调用
 */

@Slf4j
public class FileUtils {

    /* 文件路径 */
    public static final String PARENT_PATH = System.getProperty("user.dir") + "/src/main/resources/file/";

    /**
     * <p>根据文件名获取文件的Content-Type</p>
     * @param fileName
     * @return
     */
    public static String getContentType(String fileName) {
        // 文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 邮件扩展类型
        String mime = null;
        if (suffix.equals(".html")) {
            mime = "text/html";
        } else if (suffix.equals(".xml")) {
            mime = "text/xml";
        } else if (suffix.equals(".xhtml")) {
            mime = "application/xhtml+xml";
        } else if (suffix.equals(".txt")) {
            mime = "text/plain";
        } else if (suffix.equals(".rtf")) {
            mime = "application/rtf";
        } else if (suffix.equals("pdf")) {
            mime = "application/pdf";
        } else if (suffix.equals(".word")) {
            mime = "application/msword";
        } else if (suffix.equals(".ico")) {
            mime = "image/x-icon";
        } else if (suffix.equals(".png")) {
            mime = "image/png";
        } else if (suffix.equals(".gif")) {
            mime = "image/gif";
        } else if (suffix.equals(".jpg") || suffix.equals(".jpeg")) {
            mime = "image/jpeg";
        } else if (suffix.equals(".xls")) {
            mime = "application/vnd.ms-excel";
        } else if (suffix.equals(".au")) {
            mime = "audio/basic";
        } else if (suffix.equals(".mid") || suffix.equals(".midi")) {
            mime = "audio/midi";
        } else if (suffix.equals(".ra") || suffix.equals(".ram")) {
            mime = "audio/x-pn-realaudio";
        } else if (suffix.equals(".mpg") || suffix.equals("mpeg")) {
            mime = "video/mpeg";
        } else if (suffix.equals(".avi")) {
            mime = "video/x-msvideo";
        } else if (suffix.equals(".gz")) {
            mime = "application/x-gzip";
        } else if (suffix.equals(".tar")) {
            mime = "application/x-tar";
        } else {
            mime = "application/octet-stream";
        }
        return mime;
    }

    /**
     * <p>根据文件名获取文件后缀</p>
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * <p>根据url获取文件名</p>
     * @param fileUrl
     * @return
     */
    public static String getFileNameByUrl(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("/"));
    }

    /**
     * <p>根据url下载文件</p>
     * @param fileUrl
     */
    public static void downloadFileByUrl(String fileUrl)  {
        try {
            URL url = new URL(fileUrl);
            InputStream inputStream = url.openConnection().getInputStream();
            OutputStream outputStream = new FileOutputStream(new File(FileUtils.PARENT_PATH + getFileNameByUrl(fileUrl)));
            byte[] bytes = new byte[1024];
            int temp = 0;
            while ((temp = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, temp);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    /**
     * <p>客户端下载单个文件</p>
     * @param fileName 下载文件
     * @param response HttpServletResponse
     */
    public static void downloadFile(String fileName,  HttpServletResponse response) {
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        try {
            //下载文件完整路径
            File file = new File(PARENT_PATH + fileName);
            if (!file.exists()) {
                log.info(file + " 文件不存在");
                return;
            }

            response.reset();
            // 二进制流
            response.setContentType("applicaton/octet-stream; charset=utf-8");
            // UTF-8编码
            response.setCharacterEncoding("UTF-8");
            // 响应体字节数量
            response.setContentLength((int) file.length());
            // 设置响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode( fileName, "UTF-8" ));

            // 将文件字节流写入response
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024];
            int i = 0;
            while ((i = bufferedInputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, i);
                outputStream.flush();
            }
        }  catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (!ObjectUtils.isEmpty(bufferedInputStream)) {
                    bufferedInputStream.close();
                }
                if (!ObjectUtils.isEmpty(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * <p>客户端批量下载文件，打包成Zip格式</p>
     * @param fileNames       文件数组
     * @param zipFileName 下载zip文件名
     * @param response    HttpServletResponse
     */
    public static void downloadFilesInBatches(Vector<String> fileNames, String zipFileName, HttpServletResponse response) {
        File zipTmpFile = null;
        try {
            zipTmpFile = new File(PARENT_PATH + zipFileName);

            if (zipTmpFile.exists()) {
                zipTmpFile.delete();
            }
            zipTmpFile.createNewFile();

            // 创建文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(zipTmpFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            // 添加文件至压缩包
            log.info(">>>  开始压缩文件  <<<");
            for (int i = 0; i < fileNames.size(); i++) {
                String name = fileNames.get(i);
                File file = new File(PARENT_PATH + name);
                if (!file.exists()) {
                    log.error("{}不存在，已跳过", name);
                    continue;
                } else if (file.isDirectory()) {
                    log.info("向{}添加目录：{}", zipFileName, name);
                } else if (file.isFile()){
                    log.info("向{}添加文件：{}", zipFileName, name);
                }
                addFileToZip(file, zipOutputStream);
            }
            log.info(">>>  压缩文件完成  <<<");

            zipOutputStream.close();
            fileOutputStream.close();

            // 重置response
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(zipTmpFile.getName().getBytes("GB2312"), "ISO8859-1"));

            // 下载到客户端
            InputStream inputStream = new BufferedInputStream(new FileInputStream(zipTmpFile.getPath()));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            zipTmpFile.delete();
        }
    }

    /**
     * <p>向压缩包添加文件</p>
     * @param inputFile
     * @param zipOutStream
     */
    private static void addFileToZip(File inputFile, ZipOutputStream zipOutStream) {
        try {
            // 判断当前文件是文件还是目录
            if (inputFile.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(inputFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 512);
                ZipEntry entry = new ZipEntry(inputFile.getName());
                zipOutStream.putNextEntry(entry);

                int read = 0;
                byte[] buff = new byte[512];
                while ((read = bufferedInputStream.read(buff)) != -1) {
                    zipOutStream.write(buff, 0, read);
                }

                bufferedInputStream.close();
                fileInputStream.close();
            } else {
                try {
                    File[] files = inputFile.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        addFileToZip(files[i], zipOutStream);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
