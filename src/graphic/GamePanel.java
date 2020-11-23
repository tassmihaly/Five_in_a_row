package graphic;

import structural.Board;
import structural.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel {
    private JButton saveButton;
    private JLabel statLabel;
    private JButton resetButton;
    private GameComponent gamePanel;
    private JPanel contentPane;
    private Window window;


    public GamePanel(JPanel pane, Board b, Window win){
        window = win;
        contentPane = pane;
        gamePanel = new GameComponent(b);
        saveButton = new JButton("Save");
        statLabel = new JLabel("hello-bello");
        resetButton = new JButton("Reset");
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER,80,5));

        p.add(saveButton);
        p.add(statLabel);
        p.add(resetButton);
        System.out.println("The Thread name is " + Thread.currentThread().getName());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.restartGame();
            }
        });

        setLayout(new BorderLayout());
        JScrollPane sl = new JScrollPane(gamePanel);
        add(sl,BorderLayout.CENTER);
        add(p,BorderLayout.PAGE_END);
    }

    public Position getLastClick(){
        return gamePanel.getLastClick();
    }

    public void rePaint(){gamePanel.repaint();}

    public void setStatLabel(String text){
        statLabel.setText(text);
    }

}
