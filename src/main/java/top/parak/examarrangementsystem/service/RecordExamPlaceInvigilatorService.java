package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RecordExamPlaceInvigilatorService <p>
 * <p> Description: 参加监考考务人员提交信息记录业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

public interface RecordExamPlaceInvigilatorService {

    /**
     * <p>新增考务人员提交信息</p>
     * @param recordExamPlaceInvigilator
     * @return
     */
    int saveRecordExamPlaceInvigilator(RecordExamPlaceInvigilator recordExamPlaceInvigilator);

    /**
     * <p>更新考务人员提交头像</p>
     * @param id
     * @param photo
     * @return
     */
    int updateRecordExamPlaceInvigilatorPhoto(Long id, String photo);

    /**
     * <p>更新考务人员提交信息</p>
     * @param recordExamPlaceInvigilator
     * @return
     */
    int updateRecordExamPlaceInvigilator(RecordExamPlaceInvigilator recordExamPlaceInvigilator);

    /**
     * <p>根据考试ID和考点ID查询考务人员参加数量</p>
     * @param recordExamId
     * @return
     */
    int countOfRecordExamPlaceInvigilator(Long recordExamId, Long examPlaceId);

    /**
     * <p>根据考试ID查询审核通过的考务人员参加数量</p>
     * @param recordExamId
     * @return
     */
    int countOfPassedRecordExamPlaceInvigilator(Long recordExamId, Long examPlaceId);

    /**
     * <p>根据ID查询考务人员提交信息/p>
     * @param id
     * @return
     */
    RecordExamPlaceInvigilatorDTO getRecordExamPlaceInvigilatorById(Long id);

    /**
     * <p>根据考务人员ID和考试ID获取考务人员提交信息</p>
     * @param examPlaceInvigilatorId
     * @param recordExamId
     * @return
     */
    RecordExamPlaceInvigilatorDTO getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(Long examPlaceInvigilatorId, Long recordExamId);

    /**
     * <p>根据考试ID和考点ID分页查询考务人员提交信息</p>
     * @param recordExamId
     * @param current
     * @param size
     * @return
     */
    List<RecordExamPlaceInvigilatorDTO> getRecordExamPlaceInvigilatorByRecordExamIdByPage(Long recordExamId, int current, int size, Long examPlaceId);

    /**
     * <p>根据考试ID分页查询审核通过的考务人员提交信息</p>
     * @param recordExamId
     * @param current
     * @param size
     * @return
     */
    List<RecordExamPlaceInvigilatorDTO> getPassedRecordExamPlaceInvigilatorByRecordExamIdByPage(Long recordExamId, int current, int size, Long examPlaceId);

}
