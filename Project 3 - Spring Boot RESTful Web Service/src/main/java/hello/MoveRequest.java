package hello;

public class MoveRequest {

    /*
     * Member variables
     */
    private String sessionName;
    private String playerColor;
    private String startSquare;
    private String endSquare;


    /*
     * Class constructor
     */
    public MoveRequest(String sessionName, String playerColor, String startSquare, String endSquare) {
        this.sessionName = sessionName;
        this.playerColor = playerColor;
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }

    /*
     * Getters & Setters
     */
    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getStartSquare() {
        return startSquare;
    }

    public void setStartSquare(String startSquare) {
        this.startSquare = startSquare;
    }

    public String getEndSquare() {
        return endSquare;
    }

    public void setEndSquare(String endSquare) {
        this.endSquare = endSquare;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setColor(String playerColor) {
        this.playerColor = playerColor;
    }
}
