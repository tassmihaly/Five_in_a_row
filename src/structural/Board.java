package structural;

public class Board {
    private int board[][];
    private int height;
    private int width;
    public MinMaxStorage storage;

    public Board(int w, int h) {
        board = new int[w][h];
        this.height = h;
        this.width = w;
        storage = new MinMaxStorage();
    }

    public class MinMaxStorage {
        private int minHeight, minWidth, maxHeight, maxWidth;

        public MinMaxStorage() {
            init();
        }

        public void add(Position p) {
            if (p.getY() > maxHeight) maxHeight = p.getY();
            if (p.getX() > maxWidth) maxWidth = p.getX();
            if (p.getY() < minHeight) minHeight = p.getY();
            if (p.getX() < minWidth) minWidth = p.getX();
        }

        public int getMaxHeight() { return maxHeight; }

        public int getMaxWidth() { return maxWidth; }

        public int getMinHeight() {
            return minHeight;
        }

        public int getMinWidth() {
            return minWidth;
        }

        public void init(){
            minHeight = height;
            minWidth = width;
            maxHeight = 0;
            maxWidth = 0;
        }
    }

    public void print() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                System.out.print(board[j][i]);
            }
            System.out.print("\n");
        }
    }

    public void setBoardAt(Position p, int player) {
        board[p.getX()][p.getY()] = player;
        storage.add(p);
        print();
    }

    public int getBoardAt(Position p) {
        return board[p.getX()][p.getY()];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isWinner(Position lastStep) {
        Position s[] = {new Position(1, 1), new Position(0, 1), new Position(1, 0), new Position(-1, 1)};
        int color = getBoardAt(lastStep);
        for (Position p : s) {
            int counter = 1;
            for(int j = -1; j <= 1; j += 2){
                for (int i = 1; i < 5; i++) {
                    int x = lastStep.getX() + j * i * p.getX();
                    int y = lastStep.getY() + j * i * p.getY();
                    if (x >= width || y >= height || x < 0 || y < 0 || getBoardAt(new Position(x, y)) != color)
                        break;
                    else
                        counter++;
                }
            }
            if (counter >= 5) return true;
        }
        return false;
    }

    public void init(){
        for(int x = 0; x < height; ++x){
            for(int y = 0; y < width; ++y){
                board[y][x] = 0;
            }
        }
        storage.init();
    }
}


