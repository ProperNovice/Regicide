package controllers;

import card.ACard;
import card.CardCastle;
import card.CardJester;
import utils.ArrayList;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;
import utils.ListImageViewAbles;

public enum Lists {

	INSTANCE;

	public final ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();
	public ListImageViewAbles<ACard> hand, deckTavern, discardPileTavern, cardsPlayed;
	public ListImageViewAbles<CardCastle> deckCastle, panelDeckCastle;
	public ListImageViewAbles<CardJester> jesters;

	public void instantiate() {

		// hand

		this.hand = new ListImageViewAbles<>();

		this.hand.getListCredentials().coordinatesList = Credentials.INSTANCE.cHand;
		this.hand.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		this.hand.getArrayList().setCapacity(8);

		// deck tavern

		this.deckTavern = new ListImageViewAbles<>();

		this.deckTavern.getListCredentials().coordinatesList = Credentials.INSTANCE.cDeckTavern;
		this.deckTavern.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		this.deckTavern
				.getListCredentials().layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		this.deckTavern.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;
		this.deckTavern.getListCredentials().showListSize = true;

		// discard pile tavern

		this.discardPileTavern = new ListImageViewAbles<>();

		this.discardPileTavern
				.getListCredentials().coordinatesList = Credentials.INSTANCE.cDiscardPileTavern;
		this.discardPileTavern.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		this.discardPileTavern
				.getListCredentials().layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		this.discardPileTavern.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;
		this.discardPileTavern.getListCredentials().showListSize = true;

		// deck castle

		this.deckCastle = new ListImageViewAbles<>();

		this.deckCastle.getListCredentials().coordinatesList = Credentials.INSTANCE.cDeckCastle;
		this.deckCastle.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		this.deckCastle
				.getListCredentials().layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		this.deckCastle.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;

		// cards played

		this.cardsPlayed = new ListImageViewAbles<>();

		this.cardsPlayed.getListCredentials().coordinatesList = Credentials.INSTANCE.cCardsPlayed;
		this.cardsPlayed
				.getListCredentials().layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		this.cardsPlayed.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;
		this.cardsPlayed.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.STATIC;

		// jester

		this.jesters = new ListImageViewAbles<>();

		this.jesters.getListCredentials().coordinatesList = Credentials.INSTANCE.cJesters;
		this.jesters.getListCredentials().layerZListEnum = LayerZListEnum.TO_BACK_FIRST_IMAGEVIEW;
		this.jesters.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		this.jesters.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;
		this.jesters.getListCredentials().showListSize = true;

		// panel deck castle

		this.panelDeckCastle = new ListImageViewAbles<>();

		this.panelDeckCastle
				.getListCredentials().coordinatesList = Credentials.INSTANCE.cPanelDeckCastle;
		this.panelDeckCastle.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		this.panelDeckCastle
				.getListCredentials().gapBetweenComponents.x = Credentials.INSTANCE.dCard.x / 4;

	}

	public void loadListsOriginal() {

		for (ArrayList<? extends Object> list : this.lists)
			list.loadOriginal();

	}

}
