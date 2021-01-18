package top.parak.examarrangementsystem.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: ExamPlaceDTO <p>
 * <p> Description: 考点DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/3
 */

@Data
public class ExamPlaceDTO implements Serializable {

    private static final long serialVersionUID = 1945470579286813190L;

    private Long id;

    private String name;

    private String description;

    private String position;

    private String gmtCreate;

    private String gmtUpdate;

}
