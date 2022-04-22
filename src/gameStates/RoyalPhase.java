package gameStates;

import controllers.Lists;
import controllers.Model;
import utils.Flow;

public class RoyalPhase extends AGameState {

	@Override
	public void execute() {

		if (!Model.INSTANCE.performedRegicideThisTurn())
			Flow.INSTANCE.getFlow().addFirst(RoyalAttack.class);

		else if (Lists.INSTANCE.deckCastle.getArrayList().isEmpty()) {

			Flow.INSTANCE.getFlow().clear();
			Flow.INSTANCE.getFlow().addFirst(EndGameWon.class);

		}

		else
			Model.INSTANCE.setNewRoyal();

		Flow.INSTANCE.proceed();

	}

}
