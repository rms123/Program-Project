package exceptions;
import connect4.*;

public class ErrorException extends Exception{
	public ErrorException(){
		super("Error code " + Protocol.Error.INTERNAL_ERROR.code + ":  error occured");
	}

}
