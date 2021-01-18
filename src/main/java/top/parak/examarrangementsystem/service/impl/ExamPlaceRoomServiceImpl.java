package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.ExamPlaceRoomDTO;
import top.parak.examarrangementsystem.dto.converter.ExamPlaceRoomConverter;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;
import top.parak.examarrangementsystem.mapper.ExamPlaceMapper;
import top.parak.examarrangementsystem.mapper.ExamPlaceRoomMapper;
import top.parak.examarrangementsystem.service.ExamPlaceRoomService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceRoomServiceImpl <p>
 * <p> Description: 考场业务层实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class ExamPlaceRoomServiceImpl implements ExamPlaceRoomService {

    @Autowired
    private ExamPlaceRoomMapper examPlaceRoomMapper;

    @Autowired
    private ExamPlaceMapper examPlaceMapper;

    @Override
    public int saveExamPlaceRoom(String name, int examRoomSerialNumber, Long examPlaceId) {
        ExamPlaceRoom examPlaceRoom = ExamPlaceRoom.builder().name(name).examRoomSerialNumber(examRoomSerialNumber).examPlaceId(examPlaceId).build();
        int result = examPlaceRoomMapper.insert(examPlaceRoom);
        return result;
    }

    @Override
    public int countOfExamPlaceRoom(Long examPlaceId) {
        QueryWrapper<ExamPlaceRoom> examPlaceRoomQueryWrapper = new QueryWrapper<>();
        examPlaceRoomQueryWrapper.eq("exam_place_id", examPlaceId);
        return examPlaceRoomMapper.selectCount(examPlaceRoomQueryWrapper);
    }

    @Override
    public ExamPlaceRoomDTO getExamPlaceRoomById(Long id) {
        QueryWrapper<ExamPlaceRoom> examPlaceRoomQueryWrapper = new QueryWrapper<>();
        examPlaceRoomQueryWrapper.eq("id", id);
        ExamPlaceRoom examPlaceRoom = examPlaceRoomMapper.selectOne(examPlaceRoomQueryWrapper);
        return transformEntityIntoDTO(examPlaceRoom);
    }

    @Override
    public List<ExamPlaceRoomDTO> getExamPlaceRoomByExamPlaceId(Long examPlaceId) {
        List<ExamPlaceRoomDTO> examPlaceRoomDTOList = new LinkedList<>();
        QueryWrapper<ExamPlaceRoom> examPlaceRoomQueryWrapper = new QueryWrapper<>();
        examPlaceRoomQueryWrapper.eq("exam_place_id", examPlaceId);
        examPlaceRoomMapper.selectList(examPlaceRoomQueryWrapper).stream().forEach( e -> { examPlaceRoomDTOList.add(transformEntityIntoDTO(e)); });
        return examPlaceRoomDTOList;
    }

    @Override
    public List<ExamPlaceRoomDTO> getExamPlaceRoomByExamPlaceIdByPage(Long examPlaceId, int current, int size) {
        List<ExamPlaceRoomDTO> examPlaceRoomDTOList = new LinkedList<>();
        Page<ExamPlaceRoom> examPlaceRoomPage = new Page<>(current, size);
        QueryWrapper<ExamPlaceRoom> examPlaceRoomQueryWrapper = new QueryWrapper<>();
        examPlaceRoomQueryWrapper.eq("exam_place_id", examPlaceId);
        examPlaceRoomPage = examPlaceRoomMapper.selectPage(examPlaceRoomPage, examPlaceRoomQueryWrapper);
        examPlaceRoomPage.getRecords().stream().forEach( e -> { examPlaceRoomDTOList.add(transformEntityIntoDTO(e)); });
        return examPlaceRoomDTOList;
    }

    @Override
    public int updateExamPlaceRoomById(ExamPlaceRoom examPlaceRoom) {
        int result = examPlaceRoomMapper.updateById(examPlaceRoom);
        return result;
    }

    @Override
    public int deleteExamPlaceRoomById(Long id) {
        int result = examPlaceRoomMapper.deleteById(id);
        return result;
    }

    @Override
    public int deleteBatchExamPlaceRoomById(List<Long> idList) {
        int result = examPlaceRoomMapper.deleteBatchIds(idList);
//        examPlaceRoomMapper.deleteBatchIds()
        return result;
    }

    /**
     * <p>po -> dto</p>
     * @param examPlaceRoom
     * @return examPlaceRoomDTO
     */
    private ExamPlaceRoomDTO transformEntityIntoDTO(ExamPlaceRoom examPlaceRoom) {
        if (ObjectUtils.isEmpty(examPlaceRoom) || ObjectUtils.isEmpty(examPlaceRoom.getExamPlaceId())) {
            return null;
        }
        QueryWrapper<ExamPlace> examPlaceQueryWrapper = new QueryWrapper<>();
        examPlaceQueryWrapper.eq("id", examPlaceRoom.getExamPlaceId());
        ExamPlace examPlace = examPlaceMapper.selectOne(examPlaceQueryWrapper);
        return ExamPlaceRoomConverter.INSTANCE.entityToDTO(examPlaceRoom, examPlace);
    }
}
