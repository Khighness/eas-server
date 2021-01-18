package top.parak.examarrangementsystem.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.parak.examarrangementsystem.dto.RoleDTO;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.util.TimeUtils;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto.converter </p>
 * <p> FileName: RoleConverter <p>
 * <p> Description: 角色DTO转换器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    @Mappings({
            @Mapping(source = "role.id", target = "id"),
            @Mapping(source = "role.name", target = "name"),
            @Mapping(source = "role.description", target = "description"),
            @Mapping(source = "role.gmtCreate", target = "gmtCreate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT),
            @Mapping(source = "role.gmtUpdate", target = "gmtUpdate", dateFormat = TimeUtils.COMPREHENSIVE_FORMAT)
    })
    RoleDTO entityToDTO(Role role);

    List<RoleDTO> entityListToDTOList(List<Role> roleList);
}
