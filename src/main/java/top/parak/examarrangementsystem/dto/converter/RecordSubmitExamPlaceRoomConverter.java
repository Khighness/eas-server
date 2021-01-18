package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceRoom;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordSubmitExamPlaceRoomConverter <p>
 * <p> Description: 考点提交考场记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Mapper
public interface RecordSubmitExamPlaceRoomConverter {

    RecordSubmitExamPlaceRoomConverter INSTANCE = Mappers.getMapper(RecordSubmitExamPlaceRoomConverter.class);

    @Mappings({
            @Mapping(source = "recordSubmitExamPlaceRoom.id", target = "id"),
            @Mapping(source = "recordSubmitExamPlaceRoom.examRoomSerialNumber", target = "examRoomSerialNumber"),
            @Mapping(source = "examPlaceRoom.name", target = "name"),
            @Mapping(source = "recordSubmitExamPlaceRoom.remark", target = "remark"),
            @Mapping(source = "recordSubmitExamPlaceRoom.reviewed", target = "reviewed"),
            @Mapping(source = "recordSubmitExamPlaceRoom.passed", target = "passed"),
            @Mapping(source = "recordSubmitExamPlaceRoom.reason", target = "reason"),
            @Mapping(source = "recordSubmitExamPlaceRoom.recordTaskExamPlaceId", target = "recordTaskExamPlaceId"),
            @Mapping(source = "recordSubmitExamPlaceRoom.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordSubmitExamPlaceRoom.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })

    RecordSubmitExamPlaceRoomDTO entityToDTO(RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom, ExamPlaceRoom examPlaceRoom);

}
