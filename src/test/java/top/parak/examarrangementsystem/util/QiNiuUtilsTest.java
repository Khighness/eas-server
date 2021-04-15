package top.parak.examarrangementsystem.util;

import com.qiniu.storage.model.FileInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QiNiuUtilsTest {

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Test
    void uploadFile()  {
        File file = new File("C:\\Users\\18236\\Pictures\\avatar");
        if (file.isDirectory()) {
            for (File image : file.listFiles()) {
                qiNiuUtils.uploadFile(image, file.getName());
            }
        }
    }

    @Test
    void downloadFile() {
        qiNiuUtils.downloadFile("学生花名册.xlsx");
    }

    @Test
    void removeFile() {
        qiNiuUtils.removeFile("avatar_3_1.jpg");
    }

    @Test
    void showFile() {
        List<FileInfo[]> allFiles = qiNiuUtils.getAllFiles();
        for (FileInfo[] fileInfos : allFiles) {
            for (FileInfo fileInfo : fileInfos) {
                System.out.println(fileInfo.key);
            }
        }
    }

}
