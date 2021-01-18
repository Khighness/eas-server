package top.parak.examarrangementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.nio.cs.ISO_8859_2;

import java.nio.charset.StandardCharsets;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.config </p>
 * <p> FileName: SwaggerConfigure <p>
 * <p> Description: Swagger配置 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote {http://IP:PORT/swagger-ui.html} 原始风格界面
 * @apiNote {http://IP:PORT/doc.html}        BootStrap风格界面
 */

@Configuration
@EnableSwagger2
public class SwaggerConfigure {

    @Value("${swagger.host}")
    private String host;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.url}")
    private String contactUrl;

    @Value("${swagger.contact.email}")
    private String contactEmail;


    /**
     * <p>构件DocketBean</p>
     * @return docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.parak.examarrangementsystem.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * <p>构件API文档的详细信息函数</p>
     * @return apiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .description(description)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }

}
