package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.entity.RecordExamSubject;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecordExamSubjectConverterTest {

    @Test
    void entityToDTO() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RecordExamSubject recordExamSubject = RecordExamSubject.builder()
                .id(1L)
                .name("语文")
                .endTime(simpleDateFormat.parse("2020-06-07 09:00:00"))
                .startTime(simpleDateFormat.parse("2020-06-07 11:30:00"))
                .build();
        System.out.println(RecordExamSubjectConverter.INSTANCE.entityToDTO(recordExamSubject));
    }

}
