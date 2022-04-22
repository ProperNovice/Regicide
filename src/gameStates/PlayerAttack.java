package gameStates;

import controllers.Model;
import enums.EText;
import utils.Flow;

public class PlayerAttack extends AGameState {

	@Override
	public void execute() {

		EText.PLAYER_PHASE.show();
		EText.ATTACK.showAdditionally(Model.INSTANCE.getTotalValuePlayed());
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.resolveAttackPlayer();
		Flow.INSTANCE.proceed();

	}

}
