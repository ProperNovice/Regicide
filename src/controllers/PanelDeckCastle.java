package controllers;

import card.ACard;
import card.CardCastle;
import enums.ELayerZ;
import utils.ArrayList;
import utils.HashMap;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public enum PanelDeckCastle implements IImageViewAble {

	INSTANCE;

	private boolean isShowing = false;

	private PanelDeckCastle() {

		new ImageView("misc/background.png", ELayerZ.DEFAULT, this);
		getImageView().setVisible(false);

	}

	public void handlePanel() {

		if (this.isShowing)
			hide();
		else
			show();

	}

	private void show() {

		this.isShowing = true;

		getImageView().setVisible(true);

		Lists.INSTANCE.deckCastle.getArrayList().saveState();
		Lists.INSTANCE.panelDeckCastle.getArrayList().clear();

		HashMap<Integer, ArrayList<CardCastle>> map = new HashMap<>();

		for (int counter = 20; counter >= 10; counter -= 5)
			map.put(counter, new ArrayList<>());

		while (!Lists.INSTANCE.deckCastle.getArrayList().isEmpty()) {

			CardCastle cardCastle = Lists.INSTANCE.deckCastle.getArrayList().removeRandom();
			map.getValue(cardCastle.getValue()).addLast(cardCastle);

		}

		for (int counter = 20; counter >= 10; counter -= 5)
			Lists.INSTANCE.panelDeckCastle.getArrayList().addAllLast(map.getValue(counter).clear());

		for (ACard card : Lists.INSTANCE.panelDeckCastle)
			card.getImageView().flipFront();

		Lists.INSTANCE.panelDeckCastle.relocateImageViews();

	}

	private void hide() {

		this.isShowing = false;

		getImageView().setVisible(false);

		Lists.INSTANCE.deckCastle.getArrayList().loadState();

		for (CardCastle cardCastle : Lists.INSTANCE.deckCastle) {

			if (Lists.INSTANCE.deckCastle.getArrayList().getFirst().equals(cardCastle))
				continue;

			cardCastle.getImageView().flipBack();

		}

		Lists.INSTANCE.deckCastle.relocateImageViews();

	}

}
