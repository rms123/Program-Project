package exceptions;
import connect4.*;

public class NonvalidMoveException extends Exception{
	public NonvalidMoveException(){
		super(Protocol.NONVALIDMOVE);
	}
	
}
