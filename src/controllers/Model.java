package controllers;

import card.CardCastle;

public enum Model {

	INSTANCE;

	public void revealNextCardCastle() {

		CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().getFirst();
		cardCastle.getImageView().flip();

		IconsNumbers.ATTACK.setValue(cardCastle.getAttack());
		IconsNumbers.HEALTH.setValue(cardCastle.getHealth());

	}

}
