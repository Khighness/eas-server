package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordGroupInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.*;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordGroupInvigilatorConverter <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/15
 */

@Mapper
public interface RecordGroupInvigilatorConverter {

    RecordGroupInvigilatorConverter INSTANCE = Mappers.getMapper(RecordGroupInvigilatorConverter.class);

    @Mappings({
            @Mapping(source = "recordExam.name", target = "examName"),
            @Mapping(source = "recordExamSubjectInvigilatorGroup.id", target = "groupId"),
            @Mapping(source = "recordExamSubjectInvigilatorGroup.remark", target = "remark"),
            @Mapping(source = "recordSubmitExamPlaceRoomDTO.examRoomSerialNumber", target = "examRoomSerialNumber"),
            @Mapping(source = "recordSubmitExamPlaceRoomDTO.name", target = "examRoomName"),
            @Mapping(source = "recordExamSubject.name", target = "examSubjectName"),
            @Mapping(source = "recordExamSubject.startTime", target = "examSubjectStartTime", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordExamSubject.endTime", target = "examSubjectEndTime", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordTaskExamInvigilator.id", target = "taskId"),
            @Mapping(source = "recordTaskExamInvigilator.violated", target = "violated"),
            @Mapping(source = "recordSubmitExamPlaceInvigilatorDTO.name", target = "name"),
            @Mapping(source = "recordSubmitExamPlaceInvigilatorDTO.gender", target = "gender"),
            @Mapping(source = "recordSubmitExamPlaceInvigilatorDTO.invigilatorSerialNumber", target = "invigilatorSerialNumber"),
            @Mapping(source = "recordSubmitExamPlaceInvigilatorDTO.main", target = "main")
    })
    RecordGroupInvigilatorDTO entityDtoDTO(RecordExam recordExam,
                                           RecordExamSubjectInvigilatorGroup recordExamSubjectInvigilatorGroup,
                                           RecordTaskExamInvigilator recordTaskExamInvigilator,
                                           RecordSubmitExamPlaceRoomDTO recordSubmitExamPlaceRoomDTO,
                                           RecordExamSubject recordExamSubject,
                                           RecordSubmitExamPlaceInvigilatorDTO recordSubmitExamPlaceInvigilatorDTO);

}
