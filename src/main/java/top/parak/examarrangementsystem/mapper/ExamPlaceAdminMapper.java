package top.parak.examarrangementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.mapper </p>
 * <p> FileName: ExamPlaceAdminMapper <p>
 * <p> Description: 考点管理员持久层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Mapper
@Repository
public interface ExamPlaceAdminMapper extends BaseMapper<ExamPlaceAdmin> {

}
