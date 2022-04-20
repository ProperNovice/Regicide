package listCredentials;

import controllers.Credentials;
import utils.Enums.RearrangeTypeEnum;

public class Hand extends AListCredentials {

	public Hand() {

		super.coordinatesList = Credentials.INSTANCE.cHand;
		super.rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		super.capacity = 8;

	}

}
