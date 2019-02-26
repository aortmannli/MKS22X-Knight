import java.util.*;

public class KnightBoard{
  private int[][] board;
  private int area;
  private int[][] moves;


  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows, startingCols];
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        board[r][c] = 0;
      }
    }
    area = startingRows*startingCols;
    //stores all the possible moves
    moves = new int[][]{{2,1}, {-2,-1},
                {-2,1}, {2,-1},
                {1,2}, {-1,-2},
                {1,2}, {1,-2}
                                    };
  }

  public String toString(){
    String out = "";
     for (int r = 0; r < board.length; r++) {
       for (int c = 0; c < board[0].length; c++) {
         if (board[r][c] == 0) out += "_ ";
         else out += " " + board[r][c];
       }
       out += "\n";
     }
    return out;
  }

  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  public boolean solve(int startingRow, int startingCol){
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        if(board[r][c] != 0) throw new IllegalStateException();
      }
    }
    return solveHelp(startingRow, startingCol, 0);
  }

  /*
  the base case is if the nubmer move you're on is equal to the area of the board (meanuiing the number of all squares)
  sHelp(r,c,n)
    if num = area --> true
    for (loop through possible moves)
      set square = n
      check if youre inside the KnightBoard
      sHelp(r + move, c+ move, num++)
    set equal to 0
    false
  */

  public boolean solveHelp(int r, int c, int num){
    if (num == area) return true;
    //iterates through the possible moves
    for (int[] i : moves){
        // sets the square value to ther number move you're on
        if(board[r][c] == 0) board[r][c] = num;
        //checks if youre inside the board
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
        //recursion that adds the possible moves
        return solveHelp(row + i[0], col + i[1], num + 1);
    }
    board[row + i[0]][col + i[1]] = 0;
    return false;
  }

  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.
  public int countSolutions(int startingRow, int startingCol){
    if(startingCol < 0 || startingRow < 0){
        throw new IllegalArgumentException();
    }
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        if(board[r][c] != 0) throw new IllegalStateException();
      }
    }
    return cSHelp(startingRow, startingCol, 1);
  }

  public int cSHelp(int r, int c, int num){
    int out = 0;
    board[row][col] = num;
    if (level == board.length * board[0].length){
        out++;
    }
    if(x == area) return 1;
    for (int[] i : moves){
      if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length
         && board[row + i[0]][col + i[1]] == 0){
          output += countH(row + i[0], col + i[1], level + 1);
      }
    }
    board[row][col] = 0;
    return out;
 }


}
