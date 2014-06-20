import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CovertChannel {

	public static String output; 

	public static void main(String[] args) throws IOException {
		
		long start = System.nanoTime(); 
		SecureSystem.setup();   //adds Hal and Lyle to subMap

		//set verbose flag and find input file
		String inputFile = ""; 
		if (args[0].equalsIgnoreCase("v")){
			InstructionObject.verbose = true; 
			inputFile = args[1]; 
		}
		else inputFile = args[0]; 

		//find output file
		output = inputFile + ".out";

		//This code FileChannel +MappedByte is taken from 
		//http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_
		//quickly#FileChannelwithMappedByteBufferandbytearraygets

		//Open file and buffer read bytes
		FileInputStream f = new FileInputStream(inputFile);
		FileChannel ch = f.getChannel( );
		MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY, 0L, ch.size());

		File fout = new File(output);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedOutputStream bos = new BufferedOutputStream(fos);


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

			//converts byte to bits
			for (byte m = 1; m != 0; m <<= 1) {
				int bit = ((b&m) != 0) ? 1 : 0; 
				test = (byte) (test << 1); 
				test = (byte) (test|bit); 
			}

			//Need to flip bits
			for (byte m = 1; m != 0; m <<= 1) {
				int bit = ((test&m) != 0) ? 1 : 0; 
				SecureSystem.sendbit(bit, bos);
			}
			test = 0x00; //reset test
		}

		SecureSystem.writeOutRest(bos);
		
		bos.flush();
		fos.close();

		f.close();   //close file when done
		
		long totalTime = System.nanoTime() - start; 
		System.out.println(totalTime); 
	}



}
