package top.parak.examarrangementsystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.service.RoleService;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RoleController <p>
 * <p> Description: 角色控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api(value = "角色")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * <p>查询所有角色</p>
     * @return
     */
    @ApiOperation(value = "查询所有角色", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getRoleList() {
        return ServerResponse.successResponse(roleService.getRoleList());
    }

    /**
     * <p>更新角色信息</p>
     * @param role
     * @return
     */
    @ApiOperation(value = "更新角色信息", notes = "需要角色的ID和更新后的名称")
    @ApiImplicitParam(name = "role", value = "角色信息实体", required = true, dataType = "Role")
    @PatchMapping("/update")
    public ServerResponse updateRoleById(@RequestBody Role role) {
        return roleService.updateRole(role) == 1 ? ServerResponse.successResponse("修改成功") : ServerResponse.serverErrorResponse("修改失败");
    }

}
