package hello;

import chess.Game;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
public class Controller {


    private HashMap<String, Game> sessions = new HashMap<>();


    private boolean makeMove(MoveRequest move) {

        Game game = sessions.get(move.getSessionName());

        return game.makeMove(move);

    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Response test() {

        return new Response("Test works! :)");

    }


    /*
     * Get Request
     *
     * User hits the endpoint / with GET which means the user is requesting data from the database
     * returns information about the game that corresponds to the given session name
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status(@RequestParam(value="sessionName", defaultValue="Empty") String sessionName) {


        if (sessionName.equals("Empty")) {
            return "Please provide a session name";
        }
        else if (sessions.containsKey(sessionName)) {
            return sessions.get(sessionName).getBoardStatus();
        }
        else {
            return "No such session with name '" + sessionName + "'";
        }

    }



    /*
     * Post Request
     *
     * User hits the endpoint / with POST which means the user is requesting to insert data into the database
     * creates a game session with the given name if it does not already exists and makes the first move
     *
     * Request Body should be in JSON format in this form:
     *
     *
     * {
          "session": "MarkGrechanik1",
          "color": "white",
          "startSquare": "e2",
          "endSquare": "f3"
        }
     *
     */
    @RequestMapping(value = "/newGame", method = RequestMethod.POST)
    public Response newGame(@RequestBody MoveRequest move) {

        // error check
        if (move.getSessionName() == null) {
            return new Response("Please provide a session name");
        }
        else if (sessions.containsKey(move.getSessionName())) {
            return new Response("Session with name '" + move.getSessionName() + "' already exists");
        }
        else if (!move.getPlayerColor().equals("white")) {
            return new Response("White goes first");
        }

        // create the game
        Game game = new Game();
        sessions.put(move.getSessionName(), game);

        // attempt to make the move
        boolean success = makeMove(move);

        // return response
        if (success) {
            return new Response("Session with name '" + move.getSessionName() + "' created, and first move success");
        }
        else {
            return new Response("Session with name '" + move.getSessionName() + "' created, but first move unsuccessful");
        }
    }


    /*
     * Put Request
     *
     * User hits the endpoint / with PUT which means the user is requesting to update data into the database
     * finds a game session with the given name if it exists and makes the move
     *
     * Request Body should be in JSON format in this form:
     *
     *
     * {
          "session": "MarkGrechanik1",
          "color": "white",
          "startSquare": "e2",
          "endSquare": "f3"
        }
     *
     */
    @RequestMapping(value = "/move", method = RequestMethod.PUT)
    public Response move(@RequestBody MoveRequest move) {


        // error check
        if (move.getSessionName() == null) {
            return new Response("Please provide a session name");
        }
        else if (!sessions.containsKey(move.getSessionName())) {
            return new Response("No such session with name '" + move.getSessionName() + "'");
        }
        // see if the current player in the session doesn't match the color given in the request
        else if (sessions.get(move.getSessionName()).getTurn() && move.getPlayerColor().equals("white")
                || !sessions.get(move.getSessionName()).getTurn() && move.getPlayerColor().equals("black")) {

            return new Response("It isn't your turn in session '" + move.getSessionName() + "'");
        }

        // attempt to make the move
        boolean success = makeMove(move);

        // return response
        if (success) {
            return new Response("Move request success");
        }
        else {
            return new Response("Move request unsuccessful");
        }
    }


    /*
     * Delete Request
     *
     * User hits the endpoint / with DELETE which means the user is requesting to delete data from the database
     * finds a game session with the given name if it exists and deletes it
     *
     * Request Body should be in JSON format in this form:
     *
     *
     * {
          "session": "MarkGrechanik1"
        }
     *
     */
    @RequestMapping(value = "/quit", method = RequestMethod.DELETE)
    public Response quit(@RequestBody MoveRequest move) {

        if (sessions.containsKey(move.getSessionName())) {

            sessions.remove(move.getSessionName());
            return new Response("Session with name '" + move.getSessionName() + "' deleted");
        }
        else {
            return new Response("No such session with name '" + move.getSessionName() + "'");
        }

    }




    /*
     * https://www.leveluplunch.com/java/tutorials/014-post-json-to-spring-rest-webservice/
     */
    /*
     * https://spring.io/guides/gs/rest-service/#scratch
     * https://spring.io/guides/gs/intellij-idea/
     * https://www.youtube.com/watch?v=EDgCHCFKo8c&list=PLItnVRenKFQJgUVA9VakvBQRx7BE1Qzst&index=2
     * https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/2.1.3.RELEASE
     *
     */

}
