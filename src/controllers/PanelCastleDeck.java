package controllers;

import enums.ELayerZ;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public enum PanelCastleDeck implements IImageViewAble {

	INSTANCE;

	private PanelCastleDeck() {

		new ImageView("misc/background.png", ELayerZ.DEFAULT, this);
		getImageView().setVisible(false);

	}

	public void show() {

	}

	public void hide() {

	}

}
