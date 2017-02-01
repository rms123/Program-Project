package connect4;
	
import java.io.*;



/**
 * Executable class for the game Tic Tac Toe. The game can be played against the
 * computer. Lab assignment Module 2
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public class ConnectFour {
	/*private static class UncloseableInputStream extends FilterInputStream {

        *//**
         * Creates a wrapper around {@link System.in}.
         *//*
        UncloseableInputStream() {
            this(System.in);
        }

        *//**
         * Creates a wrapper around an arbitrary {@link InputStream}.
         * @param stream The stream to wrap.
         *//*
        UncloseableInputStream(InputStream stream) {
            super(stream);
        }

        *//**
         * {@inheritDoc}
         *//*
        @Override
        public void close() throws IOException {
            // Don't do anything
        }
    }*/
	/*public static Player determinePlayer(String arg, Mark mark) { 
    	String argu = arg.toUpperCase();
    	if (argu.equals("-N")) {
    		return new ComputerPlayer(mark, new NaiveStrategy());
    	}else if (argu.equals("-S")){
    		return new ComputerPlayer(mark, new SmartStrategy()); 
    	}else {
    		return new HumanPlayer(arg, mark); 
    	}
    }*/
	
    public static void main(String[] args) {
    	/*if (args.length ==3){
    		//System.setIn(new UncloseableInputStream());
    	    Player p1 = determinePlayer(args[0], Mark.REDDD); 
        	Player p2 = determinePlayer(args[1], Mark.YELLO); 
        	Game game = new Game(p1, p2,true); 
        	game.run();
    	}else{
    		System.out.println("Please input two arguments");
    		
    	}
    	
    }*/
    	Player player1 = null;
		Player player2 = null;
		Game game = null;
		if (args.length < 3) {
			exit();
		}
		try {
			Board.setDIM(Integer.parseInt(args[0]));
		} catch (NumberFormatException e) {
			exit();
		}
		if (args[1].equals("human")) {
			player1 = new HumanPlayer( "player1",Mark.REDDD);
		} else if (args[1].equals("ai")) {
			player1 = new ComputerPlayer(Mark.REDDD, new SmartStrategy());
		} else {
			exit();
		}
		
		if (args[2].equals("human")) {
			player2 = new HumanPlayer("player2",Mark.YELLO );
		} else if (args[2].equals("ai")) {
			player2 = new ComputerPlayer(Mark.YELLO, new SmartStrategy());
		} else {
			exit();
		}
		
		game = new Game(player1, player2, true);
		game.run();
	}

	public static void exit() {
		System.out.println("<dimension><human/ai><human/ai>");
		System.exit(0);
	}
}