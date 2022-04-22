package gameStates;

import enums.ESuit;
import enums.EText;

public class ResolveHearts extends AResolveESuit {

	@Override
	protected ESuit getESuit() {
		return ESuit.HEARTS;
	}

	@Override
	protected EText getEText() {
		return EText.RESOLVE_HEARTS;
	}

}
