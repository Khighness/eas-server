package top.parak.examarrangementsystem.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.RecordExamDTO;
import top.parak.examarrangementsystem.entity.RecordExam;
import top.parak.examarrangementsystem.entity.RecordExamSubject;
import top.parak.examarrangementsystem.service.RecordExamService;
import top.parak.examarrangementsystem.service.RecordExamSubjectService;
import top.parak.examarrangementsystem.service.RecordTaskExamPlaceService;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: RecordExamController <p>
 * <p> Description: 考试记录控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Api("考试记录")
@Slf4j
@RestController
@RequestMapping("/api/recordExam")
public class RecordExamController {

    @Autowired
    private RecordExamService recordExamService;

    @Autowired
    private RecordExamSubjectService recordExamSubjectService;

    @Autowired
    private RecordTaskExamPlaceService recordTaskExamPlaceService;

    /**
     * <p>新增考试</p>
     * @param recordExamJson
     * @return
     */
    @ApiOperation(value = "新增考试", notes = "需要考试信息和考试科目信息")
    @ApiImplicitParam(name = "recordExamJson", value = "recordExamJson", required = true, dataType = "String")
    @PostMapping("/save")
    public ServerResponse saveRecordExam(@RequestBody String recordExamJson) {
        /* 解析考试 */
        JSONObject exam = JSONObject.parseObject(recordExamJson);
        String serialNumber = String.valueOf(SnowFlakeIDUtils.nextID());
        RecordExam recordExam = exam.getObject("exam", RecordExam.class);
        /* 设置编号 */
        recordExam.setSerialNumber(serialNumber);
        recordExamService.saveRecordExam(recordExam);
        /* 查询ID */
        Long recordExamId = recordExamService.getRecordExamBySerialNumber(serialNumber).getId();
        /* 解析科目 */
        List<RecordExamSubject> examSubjectList = new LinkedList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Arrays.asList("chinese", "mathematics", "english", "comprehensiveLiberalArts", "comprehensiveScience").stream().forEach(
            name -> {
                JSONObject json = exam.getJSONObject(name);
                try {
                    RecordExamSubject recordExamSubject = RecordExamSubject.builder()
                            .recordExamId(recordExamId)
                            .name(json.getString("name"))
                            .startTime(simpleDateFormat.parse(json.getString("date") + " " + json.getString("startTime")))
                            .endTime(simpleDateFormat.parse(json.getString("date") + " " + json.getString("endTime")))
                            .build();
                    examSubjectList.add(recordExamSubject);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        );
        int total = examSubjectList.stream().mapToInt(s -> recordExamSubjectService.saveRecordExamSubject(s)).sum();
        if (total == 5) {
            log.info("添加考试成功 => [{}]", recordExam.getName());
            return ServerResponse.successResponse("添加成功");
        } else {
            return ServerResponse.clientErrorResponse("添加失败");
        }
    }

    /**
     * <p>根据ID获取考试</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取考试", notes = "需要考试ID")
    @GetMapping("/get")
    public ServerResponse getRecordExamById(@RequestParam("id") Long id) {
        RecordExamDTO recordExamInMySQL = recordExamService.getRecordExamById(id);
        if (!ObjectUtils.isEmpty(recordExamInMySQL)) {
            return ServerResponse.successResponse(recordExamInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }

    }

    /**
     * <p>获取所有考试</p>
     * @return
     */
    @ApiOperation(value = "获取所有考试", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getRecordExamList() {
        int count = recordExamService.countOfRecordExam();
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考试信息");
        }
        return ServerResponse.successResponse(recordExamService.getRecordExamList());

    }

    /**
     * <p>分页查询考试</p>
     * @return
     */
    @ApiOperation(value = "分页查询考试", notes = "需要分页页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecordExamByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = recordExamService.countOfRecordExam();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考试信息");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordExamService.getRecordExamByPage(current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>修改考试信息</p>
     * @param recordExamJson
     * @return
     */
    @ApiOperation(value = "修改考试信息", notes = "需要修改后考试的信息")
    @PutMapping("/update")
    public ServerResponse updateRecordExamById(@RequestBody String recordExamJson) {
        /* 解析考试 */
        JSONObject exam = JSONObject.parseObject(recordExamJson);
        RecordExam recordExam = exam.getObject("exam", RecordExam.class);
        Long recordExamId = recordExam.getId();
        recordExamService.updateRecordExamById(recordExam);
        /* 解析科目 */
        List<RecordExamSubject> examSubjectList = new LinkedList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Arrays.asList("chinese", "mathematics", "english", "comprehensiveLiberalArts", "comprehensiveScience").stream().forEach(
            subject -> {
                JSONObject json = exam.getJSONObject(subject);
                String name = json.getString("name");
                try {
                    RecordExamSubject recordExamSubjectIMySQL = recordExamSubjectService.getRecordExamSubjectRecordExamIdAndName(recordExamId, name);
                    RecordExamSubject recordExamSubject = RecordExamSubject.builder()
                            .id(recordExamSubjectIMySQL.getId())
                            .recordExamId(recordExamId)
                            .name(name)
                            .startTime(simpleDateFormat.parse(json.getString("date") + " " + json.getString("startTime")))
                            .endTime(simpleDateFormat.parse(json.getString("date") + " " + json.getString("endTime")))
                            .build();
                    examSubjectList.add(recordExamSubject);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        );
        int total = examSubjectList.stream().mapToInt(s -> recordExamSubjectService.updateRecordExamSubject(s)).sum();
        if (total == 5) {
            log.info("修改考试成功 => [{}]", recordExam.getName());
            return ServerResponse.successResponse("修改成功");
        } else {
            return ServerResponse.clientErrorResponse("修改失败");
        }
    }

    /**
     * <p>删除考试信息</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "删除考试信息", notes = "需要考试ID")
    @DeleteMapping("/delete")
    public ServerResponse deleteRecordExamById(@RequestParam("id") Long id) {
        RecordExamDTO recordExamIMySQL = recordExamService.getRecordExamById(id);
        if (ObjectUtils.isEmpty(recordExamIMySQL)) {
            return ServerResponse.clientErrorResponse("考试ID非法");
        }
        if (!ObjectUtils.isEmpty(recordTaskExamPlaceService.getRecordTaskExamPlaceByRecordExamId(id))) {
            return ServerResponse.clientErrorResponse("请先删除考点任务");
        }
        int total = recordExamService.deleteRecordExamById(id) + recordExamSubjectService.deleteRecordExamSubjectsByRecordExamId(id);
        if (total == 6) {
            return ServerResponse.successResponse("删除成功");
        } else {
            return ServerResponse.serverErrorResponse("删除失败");
        }
    }

}

