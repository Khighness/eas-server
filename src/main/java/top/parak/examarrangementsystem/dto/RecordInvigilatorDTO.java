package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordInvigilatorDTO <p>
 * <p> Description: 考务人员记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Data
public class RecordInvigilatorDTO implements Serializable {

    private static final long serialVersionUID = -3793551024751652031L;

    private Long id;

    private String serialNumber;

    private String name;

    private String examName;

    private String examSubject;

    private String examDate;

    private Boolean main;

    private Boolean finished;

    private Boolean violated;

    private String remark;

    private String gmtCreate;

    private String gmtUpdate;

}
