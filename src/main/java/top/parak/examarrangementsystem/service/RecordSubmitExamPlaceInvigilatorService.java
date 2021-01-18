package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceInvigilator;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-13
 */

public interface RecordSubmitExamPlaceInvigilatorService {

    /**
     * <p>提交考务人员</p>
     * @param recordExamPlaceInvigilatorId
     * @param recordTaskExamPlaceId
     * @return
     */
    int saveRecordSubmitExamPlaceInvigilator(Long recordExamPlaceInvigilatorId, Long recordTaskExamPlaceId);

    /**
     * <p>查询提交考务人员数量</p>
     * @return
     */
    int countOfRecordSubmitExamPlaceInvigilator(Long taskId);

    /**
     * <p>根据ID查询提交考务人员</p>
     * @param id
     * @return
     */
    RecordSubmitExamPlaceInvigilatorDTO getRecordSubmitExamPlaceInvigilatorById(Long id);

    /**
     * <p>查询所有提交考场</p>
     * @param taskId
     * @return
     */
    List<RecordSubmitExamPlaceInvigilatorDTO> getRecordSubmitExamPlaceInvigilatorList(Long taskId);

    /**
     * <p>分页查询提交考务人员</p>
     * @param current
     * @param size
     * @param taskId
     * @return
     */
    List<RecordSubmitExamPlaceInvigilatorDTO> getRecordSubmitExamPlaceInvigilatorByPage(int current, int size, Long taskId);

    /**
     * <p>删除提交考务人员</p>
     * @param id
     * @return
     */
    int deleteRecordExamPlaceInvigilatorById(Long id);

    /**
     * <p>批量删除提交考务人员</p>
     * @param idList
     * @return
     */
    int deleteBatchRecordExamPlaceInvigilatorById(List<Long> idList);

    /**
     * <p>审核考务人员</p>
     * @param recordSubmitExamPlaceInvigilator
     * @return
     */
    int reviewRecordExamPlaceInvigilatorById(RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator);

    /**
     * <p>查看考试信息</p>
     * @return
     */
    List<Map<String, Object>> getRecordExamPlaceInvigilatorResult(Long examPlaceInvigilatorId);

    /**
     * <p>查询所有审核通过考务人员</p>
     * @param taskId
     * @return
     */
    List<RecordSubmitExamPlaceInvigilatorDTO> getPassedRecordSubmitExamPlaceInvigilatorList(Long taskId);

}
