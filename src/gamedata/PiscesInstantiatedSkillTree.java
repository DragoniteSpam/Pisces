package gamedata;

import com.pisces.Settings;

import gamedata.abilities.PiscesAbility;
import gamedata.abilities.PiscesSkillTree;

public class PiscesInstantiatedSkillTree {
	private PiscesSkillTree baseTree;
	private int ap;
	
	public PiscesInstantiatedSkillTree(PiscesSkillTree baseTree) {
		this.baseTree=baseTree;
		this.ap=0;
	}
	
	public PiscesSkillTree getBaseTree() {
		return this.baseTree;
	}
	
	public PiscesAbility getMaxAbility() {
		return this.baseTree.getAbilities()[ap/Settings.BASE_AP_PER_ABILITY];
	}
}
