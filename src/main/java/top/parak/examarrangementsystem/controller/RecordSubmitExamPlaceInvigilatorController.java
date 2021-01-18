package top.parak.examarrangementsystem.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.dto.RecordTaskExamPlaceDTO;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceInvigilator;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.service.RecordTaskExamPlaceService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecordExamSubjectInvigilatorGroupController <p>
 * <p> Description: 考试监考组记录控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/8
 */

@Api("考点提交考务人员记录")
@Slf4j
@RestController
@RequestMapping("/api/recordSubmitExamPlaceInvigilator")
public class RecordSubmitExamPlaceInvigilatorController {

    @Autowired
    private RecordSubmitExamPlaceInvigilatorService recordSubmitExamPlaceInvigilatorService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private RecordTaskExamPlaceService recordTaskExamPlaceService;

    @PostMapping("/save")
    public ServerResponse saveRecordSubmitExamPlaceInvigilator(@RequestParam("invigilatorId") Long recordExamPlaceInvigilatorId,
                                                        @RequestParam("taskId") Long taskId,
                                                        HttpServletRequest request) {
        /* 解析Token */
        DecodedJWT decodedJWT = JWT.decode(request.getHeader("Authorization"));
        Long id = decodedJWT.getClaim("id").asLong();
        /* 校验是否合法 */
        ExamPlaceAdminDTO examPlaceAdmin = examPlaceAdminService.getExamPlaceAdminById(id);
        RecordTaskExamPlaceDTO recordTaskExamPlace = recordTaskExamPlaceService.getRecordTaskExamPlaceById(taskId);
        if (examPlaceAdmin.getExamPlaceId() != recordTaskExamPlace.getExamPlaceId()) {
            return ServerResponse.clientErrorResponse("请勿提交其他考点的任务");
        }
        int result = recordSubmitExamPlaceInvigilatorService.saveRecordSubmitExamPlaceInvigilator(recordExamPlaceInvigilatorId, taskId);
        if (result == 1) {
            return ServerResponse.successResponse("提交成功");
        } else {
            return ServerResponse.serverErrorResponse("提交失败");
        }
    }

    @PostMapping("/saveBatch")
    public ServerResponse saveBatchRecordSubmitExamPlaceInvigilator(@RequestBody List<Long> idList,
                                                             @RequestParam("taskId") Long taskId,
                                                             HttpServletRequest request) {
        /* 解析Token */
        DecodedJWT decodedJWT = JWT.decode(request.getHeader("Authorization"));
        Long id = decodedJWT.getClaim("id").asLong();
        /* 校验是否合法 */
        ExamPlaceAdminDTO examPlaceAdmin = examPlaceAdminService.getExamPlaceAdminById(id);
        RecordTaskExamPlaceDTO recordTaskExamPlace = recordTaskExamPlaceService.getRecordTaskExamPlaceById(taskId);
        if (examPlaceAdmin.getExamPlaceId() != recordTaskExamPlace.getExamPlaceId()) {
            return ServerResponse.clientErrorResponse("请勿提交其他考点的任务");
        }
        int result = idList.stream().mapToInt(e -> recordSubmitExamPlaceInvigilatorService.saveRecordSubmitExamPlaceInvigilator(e, taskId)).sum();
        if (result == idList.size()) {
            return ServerResponse.successResponse("提交成功");
        } else {
            return ServerResponse.serverErrorResponse("提交失败");
        }
    }

    @DeleteMapping("/delete")
    public ServerResponse deleteRecordSubmitExamPlaceInvigilator(@RequestParam("id") Long id) {
        int result = recordSubmitExamPlaceInvigilatorService.deleteRecordExamPlaceInvigilatorById(id);
        if (result == 1) {
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.serverErrorResponse("删除失败");
        }
    }

    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatchRecordSubmitExamPlaceInvigilator(@RequestBody List<Long> idList) {
        int result = recordSubmitExamPlaceInvigilatorService.deleteBatchRecordExamPlaceInvigilatorById(idList);
        if (result == idList.size()) {
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.clientErrorResponse("删除失败");
        }
    }

    @GetMapping("/list")
    public ServerResponse getRecordSubmitExamPlaceInvigilatorList(@RequestParam("taskId") Long taskId) {
        int count = recordSubmitExamPlaceInvigilatorService.countOfRecordSubmitExamPlaceInvigilator(taskId);
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无提交考务人员信息");
        }
        return ServerResponse.successResponse(recordSubmitExamPlaceInvigilatorService.getRecordSubmitExamPlaceInvigilatorList(taskId));
    }

    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecordSubmitExamPlaceInvigilatorByPage(@PathVariable("current") int current,
                                                             @PathVariable("size") int size,
                                                             @RequestParam("taskId") Long taskId) {
        int count = recordSubmitExamPlaceInvigilatorService.countOfRecordSubmitExamPlaceInvigilator(taskId);
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无提交考务人员信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordSubmitExamPlaceInvigilatorService.getRecordSubmitExamPlaceInvigilatorByPage(current, size, taskId));
        return ServerResponse.successResponse(map);
    }

    @PutMapping("/review")
    public ServerResponse reviewRecordSubmitExamPlaceInvigilator(@RequestBody RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator) {
        int result = recordSubmitExamPlaceInvigilatorService.reviewRecordExamPlaceInvigilatorById(recordSubmitExamPlaceInvigilator);
        if (result == 1) {
            return ServerResponse.successResponse("审核成功");
        } else {
            return ServerResponse.serverErrorResponse("审核失败");
        }
    }

    @PutMapping("/reviewBatch")
    public ServerResponse reviewBatchRecordSubmitExamPlaceInvigilator(@RequestBody List<RecordSubmitExamPlaceInvigilator> recordSubmitExamPlaceInvigilatorList) {
        int result = recordSubmitExamPlaceInvigilatorList.stream().mapToInt(e -> recordSubmitExamPlaceInvigilatorService.reviewRecordExamPlaceInvigilatorById(e)).sum();
        if (result == recordSubmitExamPlaceInvigilatorList.size()) {
            return ServerResponse.successResponse("审核成功");
        } else {
            return ServerResponse.serverErrorResponse("审核失败");
        }
    }

    @GetMapping("/result")
    public ServerResponse getRecordSubmitExamPlaceInvigilatorResult(HttpServletRequest request) {
        /* 解析Token */
        DecodedJWT decodedJWT = JWT.decode(request.getHeader("Authorization"));
        Long id = decodedJWT.getClaim("id").asLong();
        return ServerResponse.successResponse(recordSubmitExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorResult(id));
    }

}
