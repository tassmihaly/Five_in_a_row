package structural;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    Board b;

    @Before
    public void setUp(){
        game = new Game();
        b = new Board(20,20);
    }

    //save and reload and setBoard functions test
    @Test
    public void reLoadGameAndSaveGameAndSetBoardTest() {
        b.setBoardAt(new Position(1,1), (byte) 1);
        game.setBoard(b);
        game.saveGame();
        game.saveGame();
        GameStats gameStats = game.reLoadGame();
        assertEquals(1,gameStats.getBoard().getBoardAt(new Position(1,1)));
    }
}