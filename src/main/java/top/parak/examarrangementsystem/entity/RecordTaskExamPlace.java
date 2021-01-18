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
 * <p> Description: 考点任务记录实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/10
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record_task_exam_place")
@ApiModel("考点任务记录")
public class RecordTaskExamPlace implements Serializable {

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
     * 考点类型
     */
    private String type;

    /**
     * 考场数量
     */
    private Integer examRoomNumber;

    /**
     * 最小考场编号
     */
    private Integer minExamRoomSerialNumber;

    /**
     * 考务人员数量
     */
    private Integer invigilatorNumber;

    /**
     * 主监考员数量
     */
    private Integer mainInvigilatorNumber;

    /**
     * 男监考员数量
     */
    private Integer maleInvigilatorNumber;

    /**
     * 女监考员数量
     */
    private Integer femaleInvigilatorNumber;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deadline;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否已读
     */
    private Boolean readed;

    /**
     * 是否通过
     */
    private Boolean finished;

    /**
     * 考点ID
     */
    private Long examPlaceId;

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
