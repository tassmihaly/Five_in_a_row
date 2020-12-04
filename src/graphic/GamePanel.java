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
        statLabel = new JLabel();
        resetButton = new JButton("Reset");
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER,80,5));

        p.add(saveButton);
        p.add(statLabel);
        p.add(resetButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(window.saveGame()){
                    statLabel.setText("Game saved");
                }
                else{
                    statLabel.setText("Unsuccessful save");
                }
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

    public void updateBoard(Board b){
        gamePanel.updateBoard(b);
    }

}
