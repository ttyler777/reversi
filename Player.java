public abstract class Player {
  public int color;
  public Player(int c) {
    color = c;
  }
  
  public Move getMove(Board b) {
    return null;
  }
 
  
  public boolean hasMove(Board b) {
    return false;
  }
}

