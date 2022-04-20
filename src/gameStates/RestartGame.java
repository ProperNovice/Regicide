package gameStates;

import utils.SelectImageViewManager;

public class RestartGame extends AGameState {

	@Override
	public void execute() {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

	}

}
