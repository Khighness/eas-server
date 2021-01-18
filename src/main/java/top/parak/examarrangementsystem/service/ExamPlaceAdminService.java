package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceAdminService <p>
 * <p> Description: 考点管理员业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface ExamPlaceAdminService {

    /**
     * <p>新增考点管理员</p>
     * @param examPlaceAdmin
     * @return
     */
    int saveExamPlaceAdmin(ExamPlaceAdmin examPlaceAdmin);

    /**
     * <p>获取考点管理员数量</p>
     * @return
     */
    int countOfExamPlaceAdmin();

    /**
     * <p>根据ID获取考点管理员</p>
     * @param id
     * @return
     */
    ExamPlaceAdminDTO getExamPlaceAdminById(Long id);

    /**
     * <p>根据考点ID获取考点管理员</p>
     * @param examPlaceId
     * @return
     */
    ExamPlaceAdminDTO getExamPlaceAdminDTOByExamPlaceId(Long examPlaceId);

    /**
     * <p>根据邮箱获取考点管理员</p>
     * @param email
     * @return
     */
    ExamPlaceAdminDTO getExamPlaceAdminByEmail(String email);

    /**
     * <p>根据邮箱和密码获取考点管理员</p>
     * @param email
     * @param password
     * @return
     */
    ExamPlaceAdminDTO getExamPlaceAdminByEmailAndPassword(String email, String password);

    /**
     * <p>获取所有考点管理员</p>
     * @return
     */
    List<ExamPlaceAdminDTO> getExamPlaceAdminList();

    /**
     * <p>分页查询考点管理员</p>
     * @param current
     * @param size
     * @return
     */
    List<ExamPlaceAdminDTO> getExamPlaceAdminByPage(int current, int size);

    /**
     * <p>根据ID修改招办审批员密码</p>
     * @param id
     * @param password
     * @return
     */
    int updateExamPlaceAdminPasswordById(Long id, String password);

    /**
     * <p>根据ID修改招办审批员信息</p>
     * @param examPlaceAdmin
     * @return
     */
    int updateExamPlaceAdminInfoById(ExamPlaceAdmin examPlaceAdmin);

    /**
     * <p>根据ID删除考点管理员</p>
     * @param id
     * @return
     */
    int deleteExamPlaceAdminById(Long id);

}
