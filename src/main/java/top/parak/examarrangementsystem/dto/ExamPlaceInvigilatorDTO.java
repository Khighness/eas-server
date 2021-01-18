package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: ExamPlaceInvigilatorExamPlaceDTO <p>
 * <p> Description: 考务人员-角色-考点DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/2
 */

@Data
public class ExamPlaceInvigilatorDTO implements Serializable {

    private static final long serialVersionUID = 7312463294605156150L;

    private Long id;

    private String email;

    private String avatar;

    private String name;

    private String gender;

    private String birth;

    private String phone;

    private String position;

    private String signature;

    private Integer roleId;

    private String roleName;

    private Long examPlaceId;

    private String examPlaceName;

    private String gmtCreate;

    private String gmtUpdate;

}
