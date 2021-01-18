package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: ExamPlaceInvigilatorExamPlaceConverter <p>
 * <p> Description: 考务人员-角色-考点DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/2
 */

@Mapper
public interface ExamPlaceInvigilatorConverter {

    ExamPlaceInvigilatorConverter INSTANCE = Mappers.getMapper(ExamPlaceInvigilatorConverter.class);

    @Mappings({
            @Mapping(source = "examPlaceInvigilator.id", target = "id"),
            @Mapping(source = "examPlaceInvigilator.email", target = "email"),
            @Mapping(source = "examPlaceInvigilator.avatar", target = "avatar"),
            @Mapping(source = "examPlaceInvigilator.name", target = "name"),
            @Mapping(source = "examPlaceInvigilator.gender", target = "gender"),
            @Mapping(source = "examPlaceInvigilator.birth", target = "birth", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "examPlaceInvigilator.phone", target = "phone"),
            @Mapping(source = "examPlace.position", target = "position", defaultValue = "TA或许就在你的彼岸。"),
            @Mapping(source = "examPlaceInvigilator.signature", target = "signature", defaultValue = "这个人很懒，什么也没留下。"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.name", target = "roleName"),
            @Mapping(source = "examPlace.id", target = "examPlaceId"),
            @Mapping(source = "examPlace.name", target = "examPlaceName"),
            @Mapping(source = "examPlaceInvigilator.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "examPlaceInvigilator.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    ExamPlaceInvigilatorDTO entityToDTO(ExamPlaceInvigilator examPlaceInvigilator, Role role, ExamPlace examPlace);

}
