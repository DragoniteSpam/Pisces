package gamedata;

public class PiscesInstantiatedMove {
	protected PiscesMove baseMove;
	protected int level;
	protected boolean unlocked;
	
	public PiscesInstantiatedMove(PiscesMove baseMove) {
		this.baseMove=baseMove;
		this.level=0;
		this.unlocked=false;
	}
	
	public PiscesMove getBaseMove() {
		return this.baseMove;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void advanceLevel() {
		int maxLevel;
		if (this.unlocked) {
			maxLevel=PiscesMove.MAX_LEVEL;
		} else {
			maxLevel=PiscesMove.MAX_LEVEL/2;
		}
		this.level=Math.min(++this.level, maxLevel);
	}
	
	public void unlock() {
		this.unlocked=true;
	}
	
	public boolean getUnlocked() {
		return this.unlocked;
	}
}
