package gameStates;

import controllers.IconsNumbers;
import controllers.Model;
import enums.EText;
import utils.Flow;

public class StartGame extends AGameState {

	@Override
	public void execute() {

		EText.START_GAME.show();

		for (IconsNumbers iconsNumbers : IconsNumbers.values())
			iconsNumbers.clearIconsList();

		Model.INSTANCE.setUpNewGame();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.startNewGame();
		Flow.INSTANCE.executeGameState(StartNewTurn.class);

	}

}
