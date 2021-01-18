package top.parak.examarrangementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.common </p>
 * <p> FileName: DataResponse <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = 2788360657617180531L;

    private Integer code;
    private String message;
    private T data;

    /**
     * <p>通过HttpStatus构造返回信息</p>
     * @param httpStatus
     * @param <T>
     * @return
     */
    public static <T> ServerResponse dataResponse(HttpStatus httpStatus) {
        return new ServerResponse(httpStatus.getCode(), httpStatus.getMessage(), httpStatus.getDescription());
    }

    /**
     * <p>通过HttpStatus和Data构造返回信息</p>
     * @param data
     * @param httpStatus
     * @param <T>
     * @return
     */
    public static <T> ServerResponse dataResponse(HttpStatus httpStatus, T data) {
        return new ServerResponse(httpStatus.getCode(), httpStatus.getMessage(), data);
    }

    /**
     * <p>成功response</p>
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse successResponse(T data) {
        return new ServerResponse(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMessage(), data);
    }

    /**
     * <p>客户端错误Response</p>
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse clientErrorResponse(T data) {
        return new <T>ServerResponse(HttpStatus.CLIENT_ERROR.getCode(), HttpStatus.CLIENT_ERROR.getMessage(), data);
    }

    /**
     * <p>服务器错误Response</p>
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse serverErrorResponse(T data) {
        return new <T>ServerResponse(HttpStatus.SERVER_ERROR.getCode(), HttpStatus.SERVER_ERROR.getMessage(), data);
    }

}
