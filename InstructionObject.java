class InstructionObject {
	
	public static boolean verbose = false;
	private String command;
	private String objectName;
	private String subjectName;
	private int value;
	
	//This should be creating a write instruction
	public InstructionObject (String command, String objectName, String subjectName, int value)
	{
		this.command=command;
		this.objectName = objectName;
		this.subjectName = subjectName;
		this.value = value;
		
		if (verbose) {SecureSystem.logOutInstructions(command + " " + subjectName + " " + objectName + " " + value);}; 
	}
	
	//Creates read, destroy and create instructions
	public InstructionObject (String command, String objectName, String subjectName)
	{
		this.command=command;
		this.objectName = objectName;
		this.subjectName = subjectName;
		
		if (verbose) {SecureSystem.logOutInstructions(command + " " + subjectName + " " + objectName);}; 
	}
	
	//What is this doing?
	public InstructionObject (String command)
	{
		this.command = command;
	}
	//Why do you have this?
	public InstructionObject getInstructionObject()
	{
		return this;
	}
	
	//returns type of instruction
	public String getCommand() {
		return command;
	}
	
	//returns object name
	public String getObjectName() {
		return objectName;
	}
	
	//returns subject name
	public String getSubjectName() {
		return subjectName;
	}
	
	//returns value
	public int getValue() {
		return value;
	}

}
