package top.parak.examarrangementsystem.dto;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordExamDTO <p>
 * <p> Description: 考试记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/7
 */

@Data
public class RecordExamDTO implements Serializable {

    private static final long serialVersionUID = 8123313692814684667L;

    private Long id;

    private String serialNumber;

    private String name;

    private String sponsor;

    private String startTime;

    private String endTime;

    private Integer scienceExamineeNumber;

    private Integer liberalArtsExamineeNumber;

    private Integer scienceRoomNumber;

    private Integer liberalArtsRoomNumber;

    private Boolean arranged;

    private String gmtCreate;

    private String gmtUpdate;

    private List<RecordExamSubjectDTO> recordExamSubjectDTOList;

}
