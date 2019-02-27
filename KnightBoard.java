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
                {-1,2}, {-1,-2},
                {1,2}, {1,-2}
                              };
    nMoves = new Square[startingRows][startingCols];
    nMoves();
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
    for (int r = 0; r < board.length; r ++){
      for (int c = 0; c < board[0].length; c++){
      int possible = 0;
      for (int[] z : moves){
        if(r + z[0] >= 0 && r + z[0] < board.length && c + z[1] >= 0 && c + z[1] < board[0].length){
            possible++;
        }
      }
      nMoves[r][c] = new Square(r, c, possible);
    }
  }
  }

//gets all the possible squares to move to from a given place on the board and sorts them through the number of possibilities
  public ArrayList<Square> getMoves(int r, int c){
    ArrayList<Square> pSquare = new ArrayList<Square>();
    for (int[] z : moves){
      if(r + z[0] >= 0 && r + z[0] < board.length && c + z[1] >= 0 && c + z[1] < board[0].length && board[r + z[0]][c + z[1]] == 0){
          pSquare.add(nMoves[r + z[0]][c + z[1]]);
      }
    }
    Collections.sort(pSquare);
    return pSquare;
  }

  private boolean addKnight(int r, int c, int num){
    if (r < 0 || c < 0 || r >= board.length || c  >= board[0].length || board[r][c] != 0) return false;
    board[r][c] = num;
    for (int[] z : moves){
      if(r + z[0] >= 0 && r + z[0] < board.length && c + z[1] >= 0 && c + z[1] < board[0].length){
        nMoves[r + z[0]][c + z[1]].changeMoves(-1);
      }
    }
    return true;
  }

  private void removeKnight(int r, int c){
    board[r][c] = 0;
    for (int[] z : moves){
      if(r + z[0] >= 0 && r + z[0] < board.length && c + z[1] >= 0 && c + z[1] < board[0].length){
        nMoves[r + z[0]][c + z[1]].changeMoves(1);
      }
    }
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
    return solveH(startingRow, startingCol, 1);
  }

//non-optimized
  private boolean solveHelp(int row ,int col, int level){
        board[row][col] = level;
        if (level == area) return true;
        for (int[] i : moves){
            if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length && board[row + i[0]][col + i[1]] == 0
               && solveHelp(row + i[0], col + i[1], level + 1)){
                return true;
            }
        }
        board[row][col] = 0;
        return false;
    }

//optimized
  public boolean solveH(int r, int c, int num){
    addKnight(r, c, num);
    if (num == area) return true;
    ArrayList<Square> pos = getMoves(r, c);
    for(Square z : pos){
        if(z.getRow() >= 0 && z.getCol() < board.length && z.getCol() >= 0 && z.getCol() < board[0].length && board[z.getRow()][z.getCol()] == 0
           && solveH(z.getRow(), z.getCol(), num + 1)){
            return true;
        }
    }
    removeKnight(r, c);
    return false;
}
    //if (num == area) return true;
    //iterates through the possible moves
    /*for (int[] i : moves){
      // sets the square value to ther number move you're on
      if(board[r][c] == 0) board[r][c] = num;
      //checks if youre inside the board
      if(r + i[0] >= 0 || r + i[0] < board.length || c + i[1] >= 0 || c + i[1] < board[0].length){
          return solveHelp(r + i[0], c + i[1], num + 1);
      }
      board[r + i[0]][c + i[1]] = 0;
    }*/
    /*ArrayList<Square> pos = getMoves(r,c);
    for(Square z : pos){
      if(board[r][c] == 0) board[r][c] = num;
      return solveH(z.getCol(), z.getRow(), num + 1);
    }
    return false;
  }*/

  public int countSolutions(int startingRow, int startingCol){
    if(startingCol < 0 || startingRow < 0){
        throw new IllegalArgumentException();
    }
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        if(board[r][c] != 0) throw new IllegalStateException();
      }
    }
    return csHelp(startingRow, startingCol, 1);
  }

//not optimized
  private int csHelp(int r ,int c, int num){
    int out = 0;
    board[r][c] = num;
    if (num == board.length * board[0].length){
        out++;
    }
    for (int[] i : moves){
        if(r + i[0] >= 0 && r + i[0] < board.length && c + i[1] >= 0 && c + i[1] < board[0].length
           && board[r + i[0]][c + i[1]] == 0){
            out += csHelp(r + i[0], c + i[1], num + 1);
        }
    }
    board[r][c] = 0;
    return out;
  }


//optimized
  public int countH(int r, int c, int num){
    int out = 0;
    board[r][c] = num;
    if (num == board.length * board[0].length){
        out++;
    }
    for (int[] i : moves){
        if(r + i[0] >= 0 && r + i[0] < board.length && c + i[1] >= 0 && c + i[1] < board[0].length
           && board[r + i[0]][c + i[1]] == 0){
            out += countH(r + i[0], c + i[1], num + 1);
        }
    }
    ArrayList<Square> pos = getMoves(r,c);
    for(Square z : pos){
      out += countH(z.getCol(), z.getRow(), num + 1);
    }
    board[r][c] = 0;
    return out;

  }


}
