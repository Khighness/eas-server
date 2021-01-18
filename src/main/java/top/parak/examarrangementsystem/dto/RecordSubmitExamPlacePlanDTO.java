package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordSubmitExamPlacePlanDTO <p>
 * <p> Description: 考点提交平面图记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/12
 */

@Data
public class RecordSubmitExamPlacePlanDTO implements Serializable {

    private static final long serialVersionUID = 8081241192795425773L;

    private Long id;

    private String serialNumber;

    private String examPlacePlan;

    private String remark;

    private Boolean reviewed;

    private Boolean passed;

    private String reason;

    private Long recordTaskExamPlaceId;

    private String gmtCreate;

    private String gmtUpdate;

}
