package utils;

import java.util.Collections;
import java.util.Iterator;

import utils.Interfaces.ISaveLoadStateAble;

public class ArrayList<T> implements Iterable<T>, ISaveLoadStateAble {

	protected java.util.ArrayList<T> list = new java.util.ArrayList<>();
	private java.util.ArrayList<T> listOriginal = new java.util.ArrayList<>();
	private java.util.ArrayList<T> listState = new java.util.ArrayList<>();
	private int capacity = -1;

	public ArrayList() {

	}

	public ArrayList(T[] list) {
		addAllLast(list);
	}

	public ArrayList(int capacity) {
		this.capacity = capacity;
	}

	private ArrayList(java.util.ArrayList<T> list, int capacity) {
		this.list = list;
		this.capacity = capacity;
	}

	public void add(int index, T element) {
		this.list.add(index, element);
	}

	public void addFirst(T element) {
		add(0, element);
	}

	public void addFirst(T element, int times) {

		for (int counter = 1; counter <= times; counter++)
			addFirst(element);

	}

	public void addAllFirst(ArrayList<T> list) {

		list.reverse();

		for (T t : list)
			addFirst(t);

	}

	@SuppressWarnings("unchecked")
	public void addAllFirst(T... list) {
		addAllFirst(new ArrayList<>(list));
	}

	public void addLast(T element) {
		this.list.add(element);
	}

	public void addLast(T element, int times) {

		for (int counter = 1; counter <= times; counter++)
			addLast(element);

	}

	public void addAllLast(ArrayList<T> list) {

		for (T t : list)
			addLast(t);

	}

	@SuppressWarnings("unchecked")
	public void addAllLast(T... list) {

		for (T t : list)
			addLast(t);

	}

	public ArrayList<T> clear() {

		ArrayList<T> list = new ArrayList<>();

		while (!isEmpty())
			list.addLast(removeFirst());

		this.list.clear();

		return list;

	}

	public boolean contains(Object o) {
		return this.list.contains(o);
	}

	public T get(int index) {
		return this.list.get(index);
	}

	public int indexOf(Object o) {
		return this.list.indexOf(o);
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public boolean isMaxedCapacity() {

		if (this.capacity == -1)
			return false;
		else
			return this.list.size() == this.capacity;

	}

	public boolean isOverCapacity() {

		if (this.capacity == -1)
			return false;
		else
			return this.list.size() > this.capacity;

	}

	public int getCapacity() {
		return this.capacity;
	}

	public T remove(int index) {
		return this.list.remove(index);
	}

	public T remove(T t) {

		this.list.remove(t);
		return t;

	}

	public void shuffle() {

		java.util.ArrayList<T> listOriginal = new java.util.ArrayList<>(this.list);
		this.list.clear();

		while (!listOriginal.isEmpty())
			this.list.add(listOriginal
					.remove(Random.INSTANCE.getRandomNumber(0, listOriginal.size() - 1)));

	}

	public T getFirst() {
		return this.list.get(0);
	}

	public T removeFirst() {
		return remove(0);
	}

	public T getLast() {
		return this.list.get(this.list.size() - 1);
	}

	public T removeLast() {
		return remove(this.list.size() - 1);
	}

	public T getRandom() {
		return this.list.get(Random.INSTANCE.getRandomNumber(0, this.list.size() - 1));
	}

	public T removeRandom() {
		int randomIndex = Random.INSTANCE.getRandomNumber(0, this.list.size() - 1);
		return this.list.remove(randomIndex);
	}

	public void set(int index, T element) {
		this.list.set(index, element);
	}

	public void replace(T elementOld, T elementNew) {

		if (!this.list.contains(elementOld))
			ShutDown.INSTANCE.execute();

		set(this.list.indexOf(elementOld), elementNew);
	}

	public int size() {
		return this.list.size();
	}

	public void reverse() {
		Collections.reverse(this.list);
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public Iterator<T> iterator() {
		return this.list.iterator();
	}

	@Override
	public ArrayList<T> clone() {

		java.util.ArrayList<T> arrayList = new java.util.ArrayList<>(this.list);
		ArrayList<T> arrayListToReturn = new ArrayList<T>(arrayList, this.capacity);

		return arrayListToReturn;

	}

	@Override
	public void saveOriginal() {
		this.listOriginal.clear();
		this.listOriginal.addAll(this.list);
	}

	@Override
	public void loadOriginal() {
		this.list.clear();
		this.list.addAll(this.listOriginal);
	}

	@Override
	public void saveState() {
		this.listState.clear();
		this.listState.addAll(this.list);
	}

	@Override
	public void loadState() {
		this.list.clear();
		this.list.addAll(this.listState);
	}

}
