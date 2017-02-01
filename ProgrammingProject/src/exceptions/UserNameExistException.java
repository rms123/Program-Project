package exceptions;

import connect4.Protocol;

public class UserNameExistException extends Exception{
	public UserNameExistException(){
		super(Protocol.NAMEEXIST);
	}

}
