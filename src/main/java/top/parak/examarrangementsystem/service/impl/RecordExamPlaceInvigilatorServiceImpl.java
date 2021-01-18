package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.RecordExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.converter.RecordExamPlaceInvigilatorConverter;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;
import top.parak.examarrangementsystem.mapper.ExamPlaceInvigilatorMapper;
import top.parak.examarrangementsystem.mapper.RecordExamPlaceInvigilatorMapper;
import top.parak.examarrangementsystem.service.RecordExamPlaceInvigilatorService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecordExamPlaceInvigilatorServiceImpl <p>
 * <p> Description: 参加监考考务人员提交信息记录业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Service
public class RecordExamPlaceInvigilatorServiceImpl implements RecordExamPlaceInvigilatorService {

    @Autowired
    private RecordExamPlaceInvigilatorMapper recordExamPlaceInvigilatorMapper;

    @Autowired
    private ExamPlaceInvigilatorMapper examPlaceInvigilatorMapper;

    @Override
    public int saveRecordExamPlaceInvigilator(RecordExamPlaceInvigilator recordExamPlaceInvigilator) {
        recordExamPlaceInvigilator.setSerialNumber(String.valueOf(SnowFlakeIDUtils.nextID()));
        int result = recordExamPlaceInvigilatorMapper.insert(recordExamPlaceInvigilator);
        return result;
    }

    @Override
    public int updateRecordExamPlaceInvigilatorPhoto(Long id, String photo) {
        RecordExamPlaceInvigilator recordExamPlaceInvigilator = RecordExamPlaceInvigilator.builder().id(id).photo(photo).build();
        int result = recordExamPlaceInvigilatorMapper.updateById(recordExamPlaceInvigilator);
        return result;
    }

    @Override
    public int updateRecordExamPlaceInvigilator(RecordExamPlaceInvigilator recordExamPlaceInvigilator) {
        int result = recordExamPlaceInvigilatorMapper.updateById(recordExamPlaceInvigilator);
        return result;
    }

    @Override
    public int countOfRecordExamPlaceInvigilator(Long recordExamId, Long examPlaceId) {
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("record_exam_id", recordExamId);
        int count = 0;
        List<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorList = recordExamPlaceInvigilatorMapper.selectList(recordExamPlaceInvigilatorQueryWrapper);
        for (RecordExamPlaceInvigilator recordExamPlaceInvigilator : recordExamPlaceInvigilatorList) {
            QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
            examPlaceInvigilatorQueryWrapper.eq("id", recordExamPlaceInvigilator.getExamPlaceInvigilatorId());
            ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
            count += examPlaceInvigilator.getExamPlaceId() == examPlaceId ? 1 : 0;
        }
        return count;
    }

    @Override
    public int countOfPassedRecordExamPlaceInvigilator(Long recordExamId, Long examPlaceId) {
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("record_exam_id", recordExamId);
        recordExamPlaceInvigilatorQueryWrapper.eq("passed", 1);
        int count = 0;
        List<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorList = recordExamPlaceInvigilatorMapper.selectList(recordExamPlaceInvigilatorQueryWrapper);
        for (RecordExamPlaceInvigilator recordExamPlaceInvigilator : recordExamPlaceInvigilatorList) {
            QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
            examPlaceInvigilatorQueryWrapper.eq("id", recordExamPlaceInvigilator.getExamPlaceInvigilatorId());
            ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
            count += examPlaceInvigilator.getExamPlaceId() == examPlaceId ? 1 : 0;
        }
        return count;
    }

    @Override
    public RecordExamPlaceInvigilatorDTO getRecordExamPlaceInvigilatorById(Long id) {
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("id", id);
        RecordExamPlaceInvigilator recordExamPlaceInvigilator = recordExamPlaceInvigilatorMapper.selectOne(recordExamPlaceInvigilatorQueryWrapper);
        return transferEntityIntoDTO(recordExamPlaceInvigilator);
    }

    @Override
    public RecordExamPlaceInvigilatorDTO getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(Long examPlaceInvigilatorId, Long recordExamId) {
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("exam_place_invigilator_id", examPlaceInvigilatorId);
        recordExamPlaceInvigilatorQueryWrapper.eq("record_exam_id", recordExamId);
        RecordExamPlaceInvigilator recordExamPlaceInvigilator = recordExamPlaceInvigilatorMapper.selectOne(recordExamPlaceInvigilatorQueryWrapper);
        return transferEntityIntoDTO(recordExamPlaceInvigilator);
    }

    @Override
    public List<RecordExamPlaceInvigilatorDTO> getRecordExamPlaceInvigilatorByRecordExamIdByPage(Long recordExamId, int current, int size, Long examPlaceId) {
        List<RecordExamPlaceInvigilatorDTO> recordExamPlaceInvigilatorDTOList = new LinkedList<>();
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("record_exam_id", recordExamId);
        recordExamPlaceInvigilatorMapper.selectList(recordExamPlaceInvigilatorQueryWrapper).stream().filter(
            invigilator -> {
                QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
                examPlaceInvigilatorQueryWrapper.eq("id", invigilator.getExamPlaceInvigilatorId());
                ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
                return examPlaceInvigilator.getExamPlaceId() == examPlaceId;
            }
        ).skip((current - 1) * size).limit(size).forEach(e -> { recordExamPlaceInvigilatorDTOList.add(transferEntityIntoDTO(e));});
        return recordExamPlaceInvigilatorDTOList;
    }

    @Override
    public List<RecordExamPlaceInvigilatorDTO> getPassedRecordExamPlaceInvigilatorByRecordExamIdByPage(Long recordExamId, int current, int size, Long examPlaceId) {
        List<RecordExamPlaceInvigilatorDTO> recordExamPlaceInvigilatorDTOList = new LinkedList<>();
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("record_exam_id", recordExamId);
        recordExamPlaceInvigilatorQueryWrapper.eq("passed", 1);
        recordExamPlaceInvigilatorMapper.selectList(recordExamPlaceInvigilatorQueryWrapper).stream().filter(
            invigilator -> {
                QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
                examPlaceInvigilatorQueryWrapper.eq("id", invigilator.getExamPlaceInvigilatorId());
                ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
                return examPlaceInvigilator.getExamPlaceId() == examPlaceId;
            }
        ).skip((current - 1) * size).limit(size).forEach(e -> {recordExamPlaceInvigilatorDTOList.add(transferEntityIntoDTO(e));});
        return recordExamPlaceInvigilatorDTOList;
    }

    /**
     * <p>po -> dto</p>
     * @param recordExamPlaceInvigilator
     * @return
     */
    private RecordExamPlaceInvigilatorDTO transferEntityIntoDTO(RecordExamPlaceInvigilator recordExamPlaceInvigilator) {
        if (ObjectUtils.isEmpty(recordExamPlaceInvigilator)) {
            return null;
        }
        Long examPlaceInvigilatorId = recordExamPlaceInvigilator.getExamPlaceInvigilatorId();
        QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        examPlaceInvigilatorQueryWrapper.eq("id", examPlaceInvigilatorId);
        ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
        return RecordExamPlaceInvigilatorConverter.INSTANCE.entityToDTO(examPlaceInvigilator, recordExamPlaceInvigilator);
    }

}
