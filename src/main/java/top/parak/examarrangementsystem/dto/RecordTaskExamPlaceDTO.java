package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordTaskExamPlaceDTO <p>
 * <p> Description: 考点任务记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Data
public class RecordTaskExamPlaceDTO implements Serializable {

    private static final long serialVersionUID = 278590746791633921L;

    private Long id;

    private String serialNumber;

    private String type;

    private Integer examRoomNumber;

    private Integer minExamRoomSerialNumber;

    private Integer invigilatorNumber;

    private Integer mainInvigilatorNumber;

    private Integer maleInvigilatorNumber;

    private Integer femaleInvigilatorNumber;

    private String deadline;

    private String remark;

    private Boolean readed;

    private Boolean finished;

    private Long examPlaceId;

    private Long version;

    private Integer deleted;

    private String gmtCreate;

    private String gmtUpdate;

}
