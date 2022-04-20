package utils;

import controllers.Credentials;
import enums.EText;
import utils.Interfaces.IEventHandlerAble;

public class TextEnum implements IEventHandlerAble {

	private EText eText = null;
	private TextIndicator text = null;

	public TextEnum(EText eText) {

		this.eText = eText;
		createText();

	}

	private void createText() {

		String text = this.eText.getString();

		switch (this.eText.getTextTypeEnum()) {

		case INDICATOR:
			this.text = new TextIndicator(text);
			break;

		case OPTION:
			this.text = new TextOption(text, this);
			break;

		}

		if (this.eText.getString().contains("\n"))
			this.text.setHeight(2 * Credentials.INSTANCE.textHeight);
		else
			this.text.setHeight(Credentials.INSTANCE.textHeight);

		this.text.setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getGameStateCurrent().handleTextOptionPressed(this.eText);
	}

	public void relocate(double x, double y) {
		this.text.relocateTopLeft(x, y);
	}

	public void relocate(Vector2 numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public void toFront() {
		this.text.toFront();
	}

	public void setVisible(boolean value) {
		this.text.setVisible(value);
	}

	public EText getEText() {
		return this.eText;
	}

	public TextIndicator getText() {
		return this.text;
	}

}
