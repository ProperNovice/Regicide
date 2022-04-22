package gameStates;

import controllers.Model;
import utils.Flow;

public class RoyalPhase extends AGameState {

	@Override
	public void execute() {

		if (!Model.INSTANCE.performedRegicideThisTurn())
			Flow.INSTANCE.getFlow().addFirst(RoyalAttack.class);
		else
			Model.INSTANCE.setNewRoyal();

		Flow.INSTANCE.proceed();

	}

}
