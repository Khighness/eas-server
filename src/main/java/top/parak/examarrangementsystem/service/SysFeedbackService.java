package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.SysFeedbackDTO;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RoleService <p>
 * <p> Description: 系统反馈业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface SysFeedbackService {

    /**
     * <p>新增反馈信息</p>
     * @param email
     * @param feedback
     * @return
     */
    int saveFeedBack(String email, String feedback);

    /**
     * <p>获取反馈数量</p>
     * @return
     */
    int countOfFeedback();

    /**
     * <p>分页查询反馈</p>
     * @param current
     * @param size
     * @return
     */
    List<SysFeedbackDTO> getFeedbackByPage(int current, int size);

}
