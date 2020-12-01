package structural;

import graphic.GameComponent;
import graphic.Window;

public class Game {
    private Window window;
    private Board board;
    private Player players[];
    private byte round;


    public Game(){
        System.out.println(Thread.currentThread().getName());
        board = new Board(20,20);
        window = new Window(board,this);
        players = new Player[2];




        //players[1] = new Human('X',window);
        players[0] = new Bot('O', board,5);
        players[1] = new Human('X',window);

    }

    public void start(){
        round = 0;
        Bot bot= new Bot('X', board,2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        window.setStatusLabel("It's " + players[round].getSign() + "'s turn");
                        Position temp = players[round].step();
                        System.out.println("last step:"+temp.getX()+","+temp.getY());
                        board.setBoardAt(temp, (byte) (round+1));
                        System.out.println("current score for "+(round +1)+ ": " + bot.score(board, (byte) (round +1)));
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
