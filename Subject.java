
public class Subject {

	private String name;   //subject name
	private int seclevel;  //security level
	private int value;     //value of last read
	
	public Subject(String name, int lev, int val) {
		this.name = name;
		this.seclevel = lev;
		this.value = val;
	}
	
	//returns name
	public String getName() {
		return name;
	}

	//returns security level
	public int getSeclevel() {
		return seclevel;
	}
	
	//returns value stored
	public int getValue() {
		return value;
	}
	
	//sets value to input
	public void setValue(int value) {
		this.value = value;
	}

}
