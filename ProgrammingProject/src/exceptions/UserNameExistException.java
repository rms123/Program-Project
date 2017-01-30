package exceptions;

import connect4.Protocol;

public class UserNameExistException extends Exception{
	public UserNameExistException(){
		super("Error code " + Protocol.Error.USER_ALREADY_CONNECTED.code + ": this name have already connected");
	}

}
