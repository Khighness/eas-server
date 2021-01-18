package top.parak.examarrangementsystem.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.dto </p>
 * <p> FileName: RecordHistoricalViolationDTO <p>
 * <p> Description: 历史违规记录DTO <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/14
 */

@Data
public class RecordHistoricalViolationDTO {

    private Integer id;

    private String name;

    private String identityNumber;

    private String examName;

    private String examSubject;

    private String examDate;

    private String violation;

}
