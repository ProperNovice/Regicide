package controllers;

import card.ACard;
import card.CardCastle;
import enums.ESuit;
import utils.ArrayList;
import utils.ListImageViewAbles;
import utils.Logger;
import utils.ShutDown;

public enum Model {

	INSTANCE;

	private ArrayList<ACard> cardsPlayedThisTurn = new ArrayList<>();
	private boolean performedRegicideThisTurn = false;

	public boolean performedRegicideThisTurn() {
		return this.performedRegicideThisTurn;
	}

	public void resolveAttackPlayer() {

		this.performedRegicideThisTurn = false;

		int attack = getTotalAttackPlayed();
		int castleHealth = IconsNumbers.HEALTH.getValue();

		int healthRemaining = castleHealth - attack;
		IconsNumbers.HEALTH.setValue(Math.max(healthRemaining, 0));

		if (healthRemaining > 0)
			return;

		this.performedRegicideThisTurn = true;
		IconsNumbers.ATTACK.setValue(0);

		CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().removeFirst();

		ListImageViewAbles<ACard> listToAdd = null;

		if (healthRemaining == 0) {

			cardCastle.getImageView().flip();
			listToAdd = Lists.INSTANCE.deckTavern;

		} else
			listToAdd = Lists.INSTANCE.discardPileTavern;

		listToAdd.getArrayList().addFirst(cardCastle);
		listToAdd.animateSynchronousLock();

	}

	public int getTotalAttackPlayed() {

		int value = getTotalValuePlayed();

		for (ACard card : this.cardsPlayedThisTurn) {

			if (!card.getESuit().equals(ESuit.CLUBS))
				continue;

			value *= 2;
			break;

		}

		return value;

	}

	private void resolveSpades(int value) {

		int castleAttack = Math.max(IconsNumbers.ATTACK.getValue() - value, 0);
		IconsNumbers.ATTACK.setValue(castleAttack);

		Logger.INSTANCE.logNewLine("resolving spades");

	}

	private void resolveDiamonds(int value) {

		int cardsEmptyAtHand = Lists.INSTANCE.hand.getArrayList().getCapacity()
				- Lists.INSTANCE.hand.getArrayList().size();
		int cardsDeck = Lists.INSTANCE.deckTavern.getArrayList().size();

		int cardsToDraw = Math.min(cardsEmptyAtHand, cardsDeck);
		cardsToDraw = Math.min(value, cardsToDraw);

		Logger.INSTANCE.log("resolving diamons");
		Logger.INSTANCE.logNewLine("cards to draw -> " + cardsToDraw);

		for (int counter = 1; counter <= cardsToDraw; counter++) {

			ACard card = Lists.INSTANCE.deckTavern.getArrayList().removeFirst();
			card.getImageView().flip();

			Lists.INSTANCE.hand.getArrayList().addFirst(card);
			Lists.INSTANCE.hand.animateSynchronousLock();

		}

	}

	private void resolveHearts(int value) {

		int cardsToShuffle = Math.min(value,
				Lists.INSTANCE.discardPileTavern.getArrayList().size());

		Logger.INSTANCE.log("resolving hearts");
		Logger.INSTANCE.logNewLine("cards to shuffle -> " + cardsToShuffle);

		Lists.INSTANCE.discardPileTavern.getArrayList().shuffle();

		for (int counter = 1; counter <= cardsToShuffle; counter++) {

			ACard card = Lists.INSTANCE.discardPileTavern.getArrayList().removeRandom();
			card.getImageView().flip();
			Lists.INSTANCE.deckTavern.getArrayList().addLast(card);

		}

		Lists.INSTANCE.discardPileTavern.relocateImageViews();
		Lists.INSTANCE.deckTavern.animateSynchronousLock();

	}

	private int getTotalValuePlayed() {

		int value = 0;

		for (ACard card : this.cardsPlayedThisTurn)
			value += card.getValue();

		return value;

	}

	public void resolveESuit(ESuit eSuit) {

		int value = getTotalValuePlayed();

		switch (eSuit) {

		case HEARTS:
			resolveHearts(value);
			break;

		case DIAMONDS:
			resolveDiamonds(value);
			break;

		case SPADES:
			resolveSpades(value);
			break;

		default:
			break;

		}

	}

	public boolean canResolveESuit(ESuit eSuit) {

		if (Lists.INSTANCE.deckCastle.getArrayList().getFirst().getESuit().equals(eSuit))
			return false;

		boolean cardsPlayedContainSuit = false;

		for (ACard aCard : this.cardsPlayedThisTurn)
			if (aCard.getESuit().equals(eSuit))
				cardsPlayedContainSuit = true;

		if (!cardsPlayedContainSuit)
			return false;

		switch (eSuit) {

		case HEARTS:
			return !Lists.INSTANCE.discardPileTavern.getArrayList().isEmpty();

		case DIAMONDS:
			return !Lists.INSTANCE.deckTavern.getArrayList().isEmpty();

		case CLUBS:
			return true;

		case SPADES:
			return IconsNumbers.ATTACK.getValue() > 0;

		}

		ShutDown.INSTANCE.execute();
		return false;

	}

	public void setCardsPlayedThisTurn(ArrayList<ACard> list) {

		this.cardsPlayedThisTurn = list.clone();

		for (ACard aCard : this.cardsPlayedThisTurn)
			Lists.INSTANCE.hand.getArrayList().remove(aCard);

		Lists.INSTANCE.cardsPlayed.getArrayList().addAllFirst(this.cardsPlayedThisTurn);
		Lists.INSTANCE.hand.relocateImageViews();
		Lists.INSTANCE.cardsPlayed.relocateImageViews();

	}

	public void revealNextCardCastle() {

		CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().getFirst();
		cardCastle.getImageView().flip();

		IconsNumbers.ATTACK.setValue(cardCastle.getValue());
		IconsNumbers.HEALTH.setValue(cardCastle.getHealth());

	}

}
