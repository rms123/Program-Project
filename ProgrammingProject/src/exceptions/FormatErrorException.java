package exceptions;
import connect4.Protocol;

public class FormatErrorException extends Exception{
	public FormatErrorException(){
		super(Protocol.SYNTAX);
	}
}
