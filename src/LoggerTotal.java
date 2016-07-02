import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerTotal {
	
	
	public LoggerTotal(){
	
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("total"));
   	 	
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("total");
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("total", true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}