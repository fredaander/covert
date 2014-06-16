class InstructionObject {
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
	}
	public InstructionObject (String command, String objectName, String subjectName)
	{
		this.command=command;
		this.objectName = objectName;
		this.subjectName = subjectName;
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
