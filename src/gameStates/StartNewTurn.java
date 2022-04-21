package gameStates;

import utils.Flow;

public class StartNewTurn extends AGameState {

	@Override
	public void execute() {

		Flow.INSTANCE.getFlow().addLast(ChooseCardsToPlay.class);

		Flow.INSTANCE.proceed();

	}

}
