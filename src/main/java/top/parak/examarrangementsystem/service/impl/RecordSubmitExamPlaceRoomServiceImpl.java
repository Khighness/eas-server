package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceRoomDTO;
import top.parak.examarrangementsystem.dto.converter.RecordSubmitExamPlaceRoomConverter;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceRoom;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;
import top.parak.examarrangementsystem.mapper.ExamPlaceRoomMapper;
import top.parak.examarrangementsystem.mapper.RecordSubmitExamPlaceRoomMapper;
import top.parak.examarrangementsystem.mapper.RecordTaskExamPlaceMapper;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceRoomService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KHighness
 * @since 2021-01-13
 */

@Service
public class RecordSubmitExamPlaceRoomServiceImpl implements RecordSubmitExamPlaceRoomService {

    @Autowired
    private RecordSubmitExamPlaceRoomMapper recordSubmitExamPlaceRoomMapper;

    @Autowired
    private ExamPlaceRoomMapper examPlaceRoomMapper;

    @Autowired
    private RecordTaskExamPlaceMapper recordTaskExamPlaceMapper;

    @Override
    public int saveRecordSubmitExamPlaceRoom(RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom) {
        recordSubmitExamPlaceRoom.setSerialNumber(String.valueOf(SnowFlakeIDUtils.nextID()));
        int result = recordSubmitExamPlaceRoomMapper.insert(recordSubmitExamPlaceRoom);
        return result;
    }

    @Override
    public int countOfRecordSubmitExamPlaceRoom(Long taskId) {
        QueryWrapper<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceRoomQueryWrapper.eq("record_task_exam_place_id", taskId);
        return recordSubmitExamPlaceRoomMapper.selectCount(recordSubmitExamPlaceRoomQueryWrapper);
    }

    @Override
    public RecordSubmitExamPlaceRoomDTO getRecordSubmitExamPlaceRoomById(Long id) {
        QueryWrapper<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceRoomQueryWrapper.eq("id", id);
        return transferEntityIntoDTO(recordSubmitExamPlaceRoomMapper.selectOne(recordSubmitExamPlaceRoomQueryWrapper));
    }

    @Override
    public List<RecordSubmitExamPlaceRoomDTO> getRecordSubmitExamPlaceRoomList(Long taskId) {
        List<RecordSubmitExamPlaceRoomDTO> recordSubmitExamPlaceRoomDTOList = new LinkedList<>();
        QueryWrapper<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceRoomQueryWrapper.eq("record_task_exam_place_id", taskId);
        recordSubmitExamPlaceRoomMapper.selectList(recordSubmitExamPlaceRoomQueryWrapper).forEach( e -> { recordSubmitExamPlaceRoomDTOList.add(transferEntityIntoDTO(e)); });
        return recordSubmitExamPlaceRoomDTOList;
    }

    @Override
    public List<RecordSubmitExamPlaceRoomDTO> getRecordSubmitExamPlaceRoomByPage(int current, int size, Long taskId) {
        List<RecordSubmitExamPlaceRoomDTO> recordSubmitExamPlaceRoomDTOList = new LinkedList<>();
        Page<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomPage = new Page<>(current, size);
        QueryWrapper<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceRoomQueryWrapper.eq("record_task_exam_place_id", taskId);
        recordSubmitExamPlaceRoomPage = recordSubmitExamPlaceRoomMapper.selectPage(recordSubmitExamPlaceRoomPage, recordSubmitExamPlaceRoomQueryWrapper);
        recordSubmitExamPlaceRoomPage.getRecords().forEach( e -> { recordSubmitExamPlaceRoomDTOList.add(transferEntityIntoDTO(e)); });
        return recordSubmitExamPlaceRoomDTOList;
    }

    @Override
    public int deleteRecordSubmitExamPlaceRoomById(Long id) {
        int result = recordSubmitExamPlaceRoomMapper.deleteById(id);
        return result;
    }

    @Override
    public int deleteBatchRecordSubmitExamPlaceRoomById(List<Long> idList) {
        int result = recordSubmitExamPlaceRoomMapper.deleteBatchIds(idList);
        return result;
    }

    @Override
    public int reviewRecordSubmitExamPlaceRoomById(RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom) {
        /* 审核通过则在考点任务中的考场数量-1 */
        if (recordSubmitExamPlaceRoom.getPassed()) {
            Long id = recordSubmitExamPlaceRoom.getId();
            RecordSubmitExamPlaceRoomDTO recordSubmitExamPlaceRoomById = getRecordSubmitExamPlaceRoomById(id);
            QueryWrapper<RecordTaskExamPlace> recordTaskExamPlaceQueryWrapper = new QueryWrapper<>();
            recordTaskExamPlaceQueryWrapper.eq("id", recordSubmitExamPlaceRoomById.getRecordTaskExamPlaceId());
            RecordTaskExamPlace recordTaskExamPlace = recordTaskExamPlaceMapper.selectOne(recordTaskExamPlaceQueryWrapper);
            if (recordTaskExamPlace.getExamRoomNumber() - 1 >= 0) {
                recordTaskExamPlace.setExamRoomNumber(recordTaskExamPlace.getExamRoomNumber() - 1);
            }
            recordTaskExamPlaceMapper.updateById(recordTaskExamPlace);
        }
        recordSubmitExamPlaceRoom.setReviewed(true);
        int result = recordSubmitExamPlaceRoomMapper.updateById(recordSubmitExamPlaceRoom);
        return result;
    }

    @Override
    public List<RecordSubmitExamPlaceRoomDTO> getPassedRecordSubmitExamPlaceRoomList(Long taskId) {
        List<RecordSubmitExamPlaceRoomDTO> recordSubmitExamPlaceRoomDTOList = new LinkedList<>();
        QueryWrapper<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomQueryWrapper = new QueryWrapper<>();
        recordSubmitExamPlaceRoomQueryWrapper.eq("record_task_exam_place_id", taskId);
        recordSubmitExamPlaceRoomQueryWrapper.eq("passed", 1);
        recordSubmitExamPlaceRoomMapper.selectList(recordSubmitExamPlaceRoomQueryWrapper).forEach( e -> { recordSubmitExamPlaceRoomDTOList.add(transferEntityIntoDTO(e)); });
        return recordSubmitExamPlaceRoomDTOList;
    }

    /**
     * <p>po -> dto</p>
     * @param recordSubmitExamPlaceRoom
     * @return
     */
    private RecordSubmitExamPlaceRoomDTO transferEntityIntoDTO(RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom) {
        Long examPlaceRoomId = recordSubmitExamPlaceRoom.getExamPlaceRoomId();
        QueryWrapper<ExamPlaceRoom> examPlaceRoomQueryWrapper = new QueryWrapper<>();
        examPlaceRoomQueryWrapper.eq("id", examPlaceRoomId);
        ExamPlaceRoom examPlaceRoom = examPlaceRoomMapper.selectOne(examPlaceRoomQueryWrapper);
        return RecordSubmitExamPlaceRoomConverter.INSTANCE.entityToDTO(recordSubmitExamPlaceRoom, examPlaceRoom);
    }

}
