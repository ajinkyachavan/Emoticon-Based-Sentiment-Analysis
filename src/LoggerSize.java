import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerSize {
	
	
	public LoggerSize(){
	
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("clusterSize"));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("clusterSize");
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("clusterSize", true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}