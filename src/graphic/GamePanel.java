package graphic;

import structural.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JButton saveButton;
    private JLabel statLabel;
    private JButton resetButton;
    private GameComponent gamePanel;
    private JPanel contentPane;

    public GamePanel(JPanel pane, Board b){
        contentPane = pane;
        gamePanel = new GameComponent(b);
        saveButton = new JButton("Save");
        statLabel = new JLabel();
        resetButton = new JButton("Reset");
        JPanel p = new JPanel();
        p.add(saveButton);
        p.add(statLabel);
        p.add(resetButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setLayout(new BorderLayout());
        JScrollPane sl = new JScrollPane(gamePanel);
        add(sl,BorderLayout.CENTER);
        add(p,BorderLayout.PAGE_END);


    }


}
