package utils;

import gui.ParentInstance;
import javafx.scene.text.Font;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.INode;

public class TextIndicator implements INode {

	protected javafx.scene.text.Text text = null;
	protected String textVisibleTrue = "", textVisibleFalse = "", textOriginal = "";

	public TextIndicator() {
		this(null);
	}

	public TextIndicator(String text) {

		if (text == null)
			this.text = new javafx.scene.text.Text();

		else
			this.text = new javafx.scene.text.Text(text);

		this.textOriginal += text;

		ParentInstance.INSTANCE.get().addNode(this.text);
		LayerZ.INSTANCE.addTextIndicator(this);

	}

	public TextIndicator(int text) {
		this(Integer.toString(text));
	}

	@Override
	public void setVisible(final boolean value) {

		if (value)
			this.text.setText(this.textVisibleTrue);
		else
			this.text.setText(this.textVisibleFalse);

	}

	@Override
	public boolean isVisible() {
		return this.text.isVisible();
	}

	public void toBack() {
		this.text.toBack();
	}

	@Override
	public void toFront() {
		this.text.toFront();
	}

	@Override
	public final double getLayoutX() {
		return this.text.getLayoutX();
	}

	@Override
	public final double getLayoutY() {
		return this.text.getLayoutY();
	}

	@Override
	public double getWidth() {
		return this.text.minWidth(0);
	}

	@Override
	public double getHeight() {
		return this.text.minHeight(0);
	}

	public void setWidth(final double pixels) {

		int font = 1;
		setFont(font);

		while (getWidth() <= pixels)
			setFont(++font);

		font--;
		setFont(font);

	}

	public void setHeight(final double pixels) {

		int font = 1;
		setFont(font);

		while (getHeight() <= pixels)
			setFont(++font);

		setFont(font);

	}

	@Override
	public void relocateTopLeft(final double x, final double y) {
		this.text.relocate(x, y);
	}

	@Override
	public void relocateTopLeft(final Vector2 numbersPair) {
		relocateTopLeft(numbersPair.x, numbersPair.y);
	}

	public void setEventHandler(IEventHandlerAble eventHandlerAble) {

		EventHandler eventHandler = new EventHandler(eventHandlerAble);

		this.text.setOnMouseEntered(eventHandler);
		this.text.setOnMouseExited(eventHandler);
		this.text.setOnMousePressed(eventHandler);

	}

	public final void setText(String text) {
		this.textVisibleTrue = text;
	}

	protected final void setFont(final int value) {
		this.text.setFont(new Font(value));
	}

	@Override
	public Vector2 getLayout() {
		return new Vector2(getLayoutX(), getLayoutY());
	}

}
