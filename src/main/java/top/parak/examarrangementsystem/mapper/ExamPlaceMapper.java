package top.parak.examarrangementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.ExamPlace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.mapper </p>
 * <p> FileName: ExamPlaceMapper <p>
 * <p> Description: 考点持久层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Mapper
@Repository
public interface ExamPlaceMapper extends BaseMapper<ExamPlace> {

}
