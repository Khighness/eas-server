package top.parak.examarrangementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.mapper </p>
 * <p> FileName: RecordExamPlaceInvigilatorMapper <p>
 * <p> Description: 参加监考考务人员提交信息记录持久层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Mapper
@Repository
public interface RecordExamPlaceInvigilatorMapper extends BaseMapper<RecordExamPlaceInvigilator> {

}
