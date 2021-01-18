package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ExamPlaceInvigilatorServiceTest {

    @Autowired
    private ExamPlaceInvigilatorService examPlaceInvigilatorService;

    @Test
    void saveExamPlaceInvigilator() {
        examPlaceInvigilatorService.saveExamPlaceInvigilator("1823676372@qq.com", "CZK666");
    }

    @Test
    void countOfExamPlaceInvigilator() {
        log.info("考务人员数量：{}", examPlaceInvigilatorService.countOfExamPlaceInvigilator());
    }

    @Test
    void getExamPlaceInvigilatorById() {
        log.info("ID为[1]的考务人员信息：{}", examPlaceInvigilatorService.getExamPlaceInvigilatorById(1L));
    }

    @Test
    void getExamPlaceInvigilatorByEmail() {
        log.info("邮箱为[1823676372@qq.com]的考务人员信息：{}", examPlaceInvigilatorService.getExamPlaceInvigilatorByEmail("1823676372@qq.com"));
    }

    @Test
    void getExamPlaceInvigilatorByEmailAndPassword() {
        log.info("邮箱为[1823676372@qq.com]，密码为[CZK666]的考务人员信息：{}", examPlaceInvigilatorService.getExamPlaceInvigilatorByEmailAndPassword("1823676372@qq.com", "CZK666"));
    }

    @Test
    void getExamPlaceInvigilatorList() {
        examPlaceInvigilatorService.getExamPlaceInvigilatorList().stream().forEach(e -> { log.info(e.toString()); });
    }

    @Test
    void getExamPlaceInvigilatorByPage() {
        examPlaceInvigilatorService.getExamPlaceInvigilatorByPage(2, 2).stream().forEach(e -> { log.info(e.toString()); });
    }

    @Test
    void updateExamPlaceInvigilatorPasswordById() {
        int res = examPlaceInvigilatorService.updateExamPlaceInvigilatorPasswordById(1L, "CZK333");
        assertEquals(1, res);
    }

    @Test
    void updateExamPlaceInvigilatorInfoById() throws ParseException {
        ExamPlaceInvigilator examPlaceInvigilator = ExamPlaceInvigilator.builder()
                .id(1L)
                .gender("男")
                .birth(new SimpleDateFormat("yyyy-MM-dd").parse("2001-9-11"))
                .build();
        int res = examPlaceInvigilatorService.updateExamPlaceInvigilatorInfoById(examPlaceInvigilator);
        assertEquals(1, res);
    }

    @Test
    void deleteBatchExamPlaceInvigilatorById() {
        List<Long> list = Arrays.asList(1L);
        int res = examPlaceInvigilatorService.deleteBatchExamPlaceInvigilatorById(list);
        assertEquals(5, res);
    }
}
