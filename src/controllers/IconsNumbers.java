package controllers;

import utils.ArrayList;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.NumbersImageView;
import utils.Vector2;

public enum IconsNumbers implements IImageViewAble {

	ATTACK("attack", Credentials.INSTANCE.cAttack), HEALTH("heart", Credentials.INSTANCE.cLife);

	private int value = -1;
	private Vector2 coordinates = null;
	private ArrayList<IImageViewAble> list = new ArrayList<>();

	private IconsNumbers(String path, Vector2 coordinates) {

		this.coordinates = coordinates;
		new ImageView(path + ".png", this);
		getImageView().setDimensions(Credentials.INSTANCE.dIconIndicator);
		getImageView().relocateCenter(this.coordinates);

	}

	public void setValue(int value) {
		this.value = value;
		setIcons();
	}

	public void add(int value) {
		this.value += value;
		setIcons();
	}

	public void substract(int value) {
		this.value -= value;
		setIcons();
	}

	private void setIcons() {

		// clear list

		for (IImageViewAble imageViewAble : this.list)
			imageViewAble.getImageView().setVisible(false);

		this.list.clear();

		// create list of numbers

		int value = this.value;
		ArrayList<Integer> listInteger = new ArrayList<>();

		while (value > 0) {

			listInteger.addFirst(value % 10);
			value /= 10;

		}

		if (listInteger.isEmpty())
			listInteger.addLast(0);

		// replete list

		for (Integer integer : listInteger)
			this.list.addLast(NumbersImageView.INSTANCE.getNumberImageView(integer));

		// resize image views

		for (IImageViewAble imageViewAble : this.list)
			imageViewAble.getImageView().setDimensions(Credentials.INSTANCE.dIconNumber);

		// relocate

		double x = this.coordinates.x;
		x += Credentials.INSTANCE.dIconIndicator.x / 2;
		x += Credentials.INSTANCE.dIconNumber.x / 2;
		x += 2 * Credentials.INSTANCE.dGapBetweenComponents.x;

		for (IImageViewAble imageViewAble : this.list) {

			imageViewAble.getImageView().relocateCenter(x, this.coordinates.y);
			x += Credentials.INSTANCE.dIconNumber.x;

		}

	}

}
