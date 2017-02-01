package connect4;




public class OnlinePlayer extends Player {
	private int[] moveBuffer;

	public OnlinePlayer(Mark mark, String name) {
		super(name,mark);
		moveBuffer = new int[2];
		moveBuffer[0] = -1;

	}

	@Override
	public int[] determineMove(Board board) {
			return moveBuffer;
	}

	public void setMoveBuffer(int[] moveBuffer) {
		this.moveBuffer = moveBuffer;
	}
	
	public int getMoveBuffer() {
		return moveBuffer[0];
	}
}
