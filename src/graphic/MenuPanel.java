package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton rulesButton;
    private JPanel contentPane;
    private JButton gameButton;

    public MenuPanel(JPanel panel){
        contentPane = panel;
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
