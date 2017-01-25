package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import connect4.*;

public class PlayerTest {
	private Mark markRed;
	private Mark markYel;
	private Board board;
	private HumanPlayer Hplayer;
	private ComputerPlayer smartComputer;
	private Strategy smart;
	private Strategy naive;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		Hplayer = new HumanPlayer("human", markRed);
		smartComputer = new ComputerPlayer(markYel, smart);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
