package top.parak.examarrangementsystem.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.RecordTaskExamPlaceDTO;
import top.parak.examarrangementsystem.entity.RecordTaskExamPlace;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.RecordTaskExamPlaceService;
import top.parak.examarrangementsystem.util.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecordTaskExamPlaceController <p>
 * <p> Description: 考点任务记录控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Api("考点任务记录")
@Slf4j
@RestController
@RequestMapping("/api/recordTaskExamPlace")
public class RecordTaskExamPlaceController {

    @Autowired
    private RecordTaskExamPlaceService recordTaskExamPlaceService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    /**
     * <p>新增考点任务</p>
     * @param recordTaskExamPlace
     * @return
     */
    @ApiOperation(value = "新增考点任务", notes = "需要考点任务")
    @ApiImplicitParam(name = "recordTaskExamPlace", value = "考点任务实体", required = true, dataType = "RecordTaskExamPlace")
    @PostMapping("/save")
    public ServerResponse saveRecordTaskExamPlace(@RequestBody RecordTaskExamPlace recordTaskExamPlace, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
//        if (roleId != 2) {
//            return DataResponse.clientErrorResponse("没有权限");
//        }
        /* 校验 */
        Integer total = recordTaskExamPlace.getInvigilatorNumber();
        Integer main = recordTaskExamPlace.getMainInvigilatorNumber();
        Integer male = recordTaskExamPlace.getMaleInvigilatorNumber();
        Integer female = recordTaskExamPlace.getFemaleInvigilatorNumber();
        if (main * 3 < total) {
            return ServerResponse.clientErrorResponse("主监考员数量不得少于总数量的1/3");
        }
        if (male * 3 < total) {
            return ServerResponse.clientErrorResponse("男监考员数量不得少于总数量的1/3");
        }
        if (female * 3 < total) {
            return ServerResponse.clientErrorResponse("女监考员数量不得少于总数量的1/3");
        }
        int result = recordTaskExamPlaceService.saveRecordTaskExamPlace(recordTaskExamPlace);
        if (result == 1) {
            return ServerResponse.successResponse("发布成功");
        } else {
            return ServerResponse.clientErrorResponse("发布失败");
        }
    }

    /**
     * <p>根据ID获取考点任务</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取考点任务", notes = "需要考点任务ID")
    @GetMapping("/getById")
    public ServerResponse getRecordTaskExamPlaceById(@RequestParam("id") Long id) {
        RecordTaskExamPlaceDTO recordTaskExamPlaceInMySQL = recordTaskExamPlaceService.getRecordTaskExamPlaceById(id);
        if (!ObjectUtils.isEmpty(recordTaskExamPlaceInMySQL)) {
            return ServerResponse.successResponse(recordTaskExamPlaceInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>根据考试ID获取考点任务</p>
     * @param recordExamId
     * @param request
     * @return
     */
    @ApiOperation(value = "根据考试ID获取考点任务", notes = "需要考试ID")
    @GetMapping("/getByExamId")
    public ServerResponse getRecordTaskExamPlaceByExamPlaceIdAndRecordExamId(@RequestParam("examId") Long recordExamId, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Long examPlaceId = examPlaceAdminService.getExamPlaceAdminById(id).getExamPlaceId();
        RecordTaskExamPlaceDTO recordTaskExamPlaceInMySQL = recordTaskExamPlaceService.getRecordTaskExamPlaceByExamPlaceIdAndRecordExamId(examPlaceId, recordExamId);
        if (!ObjectUtils.isEmpty(recordTaskExamPlaceInMySQL)) {
            return ServerResponse.successResponse(recordTaskExamPlaceInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>分页查询考点任务</p>
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询考点任务", notes = "需要分页页码和页面大小")
    @GetMapping("/getAll/{current}/{size}")
    public ServerResponse getRecordTaskExamPlaceByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = recordTaskExamPlaceService.countOfRecordTaskExamPlace();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考点任务信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordTaskExamPlaceService.getRecordTaskExamPlaceByPage(current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>根据考试ID分页查询考点任务</p>
     * @param recordExamId
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "根据考试ID分页查询考点任务", notes = "需要考试ID、分页页码和页面大小")
    @GetMapping("/getByExamId/{current}/{size}")
    public ServerResponse getRecordTaskExamPlaceByRecordExamIdByPage(@RequestParam("examId") Long recordExamId,
                                                                     @PathVariable("current") int current,
                                                                     @PathVariable("size") int size) {
        int count = recordTaskExamPlaceService.countOfRecordTaskExamPlaceWithRecordExamId(recordExamId);
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考点任务信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordTaskExamPlaceService.getRecordTaskExamPlaceByRecordExamIdByPage(recordExamId, current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>修改考点任务</p>
     * @param recordTaskExamPlace
     * @return
     */
    @ApiOperation(value = "修改考点任务", notes = "需要考点任务修改后的信息")
    @ApiImplicitParam(name = "recordTaskExamPlace", value = "考点任务实体", required = true, dataType = "RecordTaskExamPlace")
    @PutMapping("/update")
    public ServerResponse updateRecordTaskExamPlace(@RequestBody RecordTaskExamPlace recordTaskExamPlace) {
        int result = recordTaskExamPlaceService.updateRecordTaskExamPlaceById(recordTaskExamPlace);
        if (result == 1) {
            return ServerResponse.successResponse("修改成功");
        } else {
            return ServerResponse.successResponse("修改失败");
        }
    }

    /**
     * <p>删除考点任务</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "删除考点任务", notes = "需要考点任务的ID")
    @DeleteMapping("/delete")
    public ServerResponse deleteRecordTaskExamPlace(@RequestParam("id") Long id) {
        int result = recordTaskExamPlaceService.deleteRecordTaskExamPlaceById(id);
        if (result == 1) {
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.successResponse("删除失败");
        }
    }

    /**
     * <p>已读考点任务</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "修改考点任务", notes = "需要考点任务的ID")
    @ApiImplicitParam(name = "recordTaskExamPlace", value = "考点任务实体", required = true, dataType = "RecordTaskExamPlace")
    @PatchMapping("/read")
    public ServerResponse readRecordTaskExamPlace(@RequestParam("id") Long id) {
        int result = recordTaskExamPlaceService.updateRecordTaskExamPlaceById(id);
        if (result == 1) {
            return ServerResponse.successResponse("修改成功");
        } else {
            return ServerResponse.successResponse("修改失败");
        }
    }

}
