package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.ExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceInvigilatorService <p>
 * <p> Description: 考场业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface ExamPlaceRoomService {

    /**
     * <p>添加考点考场</p>
     * @param name
     * @param examPlaceId
     * @return
     */
    int saveExamPlaceRoom(String name, int examRoomSerialNumber, Long examPlaceId);

    /**
     * <p>根据考点ID获取考场数量</p>
     * @param examPlaceId
     * @return
     */
    int countOfExamPlaceRoom(Long examPlaceId);

    /**
     * <p>根据ID获取考场</p>
     * @param id
     * @return
     */
    ExamPlaceRoomDTO getExamPlaceRoomById(Long id);

    /**
     * <p>根据考点ID获取所有考点</p>
     * @param examPlaceId
     * @return
     */
    List<ExamPlaceRoomDTO> getExamPlaceRoomByExamPlaceId(Long examPlaceId);

    /**
     * <p>根据考点ID分页查询考场</p>
     * @param examPlaceId
     * @param current
     * @param size
     * @return
     */
    List<ExamPlaceRoomDTO> getExamPlaceRoomByExamPlaceIdByPage(Long examPlaceId, int current, int size);

    /**
     * <p>根据ID修改考场信息</p>
     * @param examPlaceRoom
     * @return
     */
    int updateExamPlaceRoomById(ExamPlaceRoom examPlaceRoom);

    /**
     * <p>根据ID删除考场</p>
     * @param id
     * @return
     */
    int deleteExamPlaceRoomById(Long id);

    int deleteBatchExamPlaceRoomById(List<Long> idList);

}
