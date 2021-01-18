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
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.common.HttpStatus;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.entity.RecruitAdmin;
import top.parak.examarrangementsystem.service.RecruitAdminService;
import top.parak.examarrangementsystem.util.CheckUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecruitAdminController <p>
 * <p> Description: 招办管理员控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api("招办管理员")
@Slf4j
@RestController
@RequestMapping("/api/recruitAdmin")
public class RecruitAdminController {

    @Autowired
    private RecruitAdminService recruitAdminService;

    /**
     * <p>根据ID获取招办管理员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取招办管理员", notes = "需要招办管理员的ID")
    @GetMapping("/get")
    public ServerResponse getRecruitAdminById(@RequestParam("id") Long id) {
        RecruitAdminDTO recruitAdminInMySQL = recruitAdminService.getRecruitAdminById(id);
        if (!ObjectUtils.isEmpty(recruitAdminInMySQL)) {
            return ServerResponse.successResponse(recruitAdminInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>修改招办管理员密码</p>
     * @param passJson
     * @param request
     * @return
     */
    @ApiOperation(value = "修改招办管理员密码", notes = "需要招办管理员的修改前的密码和招办管理员修改后的密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/update")
    public ServerResponse updateRecruitAdminPasswordById(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 1) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        Long id = decodedJWT.getClaim("id").asLong();
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            String oldPassword = jsonObject.getString("oldPassword");
            String newPassword = jsonObject.getString("newPassword");
            /* 校验存在性 */
            RecruitAdminDTO recruitAdminInMySQL = recruitAdminService.getRecruitAdminById(id);
            if (ObjectUtils.isEmpty(recruitAdminInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验原密码 */
            RecruitAdminDTO recruitAdminByEmailAndPassword = recruitAdminService.getRecruitAdminByEmailAndPassword(recruitAdminInMySQL.getEmail(), oldPassword);
            if (ObjectUtils.isEmpty(recruitAdminByEmailAndPassword)) {
                return ServerResponse.clientErrorResponse("原密码错误");
            }
            /* 校验密码长度 */
            if (!CheckUtils.checkPassword(newPassword)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = recruitAdminService.updateRecruitAdminPasswordById(id, newPassword);
            if (result == 1) {
                log.info("修改密码成功 => [{}]", recruitAdminInMySQL.getEmail());
                return ServerResponse.successResponse("修改密码成功");
            } else {
                return ServerResponse.serverErrorResponse("修改密码失败");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>修改招办管理员信息</p>
     * @param recruitAdmin
     * @return
     */
    @ApiOperation(value = "修改招办管理员信息", notes = "需要招办管理员除了邮箱和密码的所有属性")
    @ApiImplicitParam(name = "recruitAdmin", value = "招办管理员信息实体", required = true, dataType = "RecruitAdmin")
    @PutMapping("/update")
    public ServerResponse updateRecruitAdminInfoById(@RequestBody RecruitAdmin recruitAdmin) {
        /* 校验存在性 */
        RecruitAdminDTO recruitAdminInMySQL = recruitAdminService.getRecruitAdminById(recruitAdmin.getId());
        if (ObjectUtils.isEmpty(recruitAdminInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        /* 校验姓名 */
        if (!ObjectUtils.isEmpty(recruitAdmin.getName()) && !CheckUtils.checkName(recruitAdmin.getName())) {
            return ServerResponse.clientErrorResponse("姓名非法");
        }
        /* 校验性别 */
        if (!ObjectUtils.isEmpty(recruitAdmin.getGender()) && !CheckUtils.checkGender(recruitAdmin.getGender())) {
            return ServerResponse.clientErrorResponse("性别非法");
        }
        /* 校验生日 */
        if (!ObjectUtils.isEmpty(recruitAdmin.getBirth()) && !CheckUtils.checkBirth(recruitAdmin.getBirth())) {
            return ServerResponse.clientErrorResponse("生日非法");
        }
        /* 校验电话 */
        if (!ObjectUtils.isEmpty(recruitAdmin.getPhone()) && !CheckUtils.checkPhone(recruitAdmin.getPhone())) {
            return ServerResponse.clientErrorResponse("电话非法");
        }
        int result = recruitAdminService.updateRecruitAdminInfoById(recruitAdmin);
        if (result == 1) {
            log.info("修改信息成功 => [{}]", recruitAdminInMySQL.getEmail());
            return ServerResponse.successResponse("修改信息成功");
        } else {
            return ServerResponse.serverErrorResponse("修改信息失败");
        }
    }


}

