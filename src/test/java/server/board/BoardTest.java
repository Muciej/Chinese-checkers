package server.board;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import server.ChineseCheckerServer;

public class BoardTest extends TestCase {

    public void testReadFromFile() {
        Board board = createTestBoard();
        assertEquals(5, board.fields.length);
        assertEquals(3, board.fields[4][1].getStartFieldNo());
        assertEquals(null, board.fields[0][0]);
        assertEquals(null, board.fields[2][2]);
        String valnum = board.validPlayersNumber.toString();
        assertEquals("[4, 2]", valnum);
        printBoard(board);
    }

    public void testDoMove(){
        Board board = createTestBoard();
        board.fields[2][1].setOccupant("Player 1");
        board.doMove(2,1,4,0);
        assertEquals("Player 1", board.fields[4][0].getOccupant());
    }

    public void testPlayerCount(){
        Board board = createTestBoard();
        Assert.assertTrue(board.isValidPlayerCount(4));
        Assert.assertEquals(1, board.getPositions(2)[0]);
        Assert.assertEquals(3, board.getPositions(2)[1]);
    }

    private Board createTestBoard(){
        ChineseCheckerServer c = Mockito.mock(ChineseCheckerServer.class);
        Board board = new Board(c, "testBoard.bd");
        return board;
    }
    private void printBoard(Board board){
        for(int i = 0; i < board.height; i++){
            for(int j = 0; j < board.width; j++){
                if(board.fields[j][i] != null){
                    System.out.print(board.fields[j][i].getStartFieldNo());
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }
    }
}