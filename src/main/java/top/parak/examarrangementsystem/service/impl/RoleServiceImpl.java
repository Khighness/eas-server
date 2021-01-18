package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.ss.usermodel.Row;
import top.parak.examarrangementsystem.dto.RoleDTO;
import top.parak.examarrangementsystem.dto.converter.RoleConverter;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.mapper.RoleMapper;
import top.parak.examarrangementsystem.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RoleServiceImpl <p>
 * <p> Description: 角色业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int saveRole(String name) {
        Role role = Role.builder().name(name).build();
        return roleMapper.insert(role);
    }

    @Override
    public int countOfRole() {
        return roleMapper.selectCount(null);
    }

    @Override
    public List<RoleDTO> getRoleList() {
        return RoleConverter.INSTANCE.entityListToDTOList(roleMapper.selectList(null));
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateById(role);
    }

}
