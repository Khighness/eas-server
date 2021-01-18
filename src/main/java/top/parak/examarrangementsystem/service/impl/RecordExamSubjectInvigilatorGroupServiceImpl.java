package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.RecordGroupInvigilatorConverter;
import top.parak.examarrangementsystem.dto.RecordGroupInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.*;
import top.parak.examarrangementsystem.mapper.*;
import top.parak.examarrangementsystem.service.RecordExamSubjectInvigilatorGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.service.RecordExamSubjectService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceRoomService;

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
public class RecordExamSubjectInvigilatorGroupServiceImpl implements RecordExamSubjectInvigilatorGroupService {

    @Autowired
    private RecordSubmitExamPlaceInvigilatorService recordSubmitExamPlaceInvigilatorService;

    @Autowired
    private RecordSubmitExamPlaceRoomService recordSubmitExamPlaceRoomService;

    @Autowired
    private RecordExamMapper recordExamMapper;

    @Autowired
    private RecordExamSubjectMapper recordExamSubjectMapper;

    @Autowired
    private RecordExamSubjectInvigilatorGroupMapper recordExamSubjectInvigilatorGroupMapper;

    @Autowired
    private RecordTaskExamInvigilatorMapper recordTaskExamInvigilatorMapper;


    @Override
    public int saveRecordExamSubjectInvigilatorGroup(RecordExamSubjectInvigilatorGroup recordExamSubjectInvigilatorGroup) {
        int result = recordExamSubjectInvigilatorGroupMapper.insert(recordExamSubjectInvigilatorGroup);
        return result;
    }

    @Override
    public Map<String, Object> getRecordExamSubjectInvigilatorGroup(Long subjectId, int current, int size) {
        List<RecordGroupInvigilatorDTO> recordGroupInvigilatorDTOList = new LinkedList<>();
        QueryWrapper<RecordExamSubject> recordExamSubjectQueryWrapper = new QueryWrapper<>();
        recordExamSubjectQueryWrapper.eq("id", subjectId);
        RecordExamSubject recordExamSubject = recordExamSubjectMapper.selectOne(recordExamSubjectQueryWrapper);
        Long recordExamId = recordExamSubject.getRecordExamId();
        QueryWrapper<RecordExam> recordExamQueryWrapper = new QueryWrapper<>();
        recordExamQueryWrapper.eq("id", recordExamId);
        RecordExam recordExam = recordExamMapper.selectOne(recordExamQueryWrapper);
        QueryWrapper<RecordExamSubjectInvigilatorGroup> recordExamSubjectInvigilatorGroupQueryWrapper = new QueryWrapper<>();
        recordExamSubjectInvigilatorGroupQueryWrapper.eq("record_exam_subject_id", subjectId);
        List<RecordExamSubjectInvigilatorGroup> recordExamSubjectInvigilatorGroupList = recordExamSubjectInvigilatorGroupMapper.selectList(recordExamSubjectInvigilatorGroupQueryWrapper);
        recordExamSubjectInvigilatorGroupList.stream().forEach(
            group -> {
                Long groupId = group.getId();
                Long recordSubmitExamPlaceRoomId = group.getRecordSubmitExamPlaceRoomId();
                RecordSubmitExamPlaceRoomDTO recordSubmitExamPlaceRoomDTO = recordSubmitExamPlaceRoomService.getRecordSubmitExamPlaceRoomById(recordSubmitExamPlaceRoomId);
                QueryWrapper<RecordTaskExamInvigilator> recordTaskExamInvigilatorQueryWrapper = new QueryWrapper<>();
                recordTaskExamInvigilatorQueryWrapper.eq("record_exam_subject_invigilator_group_id", groupId);
                recordTaskExamInvigilatorMapper.selectList(recordTaskExamInvigilatorQueryWrapper).stream().forEach(
                    task -> {
                        Long recordSubmitExamPlaceInvigilatorId = task.getRecordSubmitExamPlaceInvigilatorId();
                        RecordSubmitExamPlaceInvigilatorDTO recordSubmitExamPlaceInvigilatorDTO = recordSubmitExamPlaceInvigilatorService.getRecordSubmitExamPlaceInvigilatorById(recordSubmitExamPlaceInvigilatorId);
                        recordGroupInvigilatorDTOList.add(RecordGroupInvigilatorConverter.INSTANCE.entityDtoDTO(recordExam, group, task, recordSubmitExamPlaceRoomDTO, recordExamSubject, recordSubmitExamPlaceInvigilatorDTO));
                    }
                );
            }
        );
        Map<String, Object> map = new HashMap<>();
        map.put("total", recordGroupInvigilatorDTOList.size());
        map.put("page", recordGroupInvigilatorDTOList.stream().skip(size * (current)).limit(size));
        return map;
    }

}
