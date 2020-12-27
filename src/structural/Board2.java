package structural;

import java.util.BitSet;

public class Board2 {
    private int width;
    private int height;
    private int boardHeight;
    private int[] board;

    public Board2(int w, int h){
        width = w;
        height = h;
        if((height%16) == 0)
            boardHeight = height/16;
        else
            boardHeight = height/16+1;


        board = new int[width*boardHeight];
    }

    public int getBoardAt(int x, int y){
        int index = x * boardHeight + y/16;
        int shiftNum = (y%16)*2;
        return (board[index] >> shiftNum)&0x00000003;
    }

    public void setBoardAt(int x, int y, int value){
        int index = x * boardHeight + y/16;
        int shiftNum = (y%16)*2;
        int temp = ((board[index] >> shiftNum) & 0xFFFFFFFC) + value;
        temp = (temp << shiftNum);
        int temp2 = (board[index]<<(32-shiftNum))>>(32-shiftNum);
        board[index] = temp | temp2;
    }






}
