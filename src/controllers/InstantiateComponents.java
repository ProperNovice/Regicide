package controllers;

import card.CardCastle;
import card.CardJester;
import card.CardTavern;
import enums.ESuit;
import enums.EValue;
import utils.ArrayList;

public enum InstantiateComponents {

	INSTANCE;

	public void instantiate() {

		createCards();
		createIcons();

		PanelCastleDeck.INSTANCE.getImageView();

	}

	private void createIcons() {

		IconsNumbers.ATTACK.getImageView();
		IconsNumbers.HEALTH.getImageView();

	}

	private void createCards() {

		ArrayList<EValue> deckTavern = new ArrayList<>(EValue.values());
		deckTavern.remove(EValue.JACK);
		deckTavern.remove(EValue.QUEEN);
		deckTavern.remove(EValue.KING);

		ArrayList<EValue> deckCastle = new ArrayList<>();
		deckCastle.addLast(EValue.JACK);
		deckCastle.addLast(EValue.QUEEN);
		deckCastle.addLast(EValue.KING);

		for (ESuit eSuit : ESuit.values())
			for (EValue eValue : EValue.values()) {

				if (deckTavern.contains(eValue))
					Lists.INSTANCE.deckTavern.getArrayList().addLast(new CardTavern(eSuit, eValue));
				else if (deckCastle.contains(eValue))
					Lists.INSTANCE.deckCastle.getArrayList().addLast(new CardCastle(eSuit, eValue));

			}

		for (int counter = 1; counter <= 2; counter++)
			Lists.INSTANCE.jesters.getArrayList().addFirst(new CardJester());

		Lists.INSTANCE.deckTavern.getArrayList().saveOriginal();
		Lists.INSTANCE.deckCastle.getArrayList().saveOriginal();
		Lists.INSTANCE.jesters.getArrayList().saveOriginal();

		Lists.INSTANCE.deckTavern.relocateImageViews();
		Lists.INSTANCE.deckCastle.relocateImageViews();
		Lists.INSTANCE.jesters.relocateImageViews();

	}

}
