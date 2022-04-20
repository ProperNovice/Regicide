package gameStates;

import enums.EText;
import utils.Flow;

public abstract class AEndGame extends AGameState {

	@Override
	public void execute() {

		concealText();

		getEText().show();
		EText.RESTART.show();

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.executeGameState(RestartGame.class);
	}

	protected abstract EText getEText();

}
