package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordSubmitExamPlacePlanDTO;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlacePlan;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RecordSubmitExamPlacePlanService <p>
 * <p> Description: 考点提交平面图记录业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

public interface RecordSubmitExamPlacePlanService {

    /**
     * <p>提交考点平面图</p>
     * @return
     */
    int saveRecordSubmitExamPlacePlan(Long serialNumber, Long taskId, String planName);

    /**
     * <p>更新考点平面图</p>
     * @return
     */
    int updateRecordSubmitExamPlacePlan(Long id, Long serialNumber, String planName);

    /**
     * <p>审核考点平面图</p>
     * @param recordSubmitExamPlacePlan
     * @return
     */
    int reviewRecordSubmitExamPlacePlan(RecordSubmitExamPlacePlan recordSubmitExamPlacePlan);

    /**
     * <p>获取最大ID</p>
     * @return
     */
    Long getRecordSubmitExamPlacePlanMaxId();

    /**
     * <p>根据ID查询考点平面图</p>
     * @param id
     * @return
     */
    RecordSubmitExamPlacePlanDTO getRecordSubmitExamPlacePlanId(Long id);

    /**
     * <p>根据考点任务ID查询考点平面图</p>
     * @param taskId
     * @return
     */
    RecordSubmitExamPlacePlanDTO getRecordSubmitExamPlacePlanByTaskId(Long taskId);

}
