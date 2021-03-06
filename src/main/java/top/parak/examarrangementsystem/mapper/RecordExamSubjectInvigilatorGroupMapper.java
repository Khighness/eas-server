package top.parak.examarrangementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.RecordExamSubjectInvigilatorGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KHighness
 * @since 2021-01-15
 */

@Mapper
@Repository
public interface RecordExamSubjectInvigilatorGroupMapper extends BaseMapper<RecordExamSubjectInvigilatorGroup> {

}
