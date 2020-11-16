package Run;

import graphic.GameComponent;
import structural.Board;
import structural.Position;

import javax.swing.*;


public class Run {
    public static void main(String[] args) {
        Board b = new Board(19,8);
        b.setBoardAt(new Position(1,1),1);
        b.setBoardAt(new Position(2,1),2);
        b.setBoardAt(new Position(3,3),1);
        b.setBoardAt(new Position(4,5),2);
        JFrame frame = new JFrame("Demo");
        GameComponent a = new GameComponent(b);
        frame.add(a);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
