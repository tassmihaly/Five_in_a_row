package structural;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void add() {
        Position p = new Position(20,20);
        p.add(2,1);
        assertEquals(22,p.getX());
        assertEquals(21,p.getY());
    }
}