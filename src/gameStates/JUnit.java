package gameStates;

import card.ACard;
import controllers.Lists;
import enums.ESuit;

public class JUnit extends AGameState {

	@Override
	public void execute() {

		drawCardsToHand();

	}

	public void drawCardsToHand() {

		transferCardFromDeckTavernToHand(ESuit.CLUBS, 5);
		transferCardFromDeckTavernToHand(ESuit.SPADES, 10);

		Lists.INSTANCE.hand.relocateImageViews();

	}

	public void transferCardFromDeckTavernToHand(ESuit eSuit, int attack) {

		for (ACard card : Lists.INSTANCE.deckTavern.getArrayList().clone()) {

			if (!card.getESuit().equals(eSuit))
				continue;

			if (card.getAttack() != attack)
				continue;

			card.getImageView().flipFront();

			Lists.INSTANCE.deckTavern.getArrayList().remove(card);
			Lists.INSTANCE.hand.getArrayList().addLast(card);

		}

	}

}
