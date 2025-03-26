package cn.codeyang.yang.framework.transmission.api.commands;


import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;

public class FreeSpaceCommand extends RpcCommand<FreeSpacePath, FreeSpaceResult> {

    public FreeSpaceCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "free-space";
    }

}
