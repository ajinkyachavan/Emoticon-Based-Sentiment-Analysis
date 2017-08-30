import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerSVMFCMBar {
	
	
	public LoggerSVMFCMBar(){
	
		
		File f = new File("/home/"+System.getProperty("user.name")+"/workspace/EmoticonFuzzyCMeans/plotSVMFCMBar");
		
		try{
		f.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("plotSVMFCMBar"));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("plotSVMFCMBar");
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("plotSVMFCMBar", true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}