package cn.codeyang.yang.framework.pt.core.client.adapter;

import cn.codeyang.yang.framework.pt.core.client.PTClient;
import cn.codeyang.yang.framework.pt.core.domain.Torrent;

import java.util.List;

public class SpringSundayAdapter implements PTClient {

    @Override
    public List<Torrent> searchTorrents() {
        return List.of();
    }

    @Override
    public boolean checkIn() {
        return false;
    }
}
