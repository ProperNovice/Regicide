package controllers;

import card.ACard;
import card.CardCastle;
import card.CardJester;
import enums.ESuit;
import enums.EValue;
import utils.ArrayList;
import utils.HashMap;
import utils.Interfaces.IImageViewAble;
import utils.ListImageViewAbles;
import utils.Logger;
import utils.SelectImageViewManager;
import utils.ShutDown;

public enum Model {

	INSTANCE;

	private ArrayList<ACard> cardsPlayedThisTurn = new ArrayList<>();
	private boolean performedRegicideThisTurn = false;

	public void clearCardsPlayedThisTurn() {
		this.cardsPlayedThisTurn.clear();
	}

	public void playJester() {

		Lists.INSTANCE.jesters.getArrayList().removeFirst().getImageView().setVisible(false);

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		if (!Lists.INSTANCE.hand.getArrayList().isEmpty()) {

			Lists.INSTANCE.discardPileTavern.getArrayList()
					.addAllFirst(Lists.INSTANCE.hand.getArrayList().clear());

			Lists.INSTANCE.discardPileTavern.animateSynchronousLock();

		}

		resolveDiamonds(8);

	}

	public int getTotalValueInHand() {

		int value = 0;

		for (ACard card : Lists.INSTANCE.hand)
			value += card.getValue();

		return value;

	}

	public void startNewGame() {

		resolveDiamonds(8);
		setNewRoyal();

	}

	public void setUpNewGame() {

		Lists.INSTANCE.loadListsOriginal();

		// prepare deck tavern

		for (ACard card : Lists.INSTANCE.deckTavern)
			card.getImageView().flipBack();

		Lists.INSTANCE.deckTavern.getArrayList().shuffle();
		Lists.INSTANCE.deckTavern.relocateImageViews();

		// prepare deck castle

		HashMap<Integer, ArrayList<CardCastle>> map = new HashMap<>();
		map.put(EValue.JACK.getValue() / 2, new ArrayList<>());
		map.put(EValue.QUEEN.getValue() / 2, new ArrayList<>());
		map.put(EValue.KING.getValue() / 2, new ArrayList<>());

		for (CardCastle cardCastle : Lists.INSTANCE.deckCastle)
			map.getValue(cardCastle.getValue()).addLast(cardCastle);

		Lists.INSTANCE.deckCastle.getArrayList().clear();

		for (Integer integer : map) {

			map.getValue(integer).shuffle();
			Lists.INSTANCE.deckCastle.getArrayList().addAllLast(map.getValue(integer));

		}

		for (ACard card : Lists.INSTANCE.deckCastle)
			card.getImageView().flipBack();

		Lists.INSTANCE.deckCastle.relocateImageViews();

		// prepare jester

		for (CardJester cardJester : Lists.INSTANCE.jesters)
			cardJester.getImageView().setVisible(true);

	}

	public void setNewRoyal() {

		CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().getFirst();
		cardCastle.getImageView().flipFront();

		IconsNumbers.ATTACK.setValue(cardCastle.getValue());
		IconsNumbers.HEALTH.setValue(cardCastle.getHealth());

	}

	public void discardHandCardsSelected() {

		ArrayList<IImageViewAble> cardsToDiscard = SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles();

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		for (IImageViewAble imageViewAble : cardsToDiscard) {

			Lists.INSTANCE.hand.getArrayList().remove((ACard) imageViewAble);
			Lists.INSTANCE.discardPileTavern.getArrayList().addFirst((ACard) imageViewAble);

		}

		Lists.INSTANCE.hand.animateSynchronous();
		Lists.INSTANCE.discardPileTavern.animateSynchronousLock();

	}

	public boolean performedRegicideThisTurn() {
		return this.performedRegicideThisTurn;
	}

	public void resolveAttackPlayer() {

		this.performedRegicideThisTurn = false;

		int attack = getTotalValuePlayed();
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
		listToAdd.animateSynchronous();

		Lists.INSTANCE.discardPileTavern.getArrayList()
				.addAllFirst(Lists.INSTANCE.cardsPlayed.getArrayList().clear());

		Lists.INSTANCE.discardPileTavern.animateSynchronousLock();

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

		}

		Lists.INSTANCE.hand.animateSynchronousLock();

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

	public int getTotalValueSelected(boolean countingClubs) {

		int value = 0, multiplication = 1;

		for (IImageViewAble imageViewAble : SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles()) {

			ACard card = (ACard) imageViewAble;

			value += card.getValue();

			if (!countingClubs)
				continue;

			if (card.getESuit().equals(ESuit.CLUBS))
				multiplication = 2;

		}

		if (Lists.INSTANCE.deckCastle.getArrayList().getFirst().getESuit().equals(ESuit.CLUBS))
			return value;
		else
			return value * multiplication;

	}

	public int getTotalValuePlayed() {

		int value = 0, multiplication = 1;

		for (ACard card : this.cardsPlayedThisTurn) {

			value += card.getValue();

			if (card.getESuit().equals(ESuit.CLUBS))
				multiplication = 2;

		}

		if (Lists.INSTANCE.deckCastle.getArrayList().getFirst().getESuit().equals(ESuit.CLUBS))
			return value;
		else
			return value * multiplication;

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
