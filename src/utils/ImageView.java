package utils;

import enums.ELayerZ;
import gui.ParentInstance;
import javafx.scene.shape.Rectangle;
import utils.Enums.MapImageViews;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.INode;

public class ImageView implements INode {

	private javafx.scene.image.ImageView imageView = null;
	private Image imageFront = null, imageBack = null, imageShowing = null;
	private double widthOriginal, heightOriginal, scale = 1, xClip = 0, yClip = 0, rotateValue = 0,
			selectImageViewAbleRatioDimensions = 0.5;
	private EventHandler eventHandler = null;
	private IEventHandlerAble eventHandlerAble = null;
	private AnimationExecutionObject animationExecutionObject = null;
	private boolean isVisible = true;
	private Vector2 selectImageViewAbleRatio = new Vector2(0.5, 0.5);

	public ImageView(String filePath, Object object) {
		createImageView(new Image(filePath), ELayerZ.DEFAULT, object);
	}

	public ImageView(String filePath, ELayerZ eLayerZ, Object object) {
		createImageView(new Image(filePath), eLayerZ, object);
	}

	public ImageView(Image image, Object object) {
		createImageView(image, ELayerZ.DEFAULT, object);
	}

	public ImageView(Image image, ELayerZ eLayerZ, Object object) {
		createImageView(image, eLayerZ, object);
	}

	private void createImageView(Image image, ELayerZ eLayerZ, Object object) {

		this.imageFront = image;

		IImageViewAble imageViewAble = (IImageViewAble) object;

		MapImageViews.INSTANCE.getImageViewsMap().put(imageViewAble, this);

		this.imageView = new javafx.scene.image.ImageView(this.imageFront.getImage());

		ParentInstance.INSTANCE.get().addNode(this.imageView);

		this.imageShowing = this.imageFront;

		this.widthOriginal = this.imageView.minWidth(0);
		this.heightOriginal = this.imageView.minHeight(0);

		LayerZ.INSTANCE.addImageViewAbleToLayer(this, eLayerZ, this.imageView);
		LayerZ.INSTANCE.toFrontImageview(this);

		if (object instanceof IEventHandlerAble)
			setEventHandler(object);

		this.animationExecutionObject = new AnimationExecutionObject(this);

	}

	@Override
	public void setVisible(boolean value) {
		this.isVisible = value;
		LayerZ.INSTANCE.setVisible(this.isVisible, this);
	}

	public final boolean isVisible() {
		return this.isVisible;
	}

	@Override
	public void toBack() {
		LayerZ.INSTANCE.toBackImageview(this);
	}

	@Override
	public void toFront() {
		LayerZ.INSTANCE.toFrontImageview(this);
	}

	@Override
	public final double getLayoutX() {
		return this.imageView.getLayoutX();
	}

	@Override
	public final double getLayoutY() {
		return this.imageView.getLayoutY();
	}

	@Override
	public final Vector2 getLayout() {
		return new Vector2(getLayoutX(), getLayoutY());
	}

	public final double getCenterX() {
		return getLayoutX() + this.getWidth() / 2;
	}

	public final double getCenterY() {
		return getLayoutY() + this.getHeight() / 2;
	}

	public final Vector2 getCenter() {
		return new Vector2(getCenterX(), getCenterY());
	}

	@Override
	public void relocateTopLeft(final double x, final double y) {
		this.imageView.relocate(x - this.xClip, y - this.yClip);
	}

	public void relocateCenter(final double x, final double y) {
		relocateTopLeft(x - this.getWidth() / 2, y - this.getHeight() / 2);
	}

	public void relocateBottomLeft(final double x, final double y) {
		relocateTopLeft(x, y - this.getHeight());
	}

	@Override
	public void relocateTopLeft(final Vector2 vector2) {
		relocateTopLeft(vector2.x, vector2.y);
	}

	public void relocateCenter(final Vector2 vector2) {
		relocateCenter(vector2.x, vector2.y);
	}

	public void relocateBottomLeft(final Vector2 vector2) {
		relocateBottomLeft(vector2.x, vector2.y);
	}

	public boolean contains(double x, double y) {

		if (x < this.getLayoutX())
			return false;

		if (x > this.getLayoutX() + this.getWidth())
			return false;

		if (y < this.getLayoutY())
			return false;

		if (y > this.getLayoutY() + this.getHeight())
			return false;

		return true;

	}

	public boolean contains(Vector2 vector2) {
		return contains(vector2.x, vector2.y);
	}

	public final void setClip(double x, double y, double width, double height) {

		this.xClip = x;
		this.yClip = y;

		Rectangle rectangle = new Rectangle(x / scale, y / scale, width / scale, height / scale);
		this.imageView.setClip(rectangle);

	}

	public final void setRotate(double value) {
		this.rotateValue = value;
		this.imageView.setRotate(value);
	}

	public final boolean isRotated() {
		return this.rotateValue != 0;
	}

	private void setEventHandler(Object object) {

		this.eventHandlerAble = (IEventHandlerAble) object;

		this.eventHandler = new EventHandler(this.eventHandlerAble);

		this.imageView.setOnMouseEntered(this.eventHandler);
		this.imageView.setOnMouseExited(this.eventHandler);
		this.imageView.setOnMousePressed(this.eventHandler);

	}

	public final void setImage(final Image image) {

		this.imageView.setImage(image.getImage());
		this.imageFront = image;

	}

	public final Image getImage() {
		return this.imageFront;
	}

	public final void setScale(double scale) {

		this.scale = scale;

		this.imageView.setScaleX(this.scale);
		this.imageView.setScaleY(this.scale);

		double widthTotal = this.widthOriginal;
		double heightTotal = this.heightOriginal;

		double widthNew = this.scale * widthTotal;
		double heightNew = this.scale * heightTotal;

		double translateX = (widthNew - widthTotal) / 2;
		double translateY = (heightNew - heightTotal) / 2;

		this.imageView.setTranslateX(translateX);
		this.imageView.setTranslateY(translateY);

	}

	public void setWidth(double width) {

		double scale = width / this.widthOriginal;
		setScale(scale);

	}

	public void setHeight(double height) {

		double scale = height / this.heightOriginal;
		setScale(scale);

	}

	public void setDimensions(Vector2 numbersPair) {
		setWidth(numbersPair.x);
	}

	public void setBack(String path) {
		this.imageBack = new Image(path);
	}

	public void setSelectImageViewAbleRatioPosition(double x, double y) {
		setSelectImageViewAbleRatioPosition(new Vector2(x, y));
	}

	public void setSelectImageViewAbleRatioPosition(Vector2 vector2) {
		this.selectImageViewAbleRatio = vector2;
	}

	public void setSelectImageViewAbleRatioDimensions(double value) {
		this.selectImageViewAbleRatioDimensions = value;
	}

	public void flip() {

		if (this.imageShowing.equals(this.imageFront))
			this.imageShowing = this.imageBack;
		else
			this.imageShowing = this.imageFront;

		setImageShowing();

	}

	public void flipFront() {
		this.imageShowing = this.imageFront;
		setImageShowing();
	}

	public void flipBack() {
		this.imageShowing = this.imageBack;
		setImageShowing();
	}

	public boolean isFlippedFront() {
		return this.imageShowing.equals(this.imageFront);
	}

	public boolean isFlippedBack() {
		return this.imageShowing.equals(this.imageBack);
	}

	private void setImageShowing() {
		this.imageView.setImage(this.imageShowing.getImage());
	}

	@Override
	public double getWidth() {
		return this.widthOriginal * this.scale;
	}

	@Override
	public double getHeight() {
		return this.heightOriginal * this.scale;
	}

	public Vector2 getDimenions() {
		return new Vector2(getWidth(), getHeight());
	}

	public double getScale() {
		return this.scale;
	}

	public double getEventOriginalX() {
		return this.eventHandler.getMouseEvent().getX();
	}

	public double getEventOriginalY() {
		return this.eventHandler.getMouseEvent().getY();
	}

	public Vector2 getEventOriginalCoordinates() {
		return new Vector2(getEventOriginalX(), getEventOriginalY());
	}

	public double getEventScaledX() {
		return this.eventHandler.getMouseEvent().getX() * this.scale;
	}

	public double getEventScaledY() {
		return this.eventHandler.getMouseEvent().getY() * this.scale;
	}

	public Vector2 getEventScaledCoordinates() {
		return new Vector2(getEventScaledX(), getEventScaledY());
	}

	public IEventHandlerAble getEventHandlerAble() {
		return this.eventHandlerAble;
	}

	public AnimationExecutionObject getAnimationExecutionObject() {
		return this.animationExecutionObject;
	}

	public Vector2 getSelectImageViewAbleRatioPosition() {
		return this.selectImageViewAbleRatio;
	}

	public double getSelectImageViewAbleRatioDimensions() {
		return this.selectImageViewAbleRatioDimensions;
	}

}
