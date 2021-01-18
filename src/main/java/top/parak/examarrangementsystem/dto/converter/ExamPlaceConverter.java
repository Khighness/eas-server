package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.ExamPlaceDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.util.TimeUtils;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: ExamPlaceConverter <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Mapper
public interface ExamPlaceConverter {

    ExamPlaceConverter INSTANCE = Mappers.getMapper(ExamPlaceConverter.class);

    @Mappings({
            @Mapping(source = "examPlace.id", target = "id"),
            @Mapping(source = "examPlace.name", target = "name"),
            @Mapping(source = "examPlace.description", target = "description"),
            @Mapping(source = "examPlace.position", target = "position"),
            @Mapping(source = "examPlace.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "examPlace.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    ExamPlaceDTO entityToDTO(ExamPlace examPlace);

    List<ExamPlaceDTO> entityListToDTOList(List<ExamPlace> examPlaceList);

}
