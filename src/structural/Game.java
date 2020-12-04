package structural;

import graphic.GameComponent;
import graphic.Window;

public class Game {
    private Window window;
    private Board board;
    private final Player[] players;
    private byte round;
    private boolean multiplayer;
    private int  botLevel;

    public Game(){
        System.out.println(Thread.currentThread().getName());
        board = new Board(40,20);
        window = new Window(board,this);
        multiplayer = false;
        botLevel = 1;
        players = new Player[2];
        players[0] = new Human('O', window);
        players[1] = new Human('X', window);
    }

    public int getBotLevel(){
        return botLevel;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public byte getRound() {
        return round;
    }

    public void setPlayerParams(boolean isMultiplayer, int l){
        players[0] = new Human('O', window);
        if(isMultiplayer){
            players[1] = new Human('X', window);
            multiplayer = false;
        }
        else{
            players[1] = new Bot('X',board, l);
            multiplayer = true;
        }
    }

    public void setBoard(Board b){
        board = b;
        window.updateBoard(b);
    }

    public void start(byte startRound){
        round = startRound;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        window.setStatusLabel("It's " + players[round].getSign() + "'s turn");
                        Position temp = players[round].step();
                        board.setBoardAt(temp, (byte) (round+1));
                        window.rePaint();
                        if(board.isWinner(temp)){
                            window.setStatusLabel(players[round].getSign() + " won");
                            return;
                        }
                        round = (byte) ((++round)%2);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        
        Game g = new Game();
    }
}
