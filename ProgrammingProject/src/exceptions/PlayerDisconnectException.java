package exceptions;
import connect4.*;

public class PlayerDisconnectException extends Exception{
	public PlayerDisconnectException(){
		super(Protocol.PLAYERDISCONNECT);
	}

}
