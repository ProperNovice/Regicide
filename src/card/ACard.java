package card;

import controllers.Credentials;
import enums.ESuit;
import enums.EValue;
import utils.ImageView;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;

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

	}

	protected void printCRedentials() {

	}

	@Override
	public void handleMouseButtonPressedPrimary() {

	}

}
