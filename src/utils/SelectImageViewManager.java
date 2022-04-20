package utils;

import controllers.Credentials;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;

public enum SelectImageViewManager {

	INSTANCE;

	private HashMap<IImageViewAble, SelectImageView> hashMapImageViewAble = new HashMap<IImageViewAble, SelectImageView>();
	private HashMap<IEventHandlerAble, SelectImageView> hashMapEventHandlerAble = new HashMap<IEventHandlerAble, SelectImageView>();

	private SelectImageViewManager() {

	}

	public void reverseSelectImageViewAble(IImageViewAble imageViewAble) {

		if (!this.hashMapImageViewAble.containsKey(imageViewAble))
			addSelectImageViewAble(imageViewAble);
		else
			releaseSelectImageViewAble(imageViewAble);

	}

	public void addSelectImageViewAble(IImageViewAble imageViewAble) {

		if (this.hashMapImageViewAble.containsKey(imageViewAble))
			ShutDown.INSTANCE.execute();

		SelectImageView selectImageView = ObjectPool.INSTANCE.acquire(SelectImageView.class);
		this.hashMapImageViewAble.put(imageViewAble, selectImageView);

		selectImageView.getImageView().setVisible(true);

		ImageView imageView = imageViewAble.getImageView();

		// dimension

		double dimension = Math.min(imageView.getWidth(), imageView.getHeight());
		dimension *= imageView.getSelectImageViewAbleRatioDimensions();

		selectImageView.getImageView().setWidth(dimension);

		// coordinates

		double x = imageView.getLayoutX()
				+ imageView.getSelectImageViewAbleRatioPosition().x * imageView.getWidth();
		double y = imageView.getLayoutY()
				+ imageView.getSelectImageViewAbleRatioPosition().y * imageView.getHeight();

		selectImageView.getImageView().relocateCenter(x, y);

	}

	public void addSelectCoordinates(double x, double y, IEventHandlerAble eventHandlerAble) {

		if (this.hashMapEventHandlerAble.containsKey(eventHandlerAble))
			ShutDown.INSTANCE.execute();

		SelectImageView selectImageView = ObjectPool.INSTANCE.acquire(SelectImageView.class);
		this.hashMapEventHandlerAble.put(eventHandlerAble, selectImageView);

		selectImageView.getImageView().setVisible(true);

		double width = Credentials.INSTANCE.selectEventHandlerAbleWidth;
		selectImageView.getImageView().setWidth(width);
		selectImageView.getImageView().relocateCenter(x, y);

	}

	public void addSelectCoordinates(Vector2 numbersPair, IEventHandlerAble eventHandlerAble) {
		addSelectCoordinates(numbersPair.x, numbersPair.y, eventHandlerAble);
	}

	public void releaseSelectImageViewAble(IImageViewAble imageViewAble) {

		if (!this.hashMapImageViewAble.containsKey(imageViewAble))
			ShutDown.INSTANCE.execute();

		SelectImageView selectImageView = this.hashMapImageViewAble.getValue(imageViewAble);
		selectImageView.getImageView().setVisible(false);

		this.hashMapImageViewAble.removeKey(imageViewAble);

	}

	public void releaseSelectCoordinates(IEventHandlerAble eventHandlerAble) {

		if (!this.hashMapEventHandlerAble.containsKey(eventHandlerAble))
			ShutDown.INSTANCE.execute();

		SelectImageView selectImageView = this.hashMapEventHandlerAble.getValue(eventHandlerAble);
		selectImageView.getImageView().setVisible(false);

		this.hashMapEventHandlerAble.removeKey(eventHandlerAble);

	}

	public void releaseSelectImageViews() {

		for (IImageViewAble imageViewAble : this.hashMapImageViewAble.clone())
			releaseSelectImageViewAble(imageViewAble);

		for (IEventHandlerAble eventHandlerAble : this.hashMapEventHandlerAble.clone())
			releaseSelectCoordinates(eventHandlerAble);

	}

	public boolean isSelectedImageViewAble(IImageViewAble imageViewAble) {
		return this.hashMapImageViewAble.containsKey(imageViewAble);
	}

	public boolean isSelectedCoordinates(IEventHandlerAble eventHandlerAble) {
		return this.hashMapEventHandlerAble.containsKey(eventHandlerAble);
	}

	public void handleMouseButtonPressedPrimary(SelectImageView selectImageView) {
		getEventHandlerAble(selectImageView).handleMouseButtonPressedPrimary();
	}

	public void handleMouseButtonPressedSecondary(SelectImageView selectImageView) {
		getEventHandlerAble(selectImageView).handleMouseButtonPressedSecondary();
	}

	public void handleMouseEntered(SelectImageView selectImageView) {
		getEventHandlerAble(selectImageView).handleMouseEntered();
	}

	public void handleMouseExited(SelectImageView selectImageView) {

		IEventHandlerAble eventHandlerAble = getEventHandlerAble(selectImageView);

		if (eventHandlerAble != null)
			eventHandlerAble.handleMouseExited();

	}

	private IEventHandlerAble getEventHandlerAble(SelectImageView selectImageView) {

		IEventHandlerAble eventHandlerAble = null;

		if (this.hashMapImageViewAble.containsValue(selectImageView)) {

			IImageViewAble imageViewAble = this.hashMapImageViewAble.getKey(selectImageView);
			ImageView imageView = imageViewAble.getImageView();
			eventHandlerAble = imageView.getEventHandlerAble();

		} else if (this.hashMapEventHandlerAble.containsValue(selectImageView))
			eventHandlerAble = this.hashMapEventHandlerAble.getKey(selectImageView);

		return eventHandlerAble;

	}

	public int sizeSelectImageViewAbles() {
		return this.hashMapImageViewAble.size() + this.hashMapEventHandlerAble.size();
	}

	public ArrayList<IImageViewAble> getSelectedImageViewAbles() {

		ArrayList<IImageViewAble> list = new ArrayList<IImageViewAble>();

		for (IImageViewAble imageViewAble : this.hashMapImageViewAble)
			list.addLast(imageViewAble);

		return list;

	}

}
