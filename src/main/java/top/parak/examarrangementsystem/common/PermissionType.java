package top.parak.examarrangementsystem.common;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.common </p>
 * <p> FileName: DataResponse <p>
 * <p> Description: 权限类型 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

public enum PermissionType {

    RECRUIT_ADMIN(1, "系统管理员"),
    Recruit_APPROVER(2, "招办审批员"),
    EXAM_PLACE_ADMIN(3, "考点管理员"),
    EXAM_PLACE_INVIGILATOR(4, "考务人员");

    private Integer level;

    private String description;

    PermissionType(Integer level, String description) {
        this.level = level;
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
