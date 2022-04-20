package card;

import controllers.Credentials;
import enums.ESuit;
import enums.EValue;
import utils.Flow;
import utils.ImageView;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;
import utils.Logger;

public abstract class ACard implements IImageViewAble, IEventHandlerAble {

	private ESuit eSuit = null;
	protected int attack = -1;

	public ACard(ESuit eSuit, EValue eValue) {

		this.eSuit = eSuit;
		createImageView(eValue);

	}

	private void createImageView(EValue eValue) {

		String path = "";
		path += this.eSuit.toString();
		path.toLowerCase();
		path += "/";
		path += eValue.getValue();
		path += ".png";

		new ImageView(path, this);
		getImageView().setDimensions(Credentials.INSTANCE.dCard);
		getImageView().setBack("back.png");
		getImageView().flip();

	}

	public ESuit getESuit() {
		return this.eSuit;
	}

	public int getAttack() {
		return this.attack;
	}

	public final void print() {

		Logger.INSTANCE.log("printing card");
		Logger.INSTANCE.log(getClass());
		Logger.INSTANCE.log("suit -> " + this.eSuit.toString().toLowerCase());
		Logger.INSTANCE.log("attack -> " + this.attack);
		Logger.INSTANCE.newLine();

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getGameStateCurrent().handleCardPressed(this);
	}

}
