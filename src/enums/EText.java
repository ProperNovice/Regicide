package enums;

import utils.Enums.TextTypeEnum;
import utils.Text;

public enum EText {

	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	CHOOSE_CARDS_TO_PLAY("Choose cards to play", TextTypeEnum.INDICATOR),

	;

	private TextTypeEnum textTypeEnum = null;
	private String string = null;

	private EText(String string, TextTypeEnum textTypeEnum) {

		this.string = string;
		this.textTypeEnum = textTypeEnum;

	}

	public void show() {
		Text.INSTANCE.showText(this, getString());
	}

	public void showAdditionally(String string) {
		Text.INSTANCE.showText(this, getString() + string);
	}

	public void showInstead(String string) {
		Text.INSTANCE.showText(this, string);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
