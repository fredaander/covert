
import java.util.*;

public class  ReferenceMonitor {

	private static HashMap<String, Object> objList = new HashMap<String, Object>();
	
	/*
	 * Object Manager class - only reads and writes to objects
	 */
	public static class ObjectManager 
	{
		public static int executeRead (Object obj)
		{
			return obj.getValue();
		}
		public static void executeWrite (Object obj, int val)
		{
			obj.setValue(val);
		}
	}
	
	/*
	 * pre: accepts read instruction, 
	 *      checks simple security, reads to object
	 * post: returns value read, also calls setSubjectValue to 
	 *      store value into subject
	 */
	public static int processRead (InstructionObject io)
	{
		Object anObject = null;
		int valueOfRead = 0;
		String subName = io.getSubjectName(); 
		
		//calling read removes any value stored in sub regardless if valid read or not
		if ( SecureSystem.hasSubject(subName))
			 SecureSystem.setSubjectValue(subName, 0);
		else processBad();
		
		//check if valid object - if so get it
		if (objList.containsKey(io.getObjectName())) {anObject = objList.get(io.getObjectName()); } 
		else processBad(); 
		
		//simple security
		if ( SecureSystem.getSubjectLabel(subName) >= anObject.getSeclevel()){
				 SecureSystem.setSubjectValue(subName, ObjectManager.executeRead(anObject));
		}
		return valueOfRead; 
	}
	
	/*
	 * pre: accepts an write instruction, 
	 *      checks *property, writes to object
	 * post: stores value into object
	 */
	public static void processWrite (InstructionObject io)
	{
		Object anObject = null;
		
		//check if valid object and subject
		if (objList.containsKey(io.getObjectName()) &&  SecureSystem.hasSubject(io.getSubjectName())) {
			anObject = objList.get(io.getObjectName()); } 
		else processBad(); 
		
		//check *property
		if ( SecureSystem.getSubjectLabel(io.getSubjectName()) <= anObject.getSeclevel()){
				ObjectManager.executeWrite(anObject, io.getValue());
		} 
	}
	
	/*
	 * Pre: Accepts an instruction to create an object with same
	 * sensitivity level as subject
	 * Post: add object to object hashmap
	 */
	public static void processCreate(InstructionObject io)
	{
		int lvl = 0; 
		
		//check if valid subject and find lvl to create object
		if ( SecureSystem.hasSubject(io.getSubjectName()))
			lvl =  SecureSystem.getSubjectLabel(io.getSubjectName());
		else
			processBad(); 
		
		//if the object doesn't exist create it at subj level
		if (!objList.containsKey(io.getObjectName())) {
			objList.put(io.getObjectName(), new Object(io.getObjectName(), lvl, 0));
		}
	}
	
	/*
	 * Pre: Accepts an instruction to destroy an object with same
	 * sensitivity level as subject
	 * Post: remove object in object hashmap
	 */
	public static void processDestroy(InstructionObject io)
	{
		String objName = io.getObjectName(); 
		String subName = io.getSubjectName();
		
		//check if subject, objects exists and *property -- remove
		if ( SecureSystem.hasSubject(subName) 
				&& objList.containsKey(objName)
				&&  SecureSystem.getSubjectLabel(subName) <= objList.get(objName).getSeclevel())
			objList.remove(objName);
		else
			processBad(); 
	}
	
	/*
	 * pre: prints out state, doesn't run instruction
	 */
	public static void processBad()
	{
		System.out.println("Bad Instrustion");

	}
	
	//adds object to hash
	public static void addObject(Object obj) {
		// Add Object to objMap
		objList.put(obj.getName(), obj);
	}
	
	//checks if object is there
	public static boolean hasObject(String subName){
		return objList.containsKey(subName); 
	}
	
	//prints out state
	public static void printState()
	{ 
		//printout state
		System.out.println("The current state is:");
		
		for (Object obj : objList.values())
			System.out.println("\t"+ obj.getName() + " has value: " + obj.getValue());
		
		 SecureSystem.printValues(); 
		System.out.println(); 
	}
}
