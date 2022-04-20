package utils;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import utils.Interfaces.IEventHandlerAble;

public class EventHandler implements javafx.event.EventHandler<MouseEvent> {

	private IEventHandlerAble eventHandlerAble = null;
	private MouseEvent mouseEvent = null;

	public EventHandler(IEventHandlerAble eventHandlerAble) {
		this.eventHandlerAble = eventHandlerAble;
	}

	@Override
	public void handle(MouseEvent event) {

		if (Animation.INSTANCE.isAnimatingSynchronous())
			return;

		this.mouseEvent = event;

		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {

			if (event.getButton().equals(MouseButton.PRIMARY))
				this.eventHandlerAble.handleMouseButtonPressedPrimary();

			else if (event.getButton().equals(MouseButton.SECONDARY))
				this.eventHandlerAble.handleMouseButtonPressedSecondary();

		} else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
			this.eventHandlerAble.handleMouseEntered();

		else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
			this.eventHandlerAble.handleMouseExited();

	}

	public MouseEvent getMouseEvent() {
		return this.mouseEvent;
	}

}
