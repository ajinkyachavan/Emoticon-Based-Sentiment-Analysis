import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FuzzyCMeans {

	
	
	/******* Fuzzy C Means Variables***/
	static 	ArrayList<Integer> trackNumber = new ArrayList<>();

	
	boolean allowed = true;
	//LoggerFinale finale = new LoggerFinale();
LoggerCentroid logcent = new LoggerCentroid();
	BufferedReader emotInput = null;
	ArrayList<String> inData = new ArrayList<>();
	ArrayList<Integer> lineNum = new ArrayList<>();
	ArrayList<Double> values = new ArrayList<>();
	ArrayList<Double> euclid = new ArrayList<>();
	ArrayList<Double> centroids = new ArrayList<>();
	ArrayList<Double> centroidBackup = new ArrayList<>();
	ArrayList<Double> distance = new ArrayList<>();
	ArrayList<ArrayList<Double>> membership = new ArrayList<>();
	ArrayList<Double> memArray = new ArrayList<>();
	ArrayList<Double> happyNumbers = new ArrayList<>();
	ArrayList<Double> fondNumbers = new ArrayList<>();
	double mem1=0, mem2=0, mem=0, memadd=0, memHappy =0, memFond = 0;
	double euclidistance = 0;
	ArrayList<Double> num=null;
	ArrayList<Double> den = null;
	double m = 4;
	ArrayList<Double> gBest = new ArrayList<>();
	double gBest1=0, gBest2 = 0;
	LoggerFinal finale = null;
	double mean = 0;
	int iter = 0;
	
	boolean flag = false;
	
	ArrayList<ArrayList<Double>> clusters = new ArrayList<>();
	ArrayList<Double> cluster1 = new ArrayList<>();
	ArrayList<Double> cluster2 = new ArrayList<>();
	ArrayList<Double> cluster3 = new ArrayList<>();
	static int i1=1;

	HashMap<Double, String> valStrings = new HashMap<>();
	
	
/*********************** Fuzzy C Means***/
		
	String value = null;
	public FuzzyCMeans(String value,  boolean flag, HashMap<Double, String> valStrings){
		this.value =value;
		this.flag = flag;
		this.valStrings = valStrings;
		
	}
	
	

		public  void fuzzyCMeans(ArrayList<Double> scores){
			

		   // initialise centroids
			
			centroids.add(30.0);
			centroids.add(1000.0);
			centroids.add(20000.0);
			
			
			allowed = true;
			iter = 0;
		//	System.out.println(scores);
		//	System.out.println("here");
			
			// Step 1 : Calculate membership matrix using the centroids assigned intitally and then update accordingly
	label1:while(allowed){
		logcent = new LoggerCentroid();
		iter++;
		membership = new ArrayList<>();

		num = new ArrayList<>();
		den = new ArrayList<>();
	//// System.out.println("centroids new one"+centroids);

			for(int i=0; i<scores.size(); i++){ 

	double currentVal = 0;
	memArray = new ArrayList<>();

				for(int h=0;h<centroids.size();h++){
					mem1 = 0;

					currentVal = centroids.get(h);
						for(int g=0;g<centroids.size();g++){
			//				System.out.println(scores.get(i)+" "+currentVal+" "+centroids.get(g));
							mem1 += Math.pow( Math.abs((scores.get(i)-currentVal) / (scores.get(i) - centroids.get(g))),2/(m-1)  );
						}
						
						mem1 = 1/mem1;
						
						//	 System.out.println(mem1+" "+scores.get(i));
							
							memArray.add(mem1);
					
						
			}
						//		memArray.add(1-mem1);
						membership.add(memArray);
					
			
			}
			
			num = findNumerator(membership,scores);
			den = findDenominator(membership, scores);
		// System.out.println("num - "+num);
		// System.out.println("den - "+den);
			centroidBackup = new ArrayList<>();

			for(int k=0; k< num.size(); k++){
				
			//	System.out.println("num/den "+k+" - "+((num.get(k)/den.get(k))));
				centroidBackup.add(num.get(k)/den.get(k));
			}
			
			centroids = centroidBackup;
			
			for(int i5=0;i5<centroids.size();i5++){
			LoggerCentroid.log(centroids.get(i5) +"\n");
			}

		SVM.runFromTerminal("Rscript centroidsPlot.r positive centroid 4  iter-"+iter);
		
		
	//		System.out.println("centroids - "+centroids);
			centroidBackup = new ArrayList<>();
			
			
			if(membership != null){
			//	System.out.println("mem"+membership);
				FindMatrixDifference(membership, scores);
				
				if(iter > 50)
					allowed = false;
				
				if(allowed == false){
					
					System.out.println(iter+" iterations");
					
			//		System.out.println(centroids);
					
					cluster1 = new ArrayList<>();
					cluster2 = new ArrayList<>();
					cluster3 = new ArrayList<>();

					
					for(int i=0; i<scores.size();i++){
			//			System.out.println(scores.get(i));
						double dist1;
						if(centroids.get(0)>scores.get(i))
							 dist1 =  centroids.get(0)-scores.get(i) ;
						else
							 dist1 =  scores.get(i) - centroids.get(0);
		
		//			System.out.println(dist1);
						
						double dist2;
						if(centroids.get(1)>scores.get(i))
							 dist2 =  centroids.get(1)-scores.get(i) ;
						else
							 dist2 =  scores.get(i) - centroids.get(1);
		

			//			System.out.println(dist2);
						
						double dist3;
						if(centroids.get(2)>scores.get(i))
							 dist3 =  centroids.get(2)-scores.get(i) ;
						else
							 dist3 =  scores.get(i) - centroids.get(2);
		

		//			System.out.println(dist3);
			//		System.out.println("-------------");
						
			//		System.out.println("-------------");
						if(dist1 > dist2)
									if(dist2>dist3){
			//							System.out.println("3>2>1 -"+dist2+"-"+dist3+"-"+scores.get(i));
										cluster3.add(scores.get(i));
									}else{
						

				//						System.out.println("2>3>1-"+dist2+"-"+dist3+"-"+scores.get(i));
										cluster2.add(scores.get(i));
									}
						
									else
										if(dist1>dist3){

					//						System.out.println("3>1>2-"+dist1+"-"+dist3+"-"+scores.get(i));
											cluster3.add(scores.get(i));
										}
										else{

						//					System.out.println("1>3>2-"+dist1+"-"+dist3+"-"+scores.get(i));
											cluster1.add(scores.get(i));
										}
								
					}
			//		System.out.println("value "+value);
					
			//		System.out.println(valStrings);
					
					trackNumber.add(cluster1.size());

					trackNumber.add(cluster2.size());

					trackNumber.add(cluster3.size());
					
		
					
					System.out.println("cls1 "+cluster1);

					System.out.println("cls2 "+cluster2);
					

					System.out.println("cls3 "+cluster3);
					

					
					LoggerCluster clust1 = new LoggerCluster(1, value);
					
					
					//System.out.println(valStrings);
						
		//			System.out.println(flag+" flagggg");
					
					if(flag == true){
						
						LoggerFinal finale = new LoggerFinal("happy");
						

						
						for(int i=0;i<cluster1.size();i++){
							finale.log(valStrings.get(cluster1.get(i))+"- happy \n");
						}
						
						
					}else{

				LoggerFinal finale = new LoggerFinal("sorrow");
						
						
						for(int i=0;i<cluster1.size();i++){
							finale.log(valStrings.get(cluster1.get(i))+"- sorrow \n");
						}
						
						
					}
					
					for(int i=0;i<cluster1.size();i++)
						clust1.log(cluster1.get(i)+"\n");
					
					SentimentAnalysisCall.runFromTerminal("Rscript plotValues.r  cluster_"+value+"_1 1");
					
					System.out.println();

					LoggerCluster clust2 = new LoggerCluster(2, value);
					
					if(flag == true){
						
						
				LoggerFinal finale = new LoggerFinal("fond");
						
						
						
						for(int i=0;i<cluster2.size();i++){
							finale.log(valStrings.get(cluster2.get(i))+"- fond \n");
						}
						
						
					}else{
					
						
				LoggerFinal finale = new LoggerFinal("angry");
						
						
						
						for(int i=0;i<cluster2.size();i++){
							finale.log(valStrings.get(cluster2.get(i))+"- angry \n");
						}
						
						
						
					}	
					
					
					
					//System.out.println(cluster2);

					for(int i=0;i<cluster2.size();i++)
						clust2.log(cluster2.get(i)+"\n");
					System.out.println();

					SentimentAnalysisCall.runFromTerminal("Rscript plotValues.r  cluster_"+value+"_2 2");

					
					LoggerCluster clust3 = new LoggerCluster(3, value);
					
					if(flag == true){
						
						
				LoggerFinal finale = new LoggerFinal("surprise");
						
						
						
						for(int i=0;i<cluster3.size();i++){
							finale.log(valStrings.get(cluster3.get(i))+"- surprise \n");
						}
						
						
					}else{
						
						
				LoggerFinal finale = new LoggerFinal("anxious");
						
						
						
						for(int i=0;i<cluster3.size();i++){
							finale.log(valStrings.get(cluster3.get(i))+"- anxious \n");
						}
						
						
					}
					
					for(int i=0;i<cluster3.size();i++)
						clust3.log(cluster3.get(i)+"\n");
					//System.out.println(cluster3);
					
					SentimentAnalysisCall.runFromTerminal("Rscript plotValues.r  cluster_"+value+"_3 3");

					
					break label1;
								
					
					}
			//		System.out.println("-----------------------------------------------------------------------------");
					
				}
			
			}
	
		
		}
			
		
	
		
		
		public ArrayList<Double> findNumerator(ArrayList<ArrayList<Double>> membership, ArrayList<Double> scores){
			ArrayList<Double> num = new ArrayList<>();
			double mem1 = 0, mem2 = 0, mem3=0;
		
			for(int i=0; i<(scores.size()); i++){
				
				int j =0;
				mem1 += (Math.pow(membership.get(i).get(j), m)*scores.get(i));
			//	System.out.println("j=0 i= "+i+" input-"+scores.get(i)+"  membe - "+membership.get(i).get(j)+" mem1 - "+ (Math.pow(membership.get(i).get(j), m)*scores.get(i)));

		//	 System.out.println("0 - "+membership.get(i).get(j));
				j = 1;

				mem2 += (Math.pow(membership.get(i).get(j), m)*scores.get(i));

				j=2;
				
				mem3 += (Math.pow(membership.get(i).get(j), m)*scores.get(i));

				//			System.out.println("j=1 i= "+i+" input-"+scores.get(i)+"  membe - "+membership.get(i).get(j)+" mem1 - "+ (Math.pow(membership.get(i).get(j), m)*scores.get(i)));

			//	// System.out.println("1 - "+membership.get(i).get(j));
			}
		
	
			
			
			
			
			
			num.add(mem1);
			num.add(mem2);
			num.add(mem3);
			return num;
		}
		
		public ArrayList<Double> findDenominator(ArrayList<ArrayList<Double>> membership, ArrayList<Double> scores){
//	System.out.println("0-----------------------------------");
			ArrayList<Double> num = new ArrayList<>();
			double mem1 = 0, mem2 = 0, mem3=0;
			int k=0;
			for(int i=0; i<scores.size(); i++){
				int j =0;
				mem1 += (Math.pow(membership.get(i).get(j), m));
	//			System.out.println("mem1 - "+mem1);
	//		 System.out.println("den j = 0 "+(Math.pow(membership.get(i).get(j), m)));
				j = 1;
				mem2 += (Math.pow(membership.get(i).get(j), m));
	//			System.out.println("den j = 1 "+(Math.pow(membership.get(i).get(j), m)));
				j=2;
	
				mem3 += (Math.pow(membership.get(i).get(j), m));
				
			}
			
			num.add(mem1);
			num.add(mem2);
			num.add(mem3);
			return num;
		}
		
		public void FindMatrixDifference(ArrayList<ArrayList<Double>> membership, ArrayList<Double> scores){
			
			int index = 0;
			ArrayList<Double> incr = new ArrayList<>();

			double diff1 = 0,diff2 = 0;
			for(int i=0; i<scores.size(); i++){
				if(i==0){
					diff1 =  Math.abs(membership.get(i).get(0) - membership.get(i).get(1));
					diff2 =  Math.abs(membership.get(i).get(1) - membership.get(i).get(2));
				}
				if( Math.abs(membership.get(i).get(0) - membership.get(i).get(1)) > 0.10 &&  Math.abs(membership.get(i).get(1) - membership.get(i).get(2)) > 0.10){
				//	System.out.println(" lol "+membership.get(i).get(0)+" "+membership.get(i).get(1)+" "+ Math.abs(membership.get(i).get(0) - membership.get(i).get(1)));

					if( Math.abs(membership.get(i).get(0) - membership.get(i).get(1) ) < diff1  &&  Math.abs(membership.get(i).get(1) - membership.get(i).get(2)) < diff2){
						diff1 =  Math.abs(membership.get(i).get(0) - membership.get(i).get(1));
						diff2 =  Math.abs(membership.get(i).get(1) - membership.get(i).get(2));
					}
				//	System.out.println(diff+" diff ");
					index++;
				
					}
			/*	if(Math.abs(membership.get(i).get(0) - membership.get(i).get(1)) < 0.1){
					incr.add(membership.get(i).get(0)+0.09);
				incr.add(membership.get(i).get(1)-0.09);
					// System.out.println("-------------");
					// System.out.println("old membership"+membership.get(i));

					membership.get(i).add(incr.get(0));
					membership.get(i).add(incr.get(1));
					membership.get(i).remove(0);
					membership.get(i).remove(1);
					// System.out.println("new membership"+membership.get(i));
					// System.out.println("-----end-------");
				}*/
			
			
			}
			if(diff1> 0.15  && diff2 > 0.15 &&  index > scores.size()-1){
				//System.out.println("final diff "+diff);
				allowed = false;
			}
		}
		
	

		


	
	
}
