package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author KHighness
 * @since 2021-01-13
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record_submit_exam_place_invigilator")
@ApiModel("考点提交考务人员记录")
public class RecordSubmitExamPlaceInvigilator implements Serializable {

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
     * 监考证号
     */
    private Integer invigilatorSerialNumber;

    /**
     * 参加监考考务人员ID
     */
    private Long recordExamPlaceInvigilatorId;

    /**
     * 是否主考
     */
    private Boolean main;

    /**
     * 是否审核
     */
    private Boolean reviewed;

    /**
     * 是否通过
     */
    private Boolean passed;

    /**
     * 未通过原因
     */
    private String reason;

    /**
     * 考点任务ID
     */
    private Long recordTaskExamPlaceId;

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
     * 删除时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;

}
