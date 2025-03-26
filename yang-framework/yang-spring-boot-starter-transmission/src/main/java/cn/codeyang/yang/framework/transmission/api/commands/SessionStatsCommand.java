package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.api.domain.SessionStats;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class SessionStatsCommand extends RpcCommand<Void, SessionStats> {

    public SessionStatsCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "session-stats";
    }
}
