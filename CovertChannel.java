import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CovertChannel {

	
	
	public static void main(String[] args) {
		//wrap in try/catch
		//	Can possibly generate inputfilename.out
		if (args[0].equalsIgnoreCase("v")){
			//set verbose flag
			//call a verbose method
		}
		else {
			Charset charset = Charset.forName("US-ASCII");
			Path apath = Paths.get(args[0]); 
			try (BufferedReader reader = Files.newBufferedReader(apath, charset)) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			    }
			} catch (IOException x) {
			    System.err.format("IOException: %s%n", x);
			}
		}
		
	}

}
