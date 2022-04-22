package card;

import controllers.Credentials;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public class CardJester implements IImageViewAble {

	public CardJester() {

		new ImageView("jester.png", this);
		getImageView().setDimensions(Credentials.INSTANCE.dCard);

	}

}
