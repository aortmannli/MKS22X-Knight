import java.util.*;

public class KnightBoard{
  private int[][] board;
  private int area;
  private int[][] moves;
  private Square[][] nMoves;

  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        board[r][c] = 0;
      }
    }
    area = startingRows*startingCols;
    //stores all the possible moves
    moves = new int[][]{
                {2,1}, {-2,-1},
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

//just counts the number of moves possible
  private void nMoves(){
    int possible = 0;
    for(int c = 0, r = 0; (c < board.length) && (r < board[0].length); c++, r++){
      for (int[] z : moves){
        if(c + z[0] >= 0 && c + z[0] < board.length && r + z[1] >= 0 && r + z[1] < board[0].length){
            possible++;
        }
      }
      nMoves[c][r] = new Square(c, r, possible);
    }
  }

//gets all the possible squares to move to from a given place on the board and sorts them through the number of possibilities
  public ArrayList<Square> getMoves(int row, int col){
    ArrayList<Square> pSquare = new ArrayList<Square>();
    for (int[] z : moves){
      Square S = nMoves[row + z[0]][col + z[1]];
      if (S != null) pSquare.add(S);
    }
    Collections.sort(pSquare);
    return pSquare;
  }

  public boolean solve(int startingRow, int startingCol){
    if(startingCol < 0 || startingRow < 0){
        throw new IllegalArgumentException();
    }
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        if(board[r][c] != 0) throw new IllegalStateException();
      }
    }
    return solveHelp(startingRow, startingCol, 0);
  }

  public boolean solveHelp(int r, int c, int num){
    if (num == area) return true;
    //iterates through the possible moves
    for (int[] i : moves){
      // sets the square value to ther number move you're on
      if(board[r][c] == 0) board[r][c] = num;
      //checks if youre inside the board
      if(r + i[0] >= 0 || r + i[0] < board.length || c + i[1] >= 0 || c + i[1] < board[0].length){
          return solveHelp(r + i[0], c + i[1], num + 1);
      }
      board[r + i[0]][c + i[1]] = 0;
    }
    return false;
  }

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
    board[r][c] = num;
    if (num == board.length * board[0].length){
        out++;
    }
    for (int[] i : moves){
        if(r + i[0] >= 0 && r + i[0] < board.length && c + i[1] >= 0 && c + i[1] < board[0].length
           && board[r + i[0]][c + i[1]] == 0){
            out += cSHelp(r + i[0], c + i[1], num + 1);
        }
    }
    board[r][c] = 0;
    return out;
  }


}
