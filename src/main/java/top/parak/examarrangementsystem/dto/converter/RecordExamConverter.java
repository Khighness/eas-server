package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordExamDTO;
import top.parak.examarrangementsystem.entity.RecordExam;
import top.parak.examarrangementsystem.util.TimeUtils;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordExamConverter <p>
 * <p> Description: 考试记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/7
 */

@Mapper
public interface RecordExamConverter {

    RecordExamConverter INSTANCE = Mappers.getMapper(RecordExamConverter.class);

    @Mappings({
            @Mapping(source = "recordExam.id", target = "id"),
            @Mapping(source = "recordExam.serialNumber", target = "serialNumber"),
            @Mapping(source = "recordExam.name", target = "name"),
            @Mapping(source = "recordExam.sponsor", target = "sponsor"),
            @Mapping(source = "recordExam.startTime", target = "startTime", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recordExam.endTime", target = "endTime", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recordExam.scienceExamineeNumber", target = "scienceExamineeNumber"),
            @Mapping(source = "recordExam.liberalArtsExamineeNumber", target = "liberalArtsExamineeNumber"),
            @Mapping(source = "recordExam.scienceRoomNumber", target = "scienceRoomNumber"),
            @Mapping(source = "recordExam.liberalArtsRoomNumber", target = "liberalArtsRoomNumber"),
            @Mapping(source = "recordExam.arranged", target = "arranged"),
            @Mapping(source = "recordExam.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordExam.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    RecordExamDTO entityToDTO(RecordExam recordExam);

    List<RecordExamDTO> entityListToDTOList(List<RecordExam> recordExamList);
}
