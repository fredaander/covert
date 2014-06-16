import java.io.*;
import java.util.*;

/*
 * Name: Brittany Kvanvig
EID: bmk447
CS Login: bmk447
Email: brittkvanvig@gmail.com
 *
*/

public class SecureSystem 
{
	public static void main(String[] args) throws IOException 
	{
//		Subject lyle = new Subject("lyle", 0, 0);
//		Subject hal = new Subject ("hal", 10, 0);
//		ReferenceMonitor.addSubject(lyle);
//		ReferenceMonitor.addSubject(hal);
//		
//		System.exit(0);
		
		Scanner scan = new Scanner(new FileReader(args[0]));
		Subject lyle = new Subject("lyle", 0, 0);
		Subject hal = new Subject ("hal", 10, 0);
		ReferenceMonitor.addSubject(lyle);
		ReferenceMonitor.addSubject(hal);
		String inst;
		String obj;
		String sub;
		int val = 0;
		while (scan.hasNextLine()) {
			if (!scan.hasNext()){
				scan.nextLine();
				continue;
			}
			inst = scan.next().toLowerCase();
			//READ
			if (inst.equalsIgnoreCase("read"))
			{
				if (!scan.hasNext()){
					ReferenceMonitor.processBad();
					continue;
				}
				sub = scan.next().toLowerCase();
				if (!scan.hasNext()){
					ReferenceMonitor.processBad();
					continue;
				}
				obj = scan.next().toLowerCase();
				System.out.println(inst+" "+sub+" "+obj+" ");
				ReferenceMonitor.processRead(new InstructionObject(inst, obj, sub));
			}
			
			//WRITE
			else if (inst.equalsIgnoreCase("write"))
			{
				if (!scan.hasNext()){
					ReferenceMonitor.processBad();
					continue;
				}
				sub = scan.next().toLowerCase();
				if (!scan.hasNext()){
					ReferenceMonitor.processBad();
					continue;
				}
				obj = scan.next().toLowerCase();
				if (!scan.hasNextInt())
				{
					ReferenceMonitor.processBad();
					continue;
				}
				val = scan.nextInt();
				System.out.println(inst+" "+sub+" "+obj+" "+val);
				ReferenceMonitor.processWrite(new InstructionObject(inst, obj, sub, val));
			}
			else
			{
				ReferenceMonitor.processBad();
			}
		}	
		
		scan.close();
		System.exit(0);
	}
	
	//accepts an instruction method for RUN and runs
	//either Hal or Lyle or nothing
	public static void run(InstructionObject instruct){
		if (instruct.getSubjectName().equalsIgnoreCase("hal")) runHal();
		if (instruct.getObjectName().equalsIgnoreCase("lyle")) runLyle(); 
		return; 
	}
	
	private static void runHal(){
		//TODO implement
	}
	
	private static void runLyle(){
		//TODO implement
		
	}
	
	public static void createInstructionObject(String command, String subject, String object, int value)
	{
		
	}
}



