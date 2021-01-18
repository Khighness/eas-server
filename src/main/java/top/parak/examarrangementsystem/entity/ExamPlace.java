package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: ExamPlace <p>
 * <p> Description: 考点实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("exam_place")
@ApiModel("考点")
public class ExamPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考点ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 考点名称（中学名称）
     */
    private String name;

    /**
     * 考点描述
     */
    private String description;

    /**
     * 所在位置
     */
    private String position;

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
