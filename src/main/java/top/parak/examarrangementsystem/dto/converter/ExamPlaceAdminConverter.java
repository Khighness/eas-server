package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: ExamPlaceAdminConverter <p>
 * <p> Description: 考点管理员DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Mapper
public interface ExamPlaceAdminConverter {

    ExamPlaceAdminConverter INSTANCE = Mappers.getMapper(ExamPlaceAdminConverter.class);

    @Mappings({
            @Mapping(source = "examPlaceAdmin.id", target = "id"),
            @Mapping(source = "examPlaceAdmin.email", target = "email"),
            @Mapping(source = "examPlaceAdmin.avatar", target = "avatar"),
            @Mapping(source = "examPlaceAdmin.name", target = "name"),
            @Mapping(source = "examPlaceAdmin.gender", target = "gender"),
            @Mapping(source = "examPlaceAdmin.birth", target = "birth", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "examPlaceAdmin.phone", target = "phone"),
            @Mapping(source = "examPlace.position", target = "position", defaultValue = "TA或许就在你的彼岸。"),
            @Mapping(source = "examPlaceAdmin.signature", target = "signature", defaultValue = "这个人很懒，什么也没留下。"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.name", target = "roleName"),
            @Mapping(source = "examPlace.id", target = "examPlaceId"),
            @Mapping(source = "examPlace.name", target = "examPlaceName"),
            @Mapping(source = "examPlaceAdmin.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "examPlaceAdmin.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    ExamPlaceAdminDTO entityToDTO(ExamPlaceAdmin examPlaceAdmin, Role role, ExamPlace examPlace);

}
