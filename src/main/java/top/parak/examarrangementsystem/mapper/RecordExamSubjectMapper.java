package top.parak.examarrangementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.RecordExamSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.mapper </p>
 * <p> FileName: RecordExamSubjectMapper <p>
 * <p> Description: 考试科目记录持久层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/8
 */

@Mapper
@Repository
public interface RecordExamSubjectMapper extends BaseMapper<RecordExamSubject> {

}
