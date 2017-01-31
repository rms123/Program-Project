package exceptions;
import connect4.*;
public class InputErrorException extends Exception{
	public InputErrorException(){
		super("Input error.");
	}
	public InputErrorException(String message){
		super(message);
	}

}
