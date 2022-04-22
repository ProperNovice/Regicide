package card;

import controllers.Credentials;
import utils.Flow;
import utils.ImageView;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;
import utils.Logger;

public class CardJester implements IImageViewAble, IEventHandlerAble {

	public CardJester() {

		new ImageView("jester.png", this);
		getImageView().setDimensions(Credentials.INSTANCE.dCard);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {

		Logger.INSTANCE.logNewLine("jester pressed");
		Flow.INSTANCE.getGameStateCurrent().handleJesterPressed();

	}

}
