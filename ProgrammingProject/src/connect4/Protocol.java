package connect4;
public interface Protocol {

    // protocol in group 2

    // For the server
    public static final String CONNECT = "CONNECT";

    public static final String DISCONNECT = "DISCONNECT";

    public static final String READY = "GAME READY";

    public static final String UNREADY = "GAME UNREADY";

    public static final String CLIENT_MOVE = "GAME MOVE";

    public static final String ASK_PLAYERS_ALL = "PLAYERS ALL";

   

    // for client

    
    public static final String CONFIRM = "CONFIRM ";

    public static final String START = "GAME START";

    public static final String SERVER_MOVE = "GAME MOVE";

    public static final String END_DRAW = "GAME END DRAW";

    public static final String END_WINNER = "GAME END PLAYER";

    public static final String PLAYERS_ALL = "PLAYERS ALL";

    
    
    //for the error
    public static final String ERROR = "Error occured";
    
    public static final String SYNTAX = "Input valid format";
    
    public static final String METHODERROR = "Method has error:";
    
    public static final String METHODUNKNOW = "this method is unknow";
    
    public static final String NONVALIDMOVE = "Move is not valid";
    
    public static final String PLAYERDISCONNECT = "Player has diconnect";
    
    public static final String HASCONNECT ="Player with this name has connect already";
    
    public static final String NAMEEXIST = "this name have already connected";
    
    
    
    
    
    
    
    
    
    
    

    
}