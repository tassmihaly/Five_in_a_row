package graphic;

import structural.Board;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Board board;
    private JPanel contentPane;
    private MenuPanel menuPane;
    private GamePanel gamePane;
    private RulesPanel rulesPane;

    public Window(Board b){
        super("Five in a row");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = b;
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        contentPane.setLayout(new CardLayout());
        menuPane = new MenuPanel(contentPane);
        rulesPane = new RulesPanel(contentPane);
        gamePane = new GamePanel(contentPane, b);
        contentPane.add(menuPane,"menu");
        contentPane.add(rulesPane,"rules");
        contentPane.add(gamePane,"game");
        setContentPane(contentPane);
        setPreferredSize(new Dimension(800,600));
        setMinimumSize(new Dimension(600,400));

        setVisible(true);
    }


}
