import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerSVMBar {
	
	
	public LoggerSVMBar(){
	
		
		File f = new File("/home/"+System.getProperty("user.name")+"/workspace/EmoticonFuzzyCMeans/plotSVMBar");
		
		try{
		f.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader("plotSVMBar"));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter("plotSVMBar");
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter("plotSVMBar", true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}