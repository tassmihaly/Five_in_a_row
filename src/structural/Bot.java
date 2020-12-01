package structural;
import java.util.HashMap;
import java.util.Map;

public class Bot implements Player{
    private Board board;
    private char sign;
    private int level;
    private int maxScore = 1000000;
    private int counter = 0;
    private int scoreCallCount = 0;

    public Bot(char sign, Board b, int level) {
        this.sign = sign;
        board = b;
        this.level = level;
    }


    @Override
    public Position step() {
        int opponentSign = sign == 'O' ? 2 : 1;
        ScorePosData p = minimax(new Board(board), 0, -maxScore,maxScore, (byte) opponentSign);

        System.out.println("bot score:"+ p.getScore());
        System.out.println("minimax was called "+counter+" times");
        System.out.println("score was called "+scoreCallCount+" times");
        counter = 0;
        scoreCallCount = 0;
        return p.getPos();

    }

    class ScorePosData{
        Position pos;
        int score;

        public Position getPos() { return pos; }

        public int getScore() { return score; }

        public void setPos(Position pos) { this.pos = pos; }

        public void setScore(int score) { this.score = score; }

        public ScorePosData(Position pos, int score) {
            this.pos = pos;
            this.score = score;
        }
    }



    private ScorePosData minimax(Board b, int depth, int alpha, int beta, byte lastStepped){
        counter++;
        if(depth == level) return new ScorePosData(new Position(0,0),score(b, (byte) (lastStepped)));
        ScorePosData score;
        if(lastStepped == 1){
            score = new ScorePosData(new Position(board.getWidth() / 2, board.getHeight() / 2), maxScore);
            for(int x = Math.max(0,b.storage.getMinWidth()-1); x < Math.min(b.getWidth(),b.storage.getMaxWidth()+2); ++x){
                for(int y = Math.max(0,b.storage.getMinHeight()-1); y<Math.min(b.getHeight(),b.storage.getMaxHeight()+2); ++y){
                    Position f = new Position(x,y);
                    if(b.getBoardAt(f) == 0) {
                        Board tmpBoard = new Board(b);
                        tmpBoard.setBoardAt(f, (byte) 2);
                        if(tmpBoard.isWinner(f)) return new ScorePosData(f,-maxScore*2);
                        ScorePosData temp = minimax(tmpBoard, depth + 1, alpha, beta, (byte)((lastStepped%2)+1));
                        if(score.getScore() > temp.getScore()) {
                            score.setScore(temp.getScore());
                            score.setPos(f);
                        }
                        beta = Math.min(beta,temp.getScore());
                        if(beta <= alpha) {
                            return score;
                        }
                    }
                }
            }
        }
        else{
            score = new ScorePosData(new Position(board.getWidth() / 2, board.getHeight() / 2), -maxScore);
            for(int x = Math.max(0,b.storage.getMinWidth()-1); x<Math.min(b.getWidth(),b.storage.getMaxWidth()+2); ++x){
                for(int y = Math.max(0,b.storage.getMinHeight()-1); y<Math.min(b.getHeight(),b.storage.getMaxHeight()+2); ++y){
                    Position f = new Position(x,y);
                    if(b.getBoardAt(f) == 0) {
                        Board tmpBoard = new Board(b);
                        tmpBoard.setBoardAt(f, (byte) 1);
                        if(tmpBoard.isWinner(f)) return new ScorePosData(f,maxScore*2);
                        ScorePosData temp = minimax(tmpBoard, depth + 1, alpha, beta,(byte) ((lastStepped%2)+1));
                        if(score.getScore() < temp.getScore()){
                            score.setScore(temp.getScore());
                            score.setPos(f);
                        }
                        alpha = Math.max(alpha, temp.getScore());
                        if (beta <= alpha) {
                            return score;
                        }
                    }

                }
            }
        }
        return score;
    }

    private class ScoreData {
        int[] XC = new int[3];
        int[] OC = new int[3];
        int[] XO = new int[3];
        int[] OO = new int[3];

        int[] scoreTable ={
                10,  //closed 2 row
                100, // closed 3 row
                100,  // opened 2 row
                1000,  // opened 3 row
                1200  // closed 4 row
        };
        int[] opponentScoreTable ={
                12,  //closed 2 row
                120, //closed 3 row
                120,  //opened 2 row
                1200,  //opened 3 row
                1400  //closed 4 row
        };

        private void fill(int player, int len, boolean closed) {
            if (len < 2 || len > 4) return;
            if (player == 1) {
                if (closed) {
                    OC[len-2]++;
                }
                else {
                    OO[len-2]++;
                }
            }
            else {
                if (closed) {
                    XC[len-2]++;
                }
                else {
                    XO[len-2]++;
                }
            }
        }

        //returns 1/-1 if score evaluation can be terminated, fills value otherwise
        public int evaluate(int active, int player, int len, boolean b1, boolean b2) {
            if (b1 && b2) return 0;
            if (len > 4) return 1; //win
            if (active != player && len > 3) return -1; //opponent wins next turn
            fill(active, len, b1 ^ b2);
            return 0;
        }



        public  int makeScore(byte justSteppedPlayer){
            /*
            System.out.println("xc: "+XC[0]+","+XC[1]+","+XC[2]);
            System.out.println("xo: "+XO[0]+","+XO[1]+","+XO[2]);
            System.out.println("oc: "+OC[0]+","+OC[1]+","+OC[2]);
            System.out.println("oo: "+OO[0]+","+OO[1]+","+OO[2]);

             */

            if(justSteppedPlayer == 1) {
                if(XO[2] > 0 || XC[2] > 0) return -maxScore;
                if(OO[2] > 0) return maxScore;
                if(OC[2] == 0 && XO[1] > 0) return -maxScore;
                if(OC[2] > 1) return maxScore * 9/10;
                if(XO[1] > 1) return -maxScore * 9/10;

            }
            else {
                if (OO[2] > 0 || OC[2] > 0) return maxScore;
                if (XO[2] > 0) return -maxScore;
                if (XC[2] == 0 && OO[1] > 0) return maxScore;
                if (XC[2] > 1) return -maxScore * 9 / 10;
                if (OO[1] > 1) return maxScore * 9 / 10;
            }


            int[] ot = justSteppedPlayer == 1 ? scoreTable : opponentScoreTable;
            int[] xt = justSteppedPlayer == 2 ? scoreTable : opponentScoreTable;

            return  OC[0]*ot[0] - XC[0]*xt[0]+
                        OC[1]*ot[1] - XC[1]*xt[1]+
                        OO[0]*ot[2] - XO[0]*xt[2] +
                        OO[1]*ot[3] - XO[1]*xt[3]+
                        OC[2]*ot[4] - XC[2]*xt[4];

        }
    }
    /// player 1, O: maximizing player
    /// player 2, X: minimal player

    public int score(Board b, byte lastStepped){
        scoreCallCount++;
        ScoreData scoreData = new ScoreData();
        int holder = lastStepped == 1 ? 1 : -1;

        for(int x = b.storage.getMinWidth(); x <= b.storage.getMaxWidth(); ++x) {
            int playerCounter = 0;
            boolean closed = b.storage.getMinHeight() == 0;
            byte active = b.getBoardAt(new Position(x,b.storage.getMinHeight()));

            // vertical
            for(int y = b.storage.getMinHeight(); y <= b.storage.getMaxHeight(); ++y){
                byte val = b.getBoardAt(new Position(x,y));
                if (val == active) {
                    if (active != 0) playerCounter++;
                }
                else {
                    if (active != 0) {
                        int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, val != 0);
                        if (ret != 0) return ret * holder * maxScore;
                    }
                    closed = active != 0;
                    active = val;
                    playerCounter = 1;
                }
            }
            if (active != 0) {
                int ret = scoreData.evaluate(active,lastStepped,playerCounter,closed,(b.storage.getMaxHeight() == b.getHeight()-1));
                if (ret != 0) return ret*holder*maxScore;
            }

            // left diag from top side
            playerCounter = 0;
            closed = b.storage.getMinHeight() == 0 || x == 0;
            Position iterate = new Position(x,b.storage.getMinHeight());
            active = b.getBoardAt(iterate);

            while(iterate.getX() <= b.storage.getMaxWidth() && iterate.getY() <= b.storage.getMaxHeight()){
                byte val = b.getBoardAt(iterate);
                if (val == active) {
                    if (active != 0) playerCounter++;
                }
                else {
                    if (active != 0) {
                        int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, val != 0);
                        if (ret != 0) return ret *holder* maxScore; //todo: max val
                    }
                    closed = active != 0;
                    active = val;
                    playerCounter = 1;
                }
                iterate.add(1,1);
            }
            if (active != 0) {
                boolean endClose = (iterate.getX() == b.getWidth() || iterate.getY() == b.getHeight());
                int ret = scoreData.evaluate(active,lastStepped,playerCounter,closed,endClose);
                if (ret != 0) return ret*holder*maxScore; //todo: max val
            }

            // right diag from top side
            playerCounter = 0;
            closed = b.storage.getMinHeight() == 0 || x == 0;
            iterate = new Position(x,b.storage.getMinHeight());
            active = b.getBoardAt(iterate);

            while(iterate.getX() >= b.storage.getMinWidth() && iterate.getY() <= b.storage.getMaxHeight()){
                byte val = b.getBoardAt(iterate);
                if (val == active) {
                    if (active != 0) playerCounter++;
                }
                else {
                    if (active != 0) {
                        int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, val != 0);
                        if (ret != 0) return ret *holder* maxScore; //todo: max val
                    }
                    closed = active != 0;
                    active = val;
                    playerCounter = 1;
                }
                iterate.add(-1,1);
            }
            if (active != 0) {
                boolean endClose = (iterate.getX() == -1 || iterate.getY() == b.getHeight());
                int ret = scoreData.evaluate(active,lastStepped,playerCounter,closed,endClose);
                if (ret != 0) return ret*holder*maxScore; //todo: max val
            }
        }


        for(int y = b.storage.getMinHeight(); y <= b.storage.getMaxHeight(); ++y) {

            int playerCounter = 0;
            boolean closed = b.storage.getMinWidth() == 0;
            byte active = b.getBoardAt(new Position(b.storage.getMinWidth(),y));


            //horizontal
            for (int x = b.storage.getMinWidth(); x <= b.storage.getMaxWidth(); ++x) {
                byte val = b.getBoardAt(new Position(x, y));
                if (val == active) {
                    if (active != 0) playerCounter++;
                } else {
                    if (active != 0) {
                        int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, val != 0);
                        if (ret != 0) return ret *holder* maxScore; //todo: max val
                    }
                    closed = active != 0;
                    active = val;
                    playerCounter = 1;
                }
            }
            if (active != 0) {
                int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, b.storage.getMaxWidth() == b.getWidth() - 1);
                if (ret != 0) return ret *holder* maxScore; //todo: max val
            }

            playerCounter = 0;
            closed = b.storage.getMinWidth() == 0 || y == 0;
            Position iterate = new Position(b.storage.getMinWidth(),y);
            active = b.getBoardAt(iterate);

            while(iterate.getX() <= b.storage.getMaxWidth() && iterate.getY() <= b.storage.getMaxHeight() && iterate.getY()>b.storage.getMinHeight()){
                byte val = b.getBoardAt(iterate);
                if (val == active) {
                    if (active != 0) playerCounter++;
                }
                else {
                    if (active != 0) {
                        int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, val != 0);
                        if (ret != 0) return ret *holder* maxScore; //todo: max val
                    }
                    closed = active != 0;
                    active = val;
                    playerCounter = 1;
                }
                iterate.add(1,1);
            }
            if (active != 0) {
                boolean endClose = (iterate.getX() == b.getWidth() || iterate.getY() == b.getHeight());
                int ret = scoreData.evaluate(active,lastStepped,playerCounter,closed,endClose);
                if (ret != 0) return ret*maxScore; //todo: max val
            }

            playerCounter = 0;
            closed = b.storage.getMaxWidth() == b.getWidth()-1 || y == 0;
            iterate = new Position(b.storage.getMaxWidth(),y);
            active = b.getBoardAt(iterate);

            while(iterate.getX() >= b.storage.getMinWidth()  && iterate.getY() <= b.storage.getMaxHeight() && iterate.getY()>b.storage.getMinHeight()){
                byte val = b.getBoardAt(iterate);
                if (val == active) {
                    if (active != 0) playerCounter++;
                }
                else {
                    if (active != 0) {
                        int ret = scoreData.evaluate(active, lastStepped, playerCounter, closed, val != 0);
                        if (ret != 0) return ret * maxScore; //todo: max val
                    }
                    closed = active != 0;
                    active = val;
                    playerCounter = 1;
                }
                iterate.add(-1,1);
            }
            if (active != 0) {
                boolean endClose = (iterate.getX()-1 == 0 || iterate.getY() == b.getHeight());
                int ret = scoreData.evaluate(active,lastStepped,playerCounter,closed,endClose);
                if (ret != 0) return ret*maxScore; //todo: max val
            }

        }
        return scoreData.makeScore(lastStepped);
    }

    private int simpleScore(Board b, byte lastStepped){
        return 0;
    }

    public char getSign(){
        return sign;
    }
}
