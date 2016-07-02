import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerValState {
	
	
	public LoggerValState(){
	
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("valState"));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("valState");
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("valState", true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}