package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: ExamPlaceRoomDTO <p>
 * <p> Description: 考场DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/6
 */

@Data
public class ExamPlaceRoomDTO implements Serializable {

    private static final long serialVersionUID = -3963913561354680207L;

    private Long id;

    private String name;

    private Integer examRoomSerialNumber;

    private Long examPlaceId;

    private String examPlaceName;

    private String gmtCreate;

    private String gmtUpdate;

}
