import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	public static void main (String args[]) throws IOException{
		int temp;
		int number, number2;
		// get port number
		ServerSocket s1 = new ServerSocket(1342);
		Socket ss = s1.accept();
		Scanner sc = new Scanner(ss.getInputStream());
		number = sc.nextInt();
		// Make new connection with the by the client provided port number
		ServerSocket s2 = new ServerSocket(number);
		Socket ss2 = s2.accept();
		Scanner sc2 = new Scanner(ss2.getInputStream());
		number2 = sc2.nextInt();
		// accept client input
	
		// modification
		temp = number2*2;
		//send back to client
		PrintStream p = new PrintStream(ss2.getOutputStream());
		p.println(temp);
		
	}
	
}
