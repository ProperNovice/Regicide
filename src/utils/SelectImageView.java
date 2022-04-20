package utils;

import enums.ELayerZ;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;

public class SelectImageView implements IImageViewAble, IEventHandlerAble {

	public SelectImageView() {

		new ImageView("misc/select.png", ELayerZ.TOP, this);
		this.getImageView().setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		SelectImageViewManager.INSTANCE.handleMouseButtonPressedPrimary(this);
	}

	@Override
	public void handleMouseButtonPressedSecondary() {
		SelectImageViewManager.INSTANCE.handleMouseButtonPressedSecondary(this);
	}

	@Override
	public void handleMouseEntered() {
		SelectImageViewManager.INSTANCE.handleMouseEntered(this);
	}

	@Override
	public void handleMouseExited() {
		SelectImageViewManager.INSTANCE.handleMouseExited(this);
	}

}
