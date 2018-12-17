import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


//@author rammaria
//each move should have a column, row and color
class Move {
    private int row, col, color;

    public Move() {
    }

    public Move(int col, int row, int color) {
        this.col = col;
        this.row = row;
        this.color = color;
    }

    public Move(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return ("[" + this.col + ", " + this.row + ", " + (color == 1 ? "green" : "red") + "]");
    }
}

class State {
    private int[][] board;
    private int player;
    private int color;

    public State() {
        this.board = board;
        this.player = player;
        this.color = color;
    }


}

public class Hexed {
    private static Scanner kbd = new Scanner(System.in);
    private static Random randomMoveGenerator = new Random();

    private static int[][] board = new int[9][7]; // [column][row]
    public static final int GREEN = 1; //constant color value assignments
    public static final int RED = 2;
    public static final int NO_COLOR = 0; // constant value for no color tile
    public static final int NO_TILE = 3; // constant value for no tile

    public static void main(String[] args) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean gameOver = false, playerHexxed = false, opHexxed = false;

        System.out.println("Enter initial game info.");
        System.out.print("Column: ");
        int initCol = kbd.nextInt();
        System.out.print("Row: ");
        int initRow = kbd.nextInt();
        System.out.print("Color[r/g]: ");
        char color = kbd.next().charAt(0);
        int initColor = 0;

        System.out.print("\nWhat player am I? [1/2]: ");
        int player = kbd.nextInt();
        System.out.print("What is my color?[r/g]: ");
        char pColor = kbd.next().charAt(0);
        int playerColor = 1;
        int opColor = 2;
        int firstColor = 1;

        if (color == 'r') {
            initColor = Hexed.RED;
        }
        if (color == 'g') {
            initColor = Hexed.GREEN;
        }

        if (pColor == 'r') {
            playerColor = Hexed.RED;
            opColor = Hexed.GREEN;
        }
        if (pColor == 'g') {
            playerColor = Hexed.GREEN;
            opColor = Hexed.RED;
        }

        board = setInitialBoard(initCol, initRow, initColor);
      /*
      for (int i = 0; i < board.length; i++) {
          //loop through rows
          for (int j = 0; j < board[i].length; j++) {
              System.out.println("["+ i + "]" + "[" + j + "]" + " = " + board[i][j]);
          }
      }*/

      ArrayList<Move> playerMoves = new ArrayList<>();

      while(!gameOver){

        if (player == 1) {

            Move playerMove = new Move();
            System.out.print("Move:");
            playerMoves = getMoves(playerColor);
            playerMove = getRandomMove(playerMoves);
            System.out.println(playerMove.toString());

            try {
                move(playerMove, playerColor, board);
            } catch(Exception e) {
              e.printStackTrace();
            }
            playerMoves.clear();

            // for (int i = 0; i < board.length; i++) {
            //     //loop through rows
            //     for (int j = 0; j < board[i].length; j++) {
            //         System.out.println("["+ i + "]" + "[" + j + "]" + " = " + board[i][j]);
            //     }
            // }

            System.out.print("Enter opponent's move: ");
            int opCol = kbd.nextInt();
            int opRow = kbd.nextInt();
            Move opMove = new Move(opCol, opRow, opColor);

            try {
                move(opMove, opColor, board);
            } catch(Exception e) {
                e.printStackTrace();
            }

            // for (int i = 0; i < board.length; i++) {
            //     //loop through rows
            //     for (int j = 0; j < board[i].length; j++) {
            //         System.out.println("["+ i + "]" + "[" + j + "]" + " = " + board[i][j]);
            //     }
            // }

        } else {
            System.out.print("Enter opponent's move: ");
            int opCol = kbd.nextInt();
            int opRow = kbd.nextInt();
            Move opMove = new Move(opCol, opRow, opColor);

            try {
                move(opMove, opColor, board);
            } catch(Exception e) {
              e.printStackTrace();
            }

            Move playerMove = new Move();
            System.out.print("Move:");
            playerMoves = getMoves(playerColor);
            playerMove = getRandomMove(playerMoves);
            System.out.println(playerMove.toString());

            try {
                move(playerMove, playerColor, board);
            } catch(Exception e) {

            }
            playerMoves.clear();
        }
    }

    }

    //generates the board with the starting board that has 6 alternately colored tiles
    public static int[][] setInitialBoard(int col, int row, int color) {
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
            if (col % 2 == 0) {
                startingBoard[col][row] = Hexed.GREEN;
                startingBoard[col - 1][row - 1] = Hexed.RED;
                startingBoard[col - 1][row - 2] = Hexed.GREEN;
                startingBoard[col][row - 2] = Hexed.RED;
                startingBoard[col + 1][row - 2] = Hexed.GREEN;
                startingBoard[col + 1][row - 1] = Hexed.RED;
            } else {
                startingBoard[col][row] = Hexed.GREEN;
                startingBoard[col - 1][row] = Hexed.RED;
                startingBoard[col - 1][row - 1] = Hexed.GREEN;
                startingBoard[col][row - 2] = Hexed.RED;
                startingBoard[col + 1][row - 1] = Hexed.GREEN;
                startingBoard[col + 1][row] = Hexed.RED;
            }
        }

        if (color == Hexed.RED) {
            //even
            if (col % 2 == 0) {
                startingBoard[col][row] = Hexed.RED;
                startingBoard[col - 1][row - 1] = Hexed.GREEN;
                startingBoard[col - 1][row - 2] = Hexed.RED;
                startingBoard[col][row - 2] = Hexed.GREEN;
                startingBoard[col + 1][row - 2] = Hexed.RED;
                startingBoard[col + 1][row - 1] = Hexed.GREEN;
            } else {
                startingBoard[col][row] = Hexed.RED;
                startingBoard[col - 1][row] = Hexed.GREEN;
                startingBoard[col - 1][row - 1] = Hexed.RED;
                startingBoard[col][row - 2] = Hexed.GREEN;
                startingBoard[col + 1][row - 1] = Hexed.RED;
                startingBoard[col + 1][row] = Hexed.GREEN;
            }
        }
        return startingBoard;
    }

    //returns the list of valid moves
    public static ArrayList<Move> getMoves(int playingColor) {
        ArrayList<Move> validMoves = new ArrayList<Move>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isValid(i, j, board, playingColor, false)) {
                    validMoves.add(new Move(i, j, playingColor));
                }
            }
        }
        return validMoves;
    }

    //check if move is valid for all the cells in different directions
    public static boolean isValid(int col, int row, int[][] board, int player, boolean occupied) {

        if (board[col][row] != Hexed.NO_COLOR) {
            return false;
        }

        boolean valid = false;
        int opponent = 0;

        if (player == Hexed.GREEN) {
            opponent = Hexed.RED;
        } else {
            opponent = Hexed.GREEN;
        }

        // up
        int nextColNorth = col;
        int nextRowNorth = row + 1;

        try {
            if (board[nextColNorth][nextRowNorth] == opponent) {
                if (checkNorthCell(nextRowNorth, nextColNorth, player, opponent, board, occupied)) {
                    valid = true;
                }
            }
        } catch (Exception e) {

        }


        // down
        int nextColSouth = col;
        int nextRowSouth = row - 1;

        try {
            if (board[nextColSouth][nextRowSouth] == opponent) {
                if (checkSouthCell(nextRowSouth, nextColSouth, player, opponent, board, occupied)) {
                    valid = true;
                }
            }
        } catch (Exception e) {

        }

        if (col % 2 == 0) {
            //northeast
            int nextColNE1 = col + 1;
            int nextRowNE1 = row;

            try {
                if (board[nextColNE1][nextRowNE1] == opponent) {
                    if (checkNorthEastCell(nextRowNE1, nextColNE1, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

            //northwest
            int nextColNW1 = col - 1;
            int nextRowNW1 = row;

            try {
                if (board[nextColNW1][nextRowNW1] == opponent) {
                    if (checkNorthWestCell(nextRowNW1, nextColNW1, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

            // southeast
            int nextColSE1 = col + 1;
            int nextRowSE1 = row - 1;

            try {
                if (board[nextColSE1][nextRowSE1] == opponent) {
                    if (checkSouthEastCell(nextRowSE1, nextColSE1, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

            //southwest
            int nextColSW1 = col - 1;
            int nextRowSW1 = row - 1;

            try {
                if (board[nextColSW1][nextRowSW1] == opponent) {
                    if (checkSouthWestCell(nextRowSW1, nextColSW1, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

        } else {

            //northeast
            int nextColNE = col + 1;
            int nextRowNE = row + 1;

            try {
                if (board[nextColNE][nextRowNE] == opponent) {
                    if (checkNorthEastCell(nextRowNE, nextColNE, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

            //northwest
            int nextColNW = col - 1;
            int nextRowNW = row + 1;

            try {
                if (board[nextColNW][nextRowNW] == opponent) {
                    if (checkNorthWestCell(nextRowNW, nextColNW, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

            //southeast
            int nextColSE = col + 1;
            int nextRowSE = row;

            try {
                if (board[nextColSE][nextRowSE] == opponent) {
                    if (checkSouthEastCell(nextRowSE, nextColSE, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }

            //southwest
            int nextRowSW = row;
            int nextColSW = col + 1;

            try {
                if (board[nextColSW][nextRowSW] == opponent) {
                    if (checkSouthWestCell(nextRowSW, nextColSW, player, opponent, board, occupied)) {
                        valid = true;
                    }
                }
            } catch (Exception e) {

            }
        }

        return valid;
    }

    //check if there are still valid moves
    public static boolean isHexed(ArrayList<Move> moves) {
        boolean res = false;

        if (moves.size() == 0) {
            res = true;
        } else {
            res = false;
        }

        return res;
    }

    public static boolean checkNorthCell(int row, int col, int player, int opponent, int[][] board, boolean occupied) {
        boolean valid = false;
        int nextRow = row + 1;
        int nextCol = col;

        if (board[nextCol][nextRow] == player) {
            valid = true;
        }

        if (board[nextCol][nextRow] == Hexed.NO_COLOR) {
            valid = false;
        }

        //checks if next cell is out of the hexed board
        if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
            valid = false;
        }

        // if cell is opponent and is occupied, tiles will change as long as the path is validMoves
        // if not occupied, nothing happends
        if (board[nextCol][nextRow] == opponent) {
            if (occupied) {
                if (checkNorthCell(nextRow, nextCol, player, opponent, board,occupied)) {
                    board[col][row] = player;
                    return true;
                } else {
                    return false;
                }
            } else {
                return checkNorthCell(nextRow, nextCol, player, opponent, board,occupied);
            }
        }

        return valid;
    }

    public static boolean checkNorthWestCell(int row, int col, int player, int opponent, int[][] board, boolean occupied) {
        boolean valid = false;

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

        if (board[nextCol][nextRow] == Hexed.NO_COLOR) {
            valid = false;
        }

        //checks if next cell is out of the hexed board
        if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
            valid = false;
        }

        // if cell is opponent and is occupied, tiles will change as long as the path is validMoves
        // if not occupied, nothing happends
        if (board[nextCol][nextRow] == opponent) {
            if (occupied) {
                if (checkNorthWestCell(nextRow, nextCol, player, opponent, board, occupied)) {
                    board[col][row] = player;
                    return true;
                } else {
                    return false;
                }
            } else {
                return checkNorthWestCell(nextRow, nextCol, player, opponent, board, occupied);
            }
        }

        return valid;

    }

    public static boolean checkNorthEastCell(int row, int col, int player, int opponent, int[][] board, boolean occupied) {
        boolean valid = false;
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

        if (board[nextCol][nextRow] == Hexed.NO_COLOR) {
            valid = false;
        }

        //checks if next cell is out of the hexed board
        if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
            valid = false;
        }

        // if cell is opponent and is occupied, tiles will change as long as the path is validMoves
        // if not occupied, nothing happends
        if (board[nextCol][nextRow] == opponent) {
            if (occupied) {
                if (checkNorthEastCell(nextRow, nextCol, player, opponent, board, occupied)) {
                    board[col][row] = player;
                    return true;
                } else {
                    return false;
                }
            } else {
                return checkNorthEastCell(nextRow, nextCol, player, opponent, board, occupied);
            }
        }

        return valid;
    }

    public static boolean checkSouthCell(int row, int col, int player, int opponent, int[][] board, boolean occupied) {
        boolean valid = false;
        // if col is odd
        int nextRow = row - 1;
        int nextCol = col;

        if (board[nextCol][nextRow] == player) {
            valid = true;
        }

        if (board[nextCol][nextRow] == Hexed.NO_COLOR) {
            valid = false;
        }

        //checks if next cell is out of the hexed board
        if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
            valid = false;
        }

        // if cell is opponent and is occupied, tiles will change as long as the path is validMoves
        // if not occupied, nothing happends
        if (board[nextCol][nextRow] == opponent) {
            if (occupied) {
                if (checkSouthCell(nextRow, nextCol, player, opponent, board, occupied)) {
                    board[col][row] = player;
                    return true;
                } else {
                    return false;
                }
            } else {
                return checkSouthCell(nextRow, nextCol, player, opponent, board, occupied);
            }
        }

        return valid;
    }

    public static boolean checkSouthEastCell(int row, int col, int player, int opponent, int[][] board, boolean occupied) {
        boolean valid = false;
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

        if (board[nextCol][nextRow] == Hexed.NO_COLOR) {
            valid = false;
        }

        //checks if next cell is out of the hexed board
        if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
            valid = false;
        }

        // if cell is opponent and is occupied, tiles will change as long as the path is validMoves
        // if not occupied, nothing happends
        if (board[nextCol][nextRow] == opponent) {
            if (occupied) {
                if (checkSouthEastCell(nextRow, nextCol, player, opponent, board, occupied)) {
                    board[col][row] = player;
                    return true;
                } else {
                    return false;
                }
            } else {
                return checkSouthEastCell(nextRow, nextCol, player, opponent, board, occupied);
            }
        }

        return valid;
    }

    public static boolean checkSouthWestCell(int row, int col, int player, int opponent, int[][] board, boolean occupied) {
        boolean valid = false;
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

        if (board[nextCol][nextRow] == Hexed.NO_COLOR) {
            valid = false;
        }

        //checks if next cell is out of the hexed board
        if (nextRow >= board.length || nextRow < 0 || nextCol >= board[0].length || nextCol < 0) {
            valid = false;
        }

        // if cell is opponent and is occupied, tiles will change as long as the path is validMoves
        // if not occupied, nothing happends
        if (board[nextCol][nextRow] == opponent) {
            if (occupied) {
                if (checkSouthWestCell(nextRow, nextCol, player, opponent, board, occupied)) {
                    board[col][row] = player;
                    return true;
                } else {
                    return false;
                }
            } else {
                return checkSouthWestCell(nextRow, nextCol, player, opponent, board, occupied);
            }
        }

        return valid;
    }

    public static void move(Move move, int player, int[][] board) throws Exception {

        if (move == null) {
            if (player == Hexed.GREEN) {
                player = Hexed.RED;
            } else {
                player = Hexed.GREEN;
            }
        }

        int col = move.getCol();
        int row = move.getRow();
        int color = move.getColor();

        //checks if the move's color is equal to the current player's color
        if (color != player) {
            throw new Exception("Error. Wrong Player.");
        }


        //checks if the move is valid and turns the color of the cells
        if (isValid(col, row, board, player, true)) {
            board[col][row] = player;
            if (player == Hexed.GREEN) {
                player = Hexed.RED;
            } else {
                player = Hexed.GREEN;
            }
            return;
        } else {
            throw new Exception("Not a Valid Move.");
        }
    }

    public static Move getRandomMove(ArrayList<Move> moves) {
        int index = randomMoveGenerator.nextInt(moves.size());
        Move random = moves.get(index);
        return random;
    }

}
