package connect4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static void main (String args[]) throws UnknownHostException, IOException{
		int tomodify;
		int number;
		int temp;
		// scanner needed to read input from user
		Scanner sc = new Scanner(System.in);
		
		
		Socket s = new Socket("127.0.0.1",1342);
		System.out.println("Enter port number");
		number = sc.nextInt();
		// Send port number to server
		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(number);
		//Set up new connection with the by the user provided port number
		Socket s2 = new Socket("127.0.0.1",number);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter any number");
		tomodify = sc2.nextInt();
		Scanner sc1 = new Scanner(s2.getInputStream());
		// Send number to modify to server
		PrintStream p2 = new PrintStream(s2.getOutputStream());
		p2.println(tomodify);
		
		// get the info from the server and print it 
		temp = sc1.nextInt();
		System.out.println(temp);
	}
	
}
