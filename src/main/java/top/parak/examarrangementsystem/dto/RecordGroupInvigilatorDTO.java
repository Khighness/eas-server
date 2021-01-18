package top.parak.examarrangementsystem.dto;

import lombok.Data;
import top.parak.examarrangementsystem.common.ServerResponse;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordGroupInvigilatorDTO <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/15
 */

@Data
public class RecordGroupInvigilatorDTO {

    private String examName;

    private Long groupId;

    private String remark;

    private String examRoomSerialNumber;

    private String examRoomName;

    private String examSubjectName;

    private String examSubjectStartTime;

    private String examSubjectEndTime;

    private Long taskId;

    private Boolean violated;

    private String name;

    private String gender;

    private Integer invigilatorSerialNumber;

    private Boolean main;

}
