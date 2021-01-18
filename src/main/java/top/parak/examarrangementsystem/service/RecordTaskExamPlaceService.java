package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordTaskExamPlaceDTO;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RecordTaskExamPlaceService <p>
 * <p> Description: 考点任务记录业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface RecordTaskExamPlaceService {

    /**
     * <p>新增考点任务</p>
     * @param recordTaskExamPlace
     * @return
     */
    int saveRecordTaskExamPlace(RecordTaskExamPlace recordTaskExamPlace);

    /**
     * <p>查询考点任务数量</p>
     * @return
     */
    int countOfRecordTaskExamPlace();

    /**
     * <p>根据考试ID查询考点任务数量</p>
     * @param recordExamId
     * @return
     */
    int countOfRecordTaskExamPlaceWithRecordExamId(Long recordExamId);

    /**
     * <p>根据ID获取考点任务</p>
     * @param id
     * @return
     */
    RecordTaskExamPlaceDTO getRecordTaskExamPlaceById(Long id);

    /**
     * <p>根据考点ID和考试ID获取考点任务</p>
     * @param examPlaceId
     * @param recordExamId
     * @return
     */
    RecordTaskExamPlaceDTO getRecordTaskExamPlaceByExamPlaceIdAndRecordExamId(Long examPlaceId, Long recordExamId);

    /**
     * <p>根据考试ID查询考点任务</p>
     * @param recordExamId
     * @return
     */
    List<RecordTaskExamPlaceDTO> getRecordTaskExamPlaceByRecordExamId(Long recordExamId);

    /**
     * <p>分页查询考点任务</p>
     * @param current
     * @param size
     * @return
     */
    List<RecordTaskExamPlaceDTO> getRecordTaskExamPlaceByPage(int current, int size);

    /**
     * <p>根据考试ID分页查询考点任务</p>
     * @param recordExamId
     * @param current
     * @param size
     * @return
     */
    List<RecordTaskExamPlaceDTO> getRecordTaskExamPlaceByRecordExamIdByPage(Long recordExamId, int current, int size);

    /**
     * <p>修改考点任务</p>
     * @param recordTaskExamPlace
     * @return
     */
    int updateRecordTaskExamPlaceById(RecordTaskExamPlace recordTaskExamPlace);

    /**
     * <p>删除考点任务</p>
     * @param id
     * @return
     */
    int deleteRecordTaskExamPlaceById(Long id);

    /**
     * <p>已读考点任务</p>
     * @param id
     * @return
     */
    int updateRecordTaskExamPlaceById(Long id);

}
