package gameStates;

import card.ACard;
import card.CardCastle;
import controllers.Lists;
import controllers.Model;
import enums.ESuit;
import utils.Flow;

public class JUnit extends AGameState {

	@Override
	public void execute() {

		drawCardsToHand();
		setFirstCardDeckCastle();

		Model.INSTANCE.revealNextCardCastle();
		Flow.INSTANCE.executeGameState(StartNewTurn.class);

	}

	public void setFirstCardDeckCastle() {

		for (CardCastle cardCastle : Lists.INSTANCE.deckCastle.getArrayList().clone()) {

			if (!cardCastle.getESuit().equals(ESuit.DIAMONDS))
				continue;

			if (cardCastle.getHealth() != 20)
				continue;

			Lists.INSTANCE.deckCastle.getArrayList().remove(cardCastle);
			Lists.INSTANCE.deckCastle.getArrayList().addFirst(cardCastle);

		}

		Lists.INSTANCE.deckCastle.relocateImageViews();

	}

	public void drawCardsToHand() {

		transferCardFromDeckTavernToHand(ESuit.CLUBS, 5);
		transferCardFromDeckTavernToHand(ESuit.SPADES, 6);
		transferCardFromDeckTavernToHand(ESuit.HEARTS, 3);
		transferCardFromDeckTavernToHand(ESuit.DIAMONDS, 1);
		transferCardFromDeckTavernToHand(ESuit.CLUBS, 6);
		transferCardFromDeckTavernToHand(ESuit.SPADES, 3);
		transferCardFromDeckTavernToHand(ESuit.CLUBS, 1);
		transferCardFromDeckTavernToHand(ESuit.DIAMONDS, 3);

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
