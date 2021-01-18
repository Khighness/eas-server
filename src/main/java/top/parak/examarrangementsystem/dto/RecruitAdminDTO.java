package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecruitAdminAndRoleDTO <p>
 * <p> Description: 招办管理员-角色DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Data
public class RecruitAdminDTO implements Serializable {

    private static final long serialVersionUID = 380284516355835281L;

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

    private String gmtCreate;

    private String gmtUpdate;

}
