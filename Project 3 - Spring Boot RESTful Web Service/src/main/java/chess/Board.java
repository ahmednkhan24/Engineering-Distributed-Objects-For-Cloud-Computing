package chess;

import java.util.*;

public class Board{

	private Piece[][] board = new Piece[8][8];

	private ArrayList<Piece> pieces = new ArrayList<Piece>(32); // dynamic ArrayList of pieces

	private int turn; // white = 0, black = 1

	// Constructor
	public Board(){
	}


	public String currentGameState(){

		StringBuilder s = new StringBuilder();

		for(int i = 0; i < 8; i++){

			s.append(" |");
			s.append( 8-i + " |");

			for(int j = 0; j < 8; j++){

				if(board[i][j] == null){ // empty space
					s.append("   ");
					s.append("|");
				}
				else { // piece
					s.append(board[i][j].getPieceName());
					s.append("|");
				}
			}

			if(i < 8) { // border between rows
				s.append("\n");
			}
		}

		// print a - h for completeness of board notation
		s.append(" |  | a | b | c | d | e | f | g | h |");

		return s.toString();
		
	}

	// initiate the game
	public void initBoard(){
		clearBoard(); // start with clear board
		createPieces(); // create pieces, update game state
		populateBoard();
		//currentGameState();
	}

	// clear the board if necessary
	public void clearBoard(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = null;
			}
		}
	}

	// create pieces -- Note on color value: white = 0, black = 1
	public void createPieces(){

		ArrayList<Piece> pieces = getPieces(); // ArrayList of all 32 pieces in game

		for(int i = 0; i < 8; i++){ // 8 pawns/player
			pieces.add(new Piece(1, Piece.PAWN, 1, i)); // Piece(color, type, row, col)
			pieces.add(new Piece(0, Piece.PAWN, 6, i));
		}

		// 2 rooks/player
		pieces.add(new Piece(1, Piece.ROOK, 0, 0));
		pieces.add(new Piece(1, Piece.ROOK, 0, 7));
		pieces.add(new Piece(0, Piece.ROOK, 7, 0));
		pieces.add(new Piece(0, Piece.ROOK, 7, 7));

		// 2 bishops/player
		pieces.add(new Piece(1, Piece.BISHOP, 0, 2));
		pieces.add(new Piece(1, Piece.BISHOP, 0, 5));
		pieces.add(new Piece(0, Piece.BISHOP, 7, 2));
		pieces.add(new Piece(0, Piece.BISHOP, 7, 5));

		// 2 knights/player
		pieces.add(new Piece(1, Piece.KNIGHT, 0, 1));
		pieces.add(new Piece(1, Piece.KNIGHT, 0, 6));
		pieces.add(new Piece(0, Piece.KNIGHT, 7, 1));
		pieces.add(new Piece(0, Piece.KNIGHT, 7, 6));

		// 1 queen/player
		pieces.add(new Piece(1, Piece.QUEEN, 0, 3));
		pieces.add(new Piece(0, Piece.QUEEN, 7, 3));

		// 1 king/player
		pieces.add(new Piece(1, Piece.KING, 0, 4));
		pieces.add(new Piece(0, Piece.KING, 7, 4));
	}

	// populate board with pieces
	private void populateBoard(){
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){
			board[p.getRow()][p.getCol()] = p; // place pieces
		}
	}

	// remove a piece from the board, r = row, c = col
	public void removePiece(int r, int c){
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){
			if(p.getRow() == r && p.getCol() == c){ // find desired piece, remove, update game status
				pieces.remove(p);
				updatePieces(pieces);
				updateGameBoard();
				break;
			}
		}
	}

	// add a piece to the board
	public void addPiece(Piece p, int r, int c){
		ArrayList<Piece> pieces = getPieces(); // current pieces
		if(!pieceOnSpace(r, c)){ // if space is clear, add piece
			pieces.add(p);
			updatePieces(pieces); // update game status
			updateGameBoard();
		} else {
			System.out.println("There is already a piece in this space! You cannot add a piece here.");
		}
	}

	// check input location for a piece
	public boolean pieceOnSpace(int r, int c){
		for(Piece p : getPieces()){
			if(p.getRow() == r && p.getCol() == c){
				return true; // piece found
			}
		}
		return false; // no piece found
	}


	// update the pieces
	public void updatePieces(ArrayList<Piece> pieces){
		this.pieces = pieces;
	}

	// update board
	public void updateGameBoard(){
		clearBoard();
		populateBoard();
	}

	// get pieces
	public ArrayList<Piece> getPieces(){
		return pieces;
	}

	// get turn
	public int getTurn(){
		return turn;
	}

	// set turn
	public void setTurn(int turn){
		this.turn = turn;
	}

	// promote piece at input row, column location
	public void promotePiece(Piece newPiece, int r, int c){
		ArrayList<Piece> pieces = getPieces();
		for(Piece p : pieces){
			// find piece
			if(p.getRow() == r && p.getCol() == c){
				removePiece(r, c); // remove piece currently there
				addPiece(newPiece, r, c); // add new piece
				updatePieces(pieces); // update game status
				updateGameBoard();
				break;
			}
		}
	}

	// check at end of each turn for pawns that reached the enemy's end line --> promote to queen
	public void pawnPromotion(int color){
		int endRow;
		if(color == 0){
			endRow = 0;
		} else {
			endRow = 7;
		}
		ArrayList<Piece> pieces = getPieces(); // current pieces
		int count = 0; // iteration counter
		for(Piece p : pieces){ // iterate through pieces
			if(p.getRow() == endRow && p.getType() == 6 && p.getColor() == color){ // pawn on endrow found
				p.setType(2); // change to queen
				pieces.set(count, p); // put piece back in ArrayList
				updatePieces(pieces); // update game state
				updateGameBoard();
				break;
			}
			count++;
		}
	}

	// get piece at given location
	public Piece getPieces(int r, int c){
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){ // iterate through pieces to find piece
			if(p.getRow() == r && p.getCol() == c){
				return p;
			}
		}
		return new Piece(); // no piece found, blank space
	}

	// get piece at given location with given color
	public Piece getPieces(int r, int c, int color){
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){ // iterate through pieces to find piece
			if((p.getRow() == r && p.getCol() == c) && p.getColor() == color){ // if same location and color, return piece
				return p;
			}
		}
		return new Piece(); // no piece found, blank space
	}

	// swap two input pieces
	public void swapPieces(Piece one, Piece two){

		ArrayList<Piece> pieces = getPieces(); // current pieces

		Piece hold = one; // set piece one equal to a holding piece

		int count = 0;

		for(Piece p : pieces){ // iterate through pieces to find piece

			if(p == one){ // find Piece one

				p.setType(two.getType()); // set properties of piece one to piece two
				p.setRow(two.getRow());
				p.setCol(two.getCol());
				p.setColor(two.getColor());
				pieces.set(count, p); // put piece back in ArrayList
				updatePieces(pieces); // update game state
				updateGameBoard();
				break;
			}

			count++;
		}

//		count = 0; // reset count
//		for(Piece p : pieces){
//			if(p == two){
//				p.setType(hold.getType()); // set properties of piece one to piece two
//				p.setRow(hold.getRow());
//				p.setCol(hold.getCol());
//				p.setColor(hold.getColor());
//				pieces.set(count, p); // put piece back in ArrayList
//				updatePieces(pieces); // update game state
//				updateGameBoard();
//				break;
//			}
//			count++;
//		}

	}

	// convert String input into row integer
	private int inputToRow(String input){
		char r = input.charAt(1);

		int row;

		switch(r){ // '0' value corresponds to '8', etc
			case '8':
				row = 0;
				break;
			case '7':
				row = 1;
				break;
			case '6':
				row = 2;
				break;
			case '5':
				row = 3;
				break;
			case '4':
				row = 4;
				break;
			case '3':
				row = 5;
				break;
			case '2':
				row = 6;
				break;
			case '1':
				row = 7;
				break;
			default:
				row = -1;
				break;
		}
		return row;
	}

	// convert String input into column integer
	private int inputToCol(String input){
		char c = input.charAt(0);
		int col;
		switch(c){ // '0' value corresponds to 'a', etc
			case 'a':
				col = 0;
				break;
			case 'b':
				col = 1;
				break;
			case 'c':
				col = 2;
				break;
			case 'd':
				col = 3;
				break;
			case 'e':
				col = 4;
				break;
			case 'f':
				col = 5;
				break;
			case 'g':
				col = 6;
				break;
			case 'h':
				col = 7;
				break;
			default:
				col = -1;
				break;
		}
		return col;
	}
}