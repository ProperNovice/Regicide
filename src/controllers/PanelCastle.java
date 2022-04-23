package controllers;

import enums.ELayerZ;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public enum PanelCastle implements IImageViewAble {

	INSTANCE;

	private PanelCastle() {

		new ImageView("misc/background.png", ELayerZ.DEFAULT, this);
		getImageView().setVisible(false);

	}

}
