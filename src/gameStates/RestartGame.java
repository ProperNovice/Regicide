package gameStates;

import utils.Flow;
import utils.SelectImageViewManager;

public class RestartGame extends AGameState {

	@Override
	public void execute() {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		Flow.INSTANCE.getFlow().clear();

		Flow.INSTANCE.executeGameState(StartGame.class);

	}

}
