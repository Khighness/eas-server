package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.converter.RecordSubmitExamPlaceInvigilatorConverter;
import top.parak.examarrangementsystem.entity.*;
import top.parak.examarrangementsystem.mapper.*;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceInvigilatorService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-13
 */

@Service
public class RecordSubmitExamPlaceInvigilatorServiceImpl implements RecordSubmitExamPlaceInvigilatorService {

    @Autowired
    private RecordSubmitExamPlaceInvigilatorMapper recordSubmitExamPlaceInvigilatorMapper;

    @Autowired
    private RecordTaskExamPlaceMapper recordTaskExamPlaceMapper;

    @Autowired
    private RecordExamPlaceInvigilatorMapper recordExamPlaceInvigilatorMapper;

    @Autowired
    private ExamPlaceInvigilatorMapper examPlaceInvigilatorMapper;

    @Autowired
    private RecordExamMapper recordExamMapper;

    @Override
    public int saveRecordSubmitExamPlaceInvigilator(Long recordExamPlaceInvigilatorId, Long recordTaskExamPlaceId) {
        String serialNumber = String.valueOf(SnowFlakeIDUtils.nextID());
        RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator = RecordSubmitExamPlaceInvigilator.builder()
                .serialNumber(serialNumber)
                .invigilatorSerialNumber(Integer.parseInt("2020" + serialNumber.substring(13)))
                .recordExamPlaceInvigilatorId(recordExamPlaceInvigilatorId)
                .recordTaskExamPlaceId(recordTaskExamPlaceId)
                .build();
        int result = recordSubmitExamPlaceInvigilatorMapper.insert(recordSubmitExamPlaceInvigilator);
        return result;
    }

    @Override
    public int countOfRecordSubmitExamPlaceInvigilator(Long taskId) {
        QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_task_exam_place_id", taskId);
        return recordSubmitExamPlaceInvigilatorMapper.selectCount(recordSubmitExamPlaceInvigilatorQueryWrapper);
    }

    @Override
    public RecordSubmitExamPlaceInvigilatorDTO getRecordSubmitExamPlaceInvigilatorById(Long id) {
        QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("id", id);
        RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator = recordSubmitExamPlaceInvigilatorMapper.selectOne(recordSubmitExamPlaceInvigilatorQueryWrapper);
        return transferEntityIntoDTO(recordSubmitExamPlaceInvigilator);
    }

    @Override
    public List<RecordSubmitExamPlaceInvigilatorDTO> getRecordSubmitExamPlaceInvigilatorList(Long taskId) {
        List<RecordSubmitExamPlaceInvigilatorDTO> recordSubmitExamPlaceInvigilatorDTOList = new LinkedList<>();
        QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_task_exam_place_id", taskId);
        recordSubmitExamPlaceInvigilatorMapper.selectList(recordSubmitExamPlaceInvigilatorQueryWrapper).forEach(e -> {recordSubmitExamPlaceInvigilatorDTOList.add(transferEntityIntoDTO(e));});
        return recordSubmitExamPlaceInvigilatorDTOList;
    }

    @Override
    public List<RecordSubmitExamPlaceInvigilatorDTO> getRecordSubmitExamPlaceInvigilatorByPage(int current, int size, Long taskId) {
        List<RecordSubmitExamPlaceInvigilatorDTO> recordSubmitExamPlaceInvigilatorDTOList = new LinkedList<>();
        Page<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorPage = new Page<>(current, size);
        QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_task_exam_place_id", taskId);
        recordSubmitExamPlaceInvigilatorPage = recordSubmitExamPlaceInvigilatorMapper.selectPage(recordSubmitExamPlaceInvigilatorPage, recordSubmitExamPlaceInvigilatorQueryWrapper);
        recordSubmitExamPlaceInvigilatorPage.getRecords().forEach(e -> {recordSubmitExamPlaceInvigilatorDTOList.add(transferEntityIntoDTO(e));});
        return recordSubmitExamPlaceInvigilatorDTOList;
    }

    @Override
    public int deleteRecordExamPlaceInvigilatorById(Long id) {
        int result = recordSubmitExamPlaceInvigilatorMapper.deleteById(id);
        return result;
    }

    @Override
    public int deleteBatchRecordExamPlaceInvigilatorById(List<Long> idList) {
        int result = recordSubmitExamPlaceInvigilatorMapper.deleteBatchIds(idList);
        return result;
    }

    @Override
    public int reviewRecordExamPlaceInvigilatorById(RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator) {
        /* 审核通过则在考点任务相关数量 - 1*/
        if (recordSubmitExamPlaceInvigilator.getPassed()) {
            Long id = recordSubmitExamPlaceInvigilator.getId();
            RecordSubmitExamPlaceInvigilatorDTO recordSubmitExamPlaceInvigilatorById = getRecordSubmitExamPlaceInvigilatorById(id);
            Boolean main = recordSubmitExamPlaceInvigilatorById.getMain();
            String gender = recordSubmitExamPlaceInvigilatorById.getGender();
            QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
            recordTaskExamPlaceQueryWrapper.eq("id", recordSubmitExamPlaceInvigilatorById.getRecordTaskExamPlaceId());
            RecordTaskExamPlace recordTaskExamPlace = recordTaskExamPlaceMapper.selectOne(recordTaskExamPlaceQueryWrapper);
            recordTaskExamPlace.setInvigilatorNumber(recordTaskExamPlace.getInvigilatorNumber() - 1);
            if (main && recordTaskExamPlace.getMainInvigilatorNumber() - 1 >= 0) {
                recordTaskExamPlace.setMainInvigilatorNumber(recordTaskExamPlace.getMainInvigilatorNumber() - 1);
            }
            if (gender.equals("男") && recordTaskExamPlace.getMaleInvigilatorNumber() - 1 >= 0) {
                recordTaskExamPlace.setMaleInvigilatorNumber(recordTaskExamPlace.getMaleInvigilatorNumber() - 1);
            }
            if (gender.equals("女") && recordTaskExamPlace.getFemaleInvigilatorNumber() - 1 >= 0) {
                recordTaskExamPlace.setFemaleInvigilatorNumber(recordTaskExamPlace.getFemaleInvigilatorNumber() - 1);
            }
            recordTaskExamPlaceMapper.updateById(recordTaskExamPlace);
        }
        recordSubmitExamPlaceInvigilator.setReviewed(true);
        int result = recordSubmitExamPlaceInvigilatorMapper.updateById(recordSubmitExamPlaceInvigilator);
        return result;
    }

    @Override
    public List<Map<String, Object>> getRecordExamPlaceInvigilatorResult(Long examPlaceInvigilatorId) {
        List<Map<String, Object>> result = new LinkedList<>();
        QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        examPlaceInvigilatorQueryWrapper.eq("id", examPlaceInvigilatorId);
        ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("exam_place_invigilator_id", examPlaceInvigilatorId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        recordExamPlaceInvigilatorMapper.selectList(recordExamPlaceInvigilatorQueryWrapper).stream().forEach(
            recordExamPlaceInvigilator -> {
                Long id = recordExamPlaceInvigilator.getId();
                Long recordExamId = recordExamPlaceInvigilator.getRecordExamId();
                QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
                recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_exam_place_invigilator_id", id);
                RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator = recordSubmitExamPlaceInvigilatorMapper.selectOne(recordSubmitExamPlaceInvigilatorQueryWrapper);
                if (!ObjectUtils.isEmpty(recordSubmitExamPlaceInvigilator) && recordSubmitExamPlaceInvigilator.getPassed()) {
                    Map<String, Object> map = new HashMap<>();
                    QueryWrapper<RecordExam> recordExamQueryWrapper = new QueryWrapper<>();
                    recordExamQueryWrapper.eq("id", recordExamId);
                    RecordExam recordExam = recordExamMapper.selectOne(recordExamQueryWrapper);
                    map.put("examName", recordExam.getName());
                    map.put("examStartTime", simpleDateFormat.format(recordExam.getStartTime()));
                    map.put("examEndTime", simpleDateFormat.format(recordExam.getEndTime()));
                    map.put("name", examPlaceInvigilator.getName());
                    map.put("photo", recordExamPlaceInvigilator.getPhoto());
                    map.put("invigilatorSerialNumber", recordSubmitExamPlaceInvigilator.getInvigilatorSerialNumber());
                    map.put("main", recordSubmitExamPlaceInvigilator.getMain());
                    result.add(map);
                }
            }
        );
        return result;
    }

    @Override
    public List<RecordSubmitExamPlaceInvigilatorDTO> getPassedRecordSubmitExamPlaceInvigilatorList(Long taskId) {
        List<RecordSubmitExamPlaceInvigilatorDTO> recordSubmitExamPlaceInvigilatorDTOList = new LinkedList<>();
        QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_task_exam_place_id", taskId);
        recordSubmitExamPlaceInvigilatorQueryWrapper.eq("passed", 1);
        recordSubmitExamPlaceInvigilatorMapper.selectList(recordSubmitExamPlaceInvigilatorQueryWrapper).forEach(e -> {recordSubmitExamPlaceInvigilatorDTOList.add(transferEntityIntoDTO(e));});
        return recordSubmitExamPlaceInvigilatorDTOList;
    }

    /**
     * <p>po -> dto</p>
     * @param recordSubmitExamPlaceInvigilator
     * @return
     */
    public RecordSubmitExamPlaceInvigilatorDTO transferEntityIntoDTO(RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator) {
        Long recordExamPalceInvigilatorId = recordSubmitExamPlaceInvigilator.getRecordExamPlaceInvigilatorId();
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("id", recordExamPalceInvigilatorId);
        RecordExamPlaceInvigilator recordExamPlaceInvigilator = recordExamPlaceInvigilatorMapper.selectOne(recordExamPlaceInvigilatorQueryWrapper);
        Long examPlaceInvigilatorId = recordExamPlaceInvigilator.getExamPlaceInvigilatorId();
        QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        examPlaceInvigilatorQueryWrapper.eq("id", examPlaceInvigilatorId);
        ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
        return RecordSubmitExamPlaceInvigilatorConverter.INSTANCE.entityToDTO(recordSubmitExamPlaceInvigilator, recordExamPlaceInvigilator, examPlaceInvigilator);
    }

}
