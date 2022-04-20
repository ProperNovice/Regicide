package gui;

public enum ParentInstance {

	INSTANCE;

	private Parent parentInstance = null;

	public void set(Parent parent) {
		this.parentInstance = parent;
	}

	public Parent get() {
		return this.parentInstance;
	}

}
