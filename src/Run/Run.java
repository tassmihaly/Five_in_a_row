package Run;

import graphic.GameComponent;
import graphic.Window;
import structural.Board;
import structural.Position;

import javax.swing.*;
import java.awt.*;


public class Run {
    public static void main(String[] args) throws InterruptedException {

        Board b = new Board(40,40);

        /*
        JFrame frame = new JFrame("Demo");
        GameComponent a = new GameComponent(b);
        JScrollPane sl = new JScrollPane(a);

        frame.setSize(500, 500);
        frame.add(sl);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.getLastClick();
        */


        Window w = new Window(b);
    }
}
