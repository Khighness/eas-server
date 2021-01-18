package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.entity.RecordExam;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecordExamConverterTest {

    @Test
    void entityToDTO() {
        RecordExam recordExam = RecordExam.builder()
                .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                .sponsor("Khighness")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        System.out.println(RecordExamConverter.INSTANCE.entityToDTO(recordExam));
    }
}
