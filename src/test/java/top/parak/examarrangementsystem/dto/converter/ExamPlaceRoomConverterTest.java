package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.ExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExamPlaceRoomConverterTest {

    @Test
    void entityToDTO() {
        ExamPlaceRoom examPlaceRoom = ExamPlaceRoom.builder()
                .id(1L)
                .name("三教101")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        ExamPlace examPlace = ExamPlace.builder()
                .id(1L)
                .name("天子一中")
                .build();
        ExamPlaceRoomDTO examPlaceRoomDTO = ExamPlaceRoomConverter.INSTANCE.entityToDTO(examPlaceRoom, examPlace);
        System.out.println(examPlaceRoomDTO);
    }
}
