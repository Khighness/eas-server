package top.parak.examarrangementsystem.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlacePlanDTO;
import top.parak.examarrangementsystem.dto.RecordTaskExamPlaceDTO;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlacePlan;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlacePlanService;
import top.parak.examarrangementsystem.service.RecordTaskExamPlaceService;
import top.parak.examarrangementsystem.util.FileUtils;
import top.parak.examarrangementsystem.util.QiNiuUtils;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecordSubmitExamPlacePlanController <p>
 * <p> Description: 考点提交平面图记录控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/8
 */

@Api("考点提交平面图记录")
@Slf4j
@RestController
@RequestMapping("/api/recordSubmitExamPlacePlan")
public class RecordSubmitExamPlacePlanController {

    @Autowired
    private RecordSubmitExamPlacePlanService recordSubmitExamPlacePlanService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private RecordTaskExamPlaceService recordTaskExamPlaceService;

    @Autowired
    private QiNiuUtils qiNiuUtils;

    /**
     * <p>提交考点平面图</p>
     * @param multipartFile
     * @param examId
     * @param request
     * @return
     */
    @ApiOperation(value = "提交考点平面图", notes = "需要考点平面图文件和考点任务ID")
    @PostMapping("/upload")
    public ServerResponse uploadExamPlacePlan(@RequestParam("file") MultipartFile multipartFile,
                                              @RequestParam("taskId") Long taskId,
                                              HttpServletRequest request) {
        /* 文件校验 */
        String suffix = FileUtils.getFileSuffix(multipartFile.getOriginalFilename());
        String mime = FileUtils.getContentType(multipartFile.getOriginalFilename());
        if (!(suffix.equals(".png") || suffix.equals(".jpg") || suffix.equals(".jpeg"))) {
            return ServerResponse.clientErrorResponse("暂不支持" + suffix + "格式文件，请重新选择文件");
        }
        Long serialNumber = SnowFlakeIDUtils.nextID();
        String planName = "examPlace_" + serialNumber + suffix;
        /* 解析Token */
        DecodedJWT decodedJWT = JWT.decode(request.getHeader("Authorization"));
        Long id = decodedJWT.getClaim("id").asLong();
        /* 校验是否合法 */
        ExamPlaceAdminDTO examPlaceAdmin = examPlaceAdminService.getExamPlaceAdminById(id);
        RecordTaskExamPlaceDTO recordTaskExamPlace = recordTaskExamPlaceService.getRecordTaskExamPlaceById(taskId);
        if (examPlaceAdmin.getExamPlaceId() != recordTaskExamPlace.getExamPlaceId()) {
            return ServerResponse.clientErrorResponse("请勿提交其他考点的任务");
        }
        /* 校验存在性 */
        RecordSubmitExamPlacePlanDTO recordSubmitExamPlacePlan = recordSubmitExamPlacePlanService.getRecordSubmitExamPlacePlanByTaskId(taskId);
        /* 不存在则新增*/
        if (ObjectUtils.isEmpty(recordSubmitExamPlacePlan)) {
            try {
                qiNiuUtils.uploadFile(multipartFile.getInputStream(), planName, mime);
            } catch (IOException e) {
                log.error(e.getMessage());
                return ServerResponse.serverErrorResponse(e.getMessage());
            }
            int result = recordSubmitExamPlacePlanService.saveRecordSubmitExamPlacePlan(serialNumber, taskId, planName);
            if (result == 1) {
                return ServerResponse.successResponse(planName);
            } else {
                return ServerResponse.serverErrorResponse("上传失败");
            }
        }
        /* 存在则更新*/
        else {
            try {
                qiNiuUtils.removeFile(recordSubmitExamPlacePlan.getExamPlacePlan());
                qiNiuUtils.uploadFile(multipartFile.getInputStream(), planName, mime);
            } catch (IOException e) {
                return ServerResponse.serverErrorResponse(e.getMessage());
            }
            int result = recordSubmitExamPlacePlanService.updateRecordSubmitExamPlacePlan(recordSubmitExamPlacePlan.getId(), serialNumber, planName);
            if (result == 1) {
                return ServerResponse.successResponse(planName);
            } else {
                return ServerResponse.serverErrorResponse("上传失败");
            }
        }
    }

    /**
     * <p>获取考点平面图</p>
     * @param taskId
     * @return
     */
    @ApiOperation(value = "获取考点平面图", notes = "需要考点任务ID")
    @GetMapping("/get")
    public ServerResponse getExamPlacePlan(@RequestParam("taskId") Long taskId) {
        RecordSubmitExamPlacePlanDTO recordSubmitExamPlacePlanInMySQL = recordSubmitExamPlacePlanService.getRecordSubmitExamPlacePlanByTaskId(taskId);
        if (!ObjectUtils.isEmpty(recordSubmitExamPlacePlanInMySQL)) {
            return ServerResponse.successResponse(recordSubmitExamPlacePlanInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>审核考点平面图</p>
     * @param recordSubmitExamPlacePlan
     * @return
     */
    @ApiOperation(value = "审核考点平面图", notes = "需要考点平面图ID和审核结果")
    @PutMapping("/review")
    public ServerResponse reviewExamPlacePlan(@RequestBody RecordSubmitExamPlacePlan recordSubmitExamPlacePlan) {
        int result = recordSubmitExamPlacePlanService.reviewRecordSubmitExamPlacePlan(recordSubmitExamPlacePlan);
        if (result == 1) {
            return ServerResponse.successResponse("审核成功");
        } else {
            return ServerResponse.serverErrorResponse("审核失败");
        }
    }

}
