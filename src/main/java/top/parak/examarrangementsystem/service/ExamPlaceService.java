package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.ExamPlaceDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceService <p>
 * <p> Description: 考点业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface ExamPlaceService {

    /**
     * <p>新增考点信息</p>
     * @param name
     * @param description
     * @param position
     * @return
     */
    int saveExamPlace(String name, String description, String position);

    /**
     * <p>获取考点数量</p>
     * @return
     */
    int countOfExamPlace();

    /**
     * <p>根据ID获取考点</p>
     * @param id
     * @return
     */
    ExamPlaceDTO getExamPlaceById(Long id);

    /**
     * <p>获取所有考点</p>
     * @return
     */
    List<ExamPlaceDTO> getExamPlaceList();

    /**
     * <p>分页查询考点</p>
     * @param current
     * @param size
     * @return
     */
    List<ExamPlaceDTO> getExamPlaceByPage(int current, int size);

    /**
     * <p>根据ID更新考点信息</p>
     * @param examPlace
     * @return
     */
    int updateExamPlaceById(ExamPlace examPlace);

    /**
     * <p>根据ID删除单个考点</p>
     * @param id
     * @return
     */
    int deleteExamPlaceById(Long id);

}
