package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: RecordExamSubject <p>
 * <p> Description: 考务人员记录实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/7
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record_invigilator")
@ApiModel("考务人员记录")
public class RecordInvigilator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 监考科目
     */
    private String examSubject;

    /**
     * 监考日期
     */
    private Date examDate;

    /**
     * 是否主考
     */
    private Boolean main;

    /**
     * 是否完成
     */
    private Boolean finished;

    /**
     * 是否违规
     */
    private Boolean violated;

    /**
     * 备注
     */
    private String remark;

    /**
     * 考务人员ID
     */
    private Long examPlaceInvigilatorId;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

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
