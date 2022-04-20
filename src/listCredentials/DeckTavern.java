package listCredentials;

import controllers.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;

public class DeckTavern extends AListCredentials {

	public DeckTavern() {

		super.coordinatesList = Credentials.INSTANCE.cDeckTavern;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.showListSize = true;

	}

}
