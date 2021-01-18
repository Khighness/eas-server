package top.parak.examarrangementsystem.service;

import top.parak.examarrangementsystem.entity.RecordExamSubject;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service </p>
 * <p> FileName: ExamPlaceService <p>
 * <p> Description: 考试科目记录业务层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

public interface RecordExamSubjectService {

    /**
     * <p>新增考试科目</p>
     * @param recordExamSubject
     * @return
     */
    int saveRecordExamSubject(RecordExamSubject recordExamSubject);

    /**
     * <p>修改考试科目</p>
     * @param recordExamSubject
     * @return
     */
    int updateRecordExamSubject(RecordExamSubject recordExamSubject);

    /**
     * <p>删除一次考试的所有科目</p>
     * @param recordExamId
     * @return
     */
    int deleteRecordExamSubjectsByRecordExamId(Long recordExamId);

    /**
     * <p>根据考试ID和科目名称查询考试科目</p>
     * @param recordExamId
     * @param name
     * @return
     */
    RecordExamSubject getRecordExamSubjectRecordExamIdAndName(Long recordExamId, String name);

}
