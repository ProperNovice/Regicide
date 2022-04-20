package utils;

public class ArrayListImageView<T> extends ArrayList<T> {

	private Runnable showListSize = null;

	public ArrayListImageView(Runnable showListSize) {
		this.showListSize = showListSize;
	}

	@Override
	public void add(int index, T element) {
		super.add(index, element);
		runDuplicateProtection();
	}

	@Override
	public void addFirst(T element) {
		super.addFirst(element);
		runDuplicateProtection();
	}

	@Override
	public void addLast(T e) {
		super.addLast(e);
		runDuplicateProtection();
	}

	@Override
	public void set(int index, T element) {
		super.set(index, element);
		runDuplicateProtection();
	}

	@Override
	public T remove(int index) {

		T t = super.remove(index);
		this.showListSize.run();

		return t;

	}

	@Override
	public void remove(T t) {
		super.remove(t);
		this.showListSize.run();
	}

	@Override
	public T removeRandom() {

		T t = super.removeRandom();
		this.showListSize.run();

		return t;

	}

	private void runDuplicateProtection() {

		RealTimeDuplicateProtection.INSTANCE.executeDuplicateProtect();
		this.showListSize.run();

	}

}
