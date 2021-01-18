package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;
import top.parak.examarrangementsystem.annotation.Email;
import top.parak.examarrangementsystem.annotation.Password;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: ExamPlaceAdmin <p>
 * <p> Description: 考点管理员实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("exam_place_admin")
@ApiModel("考点管理员")
public class ExamPlaceAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 密码
     */
    @Password
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

    /**
     * 电话
     */
    private String phone;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 考点ID
     */
    private Long examPlaceId;

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
