
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVM {

	
	/**variables**/
	
	String[] happytrack = {":)", ":-)", ";)", ":D", ":-D", ";D", ":d", "(:"};
	String[] fondtrack  = {"<3"};
	String[] sadtrack = {":(", ";(", ":-(", ";-(", ":'(", ":'-("};
	String[] angrytrack = {":@"};
	String[] surprisetrack = {":O",":o", ":-O", ":-o"};
	String[] anxietytrack = {"8-("};
	String[] smileys = {":)", ":-)", ";)", ":D", ":-D", ";D", ":d", "(:","<3",
			":(", ";(", ":-(", ";-(", ":'(", ":'-(",":@",":O",":o", ":-O", ":-o","8-("};
	
	BufferedReader in = null, lex = null;
	Logger logu = new Logger();
	HashMap<String, Integer> m = new HashMap<>();
	HashMap<String, Integer> m1 = new HashMap<>();
	HashMap<Double, String> valStringsPos = new HashMap<>();
	HashMap<Double, String> valStringsNeg = new HashMap<>();
	
	LoggerValState vs = new LoggerValState();
	
	ArrayList<String> cleanData = new ArrayList<>();
	
	ArrayList<String> lexWords = new ArrayList<>();
	int totalPosCount =0;
	int totalNegCount= 0;
	
	ArrayList<ArrayList<String>> total = new ArrayList<>();
	ArrayList<Double> totalData =  new ArrayList<>();
	ArrayList<String> track = new ArrayList<>();
	
	ArrayList<ArrayList<String>> positive = new ArrayList<>();
	ArrayList<ArrayList<String>> negative = new ArrayList<>();
	
	ArrayList<Double> posVals = new ArrayList<>();
	 ArrayList<Double> negVals = new ArrayList<>();
	static ArrayList<Double> res = new ArrayList<>();

	
	ArrayList<Integer> scores = new ArrayList<>();
	ArrayList<Double> testDataPos = new ArrayList<>();
	ArrayList<Double> testDataNeg = new ArrayList<>();

	ArrayList<String> nonUniqueWords = new ArrayList<>();
	ArrayList<String> uniqueWords = new ArrayList<>();
	
	LoggerTotal tot = new LoggerTotal();
	LoggerPositive pos = new LoggerPositive();
	LoggerNegative neg = new LoggerNegative();
	LoggerValuesPos posV = new LoggerValuesPos();
	LoggerValuesNeg negV = new LoggerValuesNeg();
	
	double value = 0;
/*	ArrayList<Integer> happy = new ArrayList<>();
	ArrayList<Integer> fond = new ArrayList<>();
	ArrayList<Integer> surprise = new ArrayList<>();
	ArrayList<Integer> sad = new ArrayList<>();
	ArrayList<Integer> angry = new ArrayList<>();
	ArrayList<Integer> anxious = new ArrayList<>();*/
	
	boolean flag = false;
	Scanner obj = new Scanner(System.in);
	
	int[] values = {0,0,0,0,0,0};
	
	
	String args[];
	
	/**train SVM**/
	
	public void train(){
		
		// read unclean file and convert to clean file
		
		args = new String[2];
		
		args[0] = "Training Data";
		args[1] = "data_unclean";
		
		GUIMain2.main(args);
		
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
		
		try{
			
			in = new BufferedReader(new FileReader("data_unclean"));
			
			String data = null;
			
			while((data = in.readLine()) != null){
				String pattern[] = {"#"," rt ", "http[^\\s]+","@[^\\s]+","\"","&lt;","&gt;"};	
				
				int i=0;
				
				while(i < pattern.length){		
				
					Pattern r = Pattern.compile(pattern[i++]);
				
					Matcher m = r.matcher(data);
					
					while(m.find()){
						
						String s = m.group();
						data = data.replaceAll(s, "");
					}
					
				
					}
				
				logu.log(data+"\n");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// put lexicons in a map
		args = new String[2];
		
		args[0] = "Clean Data";
		args[1] = "cleanData";
		
		GUIMain6.main(args);
		
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
		
		
		try{
			
			in = new BufferedReader(new FileReader("lexicons"));
			
			String data = null;
			
			while((data = in.readLine()) != null){
				
				String emoVal[] = data.split("!");
				
				lexWords.add(emoVal[0].trim());
				
				m.put(emoVal[0].trim(),Integer.parseInt(emoVal[1].trim()));
			
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// get main words,  ArrayList of ArrayList -> total
		
		try{
			
			in = new BufferedReader(new FileReader("cleanData"));
			lex = new BufferedReader(new FileReader("lexicons"));
			
			String data = null, lexdata = null;
			
			while((data = in.readLine()) != null){
				
				flag = false;
				
				track = new ArrayList<>();
				
				String words[] = data.split(" ");
 				
				int i=0;
				
				while(i < words.length){
					
					for(String d : lexWords){
					if(d.trim().equals(words[i].trim())){
						track.add(words[i]);
						if(Arrays.asList(smileys).contains(d.trim())){
							flag = true;
						}
					}
					}
					i++;
				}
				
				if(track.size() > 1 && flag == true){
					total.add(track);
					cleanData.add(data);
				}
			
				
			}
			
	//		for(int i=0;i<total.size();i++)
	//			System.out.println(total.get(i));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//score the data
		
	/*	int score = 0;
		
		for(int i=0;i<total.size();i++){
			score = 0;
			for(int j=0;j<total.get(i).size();j++){
				
				score  += m.get(total.get(i).get(j).trim());
				
			}
	
			//System.out.println(score);
		
			scores.add(score);
			/*
			if(score > 0)
				positive.add(total.get(i));
			else
				negative.add(total.get(i));
		} */
		
		// calculate occurence of words and word-Value Pairs
		
		
		for(int i=0;i<total.size();i++){
			for(int j=0;j<total.get(i).size();j++){
				nonUniqueWords.add(total.get(i).get(j).trim());
			}
		}
		
		uniqueWords = new ArrayList<String>(new LinkedHashSet<String>(nonUniqueWords));
		
		for(int k=0;k<uniqueWords.size();k++){
			int iterations = Collections.frequency(nonUniqueWords, uniqueWords.get(k).trim());
			m1.put(uniqueWords.get(k),iterations);
		}
		
		//spread the data
		
		int mul = 0;
		for(int i=0; i<total.size();i++){
			mul = 0;
			label1:for(int j=0;j<total.get(i).size();j++){
			//	System.out.println(positive.get(i).get(j));

				if(Arrays.asList(smileys).contains(total.get(i).get(j).trim())){
					mul = m.get(total.get(i).get(j));
					break label1;
				}
					
			}
			
	//		System.out.println(mul+"pos");
			value = 0;

			for(int j=0;j<total.get(i).size();j++){
			
				if(j<total.get(i).size())
					 value += m.get(total.get(i).get(j).trim())+(((double)m1.get(total.get(i).get(j).trim())/mul));
						
		
			}
			
	//	System.out.println(value+"  "+ total.get(i));

			tot.log(value+"\n");
			totalData.add(value);
			
		}
		
		// adding values using cleandata
		
		try{
			
			BufferedReader bf1 = new BufferedReader(new FileReader("cleanData"));
			
			String data = null;
			
			while((data = bf1.readLine()) != null){
				
				String[] mylex = data.split(" ");
				
				
				for(String sd: mylex){
					if(Arrays.asList(smileys).contains(sd.trim())){
						
						
						if(Arrays.asList(happytrack).contains(sd.trim())){
							values[0]+=1;
						}else if(Arrays.asList(fondtrack).contains(sd.trim())){
							values[1]+=1;
						}else if(Arrays.asList(surprisetrack).contains(sd.trim())){
							values[2]+=1;
						}
						else if(Arrays.asList(sadtrack).contains(sd.trim())){
							values[3]+=1;
						}
						else if(Arrays.asList(angrytrack).contains(sd.trim())){
							values[4]+=1;
						}
						else if(Arrays.asList(anxietytrack).contains(sd.trim())){
							values[5]+=1;
						}
						
						
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
/*
 * 
 * 
		for(int i=0; i<negative.size();i++){
			mul = 0;
			value = 0;
			label2:for(int j=0;j<negative.get(i).size();j++){
				if(Arrays.asList(smileys).contains(negative.get(i).get(j).trim())){
					mul = -m.get(negative.get(i).get(j));
			//		System.out.println(mul+" neg");
					break label2;
				}
			}
			
			for(int j=0;j<negative.get(i).size();j++){
				
				 value += m.get(negative.get(i).get(j).trim())-2*mul-((double)m1.get(negative.get(i).get(j).trim()));
					
					
				
			}
		//	System.out.println(value+"  "+ negative.get(i));

			testDataNeg.add(value);
			neg.log(value+"\n");
		}
		*/
		
	}// end of method train
	
	
	
	
	public void svm(ArrayList<Double> scores){
		
		double sum=0;
		
		for(int i=0;i<scores.size();i++)
			sum += scores.get(i);
		
		
		
		ArrayList<String> result = new ArrayList<>();
		
		res = new ArrayList<>();
		
		for(int i=0;i<scores.size();i++){
			
			result = new ArrayList<>();
			
			if(Math.sin(scores.get(i)/sum) < 0){
				result.add( scores.get(i)+"");
				result.add(total.get(i).toString());
				result.add(cleanData.get(i));

					LoggerPositive.log(cleanData.get(i)+"\n");
					LoggerValuesPos.log(scores.get(i)+"\n");

					posVals.add(scores.get(i));
				positive.add(result);
				valStringsPos.put(scores.get(i), cleanData.get(i));
				LoggerValState.log(scores.get(i)+" -> "+cleanData.get(i)+"\n");
				res.add(scores.get(i));
		//	System.out.println(scores.get(i)+" -> "+cleanData.get(i));
			}
			else{
				result.add( scores.get(i)+"");
				result.add(total.get(i).toString());
				result.add(cleanData.get(i));
				LoggerNegative.log(cleanData.get(i)+"\n");
				LoggerValuesNeg.log(scores.get(i)+"\n");

				
				negVals.add(scores.get(i));
				negative.add(result);
				valStringsNeg.put(scores.get(i), cleanData.get(i));
				LoggerValState.log(scores.get(i)+" -> "+cleanData.get(i)+"\n");
				res.add(scores.get(i));
//	System.out.println(scores.get(i)+" -> "+cleanData.get(i));
		}
			
			
		}

		
		/*	

		System.out.println();
			System.out.println(valStringsPos);
		System.out.println();
		System.out.println(valStringsNeg);
			 * 			System.out.println("Positive: \n");
					
					
						for(int i=0;i<svm.valueStrings.size();i++){
							System.out.println("heyo");
							System.out.println(svm.scores.get(i)+"   ->  "+svm.valueStrings.get(scores.get(i)));
						}
			System.out.println("Negative: \n");
			for(int i=0;i<negative.size();i++){
				System.out.println(negative.get(i).get(0));
			}
			
		*/


		BufferedReader f1,f2;
		
		try{
			
			f1 = new BufferedReader(new FileReader("svm/posVals"));
			
			String data = null;
			int counter  = 0;
			
			while((data = f1.readLine()) != null)
				counter++;
			
			
			totalPosCount = counter;
			
			f2 = new BufferedReader(new FileReader("svm/negVals"));
			
			 data = null;
			 counter  = 0;
			
			while((data = f2.readLine()) != null)
				counter++;
			
			totalNegCount = counter;

			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	
		
			runFromTerminal("Rscript plotMain.r svm/posVals");

			runFromTerminal("Rscript plotMain.r svm/negVals");
			
			args = new String[2];
			
			args[0] = "Positive Data after SVM";
			args[1] = "svm/positive";
			
			GUIMain2.main(args);
			
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
			
		
			
			args = new String[2];
			
			args[0] = "Negative Data after SVM";
			args[1] = "svm/negative";
			
			GUIMain2.main(args);
			
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
			
		
			
			
			}//end of SVM
	
	public static ArrayList<Double> getScores(){
		return res;
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
