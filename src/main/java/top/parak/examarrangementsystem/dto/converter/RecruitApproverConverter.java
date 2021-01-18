package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.entity.RecruitApprover;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.util.TimeUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RecruitApproverConverter <p>
 * <p> Description: 招办审批员-绝DTO转化器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Mapper
public interface RecruitApproverConverter {

    RecruitApproverConverter INSTANCE = Mappers.getMapper(RecruitApproverConverter.class);

    @Mappings({
            @Mapping(source = "recruitApprover.id", target = "id"),
            @Mapping(source = "recruitApprover.email", target = "email"),
            @Mapping(source = "recruitApprover.name", target = "name"),
            @Mapping(source = "recruitApprover.avatar", target = "avatar"),
            @Mapping(source = "recruitApprover.gender", target = "gender"),
            @Mapping(source = "recruitApprover.birth", target = "birth", dateFormat = TimeUtils.ONLY_DATE_FORMAT),
            @Mapping(source = "recruitApprover.phone", target = "phone"),
            @Mapping(source = "recruitApprover.city", target = "position", defaultValue = "TA或许就在你的彼岸。"),
            @Mapping(source = "recruitApprover.signature", target = "signature", defaultValue = "这个人很懒，什么也没留下。"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.name", target = "roleName"),
            @Mapping(source = "recruitApprover.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "recruitApprover.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    RecruitApproverDTO entityToDTO(RecruitApprover recruitApprover, Role role);

}
