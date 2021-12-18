package server.state;

import junit.framework.TestCase;
import org.junit.Assert;
import org.mockito.Answers;
import org.mockito.Mockito;
import server.ChineseCheckerServer;
import server.Player;

import java.awt.*;

import static org.mockito.Mockito.when;

public class AbstractStateTest extends TestCase {

    ChineseCheckerServer manager = Mockito.mock(ChineseCheckerServer.class);

    public void testPendingDo_comm() {
        Color c = new Color(220, 24,23);
        AbstractState state = new GamePending(manager, null);

    }

    public void testMoveDo_comm(){

    }

    public void testEndDo_comm(){

    }

    public void testInvalidComm(){
        AbstractState state = new GamePending(manager, null);

        Assert.assertThrows(IllegalCommandException.class, () ->{
           state.do_comm("MOVE 1 4 15 12");
        });
    }
}