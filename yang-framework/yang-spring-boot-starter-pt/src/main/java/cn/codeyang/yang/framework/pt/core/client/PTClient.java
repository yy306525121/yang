package cn.codeyang.yang.framework.pt.core.client;

import cn.codeyang.yang.framework.pt.core.domain.Torrent;

import java.util.List;

public interface PTClient {
    default List<Torrent> searchTorrents(){
        return null;
    }
    boolean checkIn();
}
