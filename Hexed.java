import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.LinkedList;


//@author rammaria
//each move should have a column, row and color
class Move{
  private int row, col, color;

  public Move(int col, int row, int color){
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

// public class State(){
//
// }

public class Hexed {
    private static Scanner kbd = new Scanner(System.in);
    private static int[][] board = new int[9][7]; // [column][row]
    public static final int GREEN = 1; //constant color value assignments
    public static final int RED = 2;
    public static final int NO_COLOR = 0; // constant value for no color tile
    public static final int NO_TILE = 3; // constant value for no tile

  public static void main(String[] args){

      ArrayList<Move> moves = new ArrayList<Move>();

      System.out.println("Enter initial game info.");
      System.out.print("Column: ");
      int initCol = kbd.nextInt();
      System.out.print("Row: ");
      int initRow = kbd.nextInt();
      System.out.print("Color[r/g]: ");
      char color = kbd.next().charAt(0);
      int initColor = 0;
      System.out.print("First Move[r/g]: ");
      char firstMove = kbd.next().charAt(0);
      int firstColor = 1;

      if (color == 'r') {
          initColor = Hexed.RED;
      } if (color == 'g') {
          initColor = Hexed.GREEN;
      }

      if (firstMove == 'r') {
          firstColor = Hexed.RED;
      } if (firstMove == 'g') {
          firstColor = Hexed.GREEN;
      }

      board = setInitialBoard(initRow, initCol, initColor);
      /*
      for (int i = 0; i < board.length; i++) {
          //loop through rows
          for (int j = 0; j < board[i].length; j++) {
              System.out.println("["+ i + "]" + "[" + j + "]" + " = " + board[i][j]);
          }
      }*/
      System.out.println(getMoves(firstMove).toString());

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

  //returns the list of valid moves
  public static ArrayList<Move> getMoves(int playingColor){
    ArrayList<Move> validMoves = new ArrayList<Move>();
    for (int i = 0; i < board.length; i++){
     for (int j = 0; j < board[i].length; j++){
       if (isValid(i, j, board, playingColor)){
         validMoves.add(new Move(i, j, playingColor));
       }
     }
   }
    return validMoves;
  }

  //check if move is valid for all the cells in different directions
  public static boolean isValid(int col, int row, int[][] board, int player) {

    if(board[col][row] != Hexed.NO_COLOR){
      return false;
    }

    boolean valid = false;
    int opponent = 0;

    if(player == Hexed.GREEN){
      opponent = Hexed.RED;
    }else{
      opponent = Hexed.GREEN;
    }

    // up
    int nextColNorth = col;
    int nextRowNorth = row + 1;

    try {
        if (board[nextColNorth][nextRowNorth] == opponent){
            if (checkNorthCell(nextRowNorth, nextColNorth, player, opponent, board)){
              valid = true;
            }
        }
    } catch(Exception e) {

    }


    // down
    int nextColSouth = col;
    int nextRowSouth = row - 1;

    try {
        if (board[nextColSouth][nextRowSouth] == opponent){
            if (checkSouthCell(nextRowSouth, nextColSouth, player, opponent, board)){
                  valid = true;
            }
        }
    } catch(Exception e) {

    }

    if (col % 2 == 0) {
        //northeast
        int nextColNE1 = col + 1;
        int nextRowNE1 = row;

        try {
            if (board[nextColNE1][nextRowNE1] == opponent){
                if (checkNorthEastCell(nextRowNE1, nextColNE1, player, opponent, board)){
                  valid = true;
                }
            }
        } catch(Exception e) {

        }

        //northwest
        int nextColNW1 = col - 1;
        int nextRowNW1 = row;

        try {
            if (board[nextColNW1][nextRowNW1] == opponent){
                if (checkNorthWestCell(nextRowNW1, nextColNW1, player, opponent, board)){
                    valid = true;
                }
            }
        } catch(Exception e) {

        }

        // southeast
        int nextColSE1 = col + 1;
        int nextRowSE1 = row - 1;

        try {
            if (board[nextColSE1][nextRowSE1] == opponent){
                if (checkSouthEastCell(nextRowSE1, nextColSE1, player, opponent, board)){
                  valid = true;
              }
          }
        } catch(Exception e) {

        }

        //southwest
        int nextColSW1 = col - 1;
        int nextRowSW1 = row - 1;

        try {
            if (board[nextColSW1][nextRowSW1] == opponent){
                if (checkSouthWestCell(nextRowSW1, nextColSW1, player, opponent, board)){
                  valid = true;
              }
          }
        } catch(Exception e) {

        }

    } else {

        //northeast
        int nextColNE = col + 1;
        int nextRowNE = row + 1;

        try {
            if (board[nextColNE][nextRowNE] == opponent){
                if (checkNorthEastCell(nextRowNE, nextColNE, player, opponent, board)){
                  valid = true;
              }
          }
        } catch(Exception e) {

        }

        //northwest
        int nextColNW = col - 1;
        int nextRowNW = row + 1;

        try {
            if (board[nextColNW][nextRowNW] == opponent){
                if (checkNorthWestCell(nextRowNW, nextColNW, player, opponent, board)){
                  valid = true;
              }
          }
        } catch(Exception e) {

        }

        //southeast
        int nextColSE = col + 1;
        int nextRowSE = row;

        try {
            if (board[nextColSE][nextRowSE] == opponent){
                if (checkSouthEastCell(nextRowSE, nextColSE, player, opponent, board)){
                  valid = true;
              }
          }
        } catch(Exception e) {

        }

        //southwest
        int nextRowSW = row;
        int nextColSW = col + 1;

        try {
            if (board[nextColSW][nextRowSW] == opponent){
                if (checkSouthWestCell(nextRowSW, nextColSW, player, opponent, board)){
                  valid = true;
              }
          }
        } catch(Exception e) {

        }
    }

    return valid;
  }

  //check if there are still valid moves
  public static boolean isHexed(ArrayList<Move> moves){
    boolean res = false;

    if(moves.size() == 0){
      res = true;
    } else {
      res = false;
    }

    return res;
  }

  public static boolean checkNorthCell (int row, int col, int player, int opponent, int[][] board) {
    boolean valid = false;
    int nextRow = row + 1;
    int nextCol = col;

    if (board[nextCol][nextRow] == player) {
      valid = true;
    }

    if (board[nextCol][nextRow] == Hexed.NO_COLOR){
      valid = false;
    }

    //checks if next cell is out of the hexed board
    if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
      valid = false;
    }

    if(board[nextCol][nextRow] == opponent){
      return checkNorthCell(nextRow, nextCol, player, opponent, board);
    }

    return valid;
  }

  public static boolean checkNorthWestCell (int row, int col, int player, int opponent, int[][] board) {
    boolean valid = true;

    int nextRow = row, nextCol = col;

    //if col is even
    if (col % 2 == 0) {
        nextRow = row;
        nextCol = col - 1;
    } else {
        nextRow = row + 1;
        nextCol = col - 1;
    }

    if (board[nextCol][nextRow] == player) {
      valid = true;
    }

    if (board[nextCol][nextRow] == Hexed.NO_COLOR){
      valid = false;
    }

    //checks if next cell is out of the hexed board
    if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
      valid = false;
    }

    if(board[nextCol][nextRow] == opponent){
      return checkNorthWestCell(nextRow, nextCol, player, opponent, board);
    }

    return valid;

  }

  public static boolean checkNorthEastCell (int row, int col, int player, int opponent, int[][] board) {
    boolean valid = true;
    // if col is odd
    int nextRow = row, nextCol = col;

    //if col is even
    if (col % 2 == 0) {
        nextRow = row;
        nextCol = col + 1;
    } else {
        nextRow = row + 1;
        nextCol = col + 1;
    }

    if (board[nextCol][nextRow] == player) {
      valid = true;
    }

    if (board[nextCol][nextRow] == Hexed.NO_COLOR){
      valid = false;
    }

    //checks if next cell is out of the hexed board
    if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
      valid = false;
    }

    if(board[nextCol][nextRow] == opponent){
      return checkNorthEastCell(nextRow, nextCol, player, opponent, board);
    }

    return valid;
  }

  public static boolean checkSouthCell (int row, int col, int player, int opponent, int[][] board) {
    boolean valid = true;
    // if col is odd
    int nextRow = row - 1;
    int nextCol = col;

    if (board[nextCol][nextRow] == player) {
      valid = true;
    }

    if (board[nextCol][nextRow] == Hexed.NO_COLOR){
      valid = false;
    }

    //checks if next cell is out of the hexed board
    if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
      valid = false;
    }

    if(board[nextCol][nextRow] == opponent){
      return checkSouthCell(nextRow, nextCol, player, opponent, board);
    }

    return valid;
  }

  public static boolean checkSouthEastCell (int row, int col, int player, int opponent, int[][] board) {
    boolean valid = true;
    // if col is odd
    int nextRow = row, nextCol = col;

    //if col is even
    if (col % 2 == 0) {
        nextRow = row - 1;
        nextCol = col + 1;
    } else {
        nextRow = row;
        nextCol = col + 1;
    }

    if (board[nextCol][nextRow] == player) {
      valid = true;
    }

    if (board[nextCol][nextRow] == Hexed.NO_COLOR){
      valid = false;
    }

    //checks if next cell is out of the hexed board
    if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
      valid = false;
    }

    if(board[nextCol][nextRow] == opponent){
      return checkSouthEastCell(nextRow, nextCol, player, opponent, board);
    }

    return valid;
  }

  public static boolean checkSouthWestCell (int row, int col, int player, int opponent, int[][] board) {
    boolean valid = true;
    // if col is odd
    int nextRow = row, nextCol = col;

    //if col is even
    if (col % 2 == 0) {
        nextRow = row - 1;
        nextCol = col - 1;
    } else {
        nextRow = row;
        nextCol = col - 1;
    }

    if (board[nextCol][nextRow] == player) {
      valid = true;
    }

    if (board[nextCol][nextRow] == Hexed.NO_COLOR){
      valid = false;
    }

    //checks if next cell is out of the hexed board
    if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
      valid = false;
    }

    if(board[nextCol][nextRow] == opponent){
      return checkSouthWestCell(nextRow, nextCol, player, opponent, board);
    }

    return valid;
  }

  public void move(Move move, int player, int[][] board) throws Exception{

    if (move == null){
      if (player == Hexed.GREEN){
        player = Hexed.RED;
      } else {
        player = Hexed.GREEN;
      }
    }

    int col = move.getCol();
    int row = move.getRow();
    int color = move.getColor();

    //checks if the move's color is equal to the current player's color
    if (color != player){
      throw new Exception("Error. Wrong Player.");
    }


    //checks if the move is valid and turns the color of the cells
    if(isValid(col, row, board, player)){
      board[col][row] = player;
      if(player == Hexed.GREEN){
        player = Hexed.RED;
      }else{
        player = Hexed.GREEN;
      }
      return;
    }else{
      throw new Exception("Not a Valid Move.");
    }
  }

}
