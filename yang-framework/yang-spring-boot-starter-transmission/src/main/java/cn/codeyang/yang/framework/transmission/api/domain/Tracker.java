package cn.codeyang.yang.framework.transmission.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Tracker {

    private String announce;
    private Long id;
    private String scrape;
    private String sitename;
    private Long tier;
}
