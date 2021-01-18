package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.RecordExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecordExamPlaceInvigilatorConverterTest {

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
        RecordExamPlaceInvigilator recordExamPlaceInvigilator = RecordExamPlaceInvigilator.builder()
                .id(1L)
                .serialNumber("2020001")
                .identityNumber("2134141244114")
                .photo("k.jpg")
                .invigilateExperience(3)
                .teachingGrade("高二")
                .remark("嘤嘤嘤")
                .reason("不可以")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilatorDTO = RecordExamPlaceInvigilatorConverter.INSTANCE.entityToDTO(examPlaceInvigilator, recordExamPlaceInvigilator);
        System.out.println(recordExamPlaceInvigilatorDTO.toString());
    }
}
