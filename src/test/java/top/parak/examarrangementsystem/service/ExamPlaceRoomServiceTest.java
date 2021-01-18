package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ExamPlaceRoomServiceTest {

    @Autowired
    private ExamPlaceRoomService examPlaceRoomService;


    @Test
    void countOfExamPlaceRoom() {
        log.info("考点ID为[1]的考场数量：{}", examPlaceRoomService.countOfExamPlaceRoom(1L));
    }

    @Test
    void getExamPlaceRoomById() {
        log.info("考场ID为[1]的考场信息：{}", examPlaceRoomService.getExamPlaceRoomById(1L));
    }

    @Test
    void getExamPlaceRoomByExamPlaceId() {
        examPlaceRoomService.getExamPlaceRoomByExamPlaceId(1L).stream().forEach( e -> { log.info(e.toString());});
    }

    @Test
    void getExamPlaceRoomByExamPlaceIdByPage() {
        examPlaceRoomService.getExamPlaceRoomByExamPlaceIdByPage(1L, 1, 1).stream().forEach( e -> { log.info(e.toString());});
    }

    @Test
    void updateExamPlaceRoomById() {
        ExamPlaceRoom examPlaceRoom = ExamPlaceRoom.builder().id(1L).name("一教102").build();
        int result = examPlaceRoomService.updateExamPlaceRoomById(examPlaceRoom);
        assertEquals(1, result);
    }

    @Test
    void deleteExamPlaceRoomById() {
        int result = examPlaceRoomService.deleteExamPlaceRoomById(1L);
        assertEquals(1, result);
    }
}
