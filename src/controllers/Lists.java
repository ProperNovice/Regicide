package controllers;

import card.ACard;
import card.CardCastle;
import listCredentials.DeckCastle;
import listCredentials.DeckTavern;
import listCredentials.DiscardPileTavern;
import listCredentials.Hand;
import utils.ArrayList;
import utils.ListImageViewAbles;

public enum Lists {

	INSTANCE;

	public ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();
	public ListImageViewAbles<ACard> hand, deckTavern, discardPileTavern;
	public ListImageViewAbles<CardCastle> deckCastle;

	public void instantiate() {

		this.hand = new ListImageViewAbles<>(new Hand());
		this.deckTavern = new ListImageViewAbles<>(new DeckTavern());
		this.discardPileTavern = new ListImageViewAbles<>(new DiscardPileTavern());
		this.deckCastle = new ListImageViewAbles<>(new DeckCastle());

	}

}
