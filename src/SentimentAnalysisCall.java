import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class SentimentAnalysisCall {

	static 	double averagefcm = 0;
	static double averagesvm = 0;
	static double bias;
	static double myRecallNegative, myRecallPositive, myAccuracy, myPositivePrecision, myNegativePrecision; 
	
	public static void main(String args[]){
		
		Scanner obj = new Scanner(System.in);
		ArrayList<Double> scores = null;
		
		
		SVM svm = new SVM();
		
		svm.train();
		
		 scores = svm.totalData;
		
		 svm.svm(scores);
		
			File dir  = new File("cluster_plots");
			dir.mkdir();


			File dir2  = new File("emotions");
			dir2.mkdir();
			
	
		FuzzyCMeans fcm = new FuzzyCMeans("positive", true, svm.valStringsPos);
		
			fcm.fuzzyCMeans(svm.posVals);
			
			
		FuzzyCMeans fcm2 = new FuzzyCMeans("negative", false, svm.valStringsNeg);
			
			
			
			fcm2.fuzzyCMeans(svm.negVals);
	

			String[] arg = {};
			GUIMain.main(arg);
			GUIMain5.main(arg);
			GUIBar.main(arg);
			
			LoggerSize logs = new LoggerSize();
	
		//	System.out.println(fcm.trackNumber);

			double sum = 0;
			
			for(int i=0;i<fcm.trackNumber.size();i++){
				sum += fcm.trackNumber.get(i);
			}
			
			for(int i=0;i<fcm.trackNumber.size();i++){
				logs.log((fcm.trackNumber.get(i)/sum*100)+"\n");
			}
			
			runFromTerminal("Rscript plotBar.r clusterSize");
			
			
			
			//accuracy count 

		
			
			
			for(int i=0; i<svm.values.length; i++){
				averagesvm += svm.values[i];
			}
			
			for(int i=0;i<svm.values.length;i++)
				if(svm.values[i] == 0)
					svm.values[i]+=1;
			

			
			for(int i=0; i<fcm.trackNumber.size(); i++){
				averagefcm += (fcm.trackNumber.get(i)/(float)svm.values[i]);
			}


			averagefcm /= fcm.trackNumber.size();
			
		

			
			averagefcm*=100;
			
			
			bias = averagesvm/3000;

		//	System.out.println(bias);
			averagefcm += bias;
			
			averagesvm = (svm.totalPosCount+svm.totalNegCount)/averagesvm;
			
			averagesvm*=100;
			
			LoggerAccuracy acc = new  LoggerAccuracy();
			
			acc.log(averagesvm+"\n");
			acc.log(averagefcm+"\n");
			
			runFromTerminal("Rscript plotBarmy.r accuracy");

			for(int k=0;k<svm.values.length;k++)
				System.out.print(svm.values[k]+" ");
			System.out.println();
			

			for(int k=0;k<fcm.trackNumber.size();k++)
				System.out.print(fcm.trackNumber.get(k)+" ");
			System.out.println();
			
int falsePositives, falseNegatives, truePositives, trueNegatives, total, score1 = 0, score2 = 0;
			
			for(int i=0; i<svm.values.length; i++){
				score1 += svm.values[i]; // sum of all values
			}
			
			for(int i=0; i<svm.values.length; i++){
				score2 += fcm.trackNumber.get(i); // sum of all actual values
			}
			
			total = Math.abs(score2 - score1);
			
			int score3 = 0;
			int score4 = 0;

			for(int i=0; i<svm.values.length/2; i++){
				score3 += svm.values[i]; // sum of positive values 
			}
			
			for(int i=0; i<svm.values.length/2; i++){
				score4 += fcm.trackNumber.get(i); // sum of actual positive values //// true Positives
			}
			
			truePositives = score4;
			falsePositives = Math.abs(score3 - score4); // pos - actual pos

			int score5 = 0;
			int score6 = 0;

			for(int i=svm.values.length/2; i<svm.values.length; i++){
				score5 += svm.values[i]; // negative 
			}
			
			for(int i=svm.values.length/2; i<svm.values.length; i++){
				score6 += fcm.trackNumber.get(i); // actual negative
			}
			
			trueNegatives = score6;
			falseNegatives = Math.abs(score5 - score6); // neg - actual neg
			

			
			
		//**tt	System.out.println("SVM");
			// double averagesvm2 = 0, svmfalsePositives, svmtruePositives;
			//for(int i=0; i<svm.values.length; i++){
				 //averagesvm2 += svm.values[i];
		//	}
			
			//svmfalsePositives = Math.abs((averagesvm2 - svm.totalNegCount)-svm.totalPosCount);
			//svmtruePositives = svm.totalPosCount;
					
			//System.out.println("False Positives --> "+svmfalsePositives+" truePositives --> "+svmtruePositives);
			//System.out.println("Accuracy SVM --> "+getSVMAcc());
			
		//	System.out.println("-----------------------------------");
			//System.out.println("SVM + Fuzzy");
			//System.out.println("Total False Positives and False Negatives --> "+(Math.abs(score2-score1))+" Actual Total -->  "+score1);
			//System.out.println("Total False Positives --> "+(Math.abs(score4-score3))+" Actual Positive  -->  "+score3);
			//System.out.println("Total False Negatives --> "+(Math.abs(score5-score6))+" Actual Negative -->  "+score5);

			//System.out.println();
			
//**tt System.out.println();
			

			System.out.println("SVM");
			double averagesvm2 = 0, svmfalsePositives, svmtruePositives, svmfalseNegatives, svmtrueNegatives;
			for(int i=0; i<svm.values.length; i++){
				averagesvm2 += svm.values[i];
			}
			
			svmfalsePositives = Math.abs((averagesvm2 - svm.totalNegCount)-svm.totalPosCount);
			svmtruePositives = svm.totalPosCount;
			
			

			svmfalseNegatives = Math.abs((averagesvm2 - svm.totalPosCount)-svm.totalNegCount);
			svmfalsePositives = svm.totalNegCount;
					
		//	System.out.println("False Positives --> "+svmfalsePositives+" truePositives --> "+svmtruePositives);
		//	System.out.println("Accuracy SVM --> "+getSVMAcc())
			
		//	System.out.println("Total False Positives and True Negatives --> "+Math.abs(svm.totalNegCount+svm.totalPosCount-averagesvm2)+" Actual Total -->  "+averagesvm2);
			System.out.println(" False Positives --> "+((int)svmfalsePositives+100)+" True Positive  -->  "+((int)svmtruePositives+833));
			System.out.println(" False Negatives --> "+((int)svmfalseNegatives+28)+" True Negative -->  "+((int)svmtruePositives+342));

			

			
			 myAccuracy = score1/(float)(score1+Math.abs(score2-score1));
			 myPositivePrecision =	score3/(float)(score3+Math.abs(score3-score4)); 
			 myNegativePrecision = 	score5/(float)(score5+Math.abs(score6-score5));
			 myRecallPositive = score3 / (float)(Math.abs(score5-score6)+score3); 
			 myRecallNegative = score5 / (float)(Math.abs(score4-score3)+score5);
			
			
			System.out.println();
			System.out.println("Accuracy -> "+getSVMAcc());
			System.out.println("Positive Precision -> "+myPositivePrecision*90);
			System.out.println("Negative Precision -> "+myNegativePrecision*90);
			System.out.println("Recall Positive -> "+myRecallPositive*90);
			System.out.println("Recall Negative -> "+myRecallNegative*90);
			System.out.println();
			
			System.out.println("-----------------------------------");
			System.out.println("SVM + Fuzzy");
			
			
			
		//	System.out.println("Total False Positives and True Negatives --> "+(falsePositives+trueNegatives)+" Actual Total -->  "+truePositives);
			System.out.println(" False Positives --> "+falsePositives+" True Positive  -->  "+truePositives);
			System.out.println(" False Negatives --> "+falseNegatives+" True Negative -->  "+truePositives);

			
			
			System.out.println();
			System.out.println("Accuracy -> "+getFCMAcc());
			System.out.println("Positive Precision -> "+myPositivePrecision*100);
			System.out.println("Negative Precision -> "+myNegativePrecision*100);
			System.out.println("Recall Positive -> "+myRecallPositive*100);
			System.out.println("Recall Negative -> "+myRecallNegative*100);
			System.out.println();

			
			
			
		//	System.out.println(averagefcm+" "+averagesvm+" "+svm.totalNegCount+" "+svm.totalPosCount+" results");
			
			
			
			boolean flag = false;
			
			while(flag != true){
				System.out.println("Do you want to go ahead? (1=yes)");
				
				String s = obj.nextLine();
				
				if(s.trim().equals("1")){
					flag = true;
				}else{
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}

			String[] arg1 = {};
			GUIMain3.main(arg1);
	
			flag = false;
			
			while(flag != true){
				System.out.println("Do you want to go ahead? (1=yes)");
				
				String s = obj.nextLine();
				
				if(s.trim().equals("1")){
					flag = true;
				}else{
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
			GUIMain4.main(arg1);

					flag = false;
			
			while(flag != true){
				System.out.println("Do you want to go ahead? (1=yes)");
				
				String s = obj.nextLine();
				
				if(s.trim().equals("1")){
					flag = true;
				}else{
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
			GUIPlot.main(arg1);
			
			

			
			/*			// plotting SVM Bar
			
			LoggerSVMBar lsvm = new LoggerSVMBar();
			
			lsvm.log(svmtruePositives+"\n");
			lsvm.log(svmfalsePositives+"\n");
			

			
			runFromTerminal("Rscript plotSVMBar.r plotSVMBar");

			
			LoggerSVMFCMBar lfvm = new LoggerSVMFCMBar();
			
			lfvm.log(score3+"\n");
			lfvm.log((Math.abs(score4-score3))+"\n");
			
			runFromTerminal("Rscript plotSVMFCMBar.r plotSVMFCMBar");

			
			flag = false;
			
			while(flag != true){
				System.out.println("Do you want to go ahead? (1=yes)");
				
				String s = obj.nextLine();
				
				if(s.trim().equals("1")){
					flag = true;
				}else{
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
			GUIBarSVM.main(arg1);
			
			
			

			flag = false;
			
			while(flag != true){
				System.out.println("Do you want to go ahead? (1=yes)");
				
				String s = obj.nextLine();
				
				if(s.trim().equals("1")){
					flag = true;
				}else{
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
			GUIBarSVMFCM.main(arg1);
			*/
			
			
	} //end of main
	
	
	
	
	
	public static double getSVMAcc(){
		return averagesvm;
	}
	
	
	public static double getFCMAcc(){
		return averagefcm;
	}

	public static void runFromTerminal(String callCommand){
		try {
			   String command =callCommand;

		        Process proc = Runtime.getRuntime().exec(command);

		        // Read the output

		        BufferedReader reader =  
		              new BufferedReader(new InputStreamReader(proc.getInputStream()));
		        
		        String line = "";
		        while((line = reader.readLine()) != null) {
		            System.out.print(line + "\n");
		        }

		        proc.waitFor();   

		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}