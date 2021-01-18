package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RoleDTO;
import top.parak.examarrangementsystem.entity.Role;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: RoleService <p>
 * <p> Description: 角色业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface RoleService {

    /**
     * <p>添加角色</p>
     * @param name
     * @return
     */
    int saveRole(String name);

    /**
     * <p>获取角色数量</p>
     * @return
     */
    int countOfRole();

    /**
     * <p>获取所有角色</p>
     * @return
     */
    List<RoleDTO> getRoleList();

    /**
     * <p>修改角色信息</p>
     * @param role
     * @return
     */
    int updateRole(Role role);

}
