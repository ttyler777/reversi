package reversi;

import java.util.Scanner;

public class HumanPlayer extends Player {
  Scanner input;

  public HumanPlayer (int c) {
    super(c);
    input = new Scanner(System.in);
  }
  
  public Move getMove(Board b) {
    //TODO: allow the player to resign
    int row = input.nextInt();
    int column = input.nextInt();
    return new Move(row, column, this.color);
  }
}

