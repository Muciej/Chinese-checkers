package server.connection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import server.ChineseCheckerServer;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {

    @Mock
    ChineseCheckerServer MockedCheckerServer;

    @Test
    public void testCreateServer(){
        Server server = new Server(MockedCheckerServer, 60000);
        TestClient testClient = new TestClient(60000);
        server.sendCommand("message");
        Assert.assertEquals("message", testClient.testReceive());
        testClient.testSend("Sending test");
        Mockito.verify(MockedCheckerServer).addCommandToExecute("Sending test");
        server.stopServer();
        Assert.assertEquals(0, server.connected());
    }

}