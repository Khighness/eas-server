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
@TableName("record_submit_exam_place_plan")
@ApiModel("考点提交平面图记录")
public class RecordSubmitExamPlacePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 考点平面图
     */
    private String examPlacePlan;

    /**
     * 备注
     */
    private String remark;

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
     * 考点任务id
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
