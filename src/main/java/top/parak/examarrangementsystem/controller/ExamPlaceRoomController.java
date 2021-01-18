package top.parak.examarrangementsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.dto.ExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceRoom;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.ExamPlaceRoomService;
import top.parak.examarrangementsystem.util.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: ExamPlaceRoomController <p>
 * <p> Description: 考场控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api("考点考场")
@Slf4j
@RestController
@RequestMapping("/api/examPlaceRoom")
public class ExamPlaceRoomController {

    @Autowired
    private ExamPlaceRoomService examPlaceRoomService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    /**
     * <p>新增考场</p>
     * @param examPlaceRoomJson
     * @return
     */
    @ApiOperation(value = "新增考场", notes = "需要考场的名称")
    @ApiImplicitParam(name = "examPlaceRoomJson", value = "examPlaceRoomJson", required = true, dataType = "String")
    @PostMapping("/save")
    public ServerResponse saveExamPlaceRoom(@RequestBody String examPlaceRoomJson, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
        Long id = decodedJWT.getClaim("id").asLong();
        ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(id);
        /* 解析json */
        try {
            JSONObject jsonObject = JSON.parseObject(examPlaceRoomJson);
            String name = jsonObject.getString("name");
            Integer examRoomSerialNumber = jsonObject.getInteger("examRoomSerialNumber");
            /* 校验名称长度 */
            if (name.length() > 30) {
                return ServerResponse.clientErrorResponse("考场名称过长");
            }
            int result = examPlaceRoomService.saveExamPlaceRoom(name, examRoomSerialNumber, examPlaceAdminInMySQL.getExamPlaceId());
            if (result == 1) {
                log.info("[{}] 添加考场 => [{}]", examPlaceAdminInMySQL.getExamPlaceName(), name);
                return ServerResponse.successResponse("添加成功");
            } else {
                return ServerResponse.serverErrorResponse("添加失败");
            }
        } catch (JSONException e) {
            return ServerResponse.clientErrorResponse(e.getMessage());
        }
    }

    /**
     * <p>根据ID查询考场</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询考场", notes = "需要考场ID")
    @GetMapping("/get")
    public ServerResponse getExamPlaceRoomById(@RequestParam("id") Long id) {
        ExamPlaceRoomDTO examPlaceRoomInMySQL = examPlaceRoomService.getExamPlaceRoomById(id);
        if (!ObjectUtils.isEmpty(examPlaceRoomInMySQL)) {
            return ServerResponse.successResponse(examPlaceRoomInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>获取考点所有考场</p>
     * @param request
     * @return
     */
    @ApiOperation(value = "获取考点所有考场", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getExamPlaceRoomListByExamPlaceId(HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Long examPlaceId = examPlaceAdminService.getExamPlaceAdminById(id).getExamPlaceId();
        /* 没有数据 */
        if (examPlaceRoomService.countOfExamPlaceRoom(examPlaceId) == 0) {
            return ServerResponse.serverErrorResponse("该考点暂未设置考场");
        }
        return ServerResponse.successResponse(examPlaceRoomService.getExamPlaceRoomByExamPlaceId(examPlaceId));
    }

    /**
     * <p>根据考点ID分页查询考场</p>
     * @param current
     * @param size
     * @param request
     * @return
     */
    @ApiOperation(value = "分页查询考点考场", notes = "需要分页的页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getExamPlaceRoomListByExamPlaceIdByPage(@PathVariable("current") int current,
                                                                  @PathVariable("size") int size,
                                                                  HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Long examPlaceId = examPlaceAdminService.getExamPlaceAdminById(id).getExamPlaceId();
        int count = examPlaceRoomService.countOfExamPlaceRoom(examPlaceId);
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("该考点暂未设置考场");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", examPlaceRoomService.getExamPlaceRoomByExamPlaceIdByPage(examPlaceId, current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>修改考场信息</p>
     * @param examPlaceRoom
     * @return
     */
    @ApiOperation(value = "修改考场信息", notes = "需要考场的ID和修改后的名称")
    @ApiImplicitParam(name = "examPlaceRoomJson", value = "examPlaceRoomJson", required = true, dataType = "String")
    @PutMapping("/update")
    public ServerResponse updateExamPlaceRoomById(@RequestBody ExamPlaceRoom examPlaceRoom) {
        Long id = examPlaceRoom.getId();
        String name = examPlaceRoom.getName();
        /* 校验考场存在性 */
        ExamPlaceRoomDTO examPlaceRoomInMySQL = examPlaceRoomService.getExamPlaceRoomById(id);
        if (ObjectUtils.isEmpty(examPlaceRoomInMySQL)) {
            return ServerResponse.clientErrorResponse("无效的考场ID");
        }
        /* 校验名称长度 */
        if (name.length() > 30) {
            return ServerResponse.clientErrorResponse("考场名称过长");
        }
        int result = examPlaceRoomService.updateExamPlaceRoomById(examPlaceRoom);
        if (result == 1) {
            log.info("[{}] 修改考场 => [{}]", examPlaceRoomInMySQL.getExamPlaceName(), name);
            return ServerResponse.successResponse("修改成功");
        } else {
            return ServerResponse.serverErrorResponse("修改失败");
        }
    }

    /**
     * <p>删除单个考场</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个考场", notes = "需要考场的ID")
    @DeleteMapping("/delete")
    public ServerResponse deleteExamPlaceRoomById(@RequestParam("id") Long id) {
        /* 校验存在性 */
        ExamPlaceRoomDTO examPlaceRoomInMySQL = examPlaceRoomService.getExamPlaceRoomById(id);
        if (ObjectUtils.isEmpty(examPlaceRoomInMySQL)) {
            return ServerResponse.clientErrorResponse("无效的考场ID");
        }
        int result = examPlaceRoomService.deleteExamPlaceRoomById(id);
        if (result == 1) {
            log.info("[{}] 删除考场 => [{}]", examPlaceRoomInMySQL.getExamPlaceName(), examPlaceRoomInMySQL.getName());
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.serverErrorResponse("删除失败");
        }
    }

    /**
     * <p>批量删除考场</p>
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量删除考场", notes = "需要考场ID的数组")
    @ApiImplicitParam(name = "idList", value = "idList", required = true, dataType = "List")
    @PostMapping("/remove")
    public ServerResponse deleteBatchExamPlaceRoomById(@RequestBody List<Long> idList) {
        if (idList.size() == 0) {
            return ServerResponse.clientErrorResponse("参数为空");
        }
        int result = examPlaceRoomService.deleteBatchExamPlaceRoomById(idList);
        return result >= 1 ? ServerResponse.successResponse("删除成功") :  ServerResponse.serverErrorResponse("删除失败");
    }

}

