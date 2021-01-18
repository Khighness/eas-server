package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.hssf.record.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.*;
import top.parak.examarrangementsystem.entity.*;
import top.parak.examarrangementsystem.mapper.*;
import top.parak.examarrangementsystem.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-15
 */
@Service
public class RecordTaskExamInvigilatorServiceImpl  implements RecordTaskExamInvigilatorService {

    @Autowired
    private RecordExamMapper recordExamMapper;

    @Autowired
    private RecordExamSubjectMapper recordExamSubjectMapper;

    @Autowired
    private RecordTaskExamInvigilatorMapper recordTaskExamInvigilatorMapper;

    @Autowired
    private RecordExamPlaceInvigilatorMapper recordExamPlaceInvigilatorMapper;

    @Autowired
    private RecordSubmitExamPlaceInvigilatorMapper recordSubmitExamPlaceInvigilatorMapper;

    @Autowired
    private RecordExamSubjectInvigilatorGroupMapper recordExamSubjectInvigilatorGroupMapper;

    @Autowired
    private RecordSubmitExamPlaceInvigilatorService recordSubmitExamPlaceInvigilatorService;

    @Autowired
    private RecordSubmitExamPlaceRoomService recordSubmitExamPlaceRoomService;


    @Override
    public int saveRecordTaskExamInvigilator(RecordTaskExamInvigilator recordTaskExamInvigilator) {
        int result = recordTaskExamInvigilatorMapper.insert(recordTaskExamInvigilator);
        return result;
    }

    @Override
    public int updateRecordTaskExamInvigilator(Long id) {
        QueryWrapper<RecordTaskExamInvigilator> recordTaskExamInvigilatorQueryWrapper = new QueryWrapper<>();
        recordTaskExamInvigilatorQueryWrapper.eq("id", id);
        RecordTaskExamInvigilator recordTaskExamInvigilator = recordTaskExamInvigilatorMapper.selectOne(recordTaskExamInvigilatorQueryWrapper);
        recordTaskExamInvigilator.setReviewed(true);
        recordTaskExamInvigilator.setViolated(!recordTaskExamInvigilator.getViolated());
        int result = recordTaskExamInvigilatorMapper.updateById(recordTaskExamInvigilator);
        return result;
    }

    @Override
    public Map<String, Object> getRecordTaskExamInvigilatorByPage(Long id, int current, int size) {
        List<RecordGroupInvigilatorDTO> recordGroupInvigilatorDTOList = new LinkedList<>();
        QueryWrapper<RecordExamPlaceInvigilator> recordExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        recordExamPlaceInvigilatorQueryWrapper.eq("exam_place_invigilator_id", id);
        recordExamPlaceInvigilatorMapper.selectList(recordExamPlaceInvigilatorQueryWrapper).stream().forEach(
            recordExamPlaceInvigilator -> {
                Long recordExamPlaceInvigilatorId = recordExamPlaceInvigilator.getId();
                QueryWrapper<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
                recordSubmitExamPlaceInvigilatorQueryWrapper.eq("record_exam_place_invigilator_id", recordExamPlaceInvigilatorId);
                RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator = recordSubmitExamPlaceInvigilatorMapper.selectOne(recordSubmitExamPlaceInvigilatorQueryWrapper);
                if (!ObjectUtils.isEmpty(recordSubmitExamPlaceInvigilator)) {
                    RecordSubmitExamPlaceInvigilatorDTO recordSubmitExamPlaceInvigilatorDTO = recordSubmitExamPlaceInvigilatorService.getRecordSubmitExamPlaceInvigilatorById(recordSubmitExamPlaceInvigilator.getId());
                    Long recordSubmitExamPlaceInvigilatorId = recordSubmitExamPlaceInvigilator.getId();
                    QueryWrapper<RecordTaskExamInvigilator> recordTaskExamInvigilatorQueryWrapper = new QueryWrapper<>();
                    recordTaskExamInvigilatorQueryWrapper.eq("record_submit_exam_place_invigilator_id", recordSubmitExamPlaceInvigilatorId);
                    recordTaskExamInvigilatorMapper.selectList(recordTaskExamInvigilatorQueryWrapper).stream().forEach(
                        recordTaskExamInvigilator -> {
                            Long recordExamSubjectInvigilatorGroupId = recordTaskExamInvigilator.getRecordExamSubjectInvigilatorGroupId();
                            QueryWrapper<RecordExamSubjectInvigilatorGroup> recordExamSubjectInvigilatorGroupQueryWrapper = new QueryWrapper<>();
                            recordExamSubjectInvigilatorGroupQueryWrapper.eq("id", recordExamSubjectInvigilatorGroupId);
                            RecordExamSubjectInvigilatorGroup recordExamSubjectInvigilatorGroup = recordExamSubjectInvigilatorGroupMapper.selectOne(recordExamSubjectInvigilatorGroupQueryWrapper);
                            Long recordExamSubjectId = recordExamSubjectInvigilatorGroup.getRecordExamSubjectId();
                            Long recordSubmitExamPlaceRoomId = recordExamSubjectInvigilatorGroup.getRecordSubmitExamPlaceRoomId();
                            RecordSubmitExamPlaceRoomDTO recordSubmitExamPlaceRoomDTO = recordSubmitExamPlaceRoomService.getRecordSubmitExamPlaceRoomById(recordSubmitExamPlaceRoomId);
                            QueryWrapper<RecordExamSubject> recordExamSubjectQueryWrapper = new QueryWrapper<>();
                            recordExamSubjectQueryWrapper.eq("id", recordExamSubjectId);
                            RecordExamSubject recordExamSubject = recordExamSubjectMapper.selectOne(recordExamSubjectQueryWrapper);
                            Long recordExamId = recordExamSubject.getRecordExamId();
                            QueryWrapper<RecordExam> recordExamQueryWrapper = new QueryWrapper<>();
                            recordExamQueryWrapper.eq("id", recordExamId);
                            RecordExam recordExam = recordExamMapper.selectOne(recordExamQueryWrapper);
                            RecordGroupInvigilatorDTO recordGroupInvigilatorDTO = RecordGroupInvigilatorConverter.INSTANCE.entityDtoDTO(recordExam, recordExamSubjectInvigilatorGroup, recordTaskExamInvigilator, recordSubmitExamPlaceRoomDTO, recordExamSubject, recordSubmitExamPlaceInvigilatorDTO);
                            recordGroupInvigilatorDTOList.add(recordGroupInvigilatorDTO);
                        }
                    );
                }
            }
        );
        Map<String, Object> map = new HashMap<>();
        map.put("total", recordGroupInvigilatorDTOList.size());
        map.put("page", recordGroupInvigilatorDTOList.stream().skip(size * (current - 1)).limit(size));
        return map;
    }


}
