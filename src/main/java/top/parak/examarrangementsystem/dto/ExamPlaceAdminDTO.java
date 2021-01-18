package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: ExamPlaceAdminDTO <p>
 * <p> Description: 考点管理员-角色-考点DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Data
public class ExamPlaceAdminDTO implements Serializable {

    private static final long serialVersionUID = -8403145217713910258L;

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
