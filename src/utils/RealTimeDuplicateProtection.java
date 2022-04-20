package utils;

public enum RealTimeDuplicateProtection {

	INSTANCE;

	private ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();

	private RealTimeDuplicateProtection() {

	}

	public void addList(ArrayList<? extends Object> list) {
		this.lists.addLast(list);
	}

	public void executeDuplicateProtect() {

		this.objects.clear();

		for (ArrayList<? extends Object> objectList : this.lists) {

			for (Object object : objectList)
				if (this.objects.contains(object))
					ShutDown.INSTANCE.execute();
				else
					this.objects.addLast(object);

		}

	}

}
