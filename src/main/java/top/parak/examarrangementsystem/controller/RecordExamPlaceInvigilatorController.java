package top.parak.examarrangementsystem.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.RecordExamDTO;
import top.parak.examarrangementsystem.dto.RecordExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.RecordExamPlaceInvigilator;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.RecordExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.service.RecordExamService;
import top.parak.examarrangementsystem.util.FileUtils;
import top.parak.examarrangementsystem.util.QiNiuUtils;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecordExamPlaceInvigilatorController <p>
 * <p> Description: 参加监考考务人员提交信息记录控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Api("参加监考考务人员提交记录")
@Slf4j
@RestController
@RequestMapping("/api/recordExamPlaceInvigilator")
public class RecordExamPlaceInvigilatorController {

    @Autowired
    private RecordExamPlaceInvigilatorService recordExamPlaceInvigilatorService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private RecordExamService recordExamService;

    @Autowired
    private QiNiuUtils qiNiuUtils;

    /**
     * <p>考务人员提交信息</p>
     * @param recordExamPlaceInvigilator
     * @return
     */
    @ApiOperation(value = "考务人员提交信息", notes = "需要考务人员提交的信息")
    @ApiImplicitParam(name = "RecordExamPlaceInvigilator", value = "参加监考考务人员提交信息实体", required = true, dataType = "RecordExamPlaceInvigilator")
    @PostMapping("/save")
    public ServerResponse saveRecordExamPlaceInvigilator(@RequestBody RecordExamPlaceInvigilator recordExamPlaceInvigilator, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        Long id = decodedJWT.getClaim("id").asLong();
        if (roleId != 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        recordExamPlaceInvigilator.setExamPlaceInvigilatorId(id);
        /* 校验存在性 */
        RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilatorInMySQL = recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(id, recordExamPlaceInvigilator.getRecordExamId());
        if (!ObjectUtils.isEmpty(recordExamPlaceInvigilatorInMySQL)) {
            return ServerResponse.clientErrorResponse("请勿重复提交");
        }
        /* 插入数据库 */
        int result = recordExamPlaceInvigilatorService.saveRecordExamPlaceInvigilator(recordExamPlaceInvigilator);
        if (result == 1) {
            return ServerResponse.successResponse("提交成功");
        } else {
            return ServerResponse.serverErrorResponse("提交失败");
        }
    }

    /**
     * <p>考务人员上传照片</p>
     * @param multipartFile
     * @param examId
     * @param request
     * @return
     */
    @ApiOperation(value = "考务人员上传照片", notes = "需要监考考务人员近期照片")
    @PostMapping("/photo")
    public ServerResponse updateRecordExamPlaceInvigilatorPhoto(@RequestParam("file") MultipartFile multipartFile,
                                                                @RequestParam("examId") Long examId,
                                                                HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        Long id = decodedJWT.getClaim("id").asLong();
        if (roleId != 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        /* 校验是否已提交监考信息 */
        RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilatorInMySQL = recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(id, examId);
        if (ObjectUtils.isEmpty(recordExamPlaceInvigilatorInMySQL)) {
            return ServerResponse.clientErrorResponse("请先提交个人信息");
        }
        /* 文件校验 */
        String suffix = FileUtils.getFileSuffix(multipartFile.getOriginalFilename());
        String mime = FileUtils.getContentType(multipartFile.getOriginalFilename());
        if (!(suffix.equals(".png") || suffix.equals(".jpg") || suffix.equals(".jpeg"))) {
            return ServerResponse.clientErrorResponse("暂不支持" + suffix + "格式文件，请重新选择文件");
        }
        String photoName = "photo_" + SnowFlakeIDUtils.nextID() + suffix;
        /* 删除原照片 */
        if (!ObjectUtils.isEmpty(recordExamPlaceInvigilatorInMySQL.getPhoto())) {
            qiNiuUtils.removeFile(recordExamPlaceInvigilatorInMySQL.getPhoto());
        }
        /* 更新数据库 */
        int result = recordExamPlaceInvigilatorService.updateRecordExamPlaceInvigilatorPhoto(recordExamPlaceInvigilatorInMySQL.getId(), photoName);
        if (result == 1) {
            try {
                qiNiuUtils.uploadFile(multipartFile.getInputStream(), photoName, mime);
                return ServerResponse.successResponse(photoName);
            } catch (IOException e) {
                log.error(e.getMessage());
                return ServerResponse.serverErrorResponse(e.getMessage());
            }
        } else {
            return ServerResponse.serverErrorResponse("上传照片失败，请稍后再试");
        }
    }

    /**
     * <p>考务人员更新信息</p>
     * @param recordExamPlaceInvigilator
     * @param request
     * @return
     */
    @ApiOperation(value = "考务人员更新信息", notes = "需要考务人员更新后的信息")
    @ApiImplicitParam(name = "RecordExamPlaceInvigilator", value = "参加监考考务人员提交记录实体", required = true, dataType = "RecordExamPlaceInvigilator")
    @PutMapping("/update")
    public ServerResponse updateRecordExamPlaceInvigilatorInfo(@RequestBody RecordExamPlaceInvigilator recordExamPlaceInvigilator, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        Long id = decodedJWT.getClaim("id").asLong();
        if (roleId != 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        /* 校验存在性 */
        RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilatorInMySQL = recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(id, recordExamPlaceInvigilator.getRecordExamId());
        if (ObjectUtils.isEmpty(recordExamPlaceInvigilatorInMySQL)) {
            return ServerResponse.clientErrorResponse("请先提交个人信息");
        }
        /* 更新数据库 */
        recordExamPlaceInvigilator.setId(recordExamPlaceInvigilatorInMySQL.getId());
        int result = recordExamPlaceInvigilatorService.updateRecordExamPlaceInvigilator(recordExamPlaceInvigilator);
        if (result == 1) {
            return ServerResponse.successResponse("更新成功");
        } else {
            return ServerResponse.serverErrorResponse("更新失败");
        }
    }

    /**
     * <p>考务人员查看信息</p>
     * @param examId
     * @param request
     * @return
     */
    @ApiOperation(value = "考务人员查看信息", notes = "需要考试ID")
    @GetMapping("/getByExamId")
    public ServerResponse getRecordExamPlaceInvigilatorInfo(@RequestParam("examId") Long examId, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        Long id = decodedJWT.getClaim("id").asLong();
        if (roleId != 4) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilatorInMySQL = recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(id, examId);
        if (!ObjectUtils.isEmpty(recordExamPlaceInvigilatorInMySQL)) {
            return ServerResponse.successResponse(recordExamPlaceInvigilatorInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("请先提交个人信息");
        }
    }

    /**
     * <p>考务人员查看所有已提交的考试和审核信息</p>
     * @param request
     * @return
     */
    @GetMapping("/getAll")
    public ServerResponse getRecordExamPlaceInvigilatorInfoList(HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Long id = decodedJWT.getClaim("id").asLong();
        List<Map<String, Object>> dataList = new LinkedList<>();
        List<RecordExamDTO> recordExamList = recordExamService.getRecordExamList();
        for (int i = 0; i < recordExamList.size(); i++) {
            RecordExamDTO recordExamDTO = recordExamList.get(i);
            RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilator = recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByExamPlaceInvigilatorIdAndRecordExamId(id, recordExamDTO.getId());
            System.out.println(recordExamPlaceInvigilator);
            if (!ObjectUtils.isEmpty(recordExamPlaceInvigilator)) {
                Map<String, Object> map = new HashMap<>();
                map.put("exam", recordExamDTO);
                map.put("info", recordExamPlaceInvigilator);
                dataList.add(map);
            }
        }
        if (dataList.size() != 0) {
            return ServerResponse.successResponse(dataList);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关审核信息");
        }
    }

    /**
     * <p>审核考务人员信息</p>
     * @param recordExamPlaceInvigilator
     * @param request
     * @return
     */
    @ApiOperation(value = "审核考务人员信息", notes = "需要考务人员提交信息的审核信息")
    @ApiImplicitParam(name = "RecordExamPlaceInvigilator", value = "参加监考考务人员提交记录实体", required = true, dataType = "RecordExamPlaceInvigilator")
    @PutMapping("/review")
    public ServerResponse reviewRecordExamPlaceInvigilatorInfo(@RequestBody RecordExamPlaceInvigilator recordExamPlaceInvigilator, HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        if (roleId != 3) {
            return ServerResponse.clientErrorResponse("没有权限");
        }
        recordExamPlaceInvigilator.setReviewed(true);
        int result = recordExamPlaceInvigilatorService.updateRecordExamPlaceInvigilator(recordExamPlaceInvigilator);
        if (result == 1) {
            return ServerResponse.successResponse("审核成功");
        } else {
            return ServerResponse.serverErrorResponse("审核失败");
        }
    }

    /**
     * <p>根据ID查询考务人员信息</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询考务人员信息", notes = "需要考务人员提交信息的ID")
    @GetMapping("/getById")
    public ServerResponse getRecordExamPlaceInvigilatorById(@RequestParam("id") Long id) {
        RecordExamPlaceInvigilatorDTO recordExamPlaceInvigilatorInMySQL = recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorById(id);
        if (!ObjectUtils.isEmpty(recordExamPlaceInvigilatorInMySQL)) {
            return ServerResponse.successResponse(recordExamPlaceInvigilatorInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>根据考试ID分页查询考试人员信息</p>
     * @param recordExamId
     * @param current
     * @param size
     * @param request
     * @return
     */
    @ApiOperation(value = "根据考试ID分页查询考试人员信息", notes = "需要考试ID、分页页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecordExamPlaceInvigilatorByExamIdByPage(@RequestParam("examId") Long recordExamId,
                                                                      @PathVariable("current") int current,
                                                                      @PathVariable("size") int size,
                                                                      HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Long examPlaceId = examPlaceAdminService.getExamPlaceAdminById(id).getExamPlaceId();
        System.out.println("[ID] = " + examPlaceId);
        int count = recordExamPlaceInvigilatorService.countOfRecordExamPlaceInvigilator(recordExamId, examPlaceId);
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考务人员信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordExamPlaceInvigilatorService.getRecordExamPlaceInvigilatorByRecordExamIdByPage(recordExamId, current, size, examPlaceId));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>根据考试ID分页查询考试人员信息</p>
     * @param recordExamId
     * @param current
     * @param size
     * @param request
     * @return
     */
    @ApiOperation(value = "根据考试ID分页查询考试人员信息", notes = "需要考试ID、分页页码和页面大小")
    @GetMapping("/getPassed/{current}/{size}")
    public ServerResponse getPassedRecordExamPlaceInvigilatorByExamIdByPage(@RequestParam("examId") Long recordExamId,
                                                                            @PathVariable("current") int current,
                                                                            @PathVariable("size") int size,
                                                                            HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Long examPlaceId = examPlaceAdminService.getExamPlaceAdminById(id).getExamPlaceId();
        int count = recordExamPlaceInvigilatorService.countOfPassedRecordExamPlaceInvigilator(recordExamId, examPlaceId);
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考务人员信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordExamPlaceInvigilatorService.getPassedRecordExamPlaceInvigilatorByRecordExamIdByPage(recordExamId, current, size, examPlaceId));
        return ServerResponse.successResponse(map);
    }

}

