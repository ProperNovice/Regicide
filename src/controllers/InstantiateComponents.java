package controllers;

import card.CardCastle;
import card.CardTavern;
import enums.ESuit;
import enums.EValue;
import utils.ArrayList;

public enum InstantiateComponents {

	INSTANCE;

	public void instantiate() {

		createCards();

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

		Lists.INSTANCE.deckTavern.getArrayList().saveOriginal();
		Lists.INSTANCE.deckCastle.getArrayList().saveOriginal();

		Lists.INSTANCE.deckTavern.relocateImageViews();
		Lists.INSTANCE.deckCastle.relocateImageViews();

	}

}
