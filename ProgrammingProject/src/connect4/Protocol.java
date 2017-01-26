package connect4;

public interface Protocol {
	
	public static final String DELIMITER = ";";
	public static final int DIM = 4;

	/* --------------------------- Connect to server ----------------------- */
	/**
	 * 1.Client handshakes with server,via the port number 
	 * 2.Client sends user name and the extra extensions to the server 
	 * HELLO <username> [EXT_CHALLENGE] [EXT_CHAT] [EXT_LEADERBOARD] [EXT_PASSWORD] 
	 * 3.Server accepts user name or sends an error that the user name is taken 
	 * HELLO [EXT_CHALLENGE] [EXT_CHAT] [EXT_LEADERBOARD] [EXT_PASSWORD]
	 * ERROR_USERNAMETAKEN
	 */
	public static final int PORTNUMBER = 1337;
	//2.
	public static final String HELLO = "HELLO";
	public static final String EXT_CHAT = "EXT_CHAT";
	public static final String EXT_CHALLENGE = "EXT_CHALLENGE";
	public static final String EXT_LEADERBOARD = "EXT_LEADERBOARD";
	public static final String EXT_PASSWORD = "EXT_PASSWORD";
	// 3. Exception
	public static final String ERROR_USERNAMETAKEN = "ERROR_USERNAMETAKEN";

	/* --------------------------- Start a game --------------------------- */
	/**
	 * During the lecture we discussed the possibility to play with several
	 * players, playing with 2 players is enough, therefore the protocol has
	 * been changed
	 */
	
	/**
	 * 1. Player chooses with what kind of user he likes to play -Human player
	 * to human player 
	 * PLAY HUMAN [DIM] 
	 * -Human player to computer player 
	 * PLAY COMPUTER [DIM] 
	 * 2. Server: sends wait, when the player has to wait for
	 * another player 
	 * WAIT 
	 * 3. Server: sends ready when the game can start 
	 * READY <username1> <username2> 
	 * 4. Client: responds with READY when they want to
	 * start, DECLINE otherwise 
	 * READY 
	 * DECLINE 
	 * 5. Server: requests a move from
	 * the user whose turn it is, this message is send to both players
	 * REQUESTMOVE <username> 
	 * 6. Client: Make move with the coordinates 
	 * MAKEMOVE <x> <y> <z> 
	 * 7. Server will either send setmove when the move was valid to
	 * everyone; invalid move when it was invalid to the user, or not your turn
	 * when it was not his turn. 
	 * SETMOVE <username> <x> <y> <z>
	 * ERROR_INVALIDMOVE <x> <y> <z> 
	 * ERROR_NOTYOURTURN 
	 * 8. Server: when the game is not over, the server requests a new move. When the game is over its
	 * told to both players, and when there is a winner this name is added.
	 * GAMEOVER [username of winner] 
	 * When someone quits the game an error is
	 * send and user moves back to the lobby 
	 * ERROR_USERQUIT <username of quiter>
	 * The timeout is set on 20 seconds.
	 */
	// 1.
	public static final String PLAY = "PLAY";
	public static final String HUMAN = "HUMAN";
	public static final String COMPUTER = "COMPUTER";
	// 2.
	public static final String WAIT = "WAIT";
	// 3. and 4.
	public static final String READY = "READY";
	public static final String DECLINE = "DECLINE";
	// 5.
	public static final String REQUESTMOVE = "REQUESTMOVE";
	// 6.
	public static final String MAKEMOVE = "MAKEMOVE";
	// 7.
	public static final String SETMOVE = "SETMOVE";
	public static final String ERROR_INVALIDMOVE = "ERROR_INVALIDMOVE";
	public static final String ERROR_NOTYOURTURN = "ERROR_NOTYOURTURN";
	// 8. (Gameover)
	public static final String GAMEOVER = "GAMEOVER";
	// Exception (Quit)
	public static final String ERROR_USERQUIT = "ERROR_USERQUIT";
	public static final String ERROR_COMMAND_NOT_RECOGNIZED = "ERROR_COMMAND_NOT_RECOGNIZED";

}
