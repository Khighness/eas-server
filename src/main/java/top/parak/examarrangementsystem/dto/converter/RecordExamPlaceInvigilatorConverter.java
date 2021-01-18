package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordExamPlaceInvigilatorConverter <p>
 * <p> Description: 参加监考考务人员提交记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Mapper
public interface RecordExamPlaceInvigilatorConverter {

    RecordExamPlaceInvigilatorConverter INSTANCE = Mappers.getMapper(RecordExamPlaceInvigilatorConverter.class);

    @Mappings({
        @Mapping(source = "recordExamPlaceInvigilator.id", target = "id"),
        @Mapping(source = "recordExamPlaceInvigilator.serialNumber", target = "serialNumber"),
        @Mapping(source = "examPlaceInvigilator.name", target = "name"),
        @Mapping(source = "examPlaceInvigilator.gender", target = "gender"),
        @Mapping(source = "examPlaceInvigilator.birth", target = "birth", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
        @Mapping(source = "examPlaceInvigilator.phone", target = "phone"),
        @Mapping(source = "recordExamPlaceInvigilator.identityNumber", target = "identityNumber"),
        @Mapping(source = "recordExamPlaceInvigilator.photo", target = "photo"),
        @Mapping(source = "recordExamPlaceInvigilator.teachingGrade", target = "teachingGrade"),
        @Mapping(source = "recordExamPlaceInvigilator.invigilateExperience", target = "invigilateExperience"),
        @Mapping(source = "recordExamPlaceInvigilator.remark", target = "remark"),
        @Mapping(source = "recordExamPlaceInvigilator.reviewed", target = "reviewed"),
        @Mapping(source = "recordExamPlaceInvigilator.passed", target = "passed"),
        @Mapping(source = "recordExamPlaceInvigilator.reason", target = "reason"),
        @Mapping(source = "recordExamPlaceInvigilator.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
        @Mapping(source = "recordExamPlaceInvigilator.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
    })
    RecordExamPlaceInvigilatorDTO entityToDTO(ExamPlaceInvigilator examPlaceInvigilator, RecordExamPlaceInvigilator recordExamPlaceInvigilator);

}
