package structural;

public class Board {
    private int board[][];
    private int height;
    private int width;
    public MinMaxStorage storage;

    public Board(int w, int h){
        board = new int[w][h];
        this.height = h;
        this.width = w;
        storage = new MinMaxStorage();
    }

    public class MinMaxStorage{
        private int minHeight,minWidth,maxHeight,maxWidth;
        public MinMaxStorage(){
            minHeight = height;
            minWidth = width;
            maxHeight = 0;
            maxWidth = 0;
        }
        public void add (Position p){
            if(p.getY() > maxHeight) maxHeight = p.getY();
            if(p.getX() > maxWidth) maxWidth = p.getX();
            if(p.getY() < minHeight) minHeight = p.getY();
            if(p.getX() < minWidth) minWidth = p.getX();
            System.out.println(", Max h:" + maxHeight + ", min h: " + minHeight + ", max w: " + maxWidth + ", min w: " + minWidth);
        }

        public int getMaxHeight() {return maxHeight;}
        public int getMaxWidth() {return maxWidth;}
        public int getMinHeight() {return minHeight;}
        public int getMinWidth() {return minWidth;}
    }


    public void print(){
        for(int i = 0; i<height; ++i){
            for(int j=0; j<width; ++j){
                System.out.print(board[j][i]);
            }
            System.out.print("\n");
        }
    }

    public void setBoardAt(Position p, int player){
        board[p.getX()][p.getY()] = player;
        storage.add(p);
        print();
    }

    public int getBoardAt(Position p){
        return board[p.getX()][p.getY()];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
