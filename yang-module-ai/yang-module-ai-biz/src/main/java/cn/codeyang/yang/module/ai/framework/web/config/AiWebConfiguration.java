package cn.codeyang.yang.module.ai.framework.web.config;

import cn.codeyang.yang.framework.swagger.config.YangSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ai 模块的 web 组件的 Configuration
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class AiWebConfiguration {

    /**
     * ai 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi aiGroupedOpenApi() {
        return YangSwaggerAutoConfiguration.buildGroupedOpenApi("ai");
    }

}
