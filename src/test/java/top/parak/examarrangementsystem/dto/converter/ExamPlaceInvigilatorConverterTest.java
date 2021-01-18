package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.Role;

import java.util.Date;

class ExamPlaceInvigilatorConverterTest {

    @Test
    void entityToDTO() {
        ExamPlaceInvigilator examPlaceInvigilator = ExamPlaceInvigilator.builder()
                .id(1L)
                .email("1823676372@qq.com")
                .avatar("avatar.jpg")
                .name("KHighness")
                .gender("男")
                .birth(new Date())
                .signature("不读书")
                .phone("13311119999")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        Role role = Role.builder()
                .id(4)
                .name("考务人员")
                .build();
        ExamPlace examPlace = ExamPlace.builder()
                .id(1L)
                .name("天子一中")
                .build();
        ExamPlaceInvigilatorDTO examPlaceInvigilatorExamPlaceDTO = ExamPlaceInvigilatorConverter.INSTANCE.entityToDTO(examPlaceInvigilator, role, examPlace);
        System.out.println(examPlaceInvigilatorExamPlaceDTO);
    }

}
