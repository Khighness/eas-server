package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlacePlanDTO;
import top.parak.examarrangementsystem.dto.converter.RecordSubmitExamPlacePlanConverter;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlacePlan;
import top.parak.examarrangementsystem.mapper.RecordSubmitExamPlacePlanMapper;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlacePlanService;
import org.springframework.stereotype.Service;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecordSubmitExamPlacePlanService <p>
 * <p> Description: 考点提交平面图记录业务层实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Service
public class RecordSubmitExamPlacePlanServiceImpl implements RecordSubmitExamPlacePlanService {

    @Autowired
    private RecordSubmitExamPlacePlanMapper recordSubmitExamPlacePlanMapper;

    @Override
    public int saveRecordSubmitExamPlacePlan(Long serialNumber, Long taskId, String planName) {
        Long maxId = recordSubmitExamPlacePlanMapper.getMaxId();
        Long id = ObjectUtils.isEmpty(maxId) ? 1L : maxId + 1L;
        RecordSubmitExamPlacePlan recordSubmitExamPlacePlan = RecordSubmitExamPlacePlan.builder()
                .id(id)
                .serialNumber(String.valueOf(serialNumber))
                .examPlacePlan(planName)
                .recordTaskExamPlaceId(taskId)
                .build();
        int result = recordSubmitExamPlacePlanMapper.insert(recordSubmitExamPlacePlan);
        return result;
    }

    @Override
    public int updateRecordSubmitExamPlacePlan(Long id, Long serialNumber, String planName) {
        RecordSubmitExamPlacePlan recordSubmitExamPlacePlan = RecordSubmitExamPlacePlan.builder()
                .id(id)
                .serialNumber(String.valueOf(serialNumber))
                .examPlacePlan(planName)
                .build();
        int result = recordSubmitExamPlacePlanMapper.updateById(recordSubmitExamPlacePlan);
        return result;
    }

    @Override
    public int reviewRecordSubmitExamPlacePlan(RecordSubmitExamPlacePlan recordSubmitExamPlacePlan) {
        recordSubmitExamPlacePlan.setReviewed(true);
        int result = recordSubmitExamPlacePlanMapper.updateById(recordSubmitExamPlacePlan);
        return result;
    }

    @Override
    public Long getRecordSubmitExamPlacePlanMaxId() {
        return null;
    }

    @Override
    public RecordSubmitExamPlacePlanDTO getRecordSubmitExamPlacePlanId(Long id) {
        QueryWrapper<RecordSubmitExamPlacePlan> recordSubmitExamPlacePlanQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlacePlanQueryWrapper.eq("id", id);
        RecordSubmitExamPlacePlan recordSubmitExamPlacePlan = recordSubmitExamPlacePlanMapper.selectOne(recordSubmitExamPlacePlanQueryWrapper);
        return RecordSubmitExamPlacePlanConverter.INSTANCE.entityToDTO(recordSubmitExamPlacePlan);
    }

    @Override
    public RecordSubmitExamPlacePlanDTO getRecordSubmitExamPlacePlanByTaskId(Long taskId) {
        QueryWrapper<RecordSubmitExamPlacePlan> recordSubmitExamPlacePlanQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlacePlanQueryWrapper.eq("record_task_exam_place_id", taskId);
        RecordSubmitExamPlacePlan recordSubmitExamPlacePlan = recordSubmitExamPlacePlanMapper.selectOne(recordSubmitExamPlacePlanQueryWrapper);
        return RecordSubmitExamPlacePlanConverter.INSTANCE.entityToDTO(recordSubmitExamPlacePlan);
    }

}
