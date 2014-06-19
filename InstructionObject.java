class InstructionObject {
	
	public static boolean verbose = false;
	private String command;
	private String objectName;
	private String subjectName;
	private int value;
	
	//This should be creating a read instruction
	public InstructionObject (String command, String objectName, String subjectName, int value)
	{
		this.command=command;
		this.objectName = objectName;
		this.subjectName = subjectName;
		this.value = value;
		
		if (verbose) {SecureSystem.logOutInstructions(command + " " + subjectName + " " + objectName + " " + value);}; 
	}
	public InstructionObject (String command, String objectName, String subjectName)
	{
		this.command=command;
		this.objectName = objectName;
		this.subjectName = subjectName;
		
		if (verbose) {SecureSystem.logOutInstructions(command + " " + subjectName + " " + objectName);}; 
	}
	public InstructionObject (String command)
	{
		this.command = command;
	}
	public InstructionObject getInstructionObject()
	{
		return this;
	}
	public String getCommand() {
		return command;
	}
	public String getObjectName() {
		return objectName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public int getValue() {
		return value;
	}

}
