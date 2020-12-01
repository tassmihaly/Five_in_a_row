package structural;

public class Position {
    private int x,y;

    public Position(int x, int y){this.x = x; this.y = y;}

    public int getX(){return x;}
    public int getY(){return y;}

    public synchronized Position getFreshPosition(){
        return this;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addToX(int a){this.x = this.x+a;}
    public void addToY(int a){this.y = this.y+a;}
    public void add(int a, int b){this.x += a; this.y += b;}
}
