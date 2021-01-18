package top.parak.examarrangementsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.common.HttpStatus;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.ExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.service.RecruitAdminService;
import top.parak.examarrangementsystem.service.RecruitApproverService;
import top.parak.examarrangementsystem.util.CheckUtils;
import top.parak.examarrangementsystem.util.JWTUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: LoginController <p>
 * <p> Description: 登录控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Api("登录")
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private RecruitAdminService recruitAdminService;

    @Autowired
    private RecruitApproverService recruitApproverService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private ExamPlaceInvigilatorService examPlaceInvigilatorService;

    @ApiOperation(value = "登录", notes = "需要邮箱和密码，对邮箱格式和密码长度进行检查")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody String loginJson) {
        /* 解析json */
        try {
            JSONObject login = JSON.parseObject(loginJson);
            String email = login.getString("email");
            String password = login.getString("password");
            if (!CheckUtils.checkEmail(email)) {
                return ServerResponse.dataResponse(HttpStatus.EMAIL_FORMAT_ERROR);
            }
            if (!CheckUtils.checkPassword(password)) {
                return ServerResponse.dataResponse(HttpStatus.PASSWORD_LENGTH_ERROR);
            }
            Map<String, Object> map = new HashMap<>();
            /* 招办管理员 */
            RecruitAdminDTO recruitAdminByEmail = recruitAdminService.getRecruitAdminByEmail(email);
            if (!ObjectUtils.isEmpty(recruitAdminByEmail)) {
                RecruitAdminDTO recruitAdminByEmailAndPassword = recruitAdminService.getRecruitAdminByEmailAndPassword(email, password);
                if (!ObjectUtils.isEmpty(recruitAdminByEmailAndPassword)) {
                    log.info("招办管理员登陆 => [{}]", recruitAdminByEmailAndPassword.getEmail());
                    String token = JWTUtils.generateTokenById(recruitAdminByEmailAndPassword.getId(), recruitAdminByEmailAndPassword.getEmail(), recruitAdminByEmailAndPassword.getRoleId());
                    map.put("login", recruitAdminByEmailAndPassword);
                    map.put("token", token);
                    return ServerResponse.successResponse(map);
                } else {
                    return ServerResponse.dataResponse(HttpStatus.USER_PASSWORD_ERROR);
                }
            }
            /* 招办审批员 */
            RecruitApproverDTO recruitApproverByEmail = recruitApproverService.getRecruitApproverByEmail(email);
            if (!ObjectUtils.isEmpty(recruitApproverByEmail)) {
                RecruitApproverDTO recruitApproverByEmailAndPassword = recruitApproverService.getRecruitApproverByEmailAndPassword(email, password);
                if (!ObjectUtils.isEmpty(recruitApproverByEmailAndPassword)) {
                    log.info("招办审批员登录 => [{}]", recruitApproverByEmailAndPassword.getEmail());
                    String token = JWTUtils.generateTokenById(recruitApproverByEmailAndPassword.getId(), recruitApproverByEmailAndPassword.getEmail(), recruitApproverByEmailAndPassword.getRoleId());
                    map.put("login", recruitApproverByEmailAndPassword);
                    map.put("token", token);
                    return ServerResponse.successResponse(map);
                } else {
                    return ServerResponse.dataResponse(HttpStatus.USER_PASSWORD_ERROR);
                }
            }
            /* 考点管理员 */
            ExamPlaceAdminDTO examPlaceAdminByEmail = examPlaceAdminService.getExamPlaceAdminByEmail(email);
            if (!ObjectUtils.isEmpty(examPlaceAdminByEmail)) {
                ExamPlaceAdminDTO examPlaceAdminByEmailAndPassword = examPlaceAdminService.getExamPlaceAdminByEmailAndPassword(email, password);
                if (!ObjectUtils.isEmpty(examPlaceAdminByEmailAndPassword)) {
                    log.info("考点管理员登录 => [{}]", examPlaceAdminByEmailAndPassword.getEmail());
                    String token = JWTUtils.generateTokenById(examPlaceAdminByEmailAndPassword.getId(), examPlaceAdminByEmailAndPassword.getEmail(), examPlaceAdminByEmailAndPassword.getRoleId());
                    map.put("login", examPlaceAdminByEmailAndPassword);
                    map.put("token", token);
                    return ServerResponse.successResponse(map);
                } else {
                    return ServerResponse.dataResponse(HttpStatus.USER_PASSWORD_ERROR);
                }
            }
            /* 考务人员 */
            ExamPlaceInvigilatorDTO examPlaceInvigilatorByEmail = examPlaceInvigilatorService.getExamPlaceInvigilatorByEmail(email);
            if (!ObjectUtils.isEmpty(examPlaceInvigilatorByEmail)) {
                System.out.println(examPlaceInvigilatorByEmail.toString());
                ExamPlaceInvigilatorDTO examPlaceInvigilatorByEmailAndPassword = examPlaceInvigilatorService.getExamPlaceInvigilatorByEmailAndPassword(email, password);
                if (!ObjectUtils.isEmpty(examPlaceInvigilatorByEmailAndPassword)) {
                    log.info("考务人员登录 => [{}]", examPlaceInvigilatorByEmailAndPassword.getEmail());
                    String token = JWTUtils.generateTokenById(examPlaceInvigilatorByEmailAndPassword.getId(), examPlaceInvigilatorByEmailAndPassword.getEmail(), examPlaceInvigilatorByEmailAndPassword.getRoleId());
                    map.put("login", examPlaceInvigilatorByEmailAndPassword);
                    map.put("token", token);
                    return ServerResponse.successResponse(map);
                } else {
                    return ServerResponse.dataResponse(HttpStatus.USER_PASSWORD_ERROR);
                }
            }
            return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

}
