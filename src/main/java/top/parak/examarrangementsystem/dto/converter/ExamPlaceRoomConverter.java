package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.ExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: ExamPlaceRoomConverter <p>
 * <p> Description: 考场DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/6
 */

@Mapper
public interface ExamPlaceRoomConverter {

    ExamPlaceRoomConverter INSTANCE = Mappers.getMapper(ExamPlaceRoomConverter.class);

    @Mappings({
            @Mapping(source = "examPlaceRoom.id", target = "id"),
            @Mapping(source = "examPlaceRoom.name", target = "name"),
            @Mapping(source = "examPlaceRoom.examRoomSerialNumber", target = "examRoomSerialNumber"),
            @Mapping(source = "examPlace.id", target = "examPlaceId"),
            @Mapping(source = "examPlace.name", target = "examPlaceName"),
            @Mapping(source = "examPlaceRoom.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "examPlaceRoom.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })

    ExamPlaceRoomDTO entityToDTO(ExamPlaceRoom examPlaceRoom, ExamPlace examPlace);

}
