package gameStates;

import utils.Flow;

public class StartNewTurn extends AGameState {

	@Override
	public void execute() {

		Flow.INSTANCE.getFlow().addLast(ChooseCardsToPlay.class);
		Flow.INSTANCE.getFlow().addLast(ResolveHearts.class);
		Flow.INSTANCE.getFlow().addLast(ResolveDiamonds.class);
		Flow.INSTANCE.getFlow().addLast(ResolveSpades.class);
		Flow.INSTANCE.getFlow().addLast(PlayerAttack.class);
		Flow.INSTANCE.getFlow().addLast(RoyalPhase.class);

		Flow.INSTANCE.proceed();

	}

}
