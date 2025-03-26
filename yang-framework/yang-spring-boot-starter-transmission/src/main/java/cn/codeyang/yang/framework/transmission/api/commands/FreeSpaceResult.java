package cn.codeyang.yang.framework.transmission.api.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FreeSpaceResult {

    private final String path;

    private final Long sizeBytes;

    private final Long totalSize;

    @JsonCreator
    public FreeSpaceResult(@JsonProperty("path") String path, @JsonProperty("size-bytes") Long sizeBytes, @JsonProperty("total_size") Long totalSize) {
        this.path = path;
        this.sizeBytes = sizeBytes;
        this.totalSize = totalSize;
    }
}
