package graphic;

import structural.Board;
import structural.Game;
import structural.GameStats;
import structural.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class MenuPanel extends JPanel {
    private JSlider levelSlider;
    private JButton rulesButton;
    private JPanel contentPane;
    private JButton gameButton;
    private JButton multiButton;
    private JButton reloadButton;
    private JButton singleButton;
    private JTextField heightTextLabel;
    private JTextField widthTextLabel;
    private JLabel errorLabel;
    private Game game;
    private Window window;
    private boolean multiPlayer;

    public MenuPanel(JPanel panel, Game g, Window w){
        window = w;
        multiPlayer = false;
        contentPane = panel;
        game = g;
        gameButton = new JButton("Start");
        gameButton.setPreferredSize(new Dimension(150,30));
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String w = widthTextLabel.getText();
                String h = heightTextLabel.getText();
                try {
                    int we = Integer.parseInt(w);
                    int he = Integer.parseInt(h);
                    if(we > 100 || he > 50){
                        errorLabel.setText("Max width is 100, max height is 50");
                    }
                    else if(we<8 ||he<8){
                        errorLabel.setText("Min width is 8, min height is 8");
                    }
                    else{
                        game.setBoard(new Board(we,he));
                        game.setPlayerParams(multiPlayer,levelSlider.getValue()+1);
                        game.start((byte) 0);
                        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                        cardLayout.show(contentPane,"game");
                    }
                }
                catch (NumberFormatException g) {
                    errorLabel.setText("Non-numeric input");
                }

            }
        });

        rulesButton = new JButton("Rules");
        rulesButton.setPreferredSize(new Dimension(150,30));
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"rules");
            }
        });

        reloadButton = new JButton("Reload");
        reloadButton.setPreferredSize(new Dimension(150,30));
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameStats gameStats = window.reLoadGame();
                if(gameStats.getLevel() != -1){
                    game.setBoard(gameStats.getBoard());
                    game.setPlayerParams(gameStats.isMultiplayer(),gameStats.getLevel());
                    game.start(gameStats.getRound());
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.show(contentPane,"game");
                }
                else{
                    errorLabel.setText("Game can't reload");
                }
            }
        });

        levelSlider = new JSlider(1,3,1);
        levelSlider.setSnapToTicks(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(1, new JLabel("Easy") );
        labelTable.put(2 , new JLabel("Medium") );
        labelTable.put(3, new JLabel("Hard") );
        levelSlider.setLabelTable(labelTable);
        levelSlider.setPaintLabels(true);
        levelSlider.setVisible(true);

        JPanel multiButtonsPane = new JPanel(new FlowLayout());
        JPanel textLabelsPane = new JPanel(new FlowLayout());
        JPanel sp = new JPanel();
        JPanel mainButtonsPane = new JPanel(new FlowLayout(30));
        JPanel rl = new JPanel();
        JPanel mtp = new JPanel();

        heightTextLabel = new JTextField(10);
        widthTextLabel = new JTextField(10);
        heightTextLabel.setText("20");
        widthTextLabel.setText("40");
        JLabel heightLabel = new JLabel("Height: ");
        JLabel widthLabel = new JLabel("Width: ");

        textLabelsPane.add(heightLabel);
        textLabelsPane.add(heightTextLabel);
        textLabelsPane.add(widthLabel);
        textLabelsPane.add(widthTextLabel);


        singleButton = new JButton("Single Player");
        multiButton = new JButton("Multiplayer");

        singleButton.setBackground(Color.CYAN);
        multiButton.setBackground(Color.GRAY);


        errorLabel = new JLabel();
        JLabel mainText = new JLabel("Five in a row");
        mainText.setFont(new Font("Serif", Font.PLAIN, 40));

        multiButtonsPane.add(singleButton);
        multiButtonsPane.add(multiButton);
        mtp.add(mainText);
        rl.add(errorLabel);
        mainButtonsPane.add(gameButton);
        mainButtonsPane.add(rulesButton);
        mainButtonsPane.add(reloadButton);
        sp.add(levelSlider);

        JPanel p = new JPanel(new GridLayout(6,1,10,10));
        p.add(mtp);
        p.add(textLabelsPane);
        p.add(multiButtonsPane);
        p.add(sp);
        p.add(mainButtonsPane);
        p.add(rl);
        add(p,BorderLayout.CENTER);


        singleButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               singleButton.setBackground(Color.CYAN);
               multiButton.setBackground(Color.GRAY);
               multiPlayer = false;
               levelSlider.setVisible(true);
           }
       });

       multiButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               multiButton.setBackground(Color.CYAN);
               singleButton.setBackground(Color.GRAY);
               multiPlayer = true;
               levelSlider.setVisible(false);
           }
       });
    }

}
