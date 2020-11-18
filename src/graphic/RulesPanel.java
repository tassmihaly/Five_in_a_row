package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesPanel extends JPanel {
    private JButton backButton;
    private JPanel contentPane;

    public RulesPanel(JPanel pane){
        contentPane = pane;
        backButton = new JButton("Back");
        JPanel p = new JPanel();
        p.add(backButton);
        setLayout(new BorderLayout());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"menu");
            }
        });


        String string = "<html><p style=\"text-align: center;\"><strong><span" +
                " style=\"font-size: 20px;\">J&aacute;t&eacute;kszab&aacute;ly</span></strong></p>\n" +
                "<p style=\"text-align: center;\"><br></p>\n" +
                "<p style=\"text-align: center;\">Ennek a j&aacute;t&eacute;knak " +
                "a p&aacute;ly&aacute;ja egy n&eacute;gyzetr&aacute;csos h&aacute;l&oacute;." +
                " Ketten j&aacute;tszanak, felv&aacute;ltva rakhatja le a k&eacute;t j&aacute;t&eacute;kos " +
                "a saj&aacute;t jeleiket, tipikusan k&ouml;r &eacute;s kereszt. Az a j&aacute;t&eacute;kos nyer," +
                " aki hamarabb tud lerakni &ouml;t szomsz&eacute;dos jelet, amelyek egy vonalon helyezkednek el. " +
                "Ez lehet f&uuml;ggőlegesen, v&iacute;zszintesen, de ak&aacute;r &aacute;tl&oacute;ban is. " +
                "A jelen nem visszavehetőek, illetve csak olyan helyre szabad rakni, ahol m&eacute;g nincsen jel. </p></html>";

        JLabel jl = new JLabel(string);
        add(jl,BorderLayout.CENTER);
        add(p,BorderLayout.PAGE_END);
    }

}
