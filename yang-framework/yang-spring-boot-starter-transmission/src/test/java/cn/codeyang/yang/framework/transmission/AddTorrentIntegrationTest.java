package cn.codeyang.yang.framework.transmission;

import cn.codeyang.yang.framework.transmission.api.TransmissionRpcClient;
import cn.codeyang.yang.framework.transmission.api.domain.*;
import cn.codeyang.yang.framework.transmission.api.domain.ids.OmittedIds;
import cn.codeyang.yang.framework.transmission.rpc.RpcClient;
import cn.codeyang.yang.framework.transmission.rpc.RpcConfiguration;
import cn.codeyang.yang.framework.transmission.rpc.RpcException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddTorrentIntegrationTest extends IntegrationTest {

    private TransmissionRpcClient rpcClient;

    @BeforeEach
    public void before() throws RpcException, MalformedURLException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RpcConfiguration rpcConfiguration = new RpcConfiguration();
        rpcConfiguration.setHost(URI.create("http://localhost:9091/transmission/rpc"));
        RpcClient client = new RpcClient(rpcConfiguration, objectMapper);
        rpcClient = new TransmissionRpcClient(client);
        pause();
    }

    @AfterEach
    public void after() throws RpcException, InterruptedException {
        pause();
        rpcClient.removeTorrent(new RemoveTorrentInfo(new OmittedIds(), true));

    }

    @Test
    public void testAddTorrent() throws RpcException {
        AddTorrentInfo addTorrentInfo = new AddTorrentInfo();
        addTorrentInfo
                .setFilename("magnet:?xt=urn:btih:727665E0FE70263CD0B715758C2E8DB9A78554EC&dn=white+house+down+2013+720p+brrip+x264+yify&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80%2Fannounce&tr=udp%3A%2F%2Fopen.demonii.com%3A1337");
        addTorrentInfo.setPaused(true);
        addTorrentInfo.setLabels(Arrays.asList("test"));
        AddedTorrentInfo result = rpcClient.addTorrent(addTorrentInfo);
        TorrentInfo info = result.getTorrentInfo();

        assertThat(info, is(notNullValue()));
        assertThat(info.getId(), is(notNullValue()));
        assertThat(info.getName(), is("white+house+down+2013+720p+brrip+x264+yify"));
    }
}
