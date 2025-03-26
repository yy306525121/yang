package cn.codeyang.yang.framework.transmission.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddTorrentInfo {
    private String cookies;

    @JsonProperty("download-dir")
    private String downloadDir;
    private String filename;
    private String metainfo;

    private Boolean paused;

    @JsonProperty("peer-limit")
    private Long peerLimit;

    private Long bandwidthPriority;

    @JsonProperty("files-wanted")
    private List<String> filesWanted;

    @JsonProperty("files-unwanted")
    private List<String> filesUnwanted;

    @JsonProperty("priority-high")
    private List<String> priorityHigh;

    @JsonProperty("priority-low")
    private List<String> priorityLow;

    @JsonProperty("priority-normal")
    private List<String> priorityNormal;

    private List<String> labels;
}

