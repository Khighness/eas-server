package top.parak.examarrangementsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordExamPlaceInvigilatorServiceTest {

    @Autowired
    private RecordExamPlaceInvigilatorService recordExamPlaceInvigilatorService;

    @Test
    void getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId() {
        recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(Long.parseLong("8119"), Long.parseLong("8"));
    }
}
