package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.dto.RecordExamDTO;
import top.parak.examarrangementsystem.entity.RecordExam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceService <p>
 * <p> Description: 考试记录业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface RecordExamService {

    /**
     * <p>添加考试</p>
     * @param recordExam
     * @return
     */
    int saveRecordExam(RecordExam recordExam);

    /**
     * <p>获取考试数量</p>
     * @return
     */
    int countOfRecordExam();

    /**
     * <p>根据编号获取考试</p>
     * @param serialNumber
     * @return
     */
    RecordExam getRecordExamBySerialNumber(String serialNumber);

    /**
     * <p>根据ID获取考试</p>
     * @param id
     * @return
     */
    RecordExamDTO getRecordExamById(Long id);

    /**
     * <p>获取所有考试</p>
     * @return
     */
    List<RecordExamDTO> getRecordExamList();

    /**
     * <p>分页查询考试</p>
     * @return
     */
    List<RecordExamDTO> getRecordExamByPage(int current, int size);

    /**
     * <p>修改考试信息</p>
     * @param recordExam
     * @return
     */
    int updateRecordExamById(RecordExam recordExam);

    /**
     * <p>删除考试信息</p>
     * @param id
     * @return
     */
    int deleteRecordExamById(Long id);

}
