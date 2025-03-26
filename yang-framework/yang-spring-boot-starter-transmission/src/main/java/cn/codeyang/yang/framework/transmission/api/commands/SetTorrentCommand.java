package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.api.domain.SetTorrentInfo;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class SetTorrentCommand extends RpcCommand<SetTorrentInfo, Void> {

    public SetTorrentCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-set";
    }
}