package cn.codeyang.yang.framework.transmission.api;

import cn.codeyang.yang.framework.transmission.api.commands.*;
import cn.codeyang.yang.framework.transmission.api.domain.*;
import cn.codeyang.yang.framework.transmission.api.domain.ids.Ids;
import cn.codeyang.yang.framework.transmission.api.domain.ids.OmittedIds;
import cn.codeyang.yang.framework.transmission.rpc.RpcClient;
import cn.codeyang.yang.framework.transmission.rpc.RpcCommand;
import cn.codeyang.yang.framework.transmission.rpc.RpcException;
import cn.codeyang.yang.framework.transmission.rpc.TagProvider;

public class TransmissionRpcClient {

    private final RpcClient rpcClient;
    private final TagProvider tagProvider = new TagProvider();

    public TransmissionRpcClient(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public SessionStats getSessionStats() throws RpcException {
        SessionStatsCommand command = new SessionStatsCommand(nextTag());
        rpcClient.executeWithHeaders(command);
        return command.getResponse().getArguments();
    }

    public TorrentInfoCollection getAllTorrentsInfo() throws RpcException {
        return getTorrentInfo(new TorrentGetRequestInfo(new OmittedIds(), Constants.TORRENT_INFO_FIELDS));
    }

    public TorrentInfoCollection getTorrentInfo(TorrentGetRequestInfo torrentGetRequestInfo) throws RpcException {
        TorrentGetCommand torrentGetCommand = new TorrentGetCommand(nextTag());
        torrentGetCommand.setRequestArguments(torrentGetRequestInfo);
        rpcClient.executeWithHeaders(torrentGetCommand);
        return torrentGetCommand.getResponse().getArguments();
    }

    public AddedTorrentInfo addTorrent(AddTorrentInfo addTorrentInfo) throws RpcException {
        AddTorrentCommand addTorrentCommand = new AddTorrentCommand(nextTag());
        addTorrentCommand.setRequestArguments(addTorrentInfo);
        executeCommand(addTorrentCommand);
        return addTorrentCommand.getResponse().getArguments();
    }

    public void setTorrent(SetTorrentInfo setTorrentInfo) throws RpcException {
        SetTorrentCommand setTorrentCommand = new SetTorrentCommand(nextTag());
        setTorrentCommand.setRequestArguments(setTorrentInfo);
        executeCommand(setTorrentCommand);
    }

    public void removeTorrent(RemoveTorrentInfo removeTorrentInfo) throws RpcException {
        RemoveTorentCommand removeTorentCommand = new RemoveTorentCommand(nextTag());
        removeTorentCommand.setRequestArguments(removeTorrentInfo);
        rpcClient.executeWithHeaders(removeTorentCommand);
    }

    public void doAction(Ids ids, TorrentAction action) throws RpcException {
        TorrentActionCommand torrentActionCommand = new TorrentActionCommand(nextTag(), action);
        torrentActionCommand.setRequestArguments(ids);
        rpcClient.executeWithHeaders(torrentActionCommand);
    }

    public Long nextTag() {
        return tagProvider.nextTag();
    }

    public <T, V> void executeCommand(RpcCommand<T, V> command) throws RpcException {
        rpcClient.executeWithHeaders(command);
    }

    public void doQueueAction(QueueAction queueAction, Ids ids) throws RpcException {
        QueueActionCommand command = new QueueActionCommand(nextTag(), queueAction);
        command.setRequestArguments(ids);
        rpcClient.executeWithHeaders(command);
    }

    public PortCheckResult testPort() throws RpcException {
        TestPortCommand command = new TestPortCommand(nextTag());
        rpcClient.executeWithHeaders(command);
        return command.getResponse().getArguments();
    }

    public FreeSpaceResult freeSpace(String path) throws RpcException {
        FreeSpaceCommand freeSpaceCommand = new FreeSpaceCommand(nextTag());
        freeSpaceCommand.setRequestArguments(new FreeSpacePath(path));
        rpcClient.executeWithHeaders(freeSpaceCommand);
        return freeSpaceCommand.getResponse().getArguments();
    }
}
