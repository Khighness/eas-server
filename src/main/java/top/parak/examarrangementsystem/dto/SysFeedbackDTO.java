package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: SysFeedbackDTO <p>
 * <p> Description: 系统反馈DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Data
public class SysFeedbackDTO implements Serializable {

    private static final long serialVersionUID = 4891402915745805622L;

    private Long id;

    private String email;

    private String feedback;

    private String gmtCreate;

    private String gmtUpdate;

}
