package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.ExamPlaceDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExamPlaceConverterTest {

    @Test
    void entityToDTO() {
        ExamPlaceDTO examPlaceDTO = ExamPlaceConverter.INSTANCE.entityToDTO(
                ExamPlace.builder().id(1L).name("黄梅一中").position("黄冈").gmtCreate(new Date()).gmtUpdate(new Date()).build()
        );
        System.out.println(examPlaceDTO);
    }

    @Test
    void entityListToDTOList() {
        ExamPlaceConverter.INSTANCE.entityListToDTOList(Arrays.asList(
                ExamPlace.builder().id(1L).name("华师一附中").position("武汉").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                ExamPlace.builder().id(2L).name("黄冈中学").position("黄冈").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                ExamPlace.builder().id(3L).name("鄂州高中").position("鄂州").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                ExamPlace.builder().id(4L).name("黄石二中").position("黄石").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                ExamPlace.builder().id(5L).name("武汉二中").position("武汉").gmtCreate(new Date()).gmtUpdate(new Date()).build()
        )).stream().forEach(System.out::println);
    }
}
