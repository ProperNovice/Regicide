package gameStates;

import controllers.Model;
import utils.Flow;

public class RoyalPhase extends AGameState {

	@Override
	public void execute() {

		Class<? extends AGameState> gameStateNext = null;

		if (!Model.INSTANCE.performedRegicideThisTurn())
			gameStateNext = RoyalAttack.class;
		else
			gameStateNext = RoyalSetNew.class;

		Flow.INSTANCE.executeGameState(gameStateNext);

	}

}
