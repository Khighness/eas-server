package top.parak.examarrangementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.service.RecordHistoricalViolationService;

import java.util.HashMap;
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

@RestController
@RequestMapping("/api/recordHistoricalViolation")
public class RecordHistoricalViolationController {

    @Autowired
    private RecordHistoricalViolationService recordHistoricalViolationService;

    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecordHistoricalViolationByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = recordHistoricalViolationService.countOfRecordHistoricalViolation();
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无历史违规信息");
        }
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", recordHistoricalViolationService.getRecordHistoricalViolationByPage(current, size));
        return ServerResponse.successResponse(map);
    }

}

