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
        GameStats gameStats = new GameStats(board, game.getRound(), game.getBotLevel(), game.isMultiplayer());
        try {
            FileOutputStream f = new FileOutputStream("game.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(gameStats);
            out.close();
            return true;
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public GameStats reLoadGame(){
        GameStats gameStats;
        try {
            FileInputStream f = new FileInputStream("game.ser");
            ObjectInputStream in = new ObjectInputStream(f);
            gameStats = (GameStats) in.readObject();
            in.close();

        } catch(IOException ex) {
            gameStats = new GameStats(null, (byte) 0,-1,true);
            menuPane.setErrorLabel("Reload game unsuccessful");
        } catch(ClassNotFoundException ex) {
            gameStats = new GameStats(null, (byte) 0,-1,true);
            menuPane.setErrorLabel("Game not found");
        }
        return gameStats;
    }

}

