package graphic;

import structural.Board;
import structural.Game;
import structural.GameStats;
import structural.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;

public class Window extends JFrame{
    private Board board;
    private JPanel contentPane;
    private MenuPanel menuPane;
    private GamePanel gamePane;
    private RulesPanel rulesPane;
    private Game game;

    public Window(Board b, Game g){
        super("Five in a row");
        game = g;
        board = b;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        contentPane.setLayout(new CardLayout());
        menuPane = new MenuPanel(contentPane, game, this);
        rulesPane = new RulesPanel(contentPane);
        gamePane = new GamePanel(contentPane, board, this);
        contentPane.add(menuPane,"menu");
        contentPane.add(rulesPane,"rules");
        contentPane.add(gamePane,"game");
        System.out.println("Thread:" + Thread.currentThread().getName());
        setContentPane(contentPane);
        setPreferredSize(new Dimension(800,600));
        setMinimumSize(new Dimension(700,500));
        setVisible(true);

    }
    public Position getLastClick(){
        return gamePane.getLastClick();
    }

    public void rePaint(){
        gamePane.rePaint();
    }

    public void setStatusLabel(String text){
        gamePane.setStatLabel(text);
    }

    public void restartGame(){
        board.init();
        gamePane.repaint();
        game.start((byte) 0);
    }

    public void updateBoard(Board b){
        board = b;
        gamePane.updateBoard(b);
    }

    public boolean saveGame(){
        return game.saveGame();
    }

    public GameStats reLoadGame() {
        return game.reLoadGame();
    }
}

