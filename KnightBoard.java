public class KnightBoard{
  private int[][] board;
  private int area;
  private int[][] moves;

  @throws IllegalArgumentException when either parameter is negative.

  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows, startingCols];
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        board[r][c] = 0;
      }
    }
    area = startingRows*startingCols;
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

  public boolean solveHelp(int r, int c, int num){
    if (num == area) return true;
    if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
    for (int[] i : moves){
        if(board[r][c] == 0) board[r][c] = num;
        return solveHelp(row + i[0], col + i[1], level + 1);
    }
    board[row + i[0]][col + i[1]] = 0;
    return false;
  }

  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.
  public int countSolutions(int startingRow, int startingCol){
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        if(board[r][c] != 0) throw new IllegalStateException();
      }
    }
    return 0;
  }


}
