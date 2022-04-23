package gameStates;

import controllers.Model;
import utils.Flow;

public class StartNewTurn extends AGameState {

	@Override
	public void execute() {

		Model.INSTANCE.clearCardsPlayedThisTurn();

		Flow.INSTANCE.getFlow().addLast(ChooseCardsToPlay.class);
		Flow.INSTANCE.getFlow().addLast(ResolveHearts.class);
		Flow.INSTANCE.getFlow().addLast(ResolveDiamonds.class);
		Flow.INSTANCE.getFlow().addLast(ResolveSpades.class);
		Flow.INSTANCE.getFlow().addLast(PlayerAttack.class);
		Flow.INSTANCE.getFlow().addLast(RoyalPhase.class);
		Flow.INSTANCE.getFlow().addLast(StartNewTurn.class);

		Flow.INSTANCE.proceed();

	}

}
