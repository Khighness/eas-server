package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceInvigilatorService <p>
 * <p> Description: 考务人员业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface ExamPlaceInvigilatorService {

    /**
     * <p>添加考务人员</p>
     * @param examPlaceInvigilator
     * @return
     */
    int saveExamPlaceInvigilator(ExamPlaceInvigilator examPlaceInvigilator);

    /**
     * <p>添加考务人员</p>
     * @param email
     * @param password
     * @return
     */
    int saveExamPlaceInvigilator(String email, String password);

    /**
     * <p>获取考务人员数量</p>
     * @return
     */
    int countOfExamPlaceInvigilator();

    /**
     * <p>根据ID获取考务人员</p>
     * @param id
     * @return
     */
    ExamPlaceInvigilatorDTO getExamPlaceInvigilatorById(long id);

    /**
     * <p>根据邮箱获取考务人员</p>
     * @param email
     * @return
     */
    ExamPlaceInvigilatorDTO getExamPlaceInvigilatorByEmail(String email);

    /**
     * <p>根据邮箱和密码获取考务人员</p>
     * @param email
     * @param password
     * @return
     */
    ExamPlaceInvigilatorDTO getExamPlaceInvigilatorByEmailAndPassword(String email, String password);

    /**
     * <p>获取所有考务人员</p>
     * @return
     */
    List<ExamPlaceInvigilatorDTO> getExamPlaceInvigilatorList();

    /**
     * <p>分页获取考务人员</p>
     * @param current
     * @param size
     * @return
     */
    List<ExamPlaceInvigilatorDTO> getExamPlaceInvigilatorByPage(int current, int size);

    /**
     * <p>根据ID修改考务人员密码</p>
     * @param id
     * @param password
     * @return
     */
    int updateExamPlaceInvigilatorPasswordById(long id, String password);

    /**
     * <p>根据ID修改考务人员信息</p>
     * @param examPlaceInvigilator
     * @return
     */
    int updateExamPlaceInvigilatorInfoById(ExamPlaceInvigilator examPlaceInvigilator);

    /**
     * <p>根据ID删除单个考务人员</p>
     * @param id
     * @return
     */
    int deleteExamPlaceInvigilatorById(Long id);

    /**
     * <p>根据ID批量删除考务人员</p>
     * @param idList
     * @return
     */
    int deleteBatchExamPlaceInvigilatorById(List<Long> idList);

}
