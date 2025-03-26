package cn.codeyang.yang.framework.transmission;

import cn.codeyang.yang.framework.transmission.api.TransmissionRpcClient;
import cn.codeyang.yang.framework.transmission.api.commands.FreeSpaceResult;
import cn.codeyang.yang.framework.transmission.rpc.RpcClient;
import cn.codeyang.yang.framework.transmission.rpc.RpcConfiguration;
import cn.codeyang.yang.framework.transmission.rpc.RpcException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreeSpaceIntegrationTest extends IntegrationTest{


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

    @Test
    public void testFreeSpace() throws RpcException {
        FreeSpaceResult result = rpcClient.freeSpace("/");
        assertEquals(result.getPath(), "/");
        assertTrue(result.getSizeBytes() > 0);
    }

}
