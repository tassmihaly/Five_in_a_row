package structural;
import graphic.Window;

import java.util.concurrent.CountDownLatch;

public class Human implements Player{

    private Window window;
    private char sign;

    public Human(char sign, Window win) {
        this.sign = sign;
        window = win;
    }

    @Override
    public Position step(){
        Position last = window.getLastClick();
        synchronized (last){
            try {
                last.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Position r = last.getFreshPosition();
            return r;
        }
    }

    public char getSign() {
        return sign;
    }
}
