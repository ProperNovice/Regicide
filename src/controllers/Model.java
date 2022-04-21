package controllers;

import card.ACard;
import card.CardCastle;
import enums.ESuit;
import utils.ArrayList;

public enum Model {

	INSTANCE;

	private ArrayList<ACard> cardsPlayedThisTurn = new ArrayList<>();

	private void resolveSpades(int value) {

	}

	private void resolveClubs(int value) {

	}

	private void resolveDiamonds(int value) {

	}

	private void resolveHearts(int value) {

	}

	public void resolveESuit(ESuit eSuit) {

		int value = 0;

		for (ACard card : this.cardsPlayedThisTurn)
			value += card.getValue();

		switch (eSuit) {

		case HEARTS:
			resolveHearts(value);
			break;

		case DIAMONDS:
			resolveDiamonds(value);
			break;

		case CLUBS:
			resolveClubs(value);
			break;

		case SPADES:
			resolveSpades(value);
			break;

		}

	}

	public void setCardsPlayedThisTurn(ArrayList<ACard> list) {
		this.cardsPlayedThisTurn = list.clone();
	}

	public void revealNextCardCastle() {

		CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().getFirst();
		cardCastle.getImageView().flip();

		IconsNumbers.ATTACK.setValue(cardCastle.getValue());
		IconsNumbers.HEALTH.setValue(cardCastle.getHealth());

	}

}
