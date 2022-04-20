package utils;

import controllers.Credentials;
import enums.ELayerZ;
import utils.Interfaces.IImageViewAble;

public enum NumbersImageView {

	INSTANCE;

	private ArrayList<NumberImageView> list = new ArrayList<>();

	private NumbersImageView() {

	}

	public NumberImageView getNumberImageView(int number) {

		NumberImageView numberImageView = null;

		for (NumberImageView numberImageViewTemp : this.list) {

			if (numberImageViewTemp.getImageView().isVisible())
				continue;

			if (numberImageViewTemp.getNumber() != number)
				continue;

			numberImageView = numberImageViewTemp;
			numberImageView.getImageView().setVisible(true);
			break;

		}

		if (numberImageView == null) {

			numberImageView = new NumberImageView(number);
			this.list.addLast(numberImageView);

		}

		return numberImageView;

	}

	private class NumberImageView implements IImageViewAble {

		private int number = -1;

		public NumberImageView(int number) {

			this.number = number;

			String fileName = "misc/numbers/";
			fileName += Credentials.INSTANCE.numbersImageViewColor;
			fileName += "/";
			fileName += number;
			fileName += ".png";

			new ImageView(fileName, ELayerZ.TOP, this);

		}

		public int getNumber() {
			return this.number;
		}

	}

}
