

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

  //  @Test
    //public void testIndex() {
    //    assertEquals(0, board.index(0, 0));
   //     assertEquals(1, board.index(0, 1));
   //     assertEquals(3, board.index(1, 0));
   //     assertEquals(8, board.index(2, 2));
    //}

    @Test
    public void testIsFieldIndex() {
        assertFalse(board.isField(-1));
        assertTrue(board.isField(0));
        assertTrue(board.isField(8));
        assertFalse(board.isField(9));
    }

    @Test
    public void testIsFieldRowCol() {
        assertFalse(board.isField(-1, 0));
        assertFalse(board.isField(0, -1));
        assertTrue(board.isField(0, 0));
        assertTrue(board.isField(2, 2));
        assertFalse(board.isField(2, 3));
        assertFalse(board.isField(3, 2));
    }

    @Test
    public void testSetAndGetFieldIndex() {
        board.setField(0,0, Mark.REDDD);
        assertEquals(Mark.REDDD, board.getField(0));
        assertEquals(Mark.EMPTY, board.getField(1));
    }

    @Test
    public void testSetFieldRowCol() {
        board.setField(0, 0, Mark.REDDD);
        assertEquals(Mark.REDDD, board.getField(0));
        assertEquals(Mark.EMPTY, board.getField(0, 1));
        assertEquals(Mark.EMPTY, board.getField(1, 0));
        assertEquals(Mark.EMPTY, board.getField(1, 1));
    }

    @Test
    public void testSetup() {
        assertEquals(Mark.EMPTY, board.getField(0));
        assertEquals(Mark.EMPTY, board.getField(Board.DIM * Board.DIM - 1));
    }

    @Test
    public void testReset() {
        board.reset();
        assertEquals(Mark.EMPTY, board.getField(0));
        assertEquals(Mark.EMPTY, board.getField(Board.DIM * Board.DIM - 1));
    }

    @Test
    public void testDeepCopy() {
        board.setField(0, Mark.REDDD);
        Board deepCopyBoard = board.deepCopy();
        deepCopyBoard.setField(0, Mark.YELLO);

        assertEquals(Mark.REDDD, board.getField(0));
        assertEquals(Mark.YELLO, deepCopyBoard.getField(0));
    }

    @Test
    public void testIsEmptyFieldIndex() {
        board.setField(0, Mark.REDDD);
        assertFalse(board.isEmptyField(0));
        assertTrue(board.isEmptyField(1));
    }

    @Test
    public void testIsEmptyFieldRowCol() {
        board.setField(0, 0, Mark.REDDD);
        assertFalse(board.isEmptyField(0, 0));
        assertTrue(board.isEmptyField(0, 1));
        assertTrue(board.isEmptyField(1, 0));
    }

    @Test
    public void testIsFull() {
        for (int i = 0; i < 8; i++) {
            board.setField(i, Mark.REDDD);
        }
        assertFalse(board.isFull());

        board.setField(8, Mark.REDDD);
        assertTrue(board.isFull());
    }

    @Test
    public void testGameOverFullBoard() {
        /**
         * REDDDo
         * YELLOx
         * REDDDo
         */
        board.setField(0, 0, Mark.REDDD);
        board.setField(0, 1, Mark.REDDD);
        board.setField(0, 2, Mark.YELLO);
        board.setField(1, 0, Mark.YELLO);
        board.setField(1, 1, Mark.YELLO);
        board.setField(1, 2, Mark.REDDD);
        board.setField(2, 0, Mark.REDDD);
        board.setField(2, 1, Mark.YELLO);

        assertFalse(board.gameOver());
        board.setField(2, 2, Mark.REDDD);
        assertTrue(board.gameOver());
    }

    @Test
    public void testHasRow() {
        board.setField(0, Mark.REDDD);
        board.setField(1, Mark.REDDD);
        assertFalse(board.hasRow(Mark.REDDD));
        assertFalse(board.hasRow(Mark.YELLO));

        board.setField(2, Mark.REDDD);
        assertTrue(board.hasRow(Mark.REDDD));
        assertFalse(board.hasRow(Mark.YELLO));
    }

    @Test
    public void testHasColumn() {
        board.setField(0, Mark.REDDD);
        board.setField(3, Mark.REDDD);
        assertFalse(board.hasColumn(Mark.REDDD));
        assertFalse(board.hasColumn(Mark.YELLO));

        board.setField(6, Mark.REDDD);
        assertTrue(board.hasColumn(Mark.REDDD));
        assertFalse(board.hasColumn(Mark.YELLO));
    }

    @Test
    public void testHasDiagonalDown() {
        board.setField(0, 0, Mark.REDDD);
        board.setField(1, 1, Mark.REDDD);
        assertFalse(board.hasDiagonal(Mark.REDDD));
        assertFalse(board.hasDiagonal(Mark.YELLO));

        board.setField(2, 2, Mark.REDDD);
        assertTrue(board.hasDiagonal(Mark.REDDD));
        assertFalse(board.hasDiagonal(Mark.YELLO));
    }

    @Test
    public void testHasDiagonalUp() {
        board.setField(0, 2, Mark.REDDD);
        board.setField(1, 1, Mark.REDDD);
        assertFalse(board.hasDiagonal(Mark.REDDD));
        assertFalse(board.hasDiagonal(Mark.YELLO));

        board.setField(2, 0, Mark.REDDD);
        assertTrue(board.hasDiagonal(Mark.REDDD));
        assertFalse(board.hasDiagonal(Mark.YELLO));
    }

    @Test
    public void testIsWinner() {
        board.setField(0, Mark.REDDD);
        board.setField(1, Mark.REDDD);
        assertFalse(board.isWinner(Mark.REDDD));
        assertFalse(board.isWinner(Mark.YELLO));

        board.setField(2, Mark.REDDD);
        assertTrue(board.isWinner(Mark.REDDD));
        assertFalse(board.isWinner(Mark.YELLO));

        board.setField(0, 0, Mark.YELLO);
        board.setField(1, 1, Mark.YELLO);
        assertFalse(board.isWinner(Mark.REDDD));
        assertFalse(board.isWinner(Mark.YELLO));

        board.setField(2, 2, Mark.YELLO);
        assertFalse(board.isWinner(Mark.REDDD));
        assertTrue(board.isWinner(Mark.YELLO));
    }

    @Test
    public void testHasNoWinnerFullBoard() {
        /**
         * REDDDo
         * YELLOx
         * REDDDo
         */
        board.setField(0, 0, Mark.REDDD);
        board.setField(0, 1, Mark.REDDD);
        board.setField(0, 2, Mark.YELLO);
        board.setField(1, 0, Mark.YELLO);
        board.setField(1, 1, Mark.YELLO);
        board.setField(1, 2, Mark.REDDD);
        board.setField(2, 0, Mark.REDDD);
        board.setField(2, 1, Mark.YELLO);
        board.setField(2, 2, Mark.REDDD);
        assertFalse(board.hasWinner());
    }

    @Test
    public void testHasWinnerRow() {
        board.setField(0, Mark.REDDD);
        board.setField(1, Mark.REDDD);
        assertFalse(board.hasWinner());

        board.setField(2, Mark.REDDD);
        assertTrue(board.hasWinner());
    }

    @Test
    public void testHasWinnerColumn() {
        board.setField(0, Mark.REDDD);
        board.setField(3, Mark.REDDD);
        assertFalse(board.hasWinner());

        board.setField(6, Mark.REDDD);
        assertTrue(board.hasWinner());
    }

    @Test
    public void testHasWinnerDiagonal() {
        board.setField(0, Mark.REDDD);
        board.setField(1, Mark.REDDD);
        assertFalse(board.hasWinner());

        board.setField(2, Mark.REDDD);
        assertTrue(board.hasWinner());
    }
}
