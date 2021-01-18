package top.parak.examarrangementsystem.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.parak.examarrangementsystem.common.HttpStatus;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.entity </p>
 * <p> FileName: RecordExam <p>
 * <p> Description: 考试记录实体层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/7
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record_exam")
@ApiModel("考试记录")
public class RecordExam implements Serializable {

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
    private String name;

    /**
     * 发起人
     */
    private String sponsor;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 理科考生数量
     */
    private Integer scienceExamineeNumber;

    /**
     * 文科考生数量
     */
    private Integer liberalArtsExamineeNumber;

    /**
     * 文科考场数量
     */
    private Integer scienceRoomNumber;

    /**
     * 理科考场数量
     */
    private Integer liberalArtsRoomNumber;

    /**
     * 是否安排
     */
    private Boolean arranged;

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

    /**
     * 考试科目
     */
    @TableField(exist = false)
    private List<RecordExamSubject> recordExamSubjectList;

}
