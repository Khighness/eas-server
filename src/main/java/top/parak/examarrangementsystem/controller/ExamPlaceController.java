package top.parak.examarrangementsystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import top.parak.examarrangementsystem.common.ServerResponse;
import top.parak.examarrangementsystem.dto.ExamPlaceDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.service.ExamPlaceService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: ExamPlaceController <p>
 * <p> Description: 考点控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Api("考点")
@Slf4j
@RestController
@RequestMapping("/api/examPlace")
public class ExamPlaceController {

    @Autowired
    private ExamPlaceService examPlaceService;

    /**
     * <p>添加考点信息</p>
     * @param examPlace
     * @return
     */
    @ApiOperation(value = "添加考点信息", notes = "需要考点的名称、描述和位置")
    @ApiImplicitParam(name = "examPlace", value = "examPlace", required = true, dataType = "ExamPlace")
    @PostMapping("/save")
    public ServerResponse saveExamPlace(@RequestBody ExamPlace examPlace) {
        int result = examPlaceService.saveExamPlace(examPlace.getName(), examPlace.getDescription(), examPlace.getPosition());
        if (result == 1) {
            log.info("添加考点 => [{}]", examPlace.getName());
            return ServerResponse.successResponse("添加成功");
        } else {
            return ServerResponse.serverErrorResponse("添加失败");
        }
    }

    /**
     * <p>根据ID查询考点</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询考点", notes = "需要考点的ID")
    @GetMapping("/get")
    public ServerResponse getExamPlaceById(@RequestParam("id") Long id) {
        ExamPlaceDTO examPlaceByInMySQL = examPlaceService.getExamPlaceById(id);
        if (!ObjectUtils.isEmpty(examPlaceByInMySQL)) {
            return ServerResponse.successResponse(examPlaceByInMySQL);
        } else {
            return ServerResponse.clientErrorResponse("未查询到相关信息");
        }
    }

    /**
     * <p>获取所有考点</p>
     * @return
     */
    @ApiOperation(value = "获取所有考点", notes = "无需参数")
    @GetMapping("/list")
    public ServerResponse getExamPlaceList() {
        return ServerResponse.successResponse(examPlaceService.getExamPlaceList());
    }

    /**
     * <p>分页查询考点</p>
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询考点", notes = "需要分页的页码和页面大小")
    @GetMapping("/get/{current}/{size}")
    public ServerResponse getExamPlaceByPage(@PathVariable("current") int current, @PathVariable("size") int size) {
        int count = examPlaceService.countOfExamPlace();
        /* 没有数据 */
        if (count == 0) {
            return ServerResponse.serverErrorResponse("暂无考点信息");
        }
        /* 超越上限 */
        if (count <= size * (current - 1)) {
            return ServerResponse.clientErrorResponse("给不了这么多嗷");
        }
        /* 封装信息 */
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("page", examPlaceService.getExamPlaceByPage(current, size));
        return ServerResponse.successResponse(map);
    }

    /**
     * <p>修改考点信息</p>
     * @param examPlace
     * @return
     */
    @ApiOperation(value = "修改考点信息", notes = "需要考点信息实体")
    @ApiImplicitParam(name = "examPlace", value = "examPlace", required = true, dataType = "ExamPlace")
    @PutMapping("/update")
    public ServerResponse updateExamPlace(@RequestBody ExamPlace examPlace) {
        int result = examPlaceService.updateExamPlaceById(examPlace);
        return result == 1 ? ServerResponse.successResponse("修改成功") : ServerResponse.serverErrorResponse("修改失败");
    }

    /**
     * <p>删除单个考点</p>
     * @param id
     * @return
     */
    @ApiOperation(value = "修改考点信息", notes = "需要考点的ID")
    @DeleteMapping("/delete")
    public ServerResponse deleteExamPlace(@RequestParam("id") Long id) {
        int result = examPlaceService.deleteExamPlaceById(id);
        return result == 1 ? ServerResponse.successResponse("删除成功") : ServerResponse.serverErrorResponse("删除失败");
    }

}
