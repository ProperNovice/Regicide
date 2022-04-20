package controllers;

import card.ACard;
import card.CardCastle;
import utils.ArrayList;

public enum Model {

	INSTANCE;

	private ArrayList<ACard> cardsPlayedThisTurn = new ArrayList<>();

	public void playCards(ArrayList<ACard> list) {

		this.cardsPlayedThisTurn = list.clone();

	}

	public void revealNextCardCastle() {

		CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().getFirst();
		cardCastle.getImageView().flip();

		IconsNumbers.ATTACK.setValue(cardCastle.getAttack());
		IconsNumbers.HEALTH.setValue(cardCastle.getHealth());

	}

}
