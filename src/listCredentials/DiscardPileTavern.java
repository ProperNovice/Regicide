package listCredentials;

import controllers.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;

public class DiscardPileTavern extends AListCredentials {

	public DiscardPileTavern() {

		super.coordinatesList = Credentials.INSTANCE.cDiscardPileTavern;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.showListSize = true;

	}

}
