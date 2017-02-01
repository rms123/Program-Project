package exceptions;
import connect4.*;

public class ErrorException extends Exception{
	public ErrorException(){
		super( Protocol.ERROR );
	}

}
