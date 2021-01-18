package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordTaskExamPlaceDTO;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;
import top.parak.examarrangementsystem.util.TimeUtils;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordTaskExamPlaceConverter <p>
 * <p> Description: 考点任务记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Mapper
public interface RecordTaskExamPlaceConverter {

    RecordTaskExamPlaceConverter INSTANCE = Mappers.getMapper(RecordTaskExamPlaceConverter.class);

    @Mappings({
            @Mapping(source = "recordTaskExamPlace.id", target = "id"),
            @Mapping(source = "recordTaskExamPlace.serialNumber", target = "serialNumber"),
            @Mapping(source = "recordTaskExamPlace.type", target = "type"),
            @Mapping(source = "recordTaskExamPlace.examRoomNumber", target = "examRoomNumber"),
            @Mapping(source = "recordTaskExamPlace.minExamRoomSerialNumber", target = "minExamRoomSerialNumber"),
            @Mapping(source = "recordTaskExamPlace.invigilatorNumber", target = "invigilatorNumber"),
            @Mapping(source = "recordTaskExamPlace.mainInvigilatorNumber", target = "mainInvigilatorNumber"),
            @Mapping(source = "recordTaskExamPlace.maleInvigilatorNumber", target = "maleInvigilatorNumber"),
            @Mapping(source = "recordTaskExamPlace.femaleInvigilatorNumber", target = "femaleInvigilatorNumber"),
            @Mapping(source = "recordTaskExamPlace.deadline", target = "deadline", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordTaskExamPlace.remark", target = "remark"),
            @Mapping(source = "recordTaskExamPlace.readed", target = "readed"),
            @Mapping(source = "recordTaskExamPlace.finished", target = "finished"),
            @Mapping(source = "recordTaskExamPlace.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordTaskExamPlace.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    RecordTaskExamPlaceDTO entityToDTO(RecordTaskExamPlace recordTaskExamPlace);

    List<RecordTaskExamPlaceDTO> entityListToDTOList(List<RecordTaskExamPlace> recordTaskExamPlaceList);

}
