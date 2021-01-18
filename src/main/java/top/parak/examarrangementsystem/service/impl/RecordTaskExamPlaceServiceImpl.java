package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.RecordTaskExamPlaceDTO;
import top.parak.examarrangementsystem.dto.converter.RecordTaskExamPlaceConverter;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;
import top.parak.examarrangementsystem.mapper.RecordTaskExamPlaceMapper;
import top.parak.examarrangementsystem.service.RecordTaskExamPlaceService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecordExamSubjectServiceImpl <p>
 * <p> Description: 考点任务记录业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Service
public class RecordTaskExamPlaceServiceImpl implements RecordTaskExamPlaceService {

    @Autowired
    private RecordTaskExamPlaceMapper recordTaskExamPlaceMapper;

    @Override
    public int saveRecordTaskExamPlace(RecordTaskExamPlace recordTaskExamPlace) {
        recordTaskExamPlace.setSerialNumber(String.valueOf(SnowFlakeIDUtils.nextID()));
        int result = recordTaskExamPlaceMapper.insert(recordTaskExamPlace);
        return result;
    }

    @Override
    public int countOfRecordTaskExamPlace() {
        return recordTaskExamPlaceMapper.selectCount(null);
    }

    @Override
    public int countOfRecordTaskExamPlaceWithRecordExamId(Long recordExamId) {
        QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
        recordTaskExamPlaceQueryWrapper.eq("record_exam_id", recordExamId);
        return recordTaskExamPlaceMapper.selectCount(recordTaskExamPlaceQueryWrapper);
    }

    @Override
    public RecordTaskExamPlaceDTO getRecordTaskExamPlaceById(Long id) {
        QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
        recordTaskExamPlaceQueryWrapper.eq("id", id);
        RecordTaskExamPlace recordTaskExamPlace = recordTaskExamPlaceMapper.selectOne(recordTaskExamPlaceQueryWrapper);
        return RecordTaskExamPlaceConverter.INSTANCE.entityToDTO(recordTaskExamPlace);
    }

    @Override
    public RecordTaskExamPlaceDTO getRecordTaskExamPlaceByExamPlaceIdAndRecordExamId(Long examPlaceId, Long recordExamId) {
        QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
        recordTaskExamPlaceQueryWrapper.eq("exam_place_id", examPlaceId);
        recordTaskExamPlaceQueryWrapper.eq("record_exam_id", recordExamId);
        RecordTaskExamPlace recordTaskExamPlace = recordTaskExamPlaceMapper.selectOne(recordTaskExamPlaceQueryWrapper);
        return RecordTaskExamPlaceConverter.INSTANCE.entityToDTO(recordTaskExamPlace);
    }

    @Override
    public List<RecordTaskExamPlaceDTO> getRecordTaskExamPlaceByRecordExamId(Long recordExamId) {
        List<RecordTaskExamPlaceDTO> recordTaskExamPlaceDTOList = new LinkedList<>();
        QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
        recordTaskExamPlaceQueryWrapper.eq("record_exam_id", recordExamId);
        recordTaskExamPlaceMapper.selectList(recordTaskExamPlaceQueryWrapper).stream().forEach(
            task -> {
                recordTaskExamPlaceDTOList.add(RecordTaskExamPlaceConverter.INSTANCE.entityToDTO(task));
            }
        );
        return recordTaskExamPlaceDTOList;
    }

    @Override
    public List<RecordTaskExamPlaceDTO> getRecordTaskExamPlaceByPage(int current, int size) {
        List<RecordTaskExamPlaceDTO> recordTaskExamPlaceDTOList = new LinkedList<>();
        recordTaskExamPlaceMapper.selectList(null).stream().forEach(
            task -> {
                recordTaskExamPlaceDTOList.add(RecordTaskExamPlaceConverter.INSTANCE.entityToDTO(task));
            }
        );
        return recordTaskExamPlaceDTOList;
    }

    @Override
    public List<RecordTaskExamPlaceDTO> getRecordTaskExamPlaceByRecordExamIdByPage(Long recordExamId, int current, int size) {
        List<RecordTaskExamPlaceDTO> recordTaskExamPlaceDTOList = new LinkedList<>();
        Page<RecordTaskExamPlace> recordTaskExamPlacePage = new Page<>(current, size);
        QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
        recordTaskExamPlaceQueryWrapper.eq("record_exam_id", recordExamId);
        recordTaskExamPlacePage = recordTaskExamPlaceMapper.selectPage(recordTaskExamPlacePage, recordTaskExamPlaceQueryWrapper);
        recordTaskExamPlacePage.getRecords().stream().forEach(
            task -> {
                recordTaskExamPlaceDTOList.add(RecordTaskExamPlaceConverter.INSTANCE.entityToDTO(task));
            }
        );
        return recordTaskExamPlaceDTOList;
    }

    @Override
    public int updateRecordTaskExamPlaceById(RecordTaskExamPlace recordTaskExamPlace) {
        int result = recordTaskExamPlaceMapper.updateById(recordTaskExamPlace);
        return result;
    }

    @Override
    public int deleteRecordTaskExamPlaceById(Long id) {
        int result = recordTaskExamPlaceMapper.deleteById(id);
        return result;
    }

    @Override
    public int updateRecordTaskExamPlaceById(Long id) {
        RecordTaskExamPlace recordTaskExamPlace = RecordTaskExamPlace.builder().id(id).readed(true).build();
        int result = recordTaskExamPlaceMapper.updateById(recordTaskExamPlace);
        return result;
    }
}
