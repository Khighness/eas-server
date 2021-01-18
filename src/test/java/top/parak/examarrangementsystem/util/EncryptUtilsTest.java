package top.parak.examarrangementsystem.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptUtilsTest {

    @Test
    void generateSalt() {
    }

    @Test
    void encryptByMD5() {
        System.out.println(EncryptUtils.encryptByMD5("123456"));
    }

    @Test
    void encryptByMD5AndSalt() {
    }
}
