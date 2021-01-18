package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.ExamPlace;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ExamPlaceServiceTest {

    @Autowired
    private ExamPlaceService examPlaceService;

    @Test
    void saveExamPlace() {
        examPlaceService.saveExamPlace("合肥市第一中学", "安徽省重点中学、联合国教科文组织俱乐部成员、安徽省示范性普通高级中学", "安徽省合肥市滨湖新区西藏路2356号");
    }

    @Test
    void getExamPlaceById() {
        log.info("ID为[1]的考点：{}", examPlaceService.getExamPlaceById(1L));
    }

    @Test
    void getExamPlaceList() {
        examPlaceService.getExamPlaceList().stream().forEach( p -> { log.info(p.toString()); });
    }

    @Test
    void getExamPlaceByPage() {
        examPlaceService.getExamPlaceByPage(1, 1).stream().forEach( p -> { log.info(p.toString()); });
    }

    @Test
    void updateExamPlaceById() {
        int result = examPlaceService.updateExamPlaceById(
                ExamPlace.builder().id(1L).description("安徽省省级示范高中").build()
        );
        assertEquals(1, result);
    }

    @Test
    void deleteExamPlaceById() {
        int result = examPlaceService.deleteExamPlaceById(1L);
        assertEquals(1, result);
    }

}
