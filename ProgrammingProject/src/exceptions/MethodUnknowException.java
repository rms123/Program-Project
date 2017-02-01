package exceptions;
import connect4.Protocol;
public class MethodUnknowException extends Exception{
	public MethodUnknowException(){
		super(Protocol.METHODUNKNOW );
	}
}
