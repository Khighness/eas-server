package top.parak.examarrangementsystem.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.service.SysFeedbackService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: SysFeedbackController <p>
 * <p> Description: 系统反馈控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Api("系统反馈")
@Slf4j
@RestController
@RequestMapping("/api/sysFeedback")
public class SysFeedbackController {

    @Autowired
    private SysFeedbackService sysFeedbackService;

    /**
     * <p>提交反馈信息</p>
     * @param sysFeedback
     * @param request
     * @return
     */
    @ApiOperation(value = "提交反馈信息", notes = "需要反馈信息")
    @ApiImplicitParam(name = "feedback", value = "feedback", required = true, dataType = "String")
    @PostMapping("/save")
    public ServerResponse saveFeedBack(@RequestBody String sysFeedback, HttpServletRequest request) {
        /* 解析token */
        DecodedJWT decodedJWT = JWT.decode(request.getHeader("Authorization"));
        String email = decodedJWT.getClaim("email").asString();
        /* 解析json */
        String feedback = JSON.parseObject(sysFeedback).getString("feedback");
        if (feedback.length() >= 255) {
            return ServerResponse.clientErrorResponse("反馈信息不能超过255个字符");
        }
        /* 插入数据库 */
        int result = sysFeedbackService.saveFeedBack(email, feedback);
        if (result == 1) {
            log.info("系统反馈 => [{}]", feedback);
            return ServerResponse.successResponse("反馈成功");
        } else {
            return ServerResponse.clientErrorResponse("反馈失败");
        }
    }

    /**
     * <p>分页查询反馈</p>
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "提交反馈信息", notes = "需要反馈信息")
    @ApiImplicitParam(name = "feedback", value = "feedback", required = true, dataType = "SysFeedback")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getFeedBackByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = sysFeedbackService.countOfFeedback();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无系统反馈信息");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", sysFeedbackService.getFeedbackByPage(current, size));
        return ServerResponse.successResponse(map);
    }

}

