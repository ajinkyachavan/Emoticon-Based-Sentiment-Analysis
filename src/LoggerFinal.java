import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerFinal {
	
	 static String emotion = "";
	
	public LoggerFinal(String emotion){
	
		LoggerFinal.emotion = emotion;
		
		File f = new File("emotions/"+emotion);
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("emotions/"+emotion));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("emotions/"+emotion);
   		 writer1.print("");
   		 writer1.close();
   	 }else{
   		 bf = null;
   	 } ;
        }catch(Exception e){
      	  e.printStackTrace();
        }
	
	
	
	}
	
	
    public static void log(String message) { 
      PrintWriter out = null;
      
 
      
      
      
      try {
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("emotions/"+emotion, true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}