package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author KHighness
 * @since 2021-01-15
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RecordTaskExamInvigilator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 备注
     */
    private String remark;

    /**
     * （考务人员）是否完成
     */
    private Boolean finished;

    /**
     * （考务人员）是否违规
     */
    private Boolean violated;

    /**
     * （考点管理员）是否审核
     */
    private Boolean reviewed;

    /**
     * （考点管理员）是否提交
     */
    private Boolean submitted;

    /**
     * （招办审批员）是否存档
     */
    private Boolean saved;

    /**
     * 考试科目监考组id
     */
    private Long recordExamSubjectInvigilatorGroupId;

    /**
     * 考务人员id
     */
    private Long recordSubmitExamPlaceInvigilatorId;

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
