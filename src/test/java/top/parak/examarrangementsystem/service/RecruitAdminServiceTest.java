package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.RecruitAdmin;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RecruitAdminServiceTest {

    @Autowired
    private RecruitAdminService recruitAdminService;

    @Test
    void saveRecruitAdmin() {
        int result = recruitAdminService.saveRecruitAdmin("1417653774@qq.com", "KAG1823");
        assertEquals(1, result);
    }

    @Test
    void getRecruitAdminById() {
        log.info("ID为[3]的招办管理员信息：{}", recruitAdminService.getRecruitAdminById(3L));
    }

    @Test
    void getRecruitAdminByEmail() {
        log.info("邮箱为[1823676372@qq.com]的招办管理员信息：{}", recruitAdminService.getRecruitAdminByEmail("1823676372@qq.com"));
    }

    @Test
    void getRecruitAdminByEmailAndPassword() {
        log.info("邮箱为[1823676372@qq.com]，[KAG1823]的招办管理员信息：{}", recruitAdminService.getRecruitAdminByEmailAndPassword("1823676372@qq.com", "KAG1823"));
    }

    @Test
    void updateRecruitAdminPasswordById() {
        int result = recruitAdminService.updateRecruitAdminPasswordById(3L, "CZK666");
        assertEquals(1, result);
    }

    @Test
    void updateRecruitAdminInfoById() throws ParseException {
        RecruitAdmin recruitAdmin = RecruitAdmin.builder()
                .id(3L)
                .name("Khighness")
                .gender("男")
                .birth(new SimpleDateFormat("yyyy-MM-dd").parse("2001-9-11"))
                .build();
        int result = recruitAdminService.updateRecruitAdminInfoById(recruitAdmin);
        assertEquals(1, result);
    }
}
