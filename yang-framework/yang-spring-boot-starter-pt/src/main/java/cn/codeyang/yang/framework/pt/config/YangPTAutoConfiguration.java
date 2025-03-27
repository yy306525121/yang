package cn.codeyang.yang.framework.pt.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(YangPTProperties.class)
public class YangPTAutoConfiguration {

}
