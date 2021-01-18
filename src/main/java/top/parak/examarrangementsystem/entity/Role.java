package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: Role <p>
 * <p> Description: 角色实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
@ApiModel("角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

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
