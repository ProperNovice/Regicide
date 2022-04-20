package gameStates;

import card.ACard;
import enums.EText;

public class ChooseCardsToPlay extends AGameState {

	@Override
	public void execute() {

		EText.CHOOSE_CARDS_TO_PLAY.show();

	}

	@Override
	protected void handleCardPressedHand(ACard card) {

	}

}
