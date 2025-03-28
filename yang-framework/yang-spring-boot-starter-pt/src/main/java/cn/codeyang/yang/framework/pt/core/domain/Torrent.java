package cn.codeyang.yang.framework.pt.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Torrent {
    private String title;
    private String subTitle;
    private String link;
    private String downloadUrl;
    private List<String> tags;
    private String size;
    private Integer seederSize;
    private Integer leecherSize;
}
