package top.parak.examarrangementsystem.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.RecordExam;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordExamMapperTest {

    @Autowired
    private RecordExamMapper recordExamMapper;

    @Test
    void getRecordExamById() {
        System.out.println(recordExamMapper.getRecordExamById(1L));
    }

    @Test
    void getRecordExamList() {
        recordExamMapper.getRecordExamList().stream().forEach(System.out::println);
    }
}
