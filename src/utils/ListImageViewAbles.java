package utils;

import java.util.Iterator;

import controllers.Credentials;
import controllers.Lists;
import utils.Enums.AnimationSynchEnum;
import utils.Enums.ImageViewActionEnum;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IUpdateAble;

public class ListImageViewAbles<T> implements Iterable<T>, IUpdateAble {

	private ArrayListImageView<T> arrayList = new ArrayListImageView<T>(() -> showListSize());
	private ArrayList<IImageViewAble> quantityImageViewAbles = new ArrayList<>();
	private CoordinatesList<T> coordinates = new CoordinatesList<>(this);
	private ListCredentials listCredentials = new ListCredentials();

	public ListImageViewAbles() {

		this.arrayList.setCapacity(this.listCredentials.capacity);
		Lists.INSTANCE.lists.addLast(this.arrayList);
		RealTimeDuplicateProtection.INSTANCE.addList(this.arrayList);

	}

	private void layerZSort() {

		switch (this.listCredentials.layerZListEnum) {

		case TO_FRONT_FIRST_IMAGEVIEW:
			toFrontFirstImageView();
			break;

		case TO_BACK_FIRST_IMAGEVIEW:
			toBackFirstImageView();
			break;

		}

	}

	private void toFrontFirstImageView() {

		IImageViewAble imageViewAble = null;

		for (int counter = this.arrayList.size() - 1; counter >= 0; counter--) {

			imageViewAble = (IImageViewAble) this.arrayList.get(counter);
			imageViewAble.getImageView().toFront();

			toFrontDependency(imageViewAble);

		}

	}

	private void toBackFirstImageView() {

		IImageViewAble imageViewAble = null;

		for (T t : this.arrayList) {

			imageViewAble = (IImageViewAble) t;
			imageViewAble.getImageView().toFront();

			toFrontDependency(imageViewAble);

		}

	}

	private void toFrontDependency(IImageViewAble imageViewAble) {

		if (!ImageViewAbleDependency.INSTANCE.containsDependency(imageViewAble))
			return;

		for (IImageViewAble dependency : ImageViewAbleDependency.INSTANCE
				.getDependency(imageViewAble))
			dependency.getImageView().toFront();

	}

	public final void animateAsynchronous() {
		executeAction(ImageViewActionEnum.ANIMATE, AnimationSynchEnum.ASYNCHRONOUS);
	}

	public final void animateSynchronous() {
		executeAction(ImageViewActionEnum.ANIMATE, AnimationSynchEnum.SYNCHRONOUS);
	}

	public final void animateSynchronousLock() {

		animateSynchronous();
		Lock.INSTANCE.lock();

	}

	public final void animateSynchronousLock(Runnable runnable) {

		animateSynchronous();
		Lock.INSTANCE.lock(runnable);

	}

	public final void relocateImageViews() {
		executeAction(ImageViewActionEnum.RELOCATE, null);
	}

	private void showListSize() {

		if (!this.listCredentials.showListSize)
			return;

		// clear currents

		for (IImageViewAble imageViewAble : this.quantityImageViewAbles)
			imageViewAble.getImageView().setVisible(false);

		this.quantityImageViewAbles.clear();

		// check if list is empty

		if (this.arrayList.isEmpty())
			return;

		// to show

		IImageViewAble imageViewAble = (IImageViewAble) this.arrayList.getRandom();

		Vector2 center = new Vector2(this.listCredentials.coordinatesList.x,
				this.listCredentials.coordinatesList.y);
		double width = imageViewAble.getImageView().getWidth();
		double height = imageViewAble.getImageView().getHeight();

		switch (this.listCredentials.relocateTypeEnum) {

		case BOTTOM_LEFT:
			center.x += width / 2;
			center.y -= height / 2;
			break;

		case BOTTOM_RIGHT:
			center.x -= width / 2;
			center.y -= height / 2;
			break;

		case CENTER:
			break;

		case TOP_LEFT:
			center.x += width / 2;
			center.y += height / 2;
			break;

		case TOP_RIGHT:
			center.x -= width / 2;
			center.y += height / 2;
			break;

		}

		// create list of numbers

		int size = this.arrayList.size();
		ArrayList<Integer> listInteger = new ArrayList<>();

		while (size > 0) {

			listInteger.addFirst(size % 10);
			size /= 10;

		}

		// replete list

		for (Integer integer : listInteger)
			this.quantityImageViewAbles
					.addLast(NumbersImageView.INSTANCE.getNumberImageView(integer));

		// resize image views

		ImageView imageView = imageViewAble.getImageView();

		double dimensions = Math.min(imageView.getWidth(), imageView.getHeight());
		dimensions *= Credentials.INSTANCE.listQuantityRatioDimensions;

		for (IImageViewAble imageViewAbleTemp : this.quantityImageViewAbles)
			imageViewAbleTemp.getImageView().setWidth(dimensions);

		// calculate first x

		center.x -= (this.quantityImageViewAbles.size() - 1) * dimensions / 2;

		// relocate

		for (IImageViewAble imageViewAbleTemp : this.quantityImageViewAbles) {

			imageViewAbleTemp.getImageView().relocateCenter(center.x, center.y);
			center.x += dimensions;

		}

	}

	private void executeAction(ImageViewActionEnum imageViewAction,
			AnimationSynchEnum animationSynch) {

		ArrayList<T> list = this.arrayList.clone();
		list.reverse();

		for (T t : list) {

			int index = this.arrayList.indexOf(t);
			Vector2 vector2 = this.coordinates.getCoordinate(index);

			IImageViewAble imageViewAble = (IImageViewAble) t;

			switch (imageViewAction) {

			case ANIMATE:

				imageViewAble.getImageView().getAnimationExecutionObject()
						.setAnimationCredentials(vector2);

				if (imageViewAble.getImageView().getAnimationExecutionObject()
						.animationIsFinished())
					continue;
				else
					Animation.INSTANCE.animateTopLeft(imageViewAble, vector2, animationSynch);
				break;

			case RELOCATE:

				imageViewAble.getImageView().relocateTopLeft(vector2);
				showListSize();

				if (!ImageViewAbleDependency.INSTANCE.containsDependency(imageViewAble))
					break;

				for (IImageViewAble dependency : ImageViewAbleDependency.INSTANCE
						.getDependency(imageViewAble))
					dependency.getImageView().relocateTopLeft(vector2);

				break;

			}

		}

		layerZSort();

	}

	public final ArrayList<T> getArrayList() {
		return this.arrayList;
	}

	public final ListCredentials getListCredentials() {
		return this.listCredentials;
	}

	@Override
	public final Iterator<T> iterator() {
		return this.arrayList.iterator();
	}

	@Override
	public void update() {
		showListSize();
	}

}
