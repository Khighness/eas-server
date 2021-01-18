package top.parak.examarrangementsystem.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordExamSubjectDTO <p>
 * <p> Description: 考试科目记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Data
public class RecordExamSubjectDTO implements Serializable {

    private static final long serialVersionUID = -1244592551067398002L;

    private Long id;

    private String serialNumber;

    private String name;

    private String date;

    private String start;

    private String end;

    private String startTime;

    private String endTime;

}
