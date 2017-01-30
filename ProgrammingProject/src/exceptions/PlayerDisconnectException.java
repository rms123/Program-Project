package exceptions;
import connect4.*;

public class PlayerDisconnectException extends Exception{
	public PlayerDisconnectException(){
		super("Error code " + Protocol.Error.PLAYER_DISCONNECT.code + ": Player has diconnect");
	}

}
