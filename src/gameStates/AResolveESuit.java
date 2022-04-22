package gameStates;

import controllers.Model;
import enums.ESuit;
import enums.EText;
import utils.Flow;

public abstract class AResolveESuit extends AGameState {

	@Override
	public void execute() {

		if (!Model.INSTANCE.canResolveESuit(getESuit()))
			Flow.INSTANCE.proceed();

		else {
			getEText().show();
			EText.CONTINUE.show();
		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.resolveESuit(getESuit());
		Flow.INSTANCE.proceed();

	}

	protected abstract ESuit getESuit();

	protected abstract EText getEText();

}
