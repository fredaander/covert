import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class CovertChannel {
	
	public static String output; 
	
	public static void main(String[] args) throws IOException {
		
		SecureSystem.setup();
		
		String inputFile = ""; 
		if (args[0].equalsIgnoreCase("v")){
			InstructionObject.verbose = true; 
			inputFile = args[1]; 
		}
		else inputFile = args[0]; 
		
		output = inputFile + ".out";
		
		//This code FileChannel +MappedByte is taken from 
		//http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_
		//quickly#FileChannelwithMappedByteBufferandbytearraygets
		
		//Open file and buffer read bytes
		FileInputStream f = new FileInputStream(inputFile);
		FileChannel ch = f.getChannel( );
		MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY, 0L, ch.size());
		
		//get one byte at a time
		byte[] barray = new byte[1];
		byte test = 0x00;
		int nGet;
		
		while( mb.hasRemaining() )
		{
			//gets one byte at a time
		    nGet = Math.min( mb.remaining(), 1);
		    mb.get( barray, 0, nGet );
		    byte b= barray[0];
		    
		    //coverts byte to bits
		    for (byte m = 1; m != 0; m <<= 1) {
		       int bit = ((b&m) != 0) ? 1 : 0; 
		    
		      test = (byte) (test << 1); 
		      test = (byte) (test|bit); 
		     
		    }
		   
		    byte answer = 0x00; 
		    for (byte m = 1; m != 0; m <<= 1) {
			       int bit = ((test&m) != 0) ? 1 : 0; 
			    
			      SecureSystem.sendbit(bit);
			      answer = (byte) (answer << 1); 
			      answer = (byte) (answer|bit); 
			        
			    }

		    System.out.print( new String(new byte[]{ (byte)answer }, StandardCharsets.US_ASCII));
		    //System.out.println("string " + new String(new byte[]{ (byte)test },StandardCharsets.UTF_8));
		    
		    test = 0x00;
		}
		
		f.close();
	}
	
	

}
