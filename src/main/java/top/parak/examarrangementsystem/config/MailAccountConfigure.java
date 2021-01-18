package top.parak.examarrangementsystem.config;

import cn.hutool.extra.mail.MailAccount;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.config </p>
 * <p> FileName: MailAccountConfigure <p>
 * <p> Description: 邮箱配置 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Setter
@Component
@ConfigurationProperties(prefix = "mail")
public class MailAccountConfigure {

    private String host;

    private Integer port;

    private boolean auth;

    private String user;

    private String from;

    private String pass;

    private String charset;

    private boolean sslEnable;

    private String socketFactoryClass;

    private boolean socketFactoryFallback;

    private Integer socketFactoryPort;

    private Long timeout;

    private Long connectionTimeout;

    public MailAccount getInstance() {
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(port);
        account.setAuth(auth);
        account.setUser(user);
        account.setFrom(from);
        account.setPass(pass);
        account.setCharset(Charset.forName(charset));
        account.setSslEnable(sslEnable);
        account.setSocketFactoryClass(socketFactoryClass);
        account.setSocketFactoryFallback(socketFactoryFallback);
        account.setSocketFactoryPort(socketFactoryPort);
        account.setTimeout(timeout);
        account.setConnectionTimeout(connectionTimeout);
        return account;
    }

}
