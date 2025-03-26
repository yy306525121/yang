package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.api.domain.RemoveTorrentInfo;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class RemoveTorentCommand extends RpcCommand<RemoveTorrentInfo, Object> {

    public RemoveTorentCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-remove";
    }
}
