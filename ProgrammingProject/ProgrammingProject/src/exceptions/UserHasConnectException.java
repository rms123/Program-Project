package exceptions;
import connect4.*;

public class UserHasConnectException extends Exception{
	public UserHasConnectException(){
		super("Error code " + Protocol.Error.USER_ALREADY_CONNECTED.code+ ": User with this name has connect already");
	}
}
