package top.parak.examarrangementsystem.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.entity.RecordTaskExamInvigilator;
import top.parak.examarrangementsystem.service.RecordTaskExamInvigilatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KHighness
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/api/recordTaskExamInvigilator")
public class RecordTaskExamInvigilatorController {

    @Autowired
    private RecordTaskExamInvigilatorService recordTaskExamInvigilatorService;

    @PatchMapping("/update")
    public ServerResponse updateRecordTaskExamInvigilatorById(@RequestBody List<Long> idList) {
        int result = idList.stream().mapToInt(e -> recordTaskExamInvigilatorService.updateRecordTaskExamInvigilator(e)).sum();
        if (result == idList.size()) {
            return ServerResponse.successResponse("更新成功");
        } else {
            return ServerResponse.clientErrorResponse("更新失败");
        }
    }

    @GetMapping("/get/{current}/{size}")
    public ServerResponse getRecordTaskExamInvigilatorByPage(@PathVariable("current") int current,
                                                             @PathVariable("size") int size,
                                                             HttpServletRequest request) {
        /* 解析token */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Long id = decodedJWT.getClaim("id").asLong();
        return ServerResponse.successResponse(recordTaskExamInvigilatorService.getRecordTaskExamInvigilatorByPage(id, current, size));
    }

}

