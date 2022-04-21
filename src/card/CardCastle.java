package card;

import enums.ESuit;
import enums.EValue;

public class CardCastle extends ACard {

	private int health = -1;

	public CardCastle(ESuit eSuit, EValue eValue) {

		super(eSuit, eValue);
		this.health = eValue.getValue();
		super.value = this.health / 2;

	}

	public int getHealth() {
		return this.health;
	}

}
