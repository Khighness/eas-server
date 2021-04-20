package top.parak.examarrangementsystem.exception;


/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: ExamPlaceController <p>
 * <p> Description: 权限不足异常 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */


public class InsufficientAuthorityException extends Exception {

    public InsufficientAuthorityException() {
        super("权限不足");
    }

    public InsufficientAuthorityException(String message) {
        super(message);
    }

}
