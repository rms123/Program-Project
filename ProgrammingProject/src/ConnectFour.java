	
import java.io.*;

/**
 * Executable class for the game Tic Tac Toe. The game can be played against the
 * computer. Lab assignment Module 2
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public class ConnectFour {
	private static class UncloseableInputStream extends FilterInputStream {

        /**
         * Creates a wrapper around {@link System.in}.
         */
        UncloseableInputStream() {
            this(System.in);
        }

        /**
         * Creates a wrapper around an arbitrary {@link InputStream}.
         * @param stream The stream to wrap.
         */
        UncloseableInputStream(InputStream stream) {
            super(stream);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void close() throws IOException {
            // Don't do anything
        }
    }
	public static Player determinePlayer(String arg, Mark mark) { 
    	String argu = arg.toUpperCase();
    	if (argu.equals("-N")) {
    		return new ComputerPlayer(mark, new NaiveStrategy());
    	}else if (argu.equals("-S")){
    		return new ComputerPlayer(mark, new SmartStrategy()); 
    	}else {
    		return new HumanPlayer(arg, mark); 
    	}
    }
	
    public static void main(String[] args) {
    	if (args.length ==2){
    		System.setIn(new UncloseableInputStream());
    	    Player p1 = determinePlayer(args[0], Mark.REDDD); 
        	Player p2 = determinePlayer(args[1], Mark.YELLO); 
        	Game game = new Game(p1, p2); 
        	game.start();
    	}else{
    		System.out.println("need more arguments");
    	}
    	
    }
}