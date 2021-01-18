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
import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.entity.RecruitApprover;
import top.parak.examarrangementsystem.service.RecruitApproverService;
import top.parak.examarrangementsystem.util.CheckUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecruitApproverController <p>
 * <p> Description: 招办审批员控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api(value = "招办审批员")
@Slf4j
@RestController
@RequestMapping("/api/recruitApprover")
public class RecruitApproverController {

    @Autowired
    private RecruitApproverService recruitApproverService;

    /**
     * <p>新增招办审批员</p>
     * @param recruitApprover
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "新增招办审批员", notes = "需要邮箱、密码、姓名、性别和城市")
    @ApiImplicitParam(name = "recruitApprover", value = "recruitApprover", required = true, dataType = "RecruitApprover")
    @PostMapping("/save")
    public ServerResponse saveRecruitApprover(@Valid @RequestBody RecruitApprover recruitApprover, BindingResult bindingResult) {
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
        if (!ObjectUtils.isEmpty(recruitApproverService.getRecruitApproverByEmail(recruitApprover.getEmail()))) {
            return ServerResponse.dataResponse(HttpStatus.EMAIL_ALREADY_REGISTERED);
        }
        int result = recruitApproverService.saveRecruitApprover(recruitApprover);
        if (result == 1) {
            log.info("添加招办审批员 => [{}]", recruitApprover.getEmail());
            return ServerResponse.successResponse("添加成功");
        } else {
            return ServerResponse.serverErrorResponse("添加失败");
        }
    }

    /**
     * <p>根据ID获取招办审批员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取招办审批员", notes = "需要招办审批员的ID")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @GetMapping("/get")
    public ServerResponse getRecruitApproverById(@RequestParam Long id) {
        RecruitApproverDTO recruitApproverInMySQL = recruitApproverService.getRecruitApproverById(id);
        if (!ObjectUtils.isEmpty(recruitApproverInMySQL)) {
            return ServerResponse.successResponse(recruitApproverInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>获取所有招办审批员</p>
     * @return
     */
    @ApiOperation(value = "获取所有招办审批员", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getRecruitApproverList() {
        /* 没有数据 */
        if (recruitApproverService.countOfRecruitApprover() == 0) {
            return ServerResponse.serverErrorResponse("暂无考务人员信息");
        }
        return ServerResponse.successResponse(recruitApproverService.getRecruitApproverList());
    }

    /**
     * <p>分页查询招办审批员</p>
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询招办审批员", notes = "需要分页的页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecruitApproverByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = recruitApproverService.countOfRecruitApprover();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无招办审批员信息");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recruitApproverService.getRecruitApproverByPage(current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>上级重置招办审批员密码</p>
     * @param passJson
     * @param request
     * @return
     */
    @ApiOperation(value = "上级重置招办审批员密码", notes = "需要招办审批员的ID和修改后密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/reset")
    public ServerResponse updateRecruitApproverPassword(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 1) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            Long id = jsonObject.getLong("id");
            String newPassword = jsonObject.getString("password");
            /* 校验存在性 */
            RecruitApproverDTO recruitApproverInMySQL = recruitApproverService.getRecruitApproverById(id);
            if (ObjectUtils.isEmpty(recruitApproverInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验密码 */
            if (!CheckUtils.checkPassword(newPassword)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = recruitApproverService.updateRecruitApproverPasswordById(id, newPassword);
            if (result == 1) {
                log.info("重置密码成功 => [{}]", recruitApproverInMySQL.getEmail());
                return ServerResponse.successResponse("重置密码成功");
            } else {
                return ServerResponse.serverErrorResponse("重置密码成功");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>个人修改招办审批员密码</p>
     * @param passJson
     * @param request
     * @return
     */
    @ApiOperation(value = "修改招办审批员密码", notes = "需要招办审批员的ID和修改后密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/update")
    public ServerResponse updateRecruitApproverPasswordById(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 2) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        Long id = decodedJWT.getClaim("id").asLong();
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            String oldPassword = jsonObject.getString("oldPassword");
            String newPassword = jsonObject.getString("newPassword");
            /* 校验存在性 */
            RecruitApproverDTO recruitApproverInMySQL = recruitApproverService.getRecruitApproverById(id);
            if (ObjectUtils.isEmpty(recruitApproverInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验原密码 */
            RecruitApproverDTO recruitApproverByEmailAndPassword = recruitApproverService.getRecruitApproverByEmailAndPassword(recruitApproverInMySQL.getEmail(), oldPassword);
            if (ObjectUtils.isEmpty(recruitApproverByEmailAndPassword)) {
                return ServerResponse.clientErrorResponse("原密码错误");
            }
            /* 校验密码 */
            if (!CheckUtils.checkPassword(newPassword)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = recruitApproverService.updateRecruitApproverPasswordById(id, newPassword);
            if (result == 1) {
                log.info("修改密码成功 => [{}]", recruitApproverInMySQL.getEmail());
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
     * @param recruitApprover
     * @return
     */
    @ApiOperation(value = "修改招办审批员信息", notes = "需要招办审批员修改后的信息")
    @ApiImplicitParam(name = "recruitApprover", value = "recruitApprover", required = true, dataType = "RecruitApprover")
    @PutMapping("/update")
    public ServerResponse updateRecruitApproverInfoById(@RequestBody RecruitApprover recruitApprover) {
        /* 校验存在性 */
        RecruitApproverDTO recruitApproverInMySQL = recruitApproverService.getRecruitApproverById(recruitApprover.getId());
        if (ObjectUtils.isEmpty(recruitApproverInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        /* 校验姓名 */
        if (!ObjectUtils.isEmpty(recruitApprover.getName()) && !CheckUtils.checkName(recruitApprover.getName())) {
            return ServerResponse.clientErrorResponse("姓名非法");
        }
        /* 校验性别 */
        if (!ObjectUtils.isEmpty(recruitApprover.getGender()) && !CheckUtils.checkGender(recruitApprover.getGender())) {
            return ServerResponse.clientErrorResponse("性别非法");
        }
        /* 校验生日 */
        if (!ObjectUtils.isEmpty(recruitApprover.getBirth()) && !CheckUtils.checkBirth(recruitApprover.getBirth())) {
            return ServerResponse.clientErrorResponse("生日非法");
        }
        /* 校验电话 */
        if (!ObjectUtils.isEmpty(recruitApprover.getPhone()) && !CheckUtils.checkPhone(recruitApprover.getPhone())) {
            return ServerResponse.clientErrorResponse("电话非法");
        }
        int result = recruitApproverService.updateRecruitApproverInfoById(recruitApprover);
        if (result == 1) {
            log.info("修改信息成功 => [{}]", recruitApproverInMySQL.getEmail());
            return ServerResponse.successResponse("修改信息成功");
        } else {
            return ServerResponse.serverErrorResponse("修改信息失败");
        }
    }

    /**
     * <p>删除单个招办审批员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个招办审批员", notes = "需要招办审批员的ID")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @DeleteMapping("/delete")
    public ServerResponse deleteRecruitApproverById(@RequestParam("id") Long id){
        /* 校验存在性 */
        if (ObjectUtils.isEmpty(recruitApproverService.getRecruitApproverById(id))) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        int result = recruitApproverService.deleteRecruitApproverById(id);
        return result == 1 ? ServerResponse.successResponse("删除成功") : ServerResponse.serverErrorResponse("删除失败");
    }

}
