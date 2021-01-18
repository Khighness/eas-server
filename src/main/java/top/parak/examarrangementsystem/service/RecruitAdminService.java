package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.entity.RecruitAdmin;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RecruitAdminService <p>
 * <p> Description: 科目业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface RecruitAdminService {

    /**
     * <p>添加招办管理员</p>
     * @param email
     * @param password
     * @return
     */
    int saveRecruitAdmin(String email, String password);

    /**
     * <p>根据ID获取招办管理员</p>
     * @param id
     * @return
     */
    RecruitAdminDTO getRecruitAdminById(Long id);

    /**
     * <p>根据邮箱获取招办管理员</p>
     * @param email
     * @return
     */
    RecruitAdminDTO getRecruitAdminByEmail(String email);

    /**
     * <p>根据邮箱和密码获取招办管理员</p>
     * @param email
     * @param password
     * @return
     */
    RecruitAdminDTO getRecruitAdminByEmailAndPassword(String email, String password);

    /**
     * <p>根据ID修改招办管理员密码</p>
     * @param id
     * @param password
     * @return
     */
    int updateRecruitAdminPasswordById(Long id, String password);

    /**
     * <p>根据ID修改招办管理员信息</p>
     * @param recruitAdmin
     * @return
     */
    int updateRecruitAdminInfoById(RecruitAdmin recruitAdmin);

}
