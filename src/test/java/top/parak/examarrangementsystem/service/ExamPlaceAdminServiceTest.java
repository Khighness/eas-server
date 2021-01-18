package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ExamPlaceAdminServiceTest {

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Test
    void saveExamPlaceAdmin() {
        examPlaceAdminService.saveExamPlaceAdmin(ExamPlaceAdmin.builder().email("1823676372@qq.com").password("CZK666").examPlaceId(1L).build());
    }

    @Test
    void countOfExamPlaceAdmin() {
        log.info("考点管理员数量：{}", examPlaceAdminService.countOfExamPlaceAdmin());
    }

    @Test
    void getExamPlaceAdminById() {
        log.info("ID为[1]的考点管理员信息：{}", examPlaceAdminService.getExamPlaceAdminById(1L));
    }

    @Test
    void getExamPlaceAdminByEmail() {
        log.info("邮箱为[1823676372@qq.com]的考点管理员信息：{}", examPlaceAdminService.getExamPlaceAdminByEmail("1823676372@qq.com"));
    }

    @Test
    void getExamPlaceAdminByEmailAndPassword() {
        log.info("邮箱为[1823676372@qq.com]，密码为[CZK666]的考点管理员信息：{}", examPlaceAdminService.getExamPlaceAdminByEmailAndPassword("1823676372@qq.com", "KAG1823"));
    }

    @Test
    void getExamPlaceAdminList() {
        examPlaceAdminService.getExamPlaceAdminList().stream().forEach( e -> { log.info(e.toString()); });
    }

    @Test
    void getExamPlaceAdminByPage() {
        examPlaceAdminService.getExamPlaceAdminByPage(1, 1).stream().forEach( e -> { log.info(e.toString()); });
    }

    @Test
    void updateExamPlaceAdminPasswordById() {
        int result = examPlaceAdminService.updateExamPlaceAdminPasswordById(1L, "KAG1823");
        assertEquals(1, result);
    }

    @Test
    void updateExamPlaceAdminInfoById() {
        ExamPlaceAdmin examPlaceAdmin = ExamPlaceAdmin.builder().id(1L).gender("男").build();
        int result = examPlaceAdminService.updateExamPlaceAdminInfoById(examPlaceAdmin);
        assertEquals(1, result);
    }

    @Test
    void deleteRecruitAdminById() {
        int result = examPlaceAdminService.deleteExamPlaceAdminById(1L);
        assertEquals(1, result);
    }
}
