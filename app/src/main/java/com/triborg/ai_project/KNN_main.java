package com.triborg.ai_project;

import java.io.IOException;
import java.util.Scanner;

public class KNN_main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		String temp;
		//System.out.println("Welcome, This program is implementation of KNN algorthim for Dataset");
		//Scanner sc=new Scanner(System.in);
		KNN_Implementation trn_ds=new KNN_Implementation();
		//System.out.println("Enter training dataset file name");
		//String trainfilename=sc.nextLine();
		//System.out.println("Enter test dataset file name");
	
		trn_ds.getKValueandDistMetrics();
		  
		double[] test = new double[10];
		String label = "Hello";
		test[0] = 0;
		// for testing
				test[1]=3;
				test[2]=1.33E+12;
				test[3]=0;
				test[4]=1;
				test[5]=272;
				test[6]=269;
				test[7]=0.21;
				test[8]=0.04444445;
				test[9]=0;
		trn_ds.loadtrainData("data1.csv");
		trn_ds.loadtestData(test , label);
		
		  
		  temp=trn_ds.distanceCalcualte();
		 
		  System.out.println(temp);
		 

	}

}
