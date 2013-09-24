public class Board {
  public static final int SIZE = 8;
  
  public static final char WHITE = 'O';
  public static final char BLACK = 'X';
  public static final char EMPTY = '.';
  
  public static final int REGION_1_BIAS = 1;
  public static final int REGION_2_BIAS = 1;
  public static final int REGION_3_BIAS = 1;
  public static final int REGION_4_BIAS = 1;
  public static final int REGION_5_BIAS = 1;
  
  int[][] board;

  /**
   * Set up a blank board with four pieces in the center:
   *    a b c d e f g h
   * 1. 0 0 0 0 0 0 0 0
   * 2. 0 0 0 0 0 0 0 0
   * 3. 0 0 0 0 0 0 0 0
   * 4. 0 0 0 1 2 0 0 0
   * 5. 0 0 0 2 1 0 0 0
   * 6. 0 0 0 0 0 0 0 0
   * 7. 0 0 0 0 0 0 0 0
   * 8. 0 0 0 0 0 0 0 0
   * 
   */
  public Board () {
    board = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = Game.EMPTY;
      }
    }

    int center = SIZE / 2;
    board[center][center] = Game.WHITE;
    board[center - 1][center - 1] = Game.WHITE;
    board[center - 1][center] = Game.BLACK;
    board[center][center - 1] = Game.BLACK;
  }
  
  boolean set(int row, int col, int val) {
    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) return false;
    board[row][col] = val;
    return true;
  }
  
  int get(int row, int col) {
    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) return Game.OUT_OF_BOUNDS;
    return board[row][col];
  }

  /**
   * Determines whether a move is valid
   * @param m
   * @return
   */
  public boolean isValid(Move m) {
    //TODO
    // Traverse in eight directions
    // N
    // NE
    // E
    // SE
    // S
    // SW
    // W
    // NW
    return false;
  }
  
  /**
   * Starting from the point defined in the move, move in the direction
   * defined by deltaX, deltaY to verify if there is a flip
   * @param m
   * @param deltaX
   * @param deltaY
   * @return
   */
  public boolean traverse(Move m, int deltaX, int deltaY) {

    int otherColor = (m.color == Game.BLACK) ? Game.WHITE : Game.BLACK; 
    int currRow = m.row;
    int currCol = m.column;
    boolean lookingForOtherColor = true;
    while (get(currRow, currCol) != Game.OUT_OF_BOUNDS) {
      currRow += deltaX;
      currCol += deltaY;
      
      // Two states: either looking for tiles of the opposite color, or
      //             looking for one tile of the same color
      if (lookingForOtherColor) {
        if (get(currRow, currCol) == otherColor) {
          lookingForOtherColor = false;
        } else {
          return false;
        }
      } else {
        if (board[currRow][currCol] == m.color) return true;
        if (board[currRow][currCol] == Game.EMPTY) return false;
      }
    }
    return false;
  }

  /**
   * Executes a move on the board
   * @param m
   * @return
   */
  public boolean makeMove(Move m) {
    //TODO
    return false;
  }



  /**
   * Determines whether a player has any moves left
   * @param player
   * @return
   */
  public boolean existValidMove(int player) {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        Move m = new Move(i, j, player);
        if (isValid(m)) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Returns the region bias for a certain square
   * 
   * Region locations:
   *    a b c d e f g h
   * 1. 5 4 3 3 3 3 4 5
   * 2. 4 4 2 2 2 2 4 4
   * 3. 3 2 1 1 1 1 2 3
   * 4. 3 2 1 1 1 1 2 3
   * 5. 3 2 1 1 1 1 2 3
   * 6. 3 2 1 1 1 1 2 3
   * 7. 4 4 2 2 2 2 4 4
   * 8. 5 4 3 3 3 3 4 5
   * 
   * @param row
   * @param column
   * @return
   */
  public int regionBias(int row, int column) {
    // TODO
    return 1;
  }

  /**
   * Creates the string representation of the board
   */
  @Override
  public String toString() {
    String n = "   a b c d e f g h\n";
    for (int i = 0; i < SIZE; i++) {
      int row_number = i + 1;
      n += row_number + ". ";
      for (int j = 0; j < SIZE; j++) {
        int piece = board[i][j];
        char picture = EMPTY;
        if (piece == Game.BLACK) picture = BLACK;
        if (piece == Game.WHITE) picture = WHITE;
        n += picture + " ";
      }
      n += "\n";
    }
    return n;
  }

}

