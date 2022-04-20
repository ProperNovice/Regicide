package gameStates;

import enums.EText;

public class EndGameWon extends AEndGame {

	@Override
	protected EText getEText() {
		return EText.YOU_WON;
	}

}
