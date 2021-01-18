package top.parak.examarrangementsystem.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QiNiuUtilsTest {

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Test
    void uploadFile() {
        File file = new File("C:\\Users\\18236\\Pictures\\K\\DMJ.jpg");
        qiNiuUtils.uploadFile(file, "avatar_3_1.jpg");
    }

    @Test
    void downloadFile() {
        qiNiuUtils.downloadFile("学生花名册.xlsx");
    }

    @Test
    void removeFile() {
        qiNiuUtils.removeFile("avatar_3_1.jpg");
    }

}
