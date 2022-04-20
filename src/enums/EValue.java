package enums;

public enum EValue {
	
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(20),
	QUEEN(30),
	KING(40);
	
	private int value = -1;
	
	private EValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

}
