

import java.lang.*;
import java.util.*;
import java.util.LinkedList;
import java.util.*;

class Move{
  private int row, col, color;

  public Move(int row, int col, int color){
    this.col = col;
    this.row = row;
    this.color = color;
  }

  public void setCol(int col){
    this.col = col;
  }

  public void setRow(int Row){
    this.row = row;
  }

  public void setColor(int color){
    this.color = color;
  }

  public int getCol(){
    return col;
  }

  public int getRow(){
    return row;
  }

  public int getColor(){
    return color;
  }

  @Override
  public String toString(){
    return ("[" + this.col + ", " + this.row + ", " + (color == 'g' ? "green" : "red")+ "]");
  }
}

public class Hexed {
    private static Scanner kbd = new Scanner(System.in);
    private static int[][] board = new int[7][9];
    public static final int GREEN = 0; //constant color value assignments
    public static final int RED = 1;

  public static void main(String[] args){

      System.out.println("Enter initial game info.");
      System.out.print("Column: ");
      int initCol = kbd.nextInt();
      System.out.print("Row: ");
      int initRow = kbd.nextInt();
      System.out.print("Color[r/g]: ");
      char color = kbd.next().charAt(0);
      int initColor = 1;

      if (color == 'r') {
          initColor = 1;
      } if (color == 'g') {
          initColor = 0;
      }
      
      setInitialBoard(initRow, initCol, initColor, board);


  }

  public static int[][] setInitialBoard(int row, int col, int color, int[][] board){
    int[][] startingBoard = new int[7][9];

    switch(color){
      case '0':
          if(col % 2 == 0){
            board[col][row] = 0;
            board[col-1][row-1] = 1;
            board[col-1][row-2] = 0;
            board[col][row-2] = 1;
            board[col+1][row-2] = 0;
            board[col+1][row-1] = 1;
          }else{
            board[col][row] = 0;
            board[col-1][row] = 1;
            board[col-1][row-1] = 0;
            board[col][row-2] = 1;
            board[col+1][row-1] = 0;
            board[col+1][row] = 1;
          }
          break;
      case '1':
        if(col % 2 == 0){
          board[col][row] = 1;
          board[col-1][row-1] = 0;
          board[col-1][row-2] = 1;
          board[col][row-2] = 0;
          board[col+1][row-2] = 1;
          board[col+1][row-1] = 0;
        }else{
          board[col][row] = 1;
          board[col-1][row] = 0;
          board[col-1][row-1] = 1;
          board[col][row-2] = 0;
          board[col+1][row-1] = 1;
          board[col+1][row] = 0;
        }
        break;
    }

    return startingBoard;
  }

  public LinkedList<Move> getMoves(){
    LinkedList<Move> validMoves = new LinkedList<>();

    return validMoves;
  }
}
