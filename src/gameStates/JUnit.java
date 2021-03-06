package gameStates;

import card.ACard;
import card.CardCastle;
import controllers.Lists;
import enums.ESuit;
import utils.Flow;

public class JUnit extends AGameState {

	@Override
	public void execute() {

//		drawCardsToHand();
//		setFirstCardDeckCastle();
//		transferCardsFromDeckToDiscardPile();
//		addCardsToPlayed();
//
//		Model.INSTANCE.revealNextCardCastle();
//
//		Flow.INSTANCE.executeGameState(StartNewTurn.class);
		Flow.INSTANCE.executeGameState(StartGame.class);

	}

	public void addCardsToPlayed() {

		for (int counter = 1; counter <= 5; counter++) {

			ACard card = Lists.INSTANCE.deckTavern.getArrayList().removeRandom();
			card.getImageView().flip();
			Lists.INSTANCE.cardsPlayed.getArrayList().addLast(card);

		}

		Lists.INSTANCE.cardsPlayed.relocateImageViews();

	}

	public void transferCardsFromDeckToDiscardPile() {

		for (int counter = 1; counter <= 2; counter++) {

			ACard card = Lists.INSTANCE.deckTavern.getArrayList().removeRandom();
			card.getImageView().flip();
			Lists.INSTANCE.discardPileTavern.getArrayList().addLast(card);

		}

		Lists.INSTANCE.discardPileTavern.relocateImageViews();

	}

	public void setFirstCardDeckCastle() {

		for (CardCastle cardCastle : Lists.INSTANCE.deckCastle.getArrayList().clone()) {

			if (!cardCastle.getESuit().equals(ESuit.HEARTS))
				continue;

			if (cardCastle.getHealth() != 20)
				continue;

			Lists.INSTANCE.deckCastle.getArrayList().remove(cardCastle);
			Lists.INSTANCE.deckCastle.getArrayList().addFirst(cardCastle);

		}

		System.out.println("a");
		Lists.INSTANCE.deckCastle.getArrayList().getFirst().getImageView().flipFront();
		Lists.INSTANCE.deckCastle.relocateImageViews();

	}

	public void drawCardsToHand() {

//		transferCardFromDeckTavernToHand(ESuit.CLUBS, 5);
//		transferCardFromDeckTavernToHand(ESuit.SPADES, 5);
//		transferCardFromDeckTavernToHand(ESuit.HEARTS, 3);
//		transferCardFromDeckTavernToHand(ESuit.DIAMONDS, 1);
//		transferCardFromDeckTavernToHand(ESuit.CLUBS, 10);
//		transferCardFromDeckTavernToHand(ESuit.SPADES, 3);
		transferCardFromDeckTavernToHand(ESuit.CLUBS, 1);
		transferCardFromDeckTavernToHand(ESuit.DIAMONDS, 3);

		Lists.INSTANCE.hand.relocateImageViews();

	}

	public void transferCardFromDeckTavernToHand(ESuit eSuit, int attack) {

		for (ACard card : Lists.INSTANCE.deckTavern.getArrayList().clone()) {

			if (!card.getESuit().equals(eSuit))
				continue;

			if (card.getValue() != attack)
				continue;

			card.getImageView().flipFront();

			Lists.INSTANCE.deckTavern.getArrayList().remove(card);
			Lists.INSTANCE.hand.getArrayList().addLast(card);

		}

	}

}
