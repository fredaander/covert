
public class Object {

	private String name;   //objects name
	private int seclevel;  //security level
	private int value;     //value stored in object
	
	
	public Object(String name, int lev, int value) {
		this.name = name;
		this.seclevel = lev;
		this.value = value;
	}
	
	//returns name
	public String getName() {
		return name;
	}

	//returns security level
	public int getSeclevel() {
		return seclevel;
	}

	//returns value
	public int getValue() {
		return value;
	}
	
	//sets value to input integer
	public void setValue(int value) {
		this.value = value;
	}

}
