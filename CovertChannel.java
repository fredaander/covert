import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class CovertChannel {
	
	public static void main(String[] args) throws FileNotFoundException {
//		//wrap in try/catch
//		//	Can possibly generate inputfilename.out
//		if (args[0].equalsIgnoreCase("v")){
//			//set verbose flag
//			//call a verbose method
//		}
//		else {
//			Charset charset = Charset.forName("US-ASCII");
//			Path apath = Paths.get(args[0]); 
//			try (BufferedReader reader = Files.newBufferedReader(apath, charset)) {
//			    String line = null;
//			    while ((line = reader.readLine()) != null) {
//			        System.out.println(line);
//			    }
//			} catch (IOException x) {
//			    System.err.format("IOException: %s%n", x);
//			}
//		}
		
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
			//CREATE
			else if (inst.equalsIgnoreCase("create"))
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
			
				System.out.println(inst+" "+sub+" "+obj);
				ReferenceMonitor.processCreate(new InstructionObject(inst, obj, sub));
			}
			//DESTROY
			else if (inst.equalsIgnoreCase("destroy"))
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
			
				System.out.println(inst+" "+sub+" "+obj);
				ReferenceMonitor.processDestroy(new InstructionObject(inst, obj, sub));
			}
			else
			{
				ReferenceMonitor.processBad();
			}
		}	
		
		scan.close();
		System.exit(0);
		
	}
	
	

}
