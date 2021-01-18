package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecruitApproverDTO <p>
 * <p> Description: 招办审批员-角色DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Data
public class RecruitApproverDTO implements Serializable {

    private static final long serialVersionUID = -2314955254398459256L;

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
