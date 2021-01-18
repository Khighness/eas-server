package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordExamPlaceInvigilatorDTO <p>
 * <p> Description: 参加监考考务人员提交记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Data
public class RecordExamPlaceInvigilatorDTO implements Serializable {

    private static final long serialVersionUID = 7263259614623338518L;

    private Long id;

    private String serialNumber;

    private String name;

    private String gender;

    private String birth;

    private String phone;

    private String identityNumber;

    private String photo;

    private String teachingGrade;

    private Integer invigilateExperience;

    private String remark;

    private Boolean reviewed;

    private Boolean passed;

    private String reason;

    private String gmtCreate;

    private String gmtUpdate;

}
