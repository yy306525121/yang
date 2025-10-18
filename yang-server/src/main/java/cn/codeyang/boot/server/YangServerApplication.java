package cn.codeyang.boot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${yang.info.base-package}
@SpringBootApplication(scanBasePackages = {"${yang.info.base-package}.server", "${yang.info.base-package}.module"})
public class YangServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YangServerApplication.class, args);
    }
}
