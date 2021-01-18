package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlacePlanDTO;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlacePlan;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecordSubmitExamPlacePlanConverter <p>
 * <p> Description: 考点提交平面图记录DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/12
 */

@Mapper
public interface RecordSubmitExamPlacePlanConverter {

    RecordSubmitExamPlacePlanConverter INSTANCE = Mappers.getMapper(RecordSubmitExamPlacePlanConverter.class);

    @Mappings({
            @Mapping(source = "recordSubmitExamPlacePlan.id", target = "id"),
            @Mapping(source = "recordSubmitExamPlacePlan.serialNumber", target = "serialNumber"),
            @Mapping(source = "recordSubmitExamPlacePlan.examPlacePlan", target = "examPlacePlan"),
            @Mapping(source = "recordSubmitExamPlacePlan.remark", target = "remark"),
            @Mapping(source = "recordSubmitExamPlacePlan.reviewed", target = "reviewed"),
            @Mapping(source = "recordSubmitExamPlacePlan.passed", target = "passed"),
            @Mapping(source = "recordSubmitExamPlacePlan.reason", target = "reason"),
            @Mapping(source = "recordSubmitExamPlacePlan.recordTaskExamPlaceId", target = "recordTaskExamPlaceId"),
            @Mapping(source = "recordSubmitExamPlacePlan.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recordSubmitExamPlacePlan.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })

    RecordSubmitExamPlacePlanDTO entityToDTO(RecordSubmitExamPlacePlan recordSubmitExamPlacePlan);

}
