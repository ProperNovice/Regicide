package enums;

import utils.Enums.TextTypeEnum;
import utils.Text;

public enum EText {

	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	PLAY_CARDS("Play cards", TextTypeEnum.INDICATOR),
	RESOLVE_CLUBS("Resolve clubs", TextTypeEnum.INDICATOR),
	RESOLVE_DIAMONDS("Resolve diamonds", TextTypeEnum.INDICATOR),
	RESOLVE_HEARTS("Resolve hearts", TextTypeEnum.INDICATOR),
	RESOLVE_SPADES("Resolve spades", TextTypeEnum.INDICATOR),
	PLAYER_PHASE("Player phase", TextTypeEnum.INDICATOR),
	ROYAL_ATTACK("Royal attack", TextTypeEnum.INDICATOR),
	ATTACK("Attack -> ", TextTypeEnum.INDICATOR),
	DISCARD_CARDS("Discard cards", TextTypeEnum.INDICATOR),
	VOID("", TextTypeEnum.INDICATOR),
	START_GAME("Start game", TextTypeEnum.OPTION),
	SKIP("Skip", TextTypeEnum.OPTION),

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
	
	public void showAdditionally(int integer) {
		showAdditionally(Integer.toString(integer));
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
