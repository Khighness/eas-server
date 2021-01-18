package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordSubmitExamPlaceInvigilatorDTO <p>
 * <p> Description: 考点提交考务人员记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Data
public class RecordSubmitExamPlaceInvigilatorDTO implements Serializable {

    private static final long serialVersionUID = -1832637371817046182L;

    private Long id;

    private Integer invigilatorSerialNumber;

    private String name;

    private String gender;

    private String identityNumber;

    private String photo;

    private String teachingGrade;

    private Integer invigilateExperience;

    private String remark;

    private Boolean main;

    private Boolean reviewed;

    private Boolean passed;

    private String reason;

    private Long recordTaskExamPlaceId;

    private String gmtCreate;

    private String gmtUpdate;

}
