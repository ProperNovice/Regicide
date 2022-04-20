package gui;

import utils.Background;

public class Panel extends Parent {

	public Panel() {

		ParentInstance.INSTANCE.set(this);
		new Background();

	}

}
