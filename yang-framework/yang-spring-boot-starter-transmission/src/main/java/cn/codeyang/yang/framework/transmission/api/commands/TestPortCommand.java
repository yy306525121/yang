package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.api.domain.PortCheckResult;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class TestPortCommand extends RpcCommand<Void, PortCheckResult> {

    public TestPortCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "port-test";
    }
}
