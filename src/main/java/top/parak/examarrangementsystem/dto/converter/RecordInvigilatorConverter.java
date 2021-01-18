package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordInvigilator;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordInvigilatorConverter <p>
 * <p> Description: 考务人员记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Mapper
public interface RecordInvigilatorConverter {

    RecordInvigilatorConverter INSTANCE = Mappers.getMapper(RecordInvigilatorConverter.class);

    @Mappings({
            @Mapping(source = "recordInvigilator.id", target = "id"),
            @Mapping(source = "recordInvigilator.serialNumber", target = "serialNumber"),
            @Mapping(source = "examPlaceInvigilator.name", target = "name"),
            @Mapping(source = "recordInvigilator.examName", target = "examName"),
            @Mapping(source = "recordInvigilator.examSubject", target = "examSubject"),
            @Mapping(source = "recordInvigilator.examDate", target = "examDate", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recordInvigilator.main", target = "main"),
            @Mapping(source = "recordInvigilator.finished", target = "finished"),
            @Mapping(source = "recordInvigilator.violated", target = "violated"),
            @Mapping(source = "recordInvigilator.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordInvigilator.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
    })

    RecordInvigilatorDTO entityToDTO(RecordInvigilator recordInvigilator, ExamPlaceInvigilator examPlaceInvigilator);
}
