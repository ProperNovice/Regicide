package gameStates;

import enums.ESuit;
import enums.EText;

public class ResolveDiamonds extends AResolveESuit {

	@Override
	protected ESuit getESuit() {
		return ESuit.DIAMONDS;
	}

	@Override
	protected EText getEText() {
		return EText.RESOLVE_DIAMONDS;
	}

}
