
public class Object {

	private String name;
	private int seclevel;
	private int value;
	
	public Object(String name, int lev, int value) {
		this.name = name;
		this.seclevel = lev;
		this.value = value;
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
