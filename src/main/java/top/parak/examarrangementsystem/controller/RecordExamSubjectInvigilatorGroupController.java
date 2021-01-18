package top.parak.examarrangementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceRoomDTO;
import top.parak.examarrangementsystem.entity.RecordExamSubjectInvigilatorGroup;
import top.parak.examarrangementsystem.entity.RecordSubmitExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecordTaskExamInvigilator;
import top.parak.examarrangementsystem.service.RecordExamSubjectInvigilatorGroupService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.service.RecordSubmitExamPlaceRoomService;
import top.parak.examarrangementsystem.service.RecordTaskExamInvigilatorService;

import java.util.List;

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
@RequestMapping("/api/recordExamSubjectInvigilatorGroup")
public class RecordExamSubjectInvigilatorGroupController {

    @Autowired
    private RecordExamSubjectInvigilatorGroupService recordExamSubjectInvigilatorGroupService;

    @GetMapping("/get/{current}/{size}")
    public ServerResponse generateExamSubjectInvigilatorGroup(@RequestParam("subjectId") Long subjectId, @PathVariable("current") int current, @PathVariable("size") int size) {
        return ServerResponse.successResponse(recordExamSubjectInvigilatorGroupService.getRecordExamSubjectInvigilatorGroup(subjectId, current, size));
    }

}
