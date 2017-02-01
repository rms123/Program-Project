package exceptions;
import connect4.*;

public class UserHasConnectException extends Exception{
	public UserHasConnectException(){
		super(Protocol.HASCONNECT );
	}
}
