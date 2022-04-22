package gameStates;

import card.ACard;
import controllers.Lists;
import enums.EText;
import javafx.scene.input.KeyCode;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.Text;

public abstract class AGameState {

	public abstract void execute();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text option executing");
		Logger.INSTANCE.logNewLine(textEnum);

		concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		int keyCodeID = KeyCodeHandler.INSTANCE.getKeyCodeInt(keyCode);

		if (keyCodeID == -1)
			return;

		EText textEnumPressed = Text.INSTANCE.getTextEnumOptionPressed(keyCodeID);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void executeTextOption(EText eText) {

	}

	protected final void concealText() {
		Text.INSTANCE.concealText();
	}

	public final void handleCardPressed(ACard card) {

		if (Lists.INSTANCE.hand.getArrayList().contains(card)) {

			card.print();
			handleCardPressedHand(card);

		}

	}

	protected void handleCardPressedHand(ACard card) {

	}

	public void handleJesterPressed() {

	}

}
