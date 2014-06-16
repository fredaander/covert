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
		Subject lyle = new Subject("lyle", 0, 0);
		Subject hal = new Subject ("hal", 10, 0);
		ReferenceMonitor.addSubject(lyle);
		ReferenceMonitor.addSubject(hal);
		
		System.exit(0);
	}
	
	public static void createInstructionObject(String command, String subject, String object, int value)
	{
		
	}
}



