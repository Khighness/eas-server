package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordExamSubjectDTO;
import top.parak.examarrangementsystem.entity.RecordExamSubject;
import top.parak.examarrangementsystem.util.TimeUtils;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordExamSubjectConverter <p>
 * <p> Description: 考试科目记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Mapper
public interface RecordExamSubjectConverter {

    RecordExamSubjectConverter INSTANCE = Mappers.getMapper(RecordExamSubjectConverter.class);

    @Mappings({
            @Mapping(source = "recordExamSubject.id", target = "id"),
            @Mapping(source = "recordExamSubject.serialNumber", target = "serialNumber"),
            @Mapping(source = "recordExamSubject.name", target = "name"),
            @Mapping(source = "recordExamSubject.startTime", target = "date", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recordExamSubject.startTime", target = "start", dateFormat = TimeUtils.ONLY_TIME_FORMAT),
            @Mapping(source = "recordExamSubject.endTime", target = "end", dateFormat = TimeUtils.ONLY_TIME_FORMAT),
            @Mapping(source = "recordExamSubject.startTime", target = "startTime", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordExamSubject.endTime", target = "endTime", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    RecordExamSubjectDTO entityToDTO(RecordExamSubject recordExamSubject);

    List<RecordExamSubjectDTO> entityListToDTOList(List<RecordExamSubject> recordExamSubjectList);

}
