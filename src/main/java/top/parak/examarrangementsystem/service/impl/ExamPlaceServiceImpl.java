package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.parak.examarrangementsystem.dto.ExamPlaceDTO;
import top.parak.examarrangementsystem.dto.converter.ExamPlaceConverter;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.mapper.ExamPlaceMapper;
import top.parak.examarrangementsystem.service.ExamPlaceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: ExamPlaceServiceImpl <p>
 * <p> Description: 考点业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class ExamPlaceServiceImpl implements ExamPlaceService {

    @Autowired
    private ExamPlaceMapper examPlaceMapper;

    @Override
    public int saveExamPlace(String name, String description, String position) {
        ExamPlace examPlace = ExamPlace.builder()
                .name(name)
                .description(description)
                .position(position)
                .build();
        int result = examPlaceMapper.insert(examPlace);
        return result;
    }

    @Override
    public int countOfExamPlace() {
        return examPlaceMapper.selectCount(null);
    }

    @Override
    public ExamPlaceDTO getExamPlaceById(Long id) {
        return ExamPlaceConverter.INSTANCE.entityToDTO(examPlaceMapper.selectById(id));
    }

    @Override
    public List<ExamPlaceDTO> getExamPlaceList() {
        return ExamPlaceConverter.INSTANCE.entityListToDTOList(examPlaceMapper.selectList(null));
    }

    @Override
    public List<ExamPlaceDTO> getExamPlaceByPage(int current, int size) {
        Page<ExamPlace> examPlacePage = new Page<>(current, size);
        examPlacePage = examPlaceMapper.selectPage(examPlacePage, null);
        return ExamPlaceConverter.INSTANCE.entityListToDTOList(examPlacePage.getRecords());
    }

    @Override
    public int updateExamPlaceById(ExamPlace examPlace) {
        int result = examPlaceMapper.updateById(examPlace);
        return result;
    }

    @Override
    public int deleteExamPlaceById(Long id) {
        int result = examPlaceMapper.deleteById(id);
        return result;
    }

}
