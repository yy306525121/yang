package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.api.domain.TorrentGetRequestInfo;
import cn.codeyang.yang.framework.transmission.api.domain.TorrentInfoCollection;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class TorrentGetCommand extends RpcCommand<TorrentGetRequestInfo, TorrentInfoCollection> {

    public TorrentGetCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-get";
    }
}
