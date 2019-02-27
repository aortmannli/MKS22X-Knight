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
  public ArrayList<Square> getMoves(int r, int c){
    ArrayList<Square> pSquare = new ArrayList<Square>();
    Square S = new Square(0,0,0);
    for (int[] z : moves){
      if(c + z[0] >= 0 && c + z[0] < board.length && r + z[1] >= 0 && r + z[1] < board[0].length){
          S = nMoves[c + z[0]][r + z[1]];
      }
      if (S!= null) pSquare.add(S);
    }
    Collections.sort(pSquare);
    return pSquare;
  }

  private boolean addKnight(int r, int c, int num){
    if (r < 0 || c < 0 || r >= board.length || c  >= board[0].length || board[r][c] != 0) return false;
    board[r][c] = num;
    for (int[] z : moves){
      if(c + z[0] >= 0 && c + z[0] < board.length && r + z[1] >= 0 && r + z[1] < board[0].length){
        nMoves[c + z[0]][r + z[1]].changeMoves(-1);
      }
    }
    return true;
  }

  private void removeKnight(int r, int c){
    board[r][c] = 0;
    for (int[] z : moves){
      if(c + z[0] >= 0 && c + z[0] < board.length && r + z[1] >= 0 && r + z[1] < board[0].length){
        nMoves[c + z[0]][r + z[1]].changeMoves(1);
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
    return solveHelp(startingRow, startingCol, 0);
  }

//non-optimized
  private boolean solveH(int row ,int col, int level){
        board[row][col] = level;
        if (level == area) return true;
        for (int[] i : moves){
            if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length && board[row + i[0]][col + i[1]] == 0
               && solveH(row + i[0], col + i[1], level + 1)){
                return true;
            }
        }
        board[row][col] = 0;
        return false;
    }


  public boolean solveHelp(int r, int c, int num){
    if (num == area) return true;
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
    ArrayList<Square> pos = getMoves(r,c);
    for(Square z : pos){
      if(board[r][c] == 0) board[r][c] = num;
      return solveHelp(z.getCol(), z.getRow(), num + 1);
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
    return countH(startingRow, startingCol, 1);
  }

  private int countH(int r ,int c, int num){
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
    board[r][c] = 0;
    return out;
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
    ArrayList<Square> pos = getMoves(r,c);
    for(Square z : pos){
      out += cSHelp(z.getCol(), z.getRow(), num + 1);
    }
    board[r][c] = 0;
    return out;

  }


}
