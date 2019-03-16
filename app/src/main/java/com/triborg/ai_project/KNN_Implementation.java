package com.triborg.ai_project;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * Knn algorithm implementation
 */
public class KNN_Implementation {

	// created lists for storing training and testing datasets label and features.

	private List<double[]> trainfeatures = new ArrayList<>();
	private List<String> trainlabel = new ArrayList<>();
	public List<String>	AllLabels =new ArrayList<>(); 

	private List<double[]> testfeatures = new ArrayList<>();
	private List<String> testlabel = new ArrayList<>();
	/*
	 * sc object for getting user input
	 */

	Scanner sc = new Scanner(System.in);
	int knn_value = 1;
	int DistanceMetricsSelction = 0;
	int totalNumberOfLabel = 0;

	/*
	 * loading training data and extracting features and label for training dataset
	 */
	void loadtrainData(String filename) throws NumberFormatException, IOException {
		File root = new File(Environment.getExternalStorageDirectory(), "My_Data");
		File file = new File(root,filename);

		try {
			BufferedReader readFile = new BufferedReader(new FileReader(file));
			String line;
			while ((line = readFile.readLine()) != null) {

				String[] split = line.split(",");
				double[] feature = new double[split.length - 1];
				for (int i = 0; i < split.length - 1; i++)
					feature[i] = Double.parseDouble(split[i]);
				trainfeatures.add(feature);
				trainlabel.add(split[feature.length]);
				if(!AllLabels.contains(split[feature.length]))
				{
				AllLabels.add(split[feature.length]);	
				}
				
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
void clearPrevData()
{
	testlabel.clear();
	testfeatures.clear();
}
	/*
	 * loading testing data and extracting features and label for training dataset
	 * 
	 */
	void loadtestData(double features[], String label) throws NumberFormatException, IOException
	{			
				//testfeatures.clear();
				//testlabel.clear();
				testfeatures.add(features);
				testlabel.add(label);
				// writing original label for test data to file and counting number of label.				
				totalNumberOfLabel++;
	}

	/*
	 * Based on user input, calling algorithm to calculate distance
	 */
	String distanceCalcualte() throws IOException {

		String temp="";
		if (DistanceMetricsSelction == 1) {
			temp = euclideanDistance();
			// calling accuracy method to show accuracy of model.
			//accuracy();
		}

		else if (DistanceMetricsSelction == 2) {
			manhattanDistance();
			//accuracy();
		}

		else {
			// if user selecting invalid options then they must select correct option.
			System.out.println("Invalid Selection");
			getKValueandDistMetrics();
			distanceCalcualte();
		}
		return temp;
	}

	/*
	 * EuclideanDistance Calling euclidean method to calculate distance and writing
	 * output to file.
	 * 
	 */

	@SuppressWarnings("unchecked")
	String euclideanDistance() throws FileNotFoundException {
		KNN_Distance euclidean = new KNN_Distance();

		Iterator<double[]> testITR = testfeatures.iterator();

		//PrintWriter pw = new PrintWriter("EuclideanResult.txt");

		while (testITR.hasNext())
		{
			double testF[] = testITR.next();
			Iterator<double[]> trainITR = trainfeatures.iterator();
			int noOfobject = 0;
			ArrayList<DistanceAndFeatures> ts = new ArrayList<>();
			while (trainITR.hasNext()) {
				double trainF[] = trainITR.next();
				double dist = 0;
				dist = euclidean.getEuclideanDistance(trainF, testF);

				String trainFeat = trainlabel.get(noOfobject);
				DistanceAndFeatures DfObject = new DistanceAndFeatures(dist, trainFeat);
				ts.add(DfObject);
				Collections.sort(ts);
				noOfobject++;

			}

			/*
			 * counting top predicted label based on k value
			 */
           int length;
           
           length=AllLabels.size();
           int []count=new int[length];
				String s2 ;
				String s1 ;
				DistanceAndFeatures s;
				for(int i =0; i<length;i++)
				{
					s2 =AllLabels.get(i);
					
				for(int j=0; j< knn_value;j++)
				{
					  s = ts.get(j);
					 s1 = s.getLabel();	
					 if (s1.equals(s2))
							count[i]++;	
				}
				}
					int max=0;
					int temp=0;
				for(int i =0; i<length;i++)
				{
					temp = count[i];
					if(temp > max)
					{
						max=temp;
					}
				}
		   
				for(int i =0; i<length;i++)
				{
					if(max==count[i])
					{
						return AllLabels.get(i);	
					}
				}
				

		}
		//pw.close();
		return null;
	}

	/*
	 * Manhattan Distance
	 * 
	 * Calling Manhattan method to calculate distance and writing output to file.
	 * 
	 */

	@SuppressWarnings("unchecked")
	void manhattanDistance() throws FileNotFoundException {
		KNN_Distance euclidean = new KNN_Distance();

		Iterator<double[]> testITR = testfeatures.iterator();

		//PrintWriter pw = new PrintWriter("ManhattanResult.txt");

		while (testITR.hasNext()) {
			double testF[] = testITR.next();
			Iterator<double[]> trainITR = trainfeatures.iterator();
			int noOfobject = 0;
			ArrayList<DistanceAndFeatures> ts = new ArrayList<>();
			while (trainITR.hasNext()) {
				double trainF[] = trainITR.next();
				double dist = 0;
				dist = euclidean.getManhattanDistance(trainF, testF);

				String trainFeat = trainlabel.get(noOfobject);
				DistanceAndFeatures DfObject = new DistanceAndFeatures(dist, trainFeat);
				ts.add(DfObject);
				Collections.sort(ts);
				noOfobject++;

			}

			/*
			 * counting top predicted label based on k value
			 */

			int flag = 0, IR_Setsosa = 0, IR_Versicolor = 0, IR_Virginica = 0;

			while (flag < knn_value) {
				DistanceAndFeatures s = ts.get(flag);
				String s1 = s.getLabel();
				if (s1.equals("Iris-setosa"))
					IR_Setsosa++;
				else if (s1.equals("Iris-versicolor"))
					IR_Versicolor++;
				else if (s1.equals("Iris-virginica"))
					IR_Virginica++;
				flag++;

			}

			/*
			 * counting label and selecting highest label count as prediction label and
			 * writing to output file.
			 */

			if (IR_Setsosa > IR_Versicolor && IR_Setsosa > IR_Virginica) {
				System.out.println("Iris_Sestosa=" + IR_Setsosa);
				//pw.println("Iris-setosa");

			} else if (IR_Versicolor > IR_Setsosa && IR_Versicolor > IR_Virginica) {
				System.out.println("Iris_Versicolor=" + IR_Versicolor);
				//pw.println("Iris-versicolor");
			}

			else if (IR_Virginica > IR_Setsosa && IR_Virginica > IR_Versicolor) {
				System.out.println("Iris_virginica=" + IR_Virginica);
				//pw.println("Iris-virginica");
			}
		}
		//pw.close();
	}

	/*
	 * method to get K value and Distance metrics.
	 */

	void getKValueandDistMetrics() {

		//System.out.println("Enter the K value of KNN ");
		knn_value = 5;
		// Restricted k value less 50.
		if (knn_value > 50) {
			System.out.println("K Value is out of range.");
			getKValueandDistMetrics();
		} else {

			//System.out.println(
					//"Select below distance metric(1 or 2)\n1 Eucildean Distance Metrics\n2 Manhanttan Distance Metrics");
			DistanceMetricsSelction = 1;

		}

	}

	/*
	 * Calculating accuracy for model based Euclidean and Manhattan distance.
	 */
	void accuracy() throws IOException {
		int count = 0;
		File file = null;
		if (DistanceMetricsSelction == 1) {
			//file = new File("EuclideanResult.txt");
		}

		else if (DistanceMetricsSelction == 2) {
			//file = new File("ManhattanResult.txt");

		}

		BufferedReader rf = new BufferedReader(new FileReader(file));
		BufferedReader label = new BufferedReader(new FileReader(new File("RealLabel.txt")));
		String s = rf.readLine();
		while (s != null) {
			String lab = label.readLine();
			if (s.equals(lab)) {

			} else {
				count++;
			}

			s = rf.readLine();
		}

		System.out.println("Accuracy is: " + ((float) 100 - (((float) count / totalNumberOfLabel) * 100)) + "%");
		rf.close();
		label.close();
	}

}
