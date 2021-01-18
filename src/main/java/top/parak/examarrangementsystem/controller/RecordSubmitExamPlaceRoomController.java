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
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceRoom;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceRoomService;
import top.parak.examarrangementsystem.service.RecordTaskExamPlaceService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KHighness
 * @since 2021-01-13
 */

@Api("考点提交考场记录")
@Slf4j
@RestController
@RequestMapping("/api/recordSubmitExamPlaceRoom")
public class RecordSubmitExamPlaceRoomController {

    @Autowired
    private RecordSubmitExamPlaceRoomService recordSubmitExamPlaceRoomService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private RecordTaskExamPlaceService recordTaskExamPlaceService;

    @PostMapping("/save")
    public ServerResponse saveRecordSubmitExamPlaceRoom(@RequestBody RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom,
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
        recordSubmitExamPlaceRoom.setRecordTaskExamPlaceId(taskId);
        int result = recordSubmitExamPlaceRoomService.saveRecordSubmitExamPlaceRoom(recordSubmitExamPlaceRoom);
        if (result == 1) {
            return ServerResponse.successResponse("提交成功");
        } else {
            return ServerResponse.serverErrorResponse("提交失败");
        }
    }

    @PostMapping("/saveBatch")
    public ServerResponse saveBatchRecordSubmitExamPlaceRoom(@RequestBody List<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomList,
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
        recordSubmitExamPlaceRoomList.stream().forEach(e -> e.setRecordTaskExamPlaceId(taskId));
        int result = recordSubmitExamPlaceRoomList.stream().mapToInt(e -> recordSubmitExamPlaceRoomService.saveRecordSubmitExamPlaceRoom(e)).sum();
        if (result == recordSubmitExamPlaceRoomList.size()) {
            return ServerResponse.successResponse("提交成功");
        } else {
            return ServerResponse.serverErrorResponse("提交失败");
        }
    }

    @DeleteMapping("/delete")
    public ServerResponse deleteRecordSubmitExamPlaceRoom(@RequestParam("id") Long id) {
        int result = recordSubmitExamPlaceRoomService.deleteRecordSubmitExamPlaceRoomById(id);
        if (result == 1) {
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.serverErrorResponse("删除失败");
        }
    }

    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatchRecordSubmitExamPlaceRoom(@RequestBody List<Long> idList) {
        int result = recordSubmitExamPlaceRoomService.deleteBatchRecordSubmitExamPlaceRoomById(idList);
        if (result == idList.size()) {
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.clientErrorResponse("删除失败");
        }
    }

    @GetMapping("/list")
    public ServerResponse getRecordSubmitExamPlaceRoomList(@RequestParam("taskId") Long taskId) {
        int count = recordSubmitExamPlaceRoomService.countOfRecordSubmitExamPlaceRoom(taskId);
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无提交考场信息");
        }
        return ServerResponse.successResponse(recordSubmitExamPlaceRoomService.getRecordSubmitExamPlaceRoomList(taskId));
    }

    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecordSubmitExamPlaceRoomByPage(@PathVariable("current") int current,
                                                             @PathVariable("size") int size,
                                                             @RequestParam("taskId") Long taskId) {
        int count = recordSubmitExamPlaceRoomService.countOfRecordSubmitExamPlaceRoom(taskId);
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无提交考场信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordSubmitExamPlaceRoomService.getRecordSubmitExamPlaceRoomByPage(current, size, taskId));
        return ServerResponse.successResponse(map);
    }

    @PutMapping("/review")
    public ServerResponse reviewRecordSubmitExamPlaceRoom(@RequestBody RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom) {
        int result = recordSubmitExamPlaceRoomService.reviewRecordSubmitExamPlaceRoomById(recordSubmitExamPlaceRoom);
        if (result == 1) {
            return ServerResponse.successResponse("审核成功");
        } else {
            return ServerResponse.serverErrorResponse("审核失败");
        }
    }

    @PutMapping("/reviewBatch")
    public ServerResponse reviewBatchRecordSubmitExamPlaceRoom(@RequestBody List<RecordSubmitExamPlaceRoom> recordSubmitExamPlaceRoomList) {
        int result = recordSubmitExamPlaceRoomList.stream().mapToInt(e -> recordSubmitExamPlaceRoomService.reviewRecordSubmitExamPlaceRoomById(e)).sum();
        if (result == recordSubmitExamPlaceRoomList.size()) {
            return ServerResponse.successResponse("审核成功");
        } else {
            return ServerResponse.serverErrorResponse("审核失败");
        }
    }

}
