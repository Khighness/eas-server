package top.parak.examarrangementsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.mapper </p>
 * <p> FileName: RecordTaskExamPlaceMapper <p>
 * <p> Description: 考点任务记录持久层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Mapper
@Repository
public interface RecordTaskExamPlaceMapper extends BaseMapper<RecordTaskExamPlace> {

}
