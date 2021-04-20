package top.parak.examarrangementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.aspect </p>
 * <p> FileName: ClientRequest <p>
 * <p> Description: 封装请求信息<p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/11
 */

@Data
@AllArgsConstructor
public class ClientRequest {

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 接口方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private Object[] args;

}
