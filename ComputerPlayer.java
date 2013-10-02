package reversi;

public class ComputerPlayer extends Player {
	public static final int DEPTHLIMIT = 10;
	public int otherColor;

	public ComputerPlayer(int c) {
		super(c);
	}

	public Move getMove(Board b) {		
		if (color == 1) {
			otherColor = 2;
		} else {
			otherColor = 1;
		}
		
		Move[] moves = b.getValidMoves(color);		
		int depth = 0;
		int leafRating = 0;
		
		Move bestMove = new Move(-1,-1,-1);  //Initialize bestMove,  meant to be replace by real move
		bestMove.rating = -1000;  
		
		for (int element = 0; element < moves.length; element++) {
			/*
			 * This calculates the new variables to pass to simulateMin
			 */
			Board passingBoard = new Board();
			passingBoard.board = b.board.clone();
			passingBoard.makeMove(moves[element]);
			leafRating = leafRating + moves[element].rate(b); ; // Calculates new leaf rating to pass down the tree
			depth++;

			moves[element].rating = simulateMin(passingBoard, leafRating, depth); 
			if (moves[element].rating > bestMove.rating) { // Keeps track of the best move 
				bestMove = moves[element];
			}
		}
		
		return bestMove;
	}

	private int simulateMax(Board b, int leafRating, int depth) {

		if (depth == DEPTHLIMIT || !(b.existValidMove(color))) {
			return leafRating;
		}
		int bestMove = 0;
		int temp;

		Move[] moves = b.getValidMoves(color);
		for (int element = 0; element < moves.length; element++) {
			/*
			 * This calculates the new variables to pass to simulateMin
			 */
			Board passingBoard = new Board();
			passingBoard.board = b.board.clone();
			passingBoard.makeMove(moves[element]);
			leafRating = leafRating + moves[element].rate(b); ; // Calculates new leaf rating to pass down the tree
			depth++;

			temp = simulateMin(passingBoard, leafRating, depth); 
			if (temp > bestMove) { // Keeps track of the best move for this Player
				bestMove = temp;
			}
		}
		return bestMove;

	}

	private int simulateMin(Board b, int leafRating, int depth) {		
		if (depth == DEPTHLIMIT || !(b.existValidMove(otherColor))) {
			return leafRating;
		}
		int bestMove = 0;
		int temp;

		Move[] moves = b.getValidMoves(otherColor);
		for (int element = 0; element < moves.length; element++) {
			/*
			 * This calculates the new variables to pass to simulateMax
			 */
			Board passingBoard = new Board();
			passingBoard.board = b.board.clone();
			passingBoard.makeMove(moves[element]);
			leafRating = leafRating - moves[element].rate(b); // Calculates new leaf rating to pass down the tree
			depth++;

			temp = simulateMax(passingBoard, leafRating, depth); 
			if (temp < bestMove) { // Keeps track of the best move for this Player
				bestMove = temp;
			}
		}
		return bestMove;
	}
}
