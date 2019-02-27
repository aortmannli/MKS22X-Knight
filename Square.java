public class Square implements Comparable<Square>{
  private int row;
  private int col;
  private int moves;
  public Square(int row, int col, int n){
    this.row = row;
    this.col = col;
    this.moves = n;
  }

  public String toString(){
    return moves + "";
  }

  public int getRow(){
    return row;
  }
  public int getCol(){
    return col;
  }
  public int getMoves(){
    return moves;
  }

  public void changeMoves(int n){
    moves += n;
  }

  public int compareTo(Square yeet){
    return moves - yeet.moves();
  }

}
