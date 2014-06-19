import java.io.*;
import java.util.*;

public class SecureSystem 
{
	private static HashMap<String, Subject> subMap = new HashMap<String, Subject>();

	public static void setSubjectValue(String subName, int value){
		if (subMap.containsKey(subName))subMap.get(subName).setValue(value);
		else ReferenceMonitor.processBad();
	}

	public static int getSubjectValue(String subName){
		if (subMap.containsKey(subName)) return subMap.get(subName).getValue();
		else{
			ReferenceMonitor.processBad();
			return -1; 
		}
	}

	public static int getSubjectLabel(String subName){
		return subMap.get(subName).getSeclevel(); 
	}

	public static boolean hasSubject (String subName)
	{
		return (subMap.containsKey(subName)); 
	}

	public static void addSubject(Subject aSub){
		subMap.put(aSub.getName(), aSub); 
	}

	public static void setup() {
		addSubject(new Subject("lyle", 0, 0));
		addSubject(new Subject ("hal", 10, 0));
	}

	//accepts an instruction method for RUN and runs
	//either Hal or Lyle or nothing
	public static void run(InstructionObject instruct){
		if (instruct.getSubjectName().equalsIgnoreCase("hal")) runHal();
		if (instruct.getSubjectName().equalsIgnoreCase("lyle")) runLyle(); 
		return; 
	}

	private static void runHal(){
		//TODO implement
	}

	private static void runLyle(){
		int bit = getSubjectValue("lyle"); 
		if (bit != 0 || bit != 1) return;

		//get bit
		//add to storage container
		//check if we have complete byte
		//write out to file

	}

	public static void sendbit(int bit) {
		//Hal's either sending 0 or 1
		if (bit == 0) {
			ReferenceMonitor.processCreate(createInstructionObject("create", "hal", "obj", 0)); 
		}

		//Lyle needs to find out what Hal sent
		ReferenceMonitor.processCreate(createInstructionObject("create", "lyle", "obj", 0));
		ReferenceMonitor.processWrite(createInstructionObject("write", "lyle", "obj", 1));
		ReferenceMonitor.processRead(createInstructionObject("read", "lyle", "obj", 0));
		createInstructionObject("run", "lyle", "", 0);
		runLyle(); 
		ReferenceMonitor.processDestroy(createInstructionObject("destroy", "lyle", "obj", 0));
	}

	public static InstructionObject createInstructionObject(String command, String subject, String object, int value)
	{
		if (command.equalsIgnoreCase("write")) return new InstructionObject(command, object, subject, value); 
		else return new InstructionObject(command, object, subject); 
	}

	public static void printValues() {
		for (Subject aSubj : subMap.values())
			System.out.println("\t" + aSubj.getName() + " has recently read: " + aSubj.getValue()); 

	}

	public static void logOutInstructions(String string) {

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("log", true));
			bufferedWriter.write(string);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}



