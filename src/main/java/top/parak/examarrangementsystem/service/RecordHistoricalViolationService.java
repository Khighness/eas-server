package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordHistoricalViolationDTO;
import top.parak.examarrangementsystem.entity.RecordHistoricalViolation;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-14
 */
public interface RecordHistoricalViolationService {

    int saveRecordHistoricalViolation(RecordHistoricalViolation recordHistoricalViolation);

    int countOfRecordHistoricalViolation();

    List<RecordHistoricalViolationDTO> getRecordHistoricalViolationByPage(int current, int size);

}
