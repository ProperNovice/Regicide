package utils;

import controllers.Credentials;
import utils.Enums.DirectionEnum;
import utils.Interfaces.IImageViewAble;

public class CoordinatesList<T> {

	private ListImageViewAbles<T> listImageViewAbles = null;

	private Vector2 dimensionsImageViewAble = new Vector2(0, 0);
	private double firstObjectX, firstObjectY;

	public CoordinatesList(ListImageViewAbles<T> list) {
		this.listImageViewAbles = list;
	}

	public Vector2 getCoordinate(int index) {

		IImageViewAble imageViewAble = (IImageViewAble) this.listImageViewAbles.getArrayList()
				.getFirst();

		this.dimensionsImageViewAble.x = imageViewAble.getImageView().getWidth();
		this.dimensionsImageViewAble.y = imageViewAble.getImageView().getHeight();

		switch (this.listImageViewAbles.getListCredentials().rearrangeTypeEnum) {

		case LINEAR:
			this.firstObjectX = this.listImageViewAbles.getListCredentials().coordinatesList.x;
			this.firstObjectY = this.listImageViewAbles.getListCredentials().coordinatesList.y;
			break;

		case PIVOT:
			calculateFirstObjectCoordinatesPivot();
			break;

		case STATIC:
			return getRelocateTypeCoordinates(
					this.listImageViewAbles.getListCredentials().coordinatesList.x,
					this.listImageViewAbles.getListCredentials().coordinatesList.y);

		}

		double coordinateX = 0, coordinateY = 0;

		int row, column;

		if (this.listImageViewAbles.getListCredentials().objectsPerRow == -1) {

			row = 0;
			column = index;

		} else {

			row = index / this.listImageViewAbles.getListCredentials().objectsPerRow;
			column = index - row * this.listImageViewAbles.getListCredentials().objectsPerRow;

		}

		double x, y;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.x == Credentials.INSTANCE.dGapBetweenComponents.x)
			x = column * (this.dimensionsImageViewAble.x
					+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.x);
		else
			x = column * this.listImageViewAbles.getListCredentials().gapBetweenComponents.x;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.y == Credentials.INSTANCE.dGapBetweenComponents.y)
			y = row * (this.dimensionsImageViewAble.y
					+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.y);
		else
			y = row * this.listImageViewAbles.getListCredentials().gapBetweenComponents.y;

		switch (this.listImageViewAbles.getListCredentials().directionEnumHorizontal) {

		case RIGHT:
			coordinateX = this.firstObjectX + x;
			break;

		case LEFT:
			coordinateX = this.firstObjectX - x;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumHorizontal);
			break;

		}

		switch (this.listImageViewAbles.getListCredentials().directionEnumVertical) {

		case DOWN:
			coordinateY = this.firstObjectY + y;
			break;

		case UP:
			coordinateY = this.firstObjectY - y;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumVertical);
			break;

		}

		return getRelocateTypeCoordinates(coordinateX, coordinateY);

	}

	private void calculateFirstObjectCoordinatesPivot() {

		int rows, columns;
		int listSize = this.listImageViewAbles.getArrayList().size();

		if (this.listImageViewAbles.getListCredentials().objectsPerRow == -1) {

			rows = 1;
			columns = listSize;

		} else {

			rows = (int) (Math.ceil((double) listSize
					/ this.listImageViewAbles.getListCredentials().objectsPerRow));
			columns = (int) Math.min(listSize,
					this.listImageViewAbles.getListCredentials().objectsPerRow);

		}

		double totalX, totalY;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.x == Credentials.INSTANCE.dGapBetweenComponents.x)
			totalX = this.dimensionsImageViewAble.x
					+ (columns - 1) * (this.dimensionsImageViewAble.x
							+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.x);
		else
			totalX = this.dimensionsImageViewAble.x + (columns - 1)
					* this.listImageViewAbles.getListCredentials().gapBetweenComponents.x;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.y == Credentials.INSTANCE.dGapBetweenComponents.y)
			totalY = this.dimensionsImageViewAble.y + (rows - 1) * (this.dimensionsImageViewAble.y
					+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.y);
		else
			totalY = this.dimensionsImageViewAble.y + (rows - 1)
					* this.listImageViewAbles.getListCredentials().gapBetweenComponents.y;

		switch (this.listImageViewAbles.getListCredentials().directionEnumHorizontal) {

		case RIGHT:
			this.firstObjectX = this.listImageViewAbles.getListCredentials().coordinatesList.x
					- totalX / 2;
			break;

		case LEFT:
			this.firstObjectX = this.listImageViewAbles.getListCredentials().coordinatesList.x
					+ totalX / 2 - this.dimensionsImageViewAble.x;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumHorizontal);
			break;

		}

		switch (this.listImageViewAbles.getListCredentials().directionEnumVertical) {

		case DOWN:
			this.firstObjectY = this.listImageViewAbles.getListCredentials().coordinatesList.y
					- totalY / 2;
			break;

		case UP:
			this.firstObjectY = this.listImageViewAbles.getListCredentials().coordinatesList.y
					+ totalY / 2;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumVertical);
			break;

		}

	}

	private Vector2 getRelocateTypeCoordinates(double coordinateX, double coordinateY) {

		switch (this.listImageViewAbles.getListCredentials().relocateTypeEnum) {

		case TOP_LEFT:
			break;

		case TOP_RIGHT:
			coordinateX -= this.dimensionsImageViewAble.x;
			break;

		case BOTTOM_LEFT:
			coordinateY -= this.dimensionsImageViewAble.y;
			break;

		case BOTTOM_RIGHT:
			coordinateX -= this.dimensionsImageViewAble.x;
			coordinateY -= this.dimensionsImageViewAble.y;
			break;

		case CENTER:
			coordinateX -= this.dimensionsImageViewAble.x / 2;
			coordinateY -= this.dimensionsImageViewAble.y / 2;
			break;

		}

		return new Vector2(coordinateX, coordinateY);

	}

	private void logErrorShutDown(DirectionEnum directionEnum) {

		Logger.INSTANCE.log("ArrayListImageView direction error:");
		Logger.INSTANCE.log(directionEnum);
		ShutDown.INSTANCE.execute();

	}

}
