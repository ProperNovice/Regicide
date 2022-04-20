package utils;

import controllers.Credentials;

public enum Collider {

	INSTANCE;

	private ArrayList<ColliderRectangle> list = new ArrayList<ColliderRectangle>();

	public void add(double topLeftX, double topY, double width, double height) {
		this.list.addLast(new ColliderRectangle(topLeftX, topY, width, height,
				Credentials.INSTANCE.colliderVisibility));
	}

	public boolean contains(double x, double y) {

		for (ColliderRectangle collider : this.list)
			if (collider.contains(x, y))
				return true;

		return false;

	}

	public boolean contains(Vector2 numbersPair) {
		return contains(numbersPair.x, numbersPair.y);
	}

	private class ColliderRectangle {

		private double leftX, topY, rightX, bottomY;

		public ColliderRectangle(double topLeftX, double topY, double width, double height,
				boolean visibility) {

			this.leftX = topLeftX;
			this.rightX = this.leftX + width;
			this.topY = topY;
			this.bottomY = this.topY + height;

			if (!visibility)
				return;

			new Rectangle(topLeftX, topY, width, height);

		}

		public boolean contains(double x, double y) {

			if (x < this.leftX)
				return false;

			if (x > this.rightX)
				return false;

			if (y < this.topY)
				return false;

			if (y > this.bottomY)
				return false;

			return true;

		}

	}

}
