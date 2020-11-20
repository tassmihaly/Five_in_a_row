package graphic;

import structural.Board;
import structural.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CountDownLatch;


public class GameComponent extends JComponent {
    private final int cellSize = 25;
    private Board board;
    private boolean color = false;

    public GameComponent(Board b){
        board = b;
        setPreferredSize(new Dimension(board.getWidth()*cellSize, board.getHeight()*cellSize));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Position p = new Position(e.getX()/cellSize, e.getY()/cellSize);
                if(board.getBoardAt(p) == 0){
                    if(color) {
                        board.setBoardAt(p, 1);
                        color = false;
                    }else{
                        board.setBoardAt(p, 2);
                        color = true;
                    }
                    repaint();
                    board.isWinner(p);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g){
        Graphics2D graphic2d = (Graphics2D) g;
        drawField(graphic2d);
    }

    private void drawField(Graphics2D graphic2d){
        graphic2d.setColor(Color.black);
        graphic2d.setStroke(new BasicStroke(1));
        for(int i = 0; i <= board.getHeight()*cellSize; i+= cellSize){
            graphic2d.drawLine(0,i,board.getWidth()*cellSize,i);
        }
        for(int i = 0; i <= board.getWidth()*cellSize; i+= cellSize){
            graphic2d.drawLine(i,0,i,board.getHeight()*cellSize);
        }
        Position p;
        for(int x = board.storage.getMinWidth(); x <= board.storage.getMaxWidth(); x++){
            for(int y = board.storage.getMinHeight(); y <= board.storage.getMaxHeight(); y++){
                p = new Position(x,y);
                if(board.getBoardAt(p)==1)
                    drawCircle(p,graphic2d);
                else if(board.getBoardAt(p) == 2)
                    drawCross(p,graphic2d);
            }
        }
    }

    private void drawCross(Position p, Graphics2D graphic2d){
        graphic2d.setColor(Color.red);
        graphic2d.setStroke(new BasicStroke(3));
        graphic2d.drawLine(p.getX()*cellSize+7,p.getY()*cellSize+7,p.getX()*cellSize+cellSize-7, p.getY()*cellSize+cellSize-7);
        graphic2d.drawLine(p.getX()*cellSize+cellSize-7,p.getY()*cellSize+7,p.getX()*cellSize+7, p.getY()*cellSize+cellSize-7);
    }

    private void drawCircle(Position p, Graphics2D graphic2d){
        graphic2d.setColor(Color.blue);
        graphic2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic2d.setStroke(new BasicStroke(3));
        graphic2d.drawOval(p.getX()*cellSize +5,p.getY()*cellSize +5,cellSize-10,cellSize-10);
    }

    public boolean getLastClick() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("x:"+e.getX()+"-y:"+e.getY());
                c.countDown();
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        c.await();
        System.out.println("ittennnn");
        return true;
    }



}
