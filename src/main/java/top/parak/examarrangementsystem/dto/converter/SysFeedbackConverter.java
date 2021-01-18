package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.SysFeedbackDTO;
import top.parak.examarrangementsystem.entity.SysFeedback;
import top.parak.examarrangementsystem.util.TimeUtils;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: SysFeedbackConverter <p>
 * <p> Description: 系统反馈DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Mapper
public interface SysFeedbackConverter {

    SysFeedbackConverter INSTANCE = Mappers.getMapper(SysFeedbackConverter.class);

    @Mappings({
            @Mapping(source = "sysFeedback.id", target = "id"),
            @Mapping(source = "sysFeedback.email", target = "email"),
            @Mapping(source = "sysFeedback.feedback", target = "feedback"),
            @Mapping(source = "sysFeedback.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "sysFeedback.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    SysFeedbackDTO entityToDTO(SysFeedback sysFeedback);

    List<SysFeedbackDTO> entityListToDTOList(List<SysFeedback> sysFeedbackList);

}
