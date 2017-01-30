package exceptions;
import connect4.*;

public class NonvalidMoveException extends Exception{
	public NonvalidMoveException(){
		super("Error code " + Protocol.Error.ILLEGAL_MOVE.code + ": Move is not valid");
	}
	
}
