package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceInvigilator;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordSubmitExamPlaceInvigilatorConverter <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Mapper
public interface RecordSubmitExamPlaceInvigilatorConverter {

    RecordSubmitExamPlaceInvigilatorConverter INSTANCE = Mappers.getMapper(RecordSubmitExamPlaceInvigilatorConverter.class);

    @Mappings({
            @Mapping(source = "recordSubmitExamPlaceInvigilator.id", target = "id"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.invigilatorSerialNumber", target = "invigilatorSerialNumber"),
            @Mapping(source = "examPlaceInvigilator.name", target = "name"),
            @Mapping(source = "examPlaceInvigilator.gender", target = "gender"),
            @Mapping(source = "recordExamPlaceInvigilator.identityNumber", target = "identityNumber"),
            @Mapping(source = "recordExamPlaceInvigilator.photo", target = "photo"),
            @Mapping(source = "recordExamPlaceInvigilator.teachingGrade", target = "teachingGrade"),
            @Mapping(source = "recordExamPlaceInvigilator.invigilateExperience", target = "invigilateExperience"),
            @Mapping(source = "recordExamPlaceInvigilator.remark", target = "remark"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.main", target = "main"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.reviewed", target = "reviewed"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.passed", target = "passed"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.reason", target = "reason"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.recordTaskExamPlaceId", target = "recordTaskExamPlaceId"),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordSubmitExamPlaceInvigilator.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })

    RecordSubmitExamPlaceInvigilatorDTO entityToDTO(RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator, RecordExamPlaceInvigilator recordExamPlaceInvigilator, ExamPlaceInvigilator examPlaceInvigilator);

}
