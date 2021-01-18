package top.parak.examarrangementsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceInvigilator;
import top.parak.examarrangementsystem.mapper.RecordSubmitExamPlaceInvigilatorMapper;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceInvigilatorService;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamArrangementSystemTest {

    @Autowired
    private RecordSubmitExamPlaceInvigilatorMapper recordSubmitExamPlaceInvigilatorMapper;

    @Test
    void commitClose() {
        int n1 = 121, n2 = 127;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = n1; i <= n2; i++) {
            stringBuffer.append("#" + i + " ");
        }
        System.out.println(stringBuffer.toString());
    }

    @Test
    void test() {
        QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_exam_place_invigilator_id", 2);
        recordSubmitExamPlaceInvigilatorMapper.selectList(recordSubmitExamPlaceInvigilatorQueryWrapper).stream().forEach(System.out::println);
    }


}
