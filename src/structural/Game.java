package structural;

import graphic.GameComponent;
import graphic.Window;

public class Game {
    private Window window;
    private Board board;
    private Steppable players[];
    private int round;


    public Game(){
        System.out.println(Thread.currentThread().getName());
        board = new Board(40,20);
        window = new Window(board,this);
        players = new Steppable[2];

        players[0] = new Human('O',window);
        players[1] = new Human('X',window);
        round = 1;
    }

    public void start(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        window.setStatusLabel("It's " + players[round].getSign() + "'s turn");
                        Position temp = players[round].step();
                        board.setBoardAt(temp,round+1);
                        window.rePaint();
                        if(board.isWinner(temp)){
                            window.setStatusLabel(players[round].getSign() + " won");
                            break;
                        }
                        
                        if(round == 1) round = 0;
                        else round = 1;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        thread.start();
    }
}