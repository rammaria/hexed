

import java.lang.*;
import java.util.*;
import java.util.LinkedList;
import java.util.*;

//each move should have a column, row and color
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

  public void setRow(int row){
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
    private static int[][] board = new int[9][7]; // [column][row]
    public static final int GREEN = 1; //constant color value assignments
    public static final int RED = 2;
    public static final int NO_COLOR = 0; // constant value for no color tile
    public static final int NO_TILE = 3; // constant value for no tile

    //directions and their assigned values
    public static final int NORTH = 0;
    public static final int NE = 1; //northeast
    public static final int NW = 2; //northwest
    public static final int SOUTH = 3;
    public static final int SE = 4; //southeast
    public static final int SW = 5; //southwest


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
          initColor = Hexed.RED;
      } if (color == 'g') {
          initColor = Hexed.GREEN;
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

  //generates the board with the starting board that has 6 alternately colored tiles
  public static int[][] setInitialBoard(int row, int col, int color){
    int[][] startingBoard = new int[9][7];

    // assign no color to all tiles.
    // loop through cols
    for (int i = 0; i < startingBoard.length; i++) {
        //loop through rows
        for (int j = 0; j < startingBoard[i].length; j++) {
            startingBoard[i][j] = Hexed.NO_COLOR;
        }
    }

    if (color == Hexed.GREEN) {
        //even
        if(col % 2 == 0){
          startingBoard[col][row] = Hexed.GREEN;
          startingBoard[col-1][row-1] = Hexed.RED;
          startingBoard[col-1][row-2] = Hexed.GREEN;
          startingBoard[col][row-2] = Hexed.RED;
          startingBoard[col+1][row-2] = Hexed.GREEN;
          startingBoard[col+1][row-1] = Hexed.RED;
        }else{
          startingBoard[col][row] = Hexed.GREEN;
          startingBoard[col-1][row] = Hexed.RED;
          startingBoard[col-1][row-1] = Hexed.GREEN;
          startingBoard[col][row-2] = Hexed.RED;
          startingBoard[col+1][row-1] = Hexed.GREEN;
          startingBoard[col+1][row] = Hexed.RED;
        }
    }

    if (color == Hexed.RED) {
        //even
        if(col % 2 == 0){
          startingBoard[col][row] = Hexed.RED;
          startingBoard[col-1][row-1] = Hexed.GREEN;
          startingBoard[col-1][row-2] = Hexed.RED;
          startingBoard[col][row-2] = Hexed.GREEN;
          startingBoard[col+1][row-2] = Hexed.RED;
          startingBoard[col+1][row-1] = Hexed.GREEN;
        }else{
          startingBoard[col][row] = Hexed.RED;
          startingBoard[col-1][row] = Hexed.GREEN;
          startingBoard[col-1][row-1] = Hexed.RED;
          startingBoard[col][row-2] = Hexed.GREEN;
          startingBoard[col+1][row-1] = Hexed.RED;
          startingBoard[col+1][row] = Hexed.GREEN;
        }
    }
    return startingBoard;
  }

  public LinkedList<Move> getMoves(){
    LinkedList<Move> validMoves = new LinkedList<>();



    return validMoves;
  }

/*  public boolean checkValidMove(int col, int row, int[][] board) {
      boolean valid = true;

      //check if the cell is blank
      if (board[col][row] == Hexed.NO_COLOR) {

      }
      return valid;
  }
*/

  public boolean checkNorthCell(int[][] board){
    boolean valid = false;
    int col = 0, row = 0;

    //checks all the cells in the board
    //cols
    for(int i = 0; i < board.length; i++){
      //rows
      for(int j = 0; j < board[i].length; j++){

        //checks if the cell is blank
        if (board[i][j] == Hexed.NO_COLOR) {
          row = j + 1;
          col = i;
        }else{
          valid = false;
        }
      }
    }

    return valid;
  }

}
