package listCredentials;

import controllers.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;

public class DeckCastle extends AListCredentials {

	public DeckCastle() {

		super.coordinatesList = Credentials.INSTANCE.cDeckCastle;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;

	}

}
