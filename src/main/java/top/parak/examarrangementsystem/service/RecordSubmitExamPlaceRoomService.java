package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceRoom;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-13
 */

public interface RecordSubmitExamPlaceRoomService {

    /**
     * <p>提交考场</p>
     * @param recordSubmitExamPlaceRoom
     * @return
     */
    int saveRecordSubmitExamPlaceRoom(RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom);

    /**
     * <p>查询提交考场数量</p>
     * @param taskId
     * @return
     */
    int countOfRecordSubmitExamPlaceRoom(Long taskId);

    /**
     * <p>根据ID查询提交考场</p>
     * @param id
     * @return
     */
    RecordSubmitExamPlaceRoomDTO getRecordSubmitExamPlaceRoomById(Long id);

    /**
     * <p>查询所有提交考场</p>
     * @param taskId
     * @return
     */
    List<RecordSubmitExamPlaceRoomDTO> getRecordSubmitExamPlaceRoomList(Long taskId);

    /**
     * <p>分页查询提交考场</p>
     * @param current
     * @param size
     * @param taskId
     * @return
     */
    List<RecordSubmitExamPlaceRoomDTO> getRecordSubmitExamPlaceRoomByPage(int current, int size, Long taskId);

    /**
     * <p>删除提交考场</p>
     * @param id
     * @return
     */
    int deleteRecordSubmitExamPlaceRoomById(Long id);

    /**
     * <p>批量删除提交考场</p>
     * @param idList
     * @return
     */
    int deleteBatchRecordSubmitExamPlaceRoomById(List<Long> idList);

    /**
     * <p>审核考场</p>
     * @param recordSubmitExamPlaceRoom
     * @return
     */
    int reviewRecordSubmitExamPlaceRoomById(RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom);

    /**
     * <p>查询所有审核通过考场</p>
     * @param taskId
     * @return
     */
    List<RecordSubmitExamPlaceRoomDTO> getPassedRecordSubmitExamPlaceRoomList(Long taskId);

}
