package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RoleDTO <p>
 * <p> Description: 角色DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -3868136245800693444L;

    private Long id;

    private String name;

    private String description;

    private String gmtCreate;

    private String gmtUpdate;

}
