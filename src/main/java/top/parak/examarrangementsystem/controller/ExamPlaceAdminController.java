package top.parak.examarrangementsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.common.HttpStatus;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.util.CheckUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: ExamPlaceAdminController <p>
 * <p> Description: 考务人员控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api("考点管理员")
@Slf4j
@RestController
@RequestMapping("/api/examPlaceAdmin")
public class ExamPlaceAdminController {

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    /**
     * <p>新增考点管理员</p>
     * @param examPlaceAdmin
     * @return
     */
    @ApiOperation(value = "新增招办审批员", notes = "需要邮箱、密码、姓名、性别和考点ID")
    @ApiImplicitParam(name = "examPlaceAdmin", value = "examPlaceAdmin", required = true, dataType = "ExamPlaceAdmin")
    @PostMapping("/save")
    public ServerResponse saveExamPlaceAdmin(@Valid @RequestBody ExamPlaceAdmin examPlaceAdmin, BindingResult bindingResult) {
        /* 校验邮箱和密码 */
        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError().getField().equals("email")) {
                return ServerResponse.dataResponse(HttpStatus.EMAIL_FORMAT_ERROR);
            }
            if (bindingResult.getFieldError().getField().equals("password")) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
        }
        /* 校验存在性 */
        if (!ObjectUtils.isEmpty(examPlaceAdminService.getExamPlaceAdminByEmail(examPlaceAdmin.getEmail()))) {
            return ServerResponse.dataResponse(HttpStatus.EMAIL_ALREADY_REGISTERED);
        }
        /* 校验考点是否已有管理员 */
        if (!ObjectUtils.isEmpty(examPlaceAdminService.getExamPlaceAdminDTOByExamPlaceId(examPlaceAdmin.getExamPlaceId()))) {
            return ServerResponse.clientErrorResponse("该考点已设置管理员");
        }
        int result = examPlaceAdminService.saveExamPlaceAdmin(examPlaceAdmin);
        if (result == 1) {
            log.info("添加考点管理员 => [{}]", examPlaceAdmin.getEmail());
            return ServerResponse.successResponse("添加成功");
        } else {
            return ServerResponse.serverErrorResponse("添加失败");
        }
    }

    /**
     * <p>通过ID获取考点管理员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "通过ID获取考点管理员", notes = "需要考点管理员的ID")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @GetMapping("/get")
    public ServerResponse getExamPlaceAdminById(@RequestParam Long id) {
        ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(id);
        if (!ObjectUtils.isEmpty(examPlaceAdminInMySQL)) {
            return ServerResponse.successResponse(examPlaceAdminInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>获取所有考点管理员</p>
     * @return
     */
    @ApiOperation(value = "获取所有招办审批员", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getExamPlaceAdminList() {
        /* 没有数据 */
        if (examPlaceAdminService.countOfExamPlaceAdmin() == 0) {
            return ServerResponse.serverErrorResponse("暂无考点管理员信息");
        }
        return ServerResponse.successResponse(examPlaceAdminService.getExamPlaceAdminList());
    }

    /**
     * <p>分页查询考点管理员</p>
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询招办审批员", notes = "需要分页的页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getExamPlaceAdminByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = examPlaceAdminService.countOfExamPlaceAdmin();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考点管理员信息");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", examPlaceAdminService.getExamPlaceAdminByPage(current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>上级重置考点管理员密码</p>
     * @param passJson
     * @return
     */
    @ApiOperation(value = "上级重置考点管理员密码", notes = "需要考点管理员的ID和重置后的密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/reset")
    public ServerResponse updateExamPlaceAdminPasswordById(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId >= 3) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            Long id = jsonObject.getLong("id");
            String password = jsonObject.getString("password");
            /* 校验存在性 */
            ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(id);
            if (ObjectUtils.isEmpty(examPlaceAdminInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验密码长度 */
            if (!CheckUtils.checkPassword(password)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = examPlaceAdminService.updateExamPlaceAdminPasswordById(id, password);
            if (result == 1) {
                log.info("重置密码成功 => [{}]", examPlaceAdminInMySQL.getEmail());
                return ServerResponse.successResponse("重置密码成功");
            } else {
                return ServerResponse.serverErrorResponse("重置密码失败");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>个人修改考点管理员密码</p>
     * @param passJson
     * @param request
     * @return
     */
    @ApiOperation(value = "个人修改考点管理员密码", notes = "需要考点管理员的原密码和修改后的密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/update")
    public ServerResponse updateExamPlaceAdminPassword(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 3) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        Long id = decodedJWT.getClaim("id").asLong();
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            String oldPassword = jsonObject.getString("oldPassword");
            String newPassword = jsonObject.getString("newPassword");
            /* 校验存在性 */
            ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(id);
            if (ObjectUtils.isEmpty(examPlaceAdminInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验原密码 */
            ExamPlaceAdminDTO examPlaceAdminByEmailAndPassword = examPlaceAdminService.getExamPlaceAdminByEmailAndPassword(examPlaceAdminInMySQL.getEmail(), oldPassword);
            if (ObjectUtils.isEmpty(examPlaceAdminByEmailAndPassword)) {
                return ServerResponse.clientErrorResponse("原密码错误");
            }
            /* 校验密码长度 */
            if (!CheckUtils.checkPassword(newPassword)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = examPlaceAdminService.updateExamPlaceAdminPasswordById(id, newPassword);
            if (result == 1) {
                log.info("修改密码成功 => [{}]", examPlaceAdminInMySQL.getEmail());
                return ServerResponse.successResponse("修改密码成功");
            } else {
                return ServerResponse.serverErrorResponse("修改密码失败");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>修改招办审批员信息</p>
     * @param examPlaceAdmin
     * @return
     */
    @ApiOperation(value = "修改招办审批员信息", notes = "需要招办审批员修改后的信息")
    @ApiImplicitParam(name = "examPlaceAdmin", value = "examPlaceAdmin", required = true, dataType = "examPlaceAdmin")
    @PutMapping("/update")
    public ServerResponse updateExamPlaceAdminInfoById(@RequestBody ExamPlaceAdmin examPlaceAdmin) {
        /* 校验存在性 */
        ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(examPlaceAdmin.getId());
        if (ObjectUtils.isEmpty(examPlaceAdminInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        /* 校验姓名 */
        if (!ObjectUtils.isEmpty(examPlaceAdmin.getName()) && !CheckUtils.checkName(examPlaceAdmin.getName())) {
            return ServerResponse.clientErrorResponse("姓名非法");
        }
        /* 校验性别 */
        if (!ObjectUtils.isEmpty(examPlaceAdmin.getGender()) && !CheckUtils.checkGender(examPlaceAdmin.getGender())) {
            return ServerResponse.clientErrorResponse("性别非法");
        }
        /* 校验生日 */
        if (!ObjectUtils.isEmpty(examPlaceAdmin.getBirth()) && !CheckUtils.checkBirth(examPlaceAdmin.getBirth())) {
            return ServerResponse.clientErrorResponse("生日非法");
        }
        /* 校验电话 */
        if (!ObjectUtils.isEmpty(examPlaceAdmin.getPhone()) && !CheckUtils.checkPhone(examPlaceAdmin.getPhone())) {
            return ServerResponse.clientErrorResponse("电话非法");
        }
        int result = examPlaceAdminService.updateExamPlaceAdminInfoById(examPlaceAdmin);
        if (result == 1) {
            log.info("修改信息成功 => [{}]", examPlaceAdminInMySQL.getEmail());
            return ServerResponse.successResponse("修改信息成功");
        } else {
            return ServerResponse.serverErrorResponse("修改信息失败");
        }
    }

    /**
     * <p>删除单个考点管理员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个考点管理员", notes = "需要考点管理员的ID")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @DeleteMapping("/delete")
    public ServerResponse deleteExamPlaceAdminById(@RequestParam("id") Long id){
        /* 校验存在性 */
        ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(id);
        if (ObjectUtils.isEmpty(examPlaceAdminInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        int result = examPlaceAdminService.deleteExamPlaceAdminById(id);
        if (result == 1) {
            log.info("删除考务人员 => [{}]", examPlaceAdminInMySQL.getEmail());
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.serverErrorResponse("删除失败");
        }
    }

}
