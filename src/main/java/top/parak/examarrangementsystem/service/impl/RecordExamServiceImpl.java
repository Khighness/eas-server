package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.RecordExamDTO;
import top.parak.examarrangementsystem.dto.RecordExamSubjectDTO;
import top.parak.examarrangementsystem.dto.converter.RecordExamConverter;
import top.parak.examarrangementsystem.dto.converter.RecordExamSubjectConverter;
import top.parak.examarrangementsystem.entity.RecordExam;
import top.parak.examarrangementsystem.mapper.RecordExamMapper;
import top.parak.examarrangementsystem.service.RecordExamService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecruitApproverConverter <p>
 * <p> Description: 考试记录业务层实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/7
 */

@Service
public class RecordExamServiceImpl implements RecordExamService {

    @Autowired
    private RecordExamMapper recordExamMapper;

    @Override
    public int saveRecordExam(RecordExam recordExam) {
        int result = recordExamMapper.insert(recordExam);
        return result;
    }

    @Override
    public int countOfRecordExam() {
        return recordExamMapper.selectCount(null);
    }


    @Override
    public RecordExam getRecordExamBySerialNumber(String serialNumber) {
        QueryWrapper<RecordExam> recordExamQueryWrapper = new QueryWrapper<>();
        recordExamQueryWrapper.eq("serial_number", serialNumber);
        return recordExamMapper.selectOne(recordExamQueryWrapper);
    }

    @Override
    public RecordExamDTO getRecordExamById(Long id) {
        RecordExam recordExam = recordExamMapper.getRecordExamById(id);
        return transferEntityIntoDTO(recordExam);
    }

    @Override
    public List<RecordExamDTO> getRecordExamList() {
        List<RecordExamDTO> recordExamDTOList = new LinkedList<>();
        recordExamMapper.getRecordExamList().stream().forEach( e -> { recordExamDTOList.add(transferEntityIntoDTO(e)); });
        return recordExamDTOList;
    }

    @Override
    public List<RecordExamDTO> getRecordExamByPage(int current, int size) {
        List<RecordExamDTO> recordExamDTOList = new LinkedList<>();
        recordExamMapper.getRecordExamList().stream().skip((current - 1) * size).limit(size).forEach( e -> { recordExamDTOList.add(transferEntityIntoDTO(e)); });
        return recordExamDTOList;
    }

    @Override
    public int updateRecordExamById(RecordExam recordExam) {
        int result = recordExamMapper.updateById(recordExam);
        return result;
    }

    @Override
    public int deleteRecordExamById(Long id) {
        int result = recordExamMapper.deleteById(id);
        return result;
    }

    /**
     * <p>po -> dto</p>
     * @return
     */
    private RecordExamDTO transferEntityIntoDTO(RecordExam recordExam) {
        RecordExamDTO recordExamDTO = RecordExamConverter.INSTANCE.entityToDTO(recordExam);
        List<RecordExamSubjectDTO> recordExamSubjectDTOList = RecordExamSubjectConverter.INSTANCE.entityListToDTOList(recordExam.getRecordExamSubjectList());
        recordExamDTO.setRecordExamSubjectDTOList(recordExamSubjectDTOList);
        return recordExamDTO;
    }

}
