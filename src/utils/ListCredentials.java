package utils;

import controllers.Credentials;
import utils.Enums.DirectionEnum;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class ListCredentials {

	public LayerZListEnum layerZListEnum = LayerZListEnum.TO_BACK_FIRST_IMAGEVIEW;
	public int objectsPerRow = -1, capacity = -1;
	public RearrangeTypeEnum rearrangeTypeEnum = RearrangeTypeEnum.LINEAR;
	public RelocateTypeEnum relocateTypeEnum = RelocateTypeEnum.TOP_LEFT;
	public DirectionEnum directionEnumHorizontal = DirectionEnum.RIGHT,
			directionEnumVertical = DirectionEnum.DOWN;
	public boolean showListSize = false;
	public Vector2 coordinatesList = new Vector2(0, 0),
			gapBetweenComponents = new Vector2(Credentials.INSTANCE.dGapBetweenComponents.x,
					Credentials.INSTANCE.dGapBetweenComponents.x);

}
