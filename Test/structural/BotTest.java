package structural;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BotTest {
    Board b;
    Bot bot;

    @Before
    public void setUp(){
        b = new Board(10,10);
        bot = new Bot('X',b,2);
    }

    @Test
    public void scoreEmpty() {
        b.setBoardAt(new Position(4,4),(byte)1);
        assertEquals(0,bot.score(b, (byte) 1));
    }

    //tests fill() and makeScore() and score(), evaluate() also called
    @Test
    public void fillAndMakeScoreAndScoreTest(){
        b.setBoardAt(new Position(4,4),(byte)1);
        b.setBoardAt(new Position(5,4),(byte)2);
        b.setBoardAt(new Position(5,5),(byte)1);
        b.setBoardAt(new Position(5,3),(byte)2);
        //O got an opened 2 row, X got a closed 2 row and it's O's turn
        assertEquals(140,bot.score(b, (byte) 2));
    }

    @Test
    public void fillAndMakeScoreAndScoreTestForHigherScores(){
        b.setBoardAt(new Position(4,4),(byte)1);
        b.setBoardAt(new Position(5,4),(byte)2);
        b.setBoardAt(new Position(5,5),(byte)1);
        b.setBoardAt(new Position(5,3),(byte)2);
        b.setBoardAt(new Position(6,6),(byte)1);
        b.setBoardAt(new Position(3,3),(byte)2);
        b.setBoardAt(new Position(4,5),(byte)1);
        //O got 2 opened 2 row and a closed 3 row, X got a closed 2 row and it's X's turn
        assertEquals(285,bot.score(b, (byte) 1));
    }

    //evaluate() function is for early return, if somebody win for sure it's return
    @Test
    public void evaluateTest(){
        b.setBoardAt(new Position(4,4),(byte)1);
        b.setBoardAt(new Position(5,4),(byte)2);
        b.setBoardAt(new Position(5,5),(byte)1);
        b.setBoardAt(new Position(5,3),(byte)2);
        b.setBoardAt(new Position(3,3),(byte)1);
        b.setBoardAt(new Position(6,3),(byte)2);
        //O got an opened 3 row, X got a closed 2 row and two opened row, but it's O's turn so it's max value
        assertEquals(1000000,bot.score(b, (byte) 2));
    }


}