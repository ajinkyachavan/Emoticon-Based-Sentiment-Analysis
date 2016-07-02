import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerCluster {
	
	static String filename = null;

	
	public LoggerCluster(int num, String value){
	
		
		//System.out.println(num+" "+value);
		try{
			
		File f = new File("cluster_"+value+"_"+num);
		f.createNewFile();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		filename = "cluster_"+value+"_"+num;
		
    try{
        BufferedReader  bf  = new BufferedReader(new FileReader(filename));
   	 
   	 if(bf.readLine() != null){
   		 PrintWriter writer1 = new PrintWriter(filename);
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
    	  
    	
    	 
		out = new PrintWriter(new FileWriter(filename, true), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.write(message);
      out.close();
    }
}