package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordSubmitExamPlaceRoomDTO <p>
 * <p> Description: 考点提交考场记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/13
 */

@Data
public class RecordSubmitExamPlaceRoomDTO implements Serializable {

    private static final long serialVersionUID = 777508728234152166L;

    private Long id;

    private Integer examRoomSerialNumber;

    private String name;

    private String remark;

    private Boolean reviewed;

    private Boolean passed;

    private String reason;

    private Long recordTaskExamPlaceId;

    private String gmtCreate;

    private String gmtUpdate;

}
