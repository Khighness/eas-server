package top.parak.examarrangementsystem.controller;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.common.HttpStatus;
import top.parak.examarrangementsystem.config.MailAccountConfigure;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.service.ExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.util.CheckUtils;
import top.parak.examarrangementsystem.util.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: ExamPlaceInvigilatorController <p>
 * <p> Description: 考务人员控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api("考务人员")
@Slf4j
@RestController
@RequestMapping("/api/examPlaceInvigilator")
public class ExamPlaceInvigilatorController {

    private static final String DEVELOPED_COMPANY_NAME = "嘤嘤科技";

    @Autowired
    private ExamPlaceInvigilatorService examPlaceInvigilatorService;

    @Autowired
    private MailAccountConfigure mailAccountConfigure;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${verification.code.expired-time}")
    private Integer verificationCodeExpiredTime;

    /**
     * <p>发送注册验证码</p>
     * <p>redis键值：eas:examPlaceInvigilator:email</p>
     * @param
     */
    @ApiOperation(value = "发送注册验证码", notes = "需要邮箱，对邮箱格式进行校验")
    @ApiImplicitParam(name = "emailJson", value = "emailJson", required = true, dataType = "String")
    @PostMapping("/verify")
    public ServerResponse sendRegisterEmail(@RequestBody String emailJson) {
        /* 解析json */
        String email = null;
        try {
            email = JSON.parseObject(emailJson).getString("email");
        } catch (JSONException e) {
            /* json解析错误 */
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
        /* 校验邮箱 */
        if (!CheckUtils.checkEmail(email)) {
            return ServerResponse.dataResponse(HttpStatus.EMAIL_FORMAT_ERROR);
        }
        /* 查询邮箱是否已存在 */
        ExamPlaceInvigilatorDTO examPlaceInvigilatorByEmailInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorByEmail(email);
        if (!ObjectUtils.isEmpty(examPlaceInvigilatorByEmailInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.EMAIL_ALREADY_REGISTERED);
        }
        /* 设置键 */
        String emailKey = "eas:" + "examPlaceInvigilator:" + email;
        /* 生成值 */
        String codeValue = VerifyCodeUtils.generateCode();
        /* 防止攻击，如果验证码存在则直接返回错误信息 */
        if (redisTemplate.opsForValue().getOperations().getExpire(emailKey) != -2) {
            return ServerResponse.clientErrorResponse("请勿重复注册");
        }
        /* 获取发送邮箱账户 */
        MailAccount mailAccount = mailAccountConfigure.getInstance();
        /* 设置发送邮件得信息 */
        String message = "<h2>【" + DEVELOPED_COMPANY_NAME +"】</h2>" + "<p>亲爱的用户，您正在注册YYY排考系统，验证码：<b style=\"color:deepskyblue;font-size: 30px;\"><u>" +
                codeValue + "</u></b>" + "<p>请在15分钟内按页面提示提交验证码，切勿将验证码泄露于他人。</p>" + "<p>若不是本人操作，请忽略此邮件。</p>";
        log.info("发送邮件验证码 => [{}]", email);
        try {
            /* 发送邮件 */
            MailUtil.send(mailAccount, email, DEVELOPED_COMPANY_NAME, message, true);
            /* 存入缓存 */
            redisTemplate.opsForValue().set(emailKey, codeValue, verificationCodeExpiredTime, TimeUnit.SECONDS);
            log.info("验证码发送成功 => [{}]", email);
            /* 返回成功信息 */
            return ServerResponse.successResponse("验证码发送成功");
        } catch (Exception e) {
            log.error("邮件发送异常 => [{}]", e.getMessage());
            /* 返回服务器错误信息 */
            return ServerResponse.serverErrorResponse("验证码发送异常，请检查邮箱是否正确");
        }
    }

    /**
     * <p>考务人员注册</p>
     * @param infoJson
     * @return
     */
    @ApiOperation(value = "考务人员注册", notes = "需要邮箱、密码和验证码，对邮箱格式、密码长度和验证码正确性进行检查")
    @ApiImplicitParam(name = "infoJson", value = "infoJson", required = true, dataType = "String")
    @PostMapping("/register")
    public ServerResponse register(@RequestBody String infoJson) {
        /* 解析json */
        String email = null, password = null, code = null;
        try {
            JSONObject info = JSON.parseObject(infoJson);
            email = info.getString("email");
            password = info.getString("password");
            code = info.getString("code");
        } catch (JSONException e) {
            /* json解析错误 */
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
        /* 校验邮箱 */
        if (!CheckUtils.checkEmail(email)) {
            return ServerResponse.dataResponse(HttpStatus.EMAIL_FORMAT_ERROR);
        }
        /* 校验密码 */
        if (!CheckUtils.checkPassword(password)) {
            return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
        }
        /* 校验存在性 */
        ExamPlaceInvigilatorDTO examPlaceInvigilatorByEmailInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorByEmail(email);
        if (!ObjectUtils.isEmpty(examPlaceInvigilatorByEmailInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.EMAIL_ALREADY_REGISTERED);
        }
        /* 校验邮箱验证码 */
        String key = "eas:" + "examPlaceInvigilator:" + email;
        Long expire = redisTemplate.opsForValue().getOperations().getExpire(key);
        /**
         * expire > 0，代表有效时间
         * expire = -1，代表永久
         * expire = -2，代表国企
         */
        if (expire == -2) {
            return ServerResponse.clientErrorResponse("验证码无效");
        } else if (expire >= 1) {
            if (redisTemplate.opsForValue().get(key).toString().equals(code)) {
                /* 添加数据库 */
                int res = examPlaceInvigilatorService.saveExamPlaceInvigilator(email, password);
                log.info("考务人员注册 => [{}]", email);
                return res == 1 ? ServerResponse.successResponse("注册成功") : ServerResponse.serverErrorResponse("注册失败");
            } else {
                return ServerResponse.dataResponse(HttpStatus.EMAIL_VERIFICATION_CODE_ERROR);
            }
        } else {
            return ServerResponse.serverErrorResponse("服务器错误");
        }
    }

    /**
     * <p>根据ID查询考务人员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询考务人员", notes = "需要考务人员ID")
    @GetMapping("/get")
    public ServerResponse getExamPlaceInvigilatorById(@RequestParam("id") Long id) {
        ExamPlaceInvigilatorDTO examPlaceInvigilatorInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorById(id);
        if (!ObjectUtils.isEmpty(examPlaceInvigilatorInMySQL)) {
            return ServerResponse.successResponse(examPlaceInvigilatorInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>获取所有考务人员</p>
     * @return
     */
    @ApiOperation(value = "获取所有考务人员", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getExamPlaceInvigilatorList() {
        /* 没有数据 */
        if (examPlaceInvigilatorService.countOfExamPlaceInvigilator() == 0) {
            return ServerResponse.serverErrorResponse("暂无考务人员信息");
        }
        return ServerResponse.successResponse(examPlaceInvigilatorService.getExamPlaceInvigilatorList());
    }

    /**
     * <p>分页获取考务人员</p>
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询考务人员", notes = "需要分页的页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getExamPlaceInvigilatorByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = examPlaceInvigilatorService.countOfExamPlaceInvigilator();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考务人员信息");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", examPlaceInvigilatorService.getExamPlaceInvigilatorByPage(current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>上级修改考务人员密码</p>
     * @param passJson
     * @return
     */
    @ApiOperation(value = "上级修改考务人员密码", notes = "需要考务人员的ID和重置后的密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/reset")
    public ServerResponse updateExamPlaceInvigilatorPassword(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId == 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            Long id = jsonObject.getLong("id");
            String password = jsonObject.getString("password");
            /* 校验存在性 */
            ExamPlaceInvigilatorDTO examPlaceInvigilatorInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorById(id);
            if (ObjectUtils.isEmpty(examPlaceInvigilatorInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验密码长度 */
            if (!CheckUtils.checkPassword(password)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = examPlaceInvigilatorService.updateExamPlaceInvigilatorPasswordById(id, password);
            if (result == 1) {
                log.info("重置密码成功 => [{}]", examPlaceInvigilatorInMySQL.getEmail());
                return ServerResponse.successResponse("重置密码成功");
            } else {
                return ServerResponse.serverErrorResponse("重置密码失败");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>修改考务人员密码</p>
     * @param passJson
     * @return
     */
    @ApiOperation(value = "个人修改考务人员密码", notes = "需要考务人员的原密码和修改后的密码")
    @ApiImplicitParam(name = "passJson", value = "passJson", required = true, dataType = "String")
    @PatchMapping("/update")
    public ServerResponse updateExamPlaceInvigilatorPasswordById(@RequestBody String passJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        Long id = decodedJWT.getClaim("id").asLong();
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(passJson);
            String oldPassword = jsonObject.getString("oldPassword");
            String newPassword = jsonObject.getString("newPassword");
            /* 校验存在性 */
            ExamPlaceInvigilatorDTO examPlaceInvigilatorInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorById(id);
            if (ObjectUtils.isEmpty(examPlaceInvigilatorInMySQL)) {
                return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
            }
            /* 校验原密码 */
            ExamPlaceInvigilatorDTO examPlaceInvigilatorByEmailAndPassword = examPlaceInvigilatorService.getExamPlaceInvigilatorByEmailAndPassword(examPlaceInvigilatorInMySQL.getEmail(), oldPassword);
            if (ObjectUtils.isEmpty(examPlaceInvigilatorByEmailAndPassword)) {
                return ServerResponse.clientErrorResponse("原密码错误");
            }
            /* 校验密码长度 */
            if (!CheckUtils.checkPassword(newPassword)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            int result = examPlaceInvigilatorService.updateExamPlaceInvigilatorPasswordById(id, newPassword);
            if (result == 1) {
                log.info("修改密码成功 => [{}]", examPlaceInvigilatorInMySQL.getEmail());
                return ServerResponse.successResponse("修改密码成功");
            } else {
                return ServerResponse.serverErrorResponse("修改密码失败");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>上级修改考务人员信息</p>
     * @param examPlaceInvigilator
     * @return
     */
    @ApiOperation(value = "修改考务人员信息", notes = "需要考务人员除了邮箱和密码之外的所有属性")
    @ApiImplicitParam(name = "examPlaceInvigilator", value = "examPlaceInvigilator", required = true, dataType = "ExamPlaceInvigilator")
    @PutMapping("/update")
    public ServerResponse updateExamPlaceInvigilatorInfoById(@RequestBody ExamPlaceInvigilator examPlaceInvigilator) {
        /* 校验存在性 */
        ExamPlaceInvigilatorDTO examPlaceInvigilatorInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorById(examPlaceInvigilator.getId());
        if (ObjectUtils.isEmpty(examPlaceInvigilatorInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        /* 校验姓名 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getName()) && !CheckUtils.checkName(examPlaceInvigilator.getName())) {
            return ServerResponse.clientErrorResponse("姓名非法");
        }
        /* 校验性别 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getGender()) && !CheckUtils.checkGender(examPlaceInvigilator.getGender())) {
            return ServerResponse.clientErrorResponse("性别非法");
        }
        /* 校验生日 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getBirth()) && !CheckUtils.checkBirth(examPlaceInvigilator.getBirth())) {
            return ServerResponse.clientErrorResponse("生日非法");
        }
        /* 校验电话 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getPhone()) && !CheckUtils.checkPhone(examPlaceInvigilator.getPhone())) {
            return ServerResponse.clientErrorResponse("电话非法");
        }
        int result = examPlaceInvigilatorService.updateExamPlaceInvigilatorInfoById(examPlaceInvigilator);
        if (result == 1) {
            log.info("修改信息成功 => [{}]", examPlaceInvigilatorInMySQL.getEmail());
            return ServerResponse.successResponse("修改信息成功");
        } else {
            return ServerResponse.serverErrorResponse("修改信息失败");
        }
    }

    /**
     * <p>个人修改考务人员信息</p>
     * @param examPlaceInvigilator
     * @param request
     * @return
     */
    @ApiOperation(value = "修改考务人员信息", notes = "需要考务人员除了邮箱和密码之外的所有属性")
    @ApiImplicitParam(name = "examPlaceInvigilator", value = "examPlaceInvigilator", required = true, dataType = "ExamPlaceInvigilator")
    @PutMapping("/modify")
    public ServerResponse updateExamPlaceInvigilatorInfoById(@RequestBody ExamPlaceInvigilator examPlaceInvigilator, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        examPlaceInvigilator.setId(decodedJWT.getClaim("id").asLong());
        /* 校验姓名 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getName()) && !CheckUtils.checkName(examPlaceInvigilator.getName())) {
            return ServerResponse.clientErrorResponse("姓名非法");
        }
        /* 校验性别 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getGender()) && !CheckUtils.checkGender(examPlaceInvigilator.getGender())) {
            return ServerResponse.clientErrorResponse("性别非法");
        }
        /* 校验生日 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getBirth()) && !CheckUtils.checkBirth(examPlaceInvigilator.getBirth())) {
            return ServerResponse.clientErrorResponse("生日非法");
        }
        /* 校验电话 */
        if (!ObjectUtils.isEmpty(examPlaceInvigilator.getPhone()) && !CheckUtils.checkPhone(examPlaceInvigilator.getPhone())) {
            return ServerResponse.clientErrorResponse("电话非法");
        }
        int result = examPlaceInvigilatorService.updateExamPlaceInvigilatorInfoById(examPlaceInvigilator);
        if (result == 1) {
            log.info("修改信息成功 => [{}]", decodedJWT.getClaim("email").asString());
            return ServerResponse.successResponse("修改信息成功");
        } else {
            return ServerResponse.serverErrorResponse("修改信息失败");
        }
    }


    /**
     * <p>删除单个考务人员</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个考务人员", notes = "需要考务人员的ID")
    @DeleteMapping("/delete")
    public ServerResponse removeExamPlaceInvigilatorById(@RequestParam("id") Long id) {
        /* 校验存在性 */
        ExamPlaceInvigilatorDTO examPlaceInvigilatorInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorById(id);
        if (ObjectUtils.isEmpty(examPlaceInvigilatorInMySQL)) {
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        }
        int result = examPlaceInvigilatorService.deleteExamPlaceInvigilatorById(id);
        if (result == 1) {
            log.info("删除考务人员 => [{}]", examPlaceInvigilatorInMySQL.getEmail());
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.serverErrorResponse("删除失败");
        }
    }

    /**
     * <p>批量删除考务人员</p>
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量删除考务人员", notes = "需要考务人员ID的数组")
    @ApiImplicitParam(name = "idList", value = "idList", required = true, dataType = "list")
    @DeleteMapping("/remove")
    public ServerResponse removeBatchExamPlaceInvigilatorById(@RequestBody List<Long> idList) {
        if (idList.size() == 0) {
            return ServerResponse.clientErrorResponse("参数为空");
        }
        int result = examPlaceInvigilatorService.deleteBatchExamPlaceInvigilatorById(idList);
        return result >= 1 ? ServerResponse.successResponse("删除成功") :  ServerResponse.serverErrorResponse("删除失败");
    }

}
