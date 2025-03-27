package cn.codeyang.yang.framework.pt.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Torrent {
    private String name;
    private String subName;
    private String link;
    private String downloadUrl;
    private List<String> tags;
}
