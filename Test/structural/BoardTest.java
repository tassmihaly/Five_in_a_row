package structural;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board;

    @Before
    public void setUp(){
        board = new Board(10,10);
    }

    //setBoardAt() and getBoardAt() test
    @org.junit.Test
    public void setBoardAt() {
        board.setBoardAt(new Position(0,0), (byte) 1);
        board.setBoardAt(new Position(1,1), (byte) 2);
        assertEquals(1,board.getBoardAt(new Position(0,0)));
        assertEquals(2,board.getBoardAt(new Position(1,1)));
    }

    @org.junit.Test
    public void isWinner() {
        board.setBoardAt(new Position(0,0), (byte) 1);
        board.setBoardAt(new Position(0,1), (byte) 1);
        board.setBoardAt(new Position(0,2), (byte) 1);
        assertEquals(false,board.isWinner(new Position(0,2)));
        board.setBoardAt(new Position(0,3), (byte) 1);
        board.setBoardAt(new Position(0,4), (byte) 1);
        assertEquals(true,board.isWinner(new Position(0,4)));

        board.setBoardAt(new Position(1,1), (byte) 2);
        board.setBoardAt(new Position(2,2), (byte) 2);
        board.setBoardAt(new Position(3,3), (byte) 2);
        assertEquals(false,board.isWinner(new Position(3,3)));
        board.setBoardAt(new Position(4,4), (byte) 2);
        board.setBoardAt(new Position(5,5), (byte) 2);
        assertEquals(true,board.isWinner(new Position(5,5)));
    }

    //deep copy test for board and MinMaxStorage
    @org.junit.Test
    public void Board(){
        board.setBoardAt(new Position(2,2), (byte) 2);
        Board newBoard = new Board(board);
        assertEquals(board.getBoardAt(new Position(2,2)),newBoard.getBoardAt(new Position(2,2)));
        newBoard.setBoardAt(new Position(4,4), (byte) 2);
        assertNotEquals(board.getBoardAt(new Position(4,4)),newBoard.getBoardAt(new Position(4,4)));
        assertNotEquals(board.storage.getMaxHeight(),newBoard.storage.getMaxHeight());
    }


    @Test
    public void deepCopyTest(){
        board = new Board(10,10);
        board.setBoardAt(new Position(1,1), (byte) 2);
        byte[][] testArray = board.deepCopy();
        assertEquals(testArray[1][1],board.getBoardAt(new Position(1,1)));
        testArray[2][2] = 1;
        assertNotEquals(testArray[2][2],board.getBoardAt(new Position(2,2)));
    }

    @Test
    public void minMaxStorageSetValuesTest(){
        board.setBoardAt(new Position(3,3),(byte)1);
        assertEquals(3,board.storage.getMaxHeight());
        assertEquals(3,board.storage.getMinHeight());
        assertEquals(3,board.storage.getMaxWidth());
        assertEquals(3,board.storage.getMinWidth());

        board.setBoardAt(new Position(1,2),(byte)1);
        assertEquals(3,board.storage.getMaxHeight());
        assertEquals(2,board.storage.getMinHeight());
        assertEquals(3,board.storage.getMaxWidth());
        assertEquals(1,board.storage.getMinWidth());
    }
}