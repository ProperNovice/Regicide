package utils;

import utils.Interfaces.IUpdateAble;

public class ArrayListImageView<T> extends ArrayList<T> {

	private IUpdateAble updateAble = null;

	public ArrayListImageView(IUpdateAble updateAble) {
		this.updateAble = updateAble;
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
		update();

		return t;

	}

	@Override
	public void remove(T t) {
		super.remove(t);
		update();
	}

	@Override
	public T removeRandom() {

		T t = super.removeRandom();
		update();

		return t;

	}

	private void runDuplicateProtection() {

		RealTimeDuplicateProtection.INSTANCE.executeDuplicateProtect();
		update();

	}

	private void update() {
		AnimationTimerFX.INSTANCE.updateNextFrame(this.updateAble);
	}

}
