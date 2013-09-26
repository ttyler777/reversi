package reversi;

public class ComputerPlayer extends Player {

  public ComputerPlayer (int c) {
    super(c);
  }
  
  public Move getMove(Board b) {
    // make the computer give the first valid move it thinks of
    Move[] validMoves = b.getValidMoves(color);
    return validMoves[0];
  }
}

