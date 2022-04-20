package utils;

import utils.Enums.AnimationSynchEnum;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IUpdateAble;

public enum Animation implements IUpdateAble {

	INSTANCE;

	private ArrayList<IImageViewAble> animationsSynchronous = new ArrayList<IImageViewAble>();
	private ArrayList<IImageViewAble> animationsAsynchronous = new ArrayList<IImageViewAble>();

	private Animation() {

	}

	@Override
	public void update() {

		if (!this.animationsSynchronous.isEmpty())
			executeAnimationSynchronous();
		if (!this.animationsAsynchronous.isEmpty())
			executeAnimationAsynchronous();

		if (!this.animationsSynchronous.isEmpty())
			return;
		else if (!this.animationsAsynchronous.isEmpty())
			return;
		else
			AnimationTimerFX.INSTANCE.remove(this);

	}

	private void executeAnimationSynchronous() {

		executeAnimationList(this.animationsSynchronous);

		if (!this.animationsSynchronous.isEmpty())
			return;

		Lock.INSTANCE.unlock();

	}

	private void executeAnimationAsynchronous() {
		executeAnimationList(this.animationsAsynchronous);
	}

	private void executeAnimationList(ArrayList<IImageViewAble> animationsList) {

		for (IImageViewAble imageView : animationsList.clone()) {

			AnimationExecutionObject animationExecutionObject = imageView.getImageView()
					.getAnimationExecutionObject();

			animationExecutionObject.moveTowardsAnimationCoordinates();

			if (!animationExecutionObject.animationIsFinished())
				continue;

			animationsList.remove(imageView);

		}

	}

	public void animateTopLeft(IImageViewAble imageViewAble, Vector2 vector2,
			AnimationSynchEnum animationSynchEnum) {

		if (this.animationsSynchronous.contains(imageViewAble))
			this.animationsSynchronous.remove(imageViewAble);
		else if (this.animationsAsynchronous.contains(imageViewAble))
			this.animationsAsynchronous.remove(imageViewAble);

		imageViewAble.getImageView().toFront();
		imageViewAble.getImageView().getAnimationExecutionObject().setAnimationCredentials(vector2);

		ArrayList<IImageViewAble> listToAdd = null;

		switch (animationSynchEnum) {

		case SYNCHRONOUS:
			listToAdd = this.animationsSynchronous;
			break;

		case ASYNCHRONOUS:
			listToAdd = this.animationsAsynchronous;
			break;

		}

		listToAdd.addLast(imageViewAble);

		AnimationTimerFX.INSTANCE.updateEachFrame(this);

		if (!ImageViewAbleDependency.INSTANCE.containsDependency(imageViewAble))
			return;

		// dependency

		for (IImageViewAble dependency : ImageViewAbleDependency.INSTANCE
				.getDependency(imageViewAble)) {

			Vector2 primaryTopLeft = imageViewAble.getImageView().getLayout();
			Vector2 secondaryTopLeft = dependency.getImageView().getLayout();

			double secondaryX = vector2.x + (secondaryTopLeft.x - primaryTopLeft.x);
			double secondaryY = vector2.y + (secondaryTopLeft.y - primaryTopLeft.y);

			Vector2 vector2Secondary = new Vector2(secondaryX, secondaryY);
			animateTopLeft(dependency, vector2Secondary, animationSynchEnum);

		}

	}

	public void animateCenter(IImageViewAble imageViewAble, Vector2 numbersPair,
			AnimationSynchEnum animationSynchEnum) {

		double x = numbersPair.x - imageViewAble.getImageView().getWidth() / 2;
		double y = numbersPair.y - imageViewAble.getImageView().getHeight() / 2;

		animateTopLeft(imageViewAble, new Vector2(x, y), animationSynchEnum);

	}

	public boolean isAnimatingSynchronous() {
		return !this.animationsSynchronous.isEmpty();
	}

	public boolean isAnimatingAsynchronous() {
		return !this.animationsAsynchronous.isEmpty();
	}

	public boolean isAnimating() {

		if (isAnimatingSynchronous())
			return true;

		if (isAnimatingAsynchronous())
			return true;

		return false;

	}

	public void moveAsynchronousToSynchronous() {

		this.animationsSynchronous.addAllLast(this.animationsAsynchronous);
		this.animationsAsynchronous.clear();

	}

	public void moveAsynchronousToSynchronousLock() {

		moveAsynchronousToSynchronous();
		Lock.INSTANCE.lock();

	}

	public void moveAsynchronousToSynchronousLock(Runnable runnable) {

		moveAsynchronousToSynchronous();
		Lock.INSTANCE.lock(runnable);

	}

}
