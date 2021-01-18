package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: RecordExamSubject <p>
 * <p> Description: 考试科目记录实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/7
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record_exam_subject")
@ApiModel("考试科目记录")
public class RecordExamSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 科目编号
     */
    private String serialNumber;

    /**
     * 科目名称
     */
    private String name;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 考试ID
     */
    private Long recordExamId;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;

}
