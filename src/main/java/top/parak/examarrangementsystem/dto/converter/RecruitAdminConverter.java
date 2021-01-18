package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.entity.RecruitAdmin;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecruitAdminConverter <p>
 * <p> Description: 招办管理员-角色DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Mapper
public interface RecruitAdminConverter {

    RecruitAdminConverter INSTANCE = Mappers.getMapper(RecruitAdminConverter.class);

    @Mappings({
            @Mapping(source = "recruitAdmin.id", target = "id"),
            @Mapping(source = "recruitAdmin.email", target = "email"),
            @Mapping(source = "recruitAdmin.name", target = "name"),
            @Mapping(source = "recruitAdmin.avatar", target = "avatar"),
            @Mapping(source = "recruitAdmin.gender", target = "gender"),
            @Mapping(source = "recruitAdmin.birth", target = "birth", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recruitAdmin.phone", target = "phone"),
            @Mapping(source = "recruitAdmin.province", target = "position", defaultValue = "TA或许就在你的彼岸。"),
            @Mapping(source = "recruitAdmin.signature", target = "signature", defaultValue = "这个人很懒，什么也没留下。"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.name", target = "roleName"),
            @Mapping(source = "recruitAdmin.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recruitAdmin.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    RecruitAdminDTO entityToDTO(RecruitAdmin recruitAdmin, Role role);

}
