

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
    private static int[][] board = new int[9][7];
    public static final int GREEN = 0; //constant color value assignments
    public static final int RED = 1;
    public static final int NO_COLOR = 2; // constant value for no color tile
    public static final int NO_TILE = 3; // constant value for no tile

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

      board = setInitialBoard(initRow, initCol, initColor);
      /*
      for (int i = 0; i < board.length; i++) {
          //loop through rows
          for (int j = 0; j < board[i].length; j++) {
              System.out.println("["+ i + "]" + "[" + j + "]" + " = " + board[i][j]);
          }
      }*/

  }

  public static int[][] setInitialBoard(int row, int col, int color){
    int[][] startingBoard = new int[9][7];

    // assign no color to all tiles.
    // loop through cols
    for (int i = 0; i < startingBoard.length; i++) {
        //loop through rows
        for (int j = 0; j < startingBoard[i].length; j++) {
            startingBoard[i][j] = 2;
        }
    }

    if (color == 0) {
        if(col % 2 == 0){
          startingBoard[col][row] = 0;
          startingBoard[col-1][row-1] = 1;
          startingBoard[col-1][row-2] = 0;
          startingBoard[col][row-2] = 1;
          startingBoard[col+1][row-2] = 0;
          startingBoard[col+1][row-1] = 1;
        }else{
          startingBoard[col][row] = 0;
          startingBoard[col-1][row] = 1;
          startingBoard[col-1][row-1] = 0;
          startingBoard[col][row-2] = 1;
          startingBoard[col+1][row-1] = 0;
          startingBoard[col+1][row] = 1;
        }
    }

    if (color == 1) {
        if(col % 2 == 0){
          startingBoard[col][row] = 1;
          startingBoard[col-1][row-1] = 0;
          startingBoard[col-1][row-2] = 1;
          startingBoard[col][row-2] = 0;
          startingBoard[col+1][row-2] = 1;
          startingBoard[col+1][row-1] = 0;
        }else{
          startingBoard[col][row] = 1;
          startingBoard[col-1][row] = 0;
          startingBoard[col-1][row-1] = 1;
          startingBoard[col][row-2] = 0;
          startingBoard[col+1][row-1] = 1;
          startingBoard[col+1][row] = 0;
        }
    }

    return startingBoard;
  }

  public LinkedList<Move> getMoves(){
    LinkedList<Move> validMoves = new LinkedList<>();


    return validMoves;
  }
}
