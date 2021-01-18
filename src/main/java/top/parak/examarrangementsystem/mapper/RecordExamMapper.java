package top.parak.examarrangementsystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.RecordExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.mapper </p>
 * <p> FileName: ExamPlaceMapper <p>
 * <p> Description: 考试记录持久层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Mapper
@Repository
public interface RecordExamMapper extends BaseMapper<RecordExam> {

    RecordExam getRecordExamById(@Param("id") Long id);

    List<RecordExam> getRecordExamList();

}
