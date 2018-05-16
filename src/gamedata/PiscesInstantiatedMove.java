package gamedata;

public class PiscesInstantiatedMove {
	protected PiscesMove baseMove;
	protected int level;
	
	public PiscesInstantiatedMove(PiscesMove baseMove) {
		this.baseMove=baseMove;
		this.level=0;
	}
	
	public PiscesMove getBaseMove() {
		return this.baseMove;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void advanceLevel() {
		this.level=Math.min(++this.level, PiscesMove.MAX_LEVEL);
	}
}
