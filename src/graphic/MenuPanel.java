package graphic;

import structural.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton rulesButton;
    private JPanel contentPane;
    private JButton gameButton;
    private Game game;

    public MenuPanel(JPanel panel, Game g){
        contentPane = panel;
        game = g;
        gameButton = new JButton("Game");
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"game");
            }
        });

        rulesButton = new JButton("Rules");
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"rules");
            }
        });
        add(rulesButton);
        add(gameButton);
    }
}
