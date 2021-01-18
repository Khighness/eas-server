package top.parak.examarrangementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.entity.RecordInvigilator;
import top.parak.examarrangementsystem.mapper.RecordInvigilatorMapper;
import top.parak.examarrangementsystem.service.RecordInvigilatorService;
import org.springframework.stereotype.Service;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecordInvigilatorServiceImpl <p>
 * <p> Description: 考试科目记录业务层实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Service
public class RecordInvigilatorServiceImpl implements RecordInvigilatorService {

    @Autowired
    private RecordInvigilatorMapper recordInvigilatorMapper;

    @Override
    public int saveRecordInvigilator(RecordInvigilator recordInvigilator) {
        int result = recordInvigilatorMapper.insert(recordInvigilator);
        return result;
    }

}
