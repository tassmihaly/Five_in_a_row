package structural;

import java.io.Serializable;

public class GameStats implements Serializable {
    private Board board;
    private byte round;
    private boolean multiplayer;
    private int level;

    public GameStats(Board b, byte round, int level, boolean multi){
        this.board = b;
        this.round = round;
        this.level = level;
        this.multiplayer = multi;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public byte getRound() {
        return round;
    }

    public Board getBoard() {
        return board;
    }

    public int getLevel() {
        return level;
    }
}
