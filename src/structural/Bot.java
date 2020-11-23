package structural;

import java.util.Random;

public class Bot implements Steppable{
    private Board board;
    private char sign;
    public Bot(char sign, Board b) {
        this.sign = sign;
        board = b;
    }

    @Override
    public Position step() {
        Random rand = new Random();
        Position temp;
        System.out.println("na a rabot is amugy hivva van");
        do{
            temp = new Position(rand.nextInt(board.getWidth()), rand.nextInt(board.getHeight()));
        } while(board.getBoardAt(temp) != 0);

        return temp;
    }

    public char getSign() {
        return sign;
    }
}
