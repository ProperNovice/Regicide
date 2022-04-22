package gameStates;

import enums.ESuit;
import enums.EText;

public class ResolveSpades extends AResolveESuit {

	@Override
	protected ESuit getESuit() {
		return ESuit.SPADES;
	}

	@Override
	protected EText getEText() {
		return EText.RESOLVE_SPADES;
	}

}
