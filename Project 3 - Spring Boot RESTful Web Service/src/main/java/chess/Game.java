package chess;

import hello.MoveRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Board chessBoard;
    private Move mover;
    private Player white;
    private Player black;
    private boolean checkMate;
    private boolean turn;


    /*
     * Class constructor
     */
    public Game() {

        chessBoard = new Board();
        chessBoard.initBoard();

        mover = new Move(chessBoard);
        checkMate = false;
        turn = false;

        white = new Player(0, "Player1");
        black = new Player(1, "Player2");
    }


    /*
     * Get whose turn it is
     * turn = true  -> player2's turn ie black
     * turn = false -> player1's turn ie white
     */
    public boolean getTurn() {
        return turn;
    }


    /*
     * turns the value of turn to its opposite
     */
    public void toggleTurn() {
        turn = !turn;
    }


    /*
     * returns a string representation of the current board
     */
    public String getBoardStatus() {
        return chessBoard.currentGameState();
    }


    /*
     * exposed method to make a move
     */
    public boolean makeMove(MoveRequest moveRequest) {
        if (turn)
            return takeTurn(black, moveRequest, 1);
        else
            return takeTurn(white, moveRequest, 0);
    }

    /*
     * executes the turn of the current player
     */
    private boolean takeTurn(Player player, MoveRequest moveRequest, int playerVal) {

        // 0 for white, or 1 for black
        chessBoard.setTurn(playerVal);

//        if(mover.checkCheck(player.getColor())){
//            System.out.println(player.getName() + ", you are in check. Proceed with caution.");
//        }
//        else{
//            System.out.println(player.getName() + " turn.");
//        }

        // attempt to make the move
        boolean success = mover.move(moveRequest.getStartSquare(), moveRequest.getEndSquare(), player);

        // check if the move was successful or not
        if (success) {
            toggleTurn();
            return true;
        }
        else {
            return false;
        }
    }


}
