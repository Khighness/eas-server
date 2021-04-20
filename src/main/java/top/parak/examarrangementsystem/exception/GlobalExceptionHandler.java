package top.parak.examarrangementsystem.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.parak.examarrangementsystem.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: ExamPlaceController <p>
 * <p> Description: 考点控制层 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Null pointer
    @ExceptionHandler(NullPointerException.class)
    public ServerResponse handleNullPointerException(NullPointerException e) {
        return ServerResponse.clientErrorResponse("空指针异常");
    }

    // Request path does not exist
    @ExceptionHandler(NoHandlerFoundException.class)
    public ServerResponse handlerNoFoundException(Exception e, HttpServletRequest request) {
        return ServerResponse.clientErrorResponse("路径错误");
    }

    // The request method is wrong
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ServerResponse HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("不支持" + e.getMethod() + "请求方法，");
        stringBuilder.append("支持请求方法: [ ");
        String [] methods = e.getSupportedMethods();
        if(methods!=null){
            for(String str:methods){
                stringBuilder.append(str + " ");
            }
        }
        stringBuilder.append("]");
        return ServerResponse.clientErrorResponse(stringBuilder.toString());
    }

    // File size exceeds limit
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ServerResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return ServerResponse.clientErrorResponse("文件过大");
    }

    // Data length exceeds limit
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ServerResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ServerResponse.clientErrorResponse("数据过长");
    }


    // Data is duplicated
    @ExceptionHandler(DuplicateKeyException.class)
    public ServerResponse handleDuplicateKeyException(DuplicateKeyException e){
        return ServerResponse.clientErrorResponse("重复数据");
    }

    // Redis connection is abnormal
    @ExceptionHandler(PoolException.class)
    public ServerResponse handlePoolException(PoolException e){
        return ServerResponse.serverErrorResponse("Redis连接异常");
    }

    // Insufficient authority
    @ExceptionHandler(InsufficientAuthorityException.class)
    public ServerResponse handleInsufficientAuthorityException(InsufficientAuthorityException e){
        return ServerResponse.clientErrorResponse(e.getMessage());
    }

}
