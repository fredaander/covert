import java.io.*;
import java.util.*;

public class SecureSystem 
{
	//holds subjects that are currently in the system
	private static HashMap<String, Subject> subMap = new HashMap<String, Subject>();
	private static byte answer = 0x00;
	public static int numOfBits = 0; 
	private static byte[] barray = new byte[128];
	public static int arrayIdx = 0; 

	//sets the subject value
	public static void setSubjectValue(String subName, int value){
		if (subMap.containsKey(subName))subMap.get(subName).setValue(value);
		else ReferenceMonitor.processBad();
	}

	//returns the subject value
	public static int getSubjectValue(String subName){
		if (subMap.containsKey(subName)) return subMap.get(subName).getValue();
		else{
			ReferenceMonitor.processBad();
			return -1; 
		}
	}

	//returns the security level
	public static int getSubjectLabel(String subName){
		return subMap.get(subName).getSeclevel(); 
	}

	//returns bool if valid subject name
	public static boolean hasSubject (String subName)
	{
		return (subMap.containsKey(subName)); 
	}

	//adds subject to subMap
	public static void addSubject(Subject aSub){
		subMap.put(aSub.getName(), aSub); 
	}

	//sets up the system by creating two new subjects
	public static void setup() {
		addSubject(new Subject("lyle", 0, 0));
		addSubject(new Subject ("hal", 10, 0)); 
	}

	//gets value stored in Lyle and appends to a byte to be written to a file
	private static void runLyle(BufferedOutputStream bos) throws IOException{
		//get bit
		int bit = getSubjectValue("lyle"); 
		
		//shifts bits and adds next bit
	    answer = (byte) (answer << 1); 
	    answer = (byte) (answer|bit); 
	    numOfBits++; 
	    
	    //when we have a full byte place in barray
	    if (numOfBits == 8) {
	    	barray[arrayIdx] = answer; 
	    	arrayIdx++;
	    	answer = 0x00; 
	    	numOfBits =0; 
	    }
	    
	    //store values in barray for less times to io
	    if (arrayIdx == 128) {
	    	bos.write(barray);
	    	arrayIdx = 0; 
	    	barray = new byte[128]; 
	    }
	}
	
	public static void writeOutRest(BufferedOutputStream bos) throws IOException {
	
		//System.out.print( new String(barray, 0, arrayIdx, StandardCharsets.US_ASCII));
		bos.write(barray, 0, arrayIdx);
	}

	//Generates instructions for Hal and Lyle on the bit that is sent
	public static void sendbit(int bit, BufferedOutputStream bos) throws IOException {
		//Hal's either sending 0 or 1
		if (bit == 0) {
			ReferenceMonitor.processCreate(createInstructionObject("create", "hal", "obj", 0)); 
		}

		//Lyle needs to find out what Hal sent
		ReferenceMonitor.processCreate(createInstructionObject("create", "lyle", "obj", 0));
		ReferenceMonitor.processWrite(createInstructionObject("write", "lyle", "obj", 1));
		ReferenceMonitor.processRead(createInstructionObject("read", "lyle", "obj", 0));
		createInstructionObject("run", "lyle", "", 0);
		runLyle(bos); 
		ReferenceMonitor.processDestroy(createInstructionObject("destroy", "lyle", "obj", 0));
	}

	//generates an instruction object
	public static InstructionObject createInstructionObject(String command, String subject, String object, int value)
	{
		if (command.equalsIgnoreCase("write")) return new InstructionObject(command, object, subject, value); 
		else return new InstructionObject(command, object, subject); 
	}

	public static void printValues() {
		for (Subject aSubj : subMap.values())
			System.out.println("\t" + aSubj.getName() + " has recently read: " + aSubj.getValue()); 

	}

	//writes out instructions to file
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



