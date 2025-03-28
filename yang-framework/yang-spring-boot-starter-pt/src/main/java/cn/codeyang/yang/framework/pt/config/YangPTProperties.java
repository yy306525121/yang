package cn.codeyang.yang.framework.pt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "yang.pt")
@Data
public class YangPTProperties {
    private static final Boolean ENABLE_DEFAULT = true;

    private List<Xpath> xpathList = new ArrayList<>();

    @Data
    public static class Xpath {
        private String name;

        private UserInfo userInfo;
        private Torrent torrent;

        @Data
        public static class UserInfo {
            private String usernameSelector;
            private String uploadedSelector;
            private String downloadedSelector;
        }

        @Data
        public static class Torrent {
            private String rowSelector;
            private String titleSelector;
            private String subTitleSelector;
            private String downloadInnerSelector;
            private String tagSelector;
            private String sizeSelector;
            private String seederSizeSelector;
            private String leecherSizeSelector;

            private String nextPageSelector;
        }
    }
}
