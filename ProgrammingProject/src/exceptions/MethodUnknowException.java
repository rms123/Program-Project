package exceptions;
import connect4.Protocol;
public class MethodUnknowException extends Exception{
	public MethodUnknowException(){
		super("Error code " + Protocol.Error.UNKNOWN_METHOD.code  + ": this method is unknow");
	}
}
