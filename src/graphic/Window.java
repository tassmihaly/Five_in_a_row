package graphic;

import structural.Board;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Board board;
    private JPanel contentPane;
    private MenuPanel menuPane;
    //private GamePanel gamePane;
    private RulesPanel rulesPane;


    public Window(){
        super("Five in a row");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        contentPane.setLayout(new CardLayout());
        menuPane = new MenuPanel(contentPane);
        rulesPane = new RulesPanel(contentPane);
        contentPane.add(menuPane,"menu");
        contentPane.add(rulesPane,"rules");
        setContentPane(contentPane);
        setPreferredSize(new Dimension(600,500));
        setMinimumSize(new Dimension(600,400));

        setVisible(true);
    }


}
