
public class Subject {

	private String name;
	private int seclevel;
	private int value;
	
	public Subject(String name, int lev, int val) {
		this.name = name;
		this.seclevel = lev;
		this.value = val;
	}
	
	public String getName() {
		return name;
	}

	public int getSeclevel() {
		return seclevel;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
