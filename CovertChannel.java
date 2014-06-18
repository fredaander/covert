import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class CovertChannel {
	
	public static void main(String[] args) throws IOException {
		
		//wrap in try/catch
		//	Can possibly generate inputfilename.out
//		if (args[0].equalsIgnoreCase("v")){
//			//set verbose flag
//			//call a verbose method
//			System.out.println("Verbose");
//		}
//		else {
//			//Use scanner classes: nextByte(), hasNextByte(), 
//			File file = new File(args[0]);
//
//	         byte[] b = new byte[(int) file.length()];
//	         try {
//	               FileInputStream fileInputStream = new FileInputStream(file);
//	               fileInputStream.read(b);
//	               for (int i = 0; i < b.length; i++) {
//	                           System.out.print(b[i]);
//	                }
//	          } catch (FileNotFoundException e) {
//	                      System.out.println("File Not Found.");
//	                      e.printStackTrace();
//	          }
//	          catch (IOException e1) {
//	                   System.out.println("Error Reading The File.");
//	                    e1.printStackTrace();
//	          }
//		}
		
		
		//This code FileChannel +MappedByte is taken from 
		//http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_
		//quickly#FileChannelwithMappedByteBufferandbytearraygets
		
		//Need to ask if this is ok to lift code below - works cited on README?
		
		//Open file and buffer read bytes
		FileInputStream f = new FileInputStream(args[0]);
		FileChannel ch = f.getChannel( );
		MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
		    0L, ch.size());
		
		//get one byte at a time
		byte[] barray = new byte[1];
		long checkSum = 0L;
		int nGet;
		
		while( mb.hasRemaining() )
		{
			//gets one byte at a time
		    nGet = Math.min( mb.remaining(), 1);
		    mb.get( barray, 0, nGet );
		    byte b= barray[0];
		    String bits = ""; 
		    
		    //coverts byte to bits
		    for (byte m= 1; m != 0; m<<= 1) {
		       int bit= ((b&m) != 0)?1:0; 
		       bits = bits + bit; //slow need to change or remove line
		       //Instead of line above - probably best to create or not create Hal object
		       //then run lyle's code 
		       //and and come back here.
		       
		    }
		    
		    //THIS IS A PROOF OF CONCEPT-THIS CODE WILL BE DELETED
		    String s = new String(barray); 
		    System.out.println(bits + "\t" + s); 
		}
	}
	
	

}
