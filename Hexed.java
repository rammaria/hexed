import java.lang.*;
import java.util.*;

class Move{
  private int row, col;
  private char color;

  public Move(int row, int col, char color){
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

  public void setColor(char color){
    this.color = color;
  }

  public int getCol(){
    return col;
  }

  public int getRow(){
    return row;
  }

  public char getColor(){
    return color;
  }

  @Override
  public String toString(){
    return ("[" + this.col + ", " + this.row + ", " + this.color + "]");
  }
}

public class Hexed {
    private static Scanner kbd = new Scanner(System.in);
    private int[][] board = new int[7][9];

  public static void main(String[] args){

      System.out.println("Enter initial game info.");
      System.out.print("Column: ");
      int initCol = kbd.nextInt();
      System.out.print("Row: ");
      int initRow = kbd.nextInt();
      System.out.print("Color[r/g]: ");
      char initColor = kbd.next().charAt(0);

      setInitial(initRow, initCol, initColor);


  }

  public static void setInitial(int row, int col, char color){

    switch(color){
      case 'r':
        if(col % 2 == 0){

        }
        break;
      case 'g':


        break;
    }
  }

  public LinkedList<Move> getValidMoves(){

  }
}
