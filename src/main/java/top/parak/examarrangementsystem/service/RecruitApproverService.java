package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.entity.RecruitApprover;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RecruitApproverService <p>
 * <p> Description: 招办审批员业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface RecruitApproverService {

    /**
     * <p>新增招办审批员</p>
     * @param recruitApprover
     * @return
     */
    int saveRecruitApprover(RecruitApprover recruitApprover);

    /**
     * <p>获取招办审批员数量</p>
     * @return
     */
    int countOfRecruitApprover();

    /**
     * <p>根据ID获取招办审批员</p>
     * @param id
     * @return
     */
    RecruitApproverDTO getRecruitApproverById(Long id);

    /**
     * <p>根据邮箱获取招办审批员</p>
     * @param email
     * @return
     */
    RecruitApproverDTO getRecruitApproverByEmail(String email);

    /**
     * <p>根据邮箱和密码获取招办审批员</p>
     * @param email
     * @param password
     * @return
     */
    RecruitApproverDTO getRecruitApproverByEmailAndPassword(String email, String password);

    /**
     * <p>获取所有招办审批员</p>
     * @return
     */
    List<RecruitApproverDTO> getRecruitApproverList();

    /**
     * <p>分页查询招办审批员</p>
     * @param current
     * @param size
     * @return
     */
    List<RecruitApproverDTO> getRecruitApproverByPage(int current, int size);

    /**
     * <p>根据ID修改招办审批员密码</p>
     * @param id
     * @param password
     * @return
     */
    int updateRecruitApproverPasswordById(Long id, String password);

    /**
     * <p>根据ID修改招办审批员信息</p>
     * @param recruitApprover
     * @return
     */
    int updateRecruitApproverInfoById(RecruitApprover recruitApprover);

    /**
     * <p>根据ID删除招办审批员</p>
     * @param id
     * @return
     */
    int deleteRecruitApproverById(Long id);

}
