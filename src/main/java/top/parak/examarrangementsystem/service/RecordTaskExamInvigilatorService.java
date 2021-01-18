package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordGroupInvigilatorDTO;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordTaskExamInvigilator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-15
 */
public interface RecordTaskExamInvigilatorService  {

    int saveRecordTaskExamInvigilator(RecordTaskExamInvigilator recordTaskExamInvigilator);

    int updateRecordTaskExamInvigilator(Long id);

    Map<String, Object> getRecordTaskExamInvigilatorByPage(Long id, int current, int size);

}
