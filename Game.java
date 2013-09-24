public class Game {
  public static final int BLACK = 1;
  public static final int WHITE = 2;
  public static final int EMPTY = 0;
  public static final int OUT_OF_BOUNDS = -1;
  public Board board;
  public Player player1;
  public Player player2;

  public Game (int numHumans) {
    board = new Board();
    switch (numHumans) {
    case 0:
      player1 = new ComputerPlayer(BLACK);
      player2 = new ComputerPlayer(WHITE);
    case 1:
      player1 = new HumanPlayer(BLACK);
      player2 = new ComputerPlayer(WHITE);
    case 2:
      player1 = new HumanPlayer(BLACK);
      player2 = new ComputerPlayer(WHITE);
    }
  }

  public boolean beginGameLoop() {
    System.out.println(board);
    Player currentPlayer = player1;
    while (board.existValidMove(currentPlayer.color)) {
      // get the current player to decide a move
      Move m = currentPlayer.getMove(board);
      
      // put their piece on the board
      board.makeMove(m);
      
      // display the updated board
      System.out.println(board);
      
      // display the scores
      
      
      // switch to the other player
      currentPlayer = (currentPlayer.color == WHITE) ? player1 : player2;
    }
    return false;
  }

  public static void main(String[] args) {
    Game g = new Game(0);
    g.beginGameLoop();
  }
}

