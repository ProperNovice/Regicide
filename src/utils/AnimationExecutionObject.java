package utils;

import controllers.Credentials;

public class AnimationExecutionObject {

	private ImageView imageView = null;
	private Vector2 targetCoordinates = null, animationStep = null;

	public AnimationExecutionObject(ImageView imageView) {
		this.imageView = imageView;
	}

	public void setAnimationCredentials(Vector2 animationTargetCoordinates) {

		this.targetCoordinates = animationTargetCoordinates;

		// calculating animation steps

		this.animationStep = new Vector2(Credentials.INSTANCE.animationStep, Credentials.INSTANCE.animationStep);

		double distanceX = Math.abs(this.targetCoordinates.x - this.imageView.getLayoutX());
		double distanceY = Math.abs(this.targetCoordinates.y - this.imageView.getLayoutY());

		if (distanceX < distanceY)
			this.animationStep.x = distanceX * Credentials.INSTANCE.animationStep / distanceY;

		else if (distanceY < distanceX)
			this.animationStep.y = distanceY * Credentials.INSTANCE.animationStep / distanceX;

		// set sign

		if (this.targetCoordinates.x < this.imageView.getLayoutX())
			this.animationStep.x = -this.animationStep.x;
		if (this.targetCoordinates.y < this.imageView.getLayoutY())
			this.animationStep.y = -this.animationStep.y;

	}

	public boolean animationIsFinished() {

		if (Math.abs(this.targetCoordinates.x - this.imageView.getLayoutX()) > 0.1)
			return false;
		else if (Math.abs(this.targetCoordinates.y - this.imageView.getLayoutY()) > 0.1)
			return false;
		else
			return true;

	}

	public void moveTowardsAnimationCoordinates() {
		this.imageView.relocateTopLeft(getCoordinateX(), getCoordinateY());
	}

	private double getCoordinateX() {

		if (Math.abs(this.targetCoordinates.x - this.imageView.getLayoutX()) < Math.abs(this.animationStep.x))
			return this.targetCoordinates.x;
		else
			return this.imageView.getLayoutX() + this.animationStep.x;

	}

	private double getCoordinateY() {

		if (Math.abs(this.targetCoordinates.y - this.imageView.getLayoutY()) < Math.abs(this.animationStep.y))
			return this.targetCoordinates.y;
		else
			return this.imageView.getLayoutY() + this.animationStep.y;

	}

}
