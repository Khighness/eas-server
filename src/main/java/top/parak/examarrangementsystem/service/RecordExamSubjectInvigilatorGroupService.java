package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.entity.RecordExamSubjectInvigilatorGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-15
 */
public interface RecordExamSubjectInvigilatorGroupService {

    int saveRecordExamSubjectInvigilatorGroup(RecordExamSubjectInvigilatorGroup recordExamSubjectInvigilatorGroup);

    Map<String, Object> getRecordExamSubjectInvigilatorGroup(Long subjectId, int current, int size);

}
