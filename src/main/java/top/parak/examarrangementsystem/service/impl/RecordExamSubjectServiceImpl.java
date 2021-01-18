package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.entity.RecordExamSubject;
import top.parak.examarrangementsystem.mapper.RecordExamSubjectMapper;
import top.parak.examarrangementsystem.service.RecordExamSubjectService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecordExamSubjectServiceImpl <p>
 * <p> Description: 考试科目记录业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class RecordExamSubjectServiceImpl implements RecordExamSubjectService {

    @Autowired
    private RecordExamSubjectMapper recordExamSubjectMapper;

    @Override
    public int saveRecordExamSubject(RecordExamSubject recordExamSubject) {
        recordExamSubject.setSerialNumber(String.valueOf(SnowFlakeIDUtils.nextID()));
        int result = recordExamSubjectMapper.insert(recordExamSubject);
        return result;
    }

    @Override
    public int updateRecordExamSubject(RecordExamSubject recordExamSubject) {
        int result = recordExamSubjectMapper.updateById(recordExamSubject);
        return result;
    }

    @Override
    public int deleteRecordExamSubjectsByRecordExamId(Long recordExamId) {
        QueryWrapper<RecordExamSubject> recordExamSubjectQueryWrapper = new QueryWrapper<>();
        recordExamSubjectQueryWrapper.eq("record_exam_id", recordExamId);
        return recordExamSubjectMapper.delete(recordExamSubjectQueryWrapper);
    }

    @Override
    public RecordExamSubject getRecordExamSubjectRecordExamIdAndName(Long recordExamId, String name) {
        QueryWrapper<RecordExamSubject> recordExamSubjectQueryWrapper = new QueryWrapper<>();
        recordExamSubjectQueryWrapper.eq("record_exam_id", recordExamId);
        recordExamSubjectQueryWrapper.eq("name", name);
        return recordExamSubjectMapper.selectOne(recordExamSubjectQueryWrapper);
    }

}
