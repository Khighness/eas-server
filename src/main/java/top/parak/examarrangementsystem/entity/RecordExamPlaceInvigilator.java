package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: RecordExamPlaceInvigilator <p>
 * <p> Description: 参加监考考务人员提交信息记录实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record_exam_place_invigilator")
@ApiModel("参加监考考务人员提交信息记录")
public class RecordExamPlaceInvigilator implements Serializable {

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
     * 身份证号
     */
    private String identityNumber;

    /**
     * 近期照片
     */
    private String photo;

    /**
     * 授课年级
     */
    private String teachingGrade;

    /**
     * 监考经验
     */
    private Integer invigilateExperience;

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
     * 考务人员ID
     */
    private Long examPlaceInvigilatorId;

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
