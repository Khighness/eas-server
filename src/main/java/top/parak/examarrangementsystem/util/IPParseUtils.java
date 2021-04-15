package top.parak.examarrangementsystem.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: IPParseUtils <p>
 * <p> Description: IP解析工具类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote 可以静态调用
 */

public class IPParseUtils {

    /**
     * <P>当我们通过request获取客户端IP时，自身服务器通常会为了保护信息或者负载均衡的目的，对自身服务器做反向代理。
     * 此时通过request.getRemoteAddr()可能获取到的是自身代理服务器的IP，而无法获取到用户请求ip的真实ip。</P>
     * <p>各个代理服务器有自己的转发服务请求头，这些请求头都不是标准的http请求头，不一定所有的代理都会带上这些请求头。
     * 这样我们只能尽可能的获取到真实IP，但不能保证一定获取到真实IP，并且代理服务器请求头中获取的ip是可伪造的。</p>
     * <p>当服务器和客户端在同一台电脑上的时候，getRemoteAddr()为一个IPV6地址: 0:0:0:0:0:0:0:1</p>
     *
     * <ul>参数</ul>
     * <li>X-Forwarded-For: Squid 服务代理</li>
     * <li>Proxy-Client-IP: apache服务代理</li>
     * <li>WL-Proxy-Client-IP: weblogic服务代理</li>
     * <li>HTTP_CLIENT_IP: 有些代理服务器</li>
     * <li>X-Real-IP: nginx服务代理</li>
     *
     * @param request
     * @return 真实IP
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        /* X-Forwarded-For：Squid 服务代理 */
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            /* Proxy-Client-IP：apache 服务代理 */
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            /* WL-Proxy-Client-IP：weblogic 服务代理 */
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            /* HTTP_CLIENT_IP：有些代理服务器 */
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理 */
            ipAddresses = request.getHeader("X-Real-IP");
        }

        /* 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号','分割开来，并且第一个ip为客户端的真实IP */
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        /* 还是不能获取到，最后再通过request.getRemoteAddr()获取 */
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }

        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

}
