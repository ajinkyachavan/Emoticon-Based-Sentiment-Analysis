import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerValuesPos {
	
	
	public LoggerValuesPos(){
	

		
		
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("svm/posVals"));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("svm/posVals");
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("svm/posVals", true), true);

      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
 
      
      
      
      out.close();
    }
}