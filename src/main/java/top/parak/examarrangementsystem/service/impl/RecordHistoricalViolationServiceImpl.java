package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.RecordHistoricalViolationDTO;
import top.parak.examarrangementsystem.dto.converter.RecordHistoricalViolationConverter;
import top.parak.examarrangementsystem.entity.RecordHistoricalViolation;
import top.parak.examarrangementsystem.mapper.RecordHistoricalViolationMapper;
import top.parak.examarrangementsystem.service.RecordHistoricalViolationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-14
 */
@Service
public class RecordHistoricalViolationServiceImpl implements RecordHistoricalViolationService {

    @Autowired
    private RecordHistoricalViolationMapper recordHistoricalViolationMapper;

    @Override
    public int saveRecordHistoricalViolation(RecordHistoricalViolation recordHistoricalViolation) {
        int result = recordHistoricalViolationMapper.insert(recordHistoricalViolation);
        return result;
    }

    @Override
    public int countOfRecordHistoricalViolation() {
        return recordHistoricalViolationMapper.selectCount(null);
    }

    @Override
    public List<RecordHistoricalViolationDTO> getRecordHistoricalViolationByPage(int current, int size) {
        List<RecordHistoricalViolationDTO> recordHistoricalViolationDTOList = new LinkedList<>();
        Page<RecordHistoricalViolation> recordHistoricalViolationPage = new Page<>(current, size);
        recordHistoricalViolationPage = recordHistoricalViolationMapper.selectPage(recordHistoricalViolationPage, null);
        recordHistoricalViolationPage.getRecords().stream().forEach(e -> {recordHistoricalViolationDTOList.add(RecordHistoricalViolationConverter.INSTANCE.entityToDTO(e));});
        return recordHistoricalViolationDTOList;
    }

}
