package top.parak.examarrangementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.parak.examarrangementsystem.entity.RecordHistoricalViolation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KHighness
 * @since 2021-01-14
 */

@Mapper
@Repository
public interface RecordHistoricalViolationMapper extends BaseMapper<RecordHistoricalViolation> {

}
