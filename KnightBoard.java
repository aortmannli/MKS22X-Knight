public class KnightBoard{
  int[][] board;
  @throws IllegalArgumentException when either parameter is negative.

  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows, startingCols];
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        board[r][c] = 0;
      }
    }
  }

  public String toString(){

  }

  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  public boolean solve(int startingRow, int startingCol){

  }

  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.
  public int countSolutions(int startingRow, int startingCol){

  }

  private boolean solveH(int row ,int col, int level){

  }

}
