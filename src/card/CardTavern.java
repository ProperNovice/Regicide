package card;

import enums.ESuit;
import enums.EValue;

public class CardTavern extends ACard {

	public CardTavern(ESuit eSuit, EValue eValue) {

		super(eSuit, eValue);
		super.attack = eValue.getValue();

	}

}
