package exceptions;

import connect4.Protocol;

public class MethodErrorException extends Exception{
	public MethodErrorException(){
		super("Error in method");
	}
	
	public MethodErrorException(String message){
		super( Protocol.METHODERROR + message);
	}
}
