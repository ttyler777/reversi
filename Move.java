package reversi;

public class Move {
  int row;
  int column;
  int color;
  int rating;
  
  public Move(int row, int column, int color) {
    this.row = row;
    this.column = column;
    this.color = color;
  }
  
  /**
   * Gets the value of a move
   * @return
   */
  int rate(Board b) {
    
    return 0;
  }
  
  @Override
  public String toString() {
    char colChar = (char) ('a' + column);
    String colorStr = (color == Game.BLACK) ? "BLACK" : "WHITE";
    return colorStr + " " + Integer.toString(row + 1) + "" + colChar;
  }
}

