package top.parak.examarrangementsystem.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.config </p>
 * <p> FileName: QiNiuCloudConfigure <p>
 * <p> Description: 七牛云配置类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Data
@ToString
@org.springframework.context.annotation.Configuration
public class QiNiuCloudConfigure {

    /* 七牛云存储区域 */
    @Value("${qiniu.cloud.zone}")
    private String zone;

    /* 七牛云公钥 */
    @Value("${qiniu.cloud.accessKey}")
    private String accessKey;

    /* 七牛云私钥 */
    @Value("${qiniu.cloud.secretKey}")
    private String secretKey;

    /* 七牛云空间名称 */
    @Value("${qiniu.cloud.bucket}")
    private String bucket;

    /* 七牛云域名 */
    @Value("${qiniu.cloud.domain}")
    private String domain;

    /* 七牛云重试次数 */
    @Value("${qiniu.cloud.retryTimes}")
    private int retryTimes;

    /**
     * <p>七牛云空间配置</p>
     * @return
     */
    @Bean
    public com.qiniu.storage.Configuration qiNiuConfiguration() {
        if (zone.equals("huadong")) {
            return new com.qiniu.storage.Configuration(Zone.zone0());
        } else if (zone.equals("huabei")) {
            return new com.qiniu.storage.Configuration(Zone.zone1());
        } else if (zone.equals("huanan")) {
            return new com.qiniu.storage.Configuration(Zone.zone2());
        } else {
            throw new IllegalArgumentException("Error QiNiu Cloud Zone");
        }
    }

    /**
     * <p>七牛云认证信息实例</p>
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * <p>七牛云上传工具实例</p>
     * @return
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiNiuConfiguration());
    }

    /**
     * <p>七牛云空间管理实例</p>
     * @return
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiNiuConfiguration());
    }

}
