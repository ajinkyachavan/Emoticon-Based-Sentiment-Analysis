import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerAccuracy {
	
	
	public LoggerAccuracy(){
	
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("accuracy"));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("accuracy");
   		 writer1.print("");
   		 writer1.close();
   	 }else{
   		 bf = null;
   	 } ;
        }catch(Exception e){
      	  e.printStackTrace();
        }
	
	
	
	}
	
	
    public void log(String message) { 
      PrintWriter out = null;
      
 
      
      
      
      try {
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("accuracy", true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}