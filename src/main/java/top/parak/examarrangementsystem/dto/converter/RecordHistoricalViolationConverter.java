package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordHistoricalViolationDTO;
import top.parak.examarrangementsystem.entity.RecordHistoricalViolation;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordHistoricalViolationConverter <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/14
 */

@Mapper
public interface RecordHistoricalViolationConverter {

    RecordHistoricalViolationConverter INSTANCE = Mappers.getMapper(RecordHistoricalViolationConverter.class);

    @Mappings({
            @Mapping(source = "recordHistoricalViolation.id", target = "id"),
            @Mapping(source = "recordHistoricalViolation.name", target = "name"),
            @Mapping(source = "recordHistoricalViolation.identityNumber", target = "identityNumber"),
            @Mapping(source = "recordHistoricalViolation.examName", target = "examName"),
            @Mapping(source = "recordHistoricalViolation.examSubject", target = "examSubject"),
            @Mapping(source = "recordHistoricalViolation.examDate", target = "examDate", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recordHistoricalViolation.violation", target = "violation")
    })
    RecordHistoricalViolationDTO entityToDTO(RecordHistoricalViolation recordHistoricalViolation);

}
