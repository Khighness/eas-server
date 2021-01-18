package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.SysFeedbackDTO;
import top.parak.examarrangementsystem.dto.converter.SysFeedbackConverter;
import top.parak.examarrangementsystem.entity.SysFeedback;
import top.parak.examarrangementsystem.mapper.SysFeedbackMapper;
import top.parak.examarrangementsystem.service.SysFeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: SysFeedbackServiceImpl <p>
 * <p> Description: 系统反馈业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class SysFeedbackServiceImpl implements SysFeedbackService {

    @Autowired
    private SysFeedbackMapper sysFeedbackMapper;

    @Override
    public int saveFeedBack(String email, String feedback) {
        SysFeedback sysFeedback = SysFeedback.builder().email(email).feedback(feedback).build();
        int result = sysFeedbackMapper.insert(sysFeedback);
        return result;
    }

    @Override
    public int countOfFeedback() {
        return sysFeedbackMapper.selectCount(null);
    }

    @Override
    public List<SysFeedbackDTO> getFeedbackByPage(int current, int size) {
        Page<SysFeedback> sysFeedbackPage = new Page<>(current, size);
        sysFeedbackPage = sysFeedbackMapper.selectPage(sysFeedbackPage, null);
        return SysFeedbackConverter.INSTANCE.entityListToDTOList(sysFeedbackPage.getRecords());
    }
}
