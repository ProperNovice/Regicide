package utils;

public class Vector2 {

	public double x, y;

	public Vector2(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public void print() {

		Logger.INSTANCE.log("x -> " + this.x);
		Logger.INSTANCE.log("y -> " + this.y);
		Logger.INSTANCE.newLine();

	}

}
