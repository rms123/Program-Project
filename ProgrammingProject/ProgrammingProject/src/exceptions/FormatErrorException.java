package exceptions;
import connect4.Protocol;

public class FormatErrorException extends Exception{
	public FormatErrorException(){
		super("Error code " + Protocol.Error.ILLEGAL_SYNTAX.code + ": Input valid format");
	}
}
