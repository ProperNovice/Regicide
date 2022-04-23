package utils;

public class ArrayListImageView<T> extends ArrayList<T> {

	private Runnable runnable = null;

	public ArrayListImageView(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void add(int index, T element) {

		super.add(index, element);
		runDuplicateProtection();
		this.runnable.run();

	}

	@Override
	public void addFirst(T element) {

		super.addFirst(element);
		runDuplicateProtection();
		this.runnable.run();

	}

	@Override
	public void addLast(T e) {

		super.addLast(e);
		runDuplicateProtection();
		this.runnable.run();

	}

	@Override
	public void set(int index, T element) {

		super.set(index, element);
		runDuplicateProtection();
		this.runnable.run();

	}

	@Override
	public T remove(int index) {

		T t = super.remove(index);
		this.runnable.run();

		return t;

	}

	@Override
	public T remove(T t) {

		super.remove(t);
		this.runnable.run();

		return t;

	}

	@Override
	public T removeRandom() {

		T t = super.removeRandom();
		this.runnable.run();

		return t;

	}

	@Override
	public void loadOriginal() {

		super.loadOriginal();
		this.runnable.run();

	}

	@Override
	public void loadState() {

		super.loadState();
		this.runnable.run();

	}

	private void runDuplicateProtection() {
		RealTimeDuplicateProtection.INSTANCE.executeDuplicateProtect();
	}

}
