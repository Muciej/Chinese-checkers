package server.moves;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import server.ChineseCheckerServer;
import server.board.Board;

@RunWith(MockitoJUnitRunner.class)
public class MovesMasterTest extends TestCase {

    MovesMaster movesMaster;
    Board board;

    @Mock
    ChineseCheckerServer MockedCheckerServer;

    @Test
    public void CheckingMovesTest(){
        generateTestBoard();
        movesMaster = new MovesMaster(board);
        Move move = new Move("Ex", 10, 2, 12, 4);
        Assert.assertTrue(movesMaster.checkFor(move));
        move = new Move("Ex", 10, 2, 10, 6);
        Assert.assertTrue(movesMaster.checkFor(move));
        move = new Move("Ex", 10, 2, 11, 3);
        Assert.assertFalse(movesMaster.checkFor(move));
        move = new Move("Ex", 10, 2, 9, 3);
        Assert.assertTrue(movesMaster.checkFor(move));
    }

    private void generateTestBoard(){
        board = new Board(MockedCheckerServer, "Chinese-checkers.bd");
        //board.showBoard();
        board.getField(10, 2).setOccupant("Ex");
        board.getField(11, 3).setOccupant("Ex");
        board.getField(10, 4).setOccupant("Ex");
        board.getField(11, 5).setOccupant("Ex");
        //board.showBoard();
    }


}