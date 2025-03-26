package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.api.domain.AddTorrentInfo;
import cn.codeyang.yang.framework.transmission.api.domain.AddedTorrentInfo;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class AddTorrentCommand extends RpcCommand<AddTorrentInfo, AddedTorrentInfo> {

    public AddTorrentCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-add";
    }
}
