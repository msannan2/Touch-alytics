package com.triborg.ai_project;

/*
 * This class is for calculating  Euclidean and Manhattan distance. 
 *   */

public class KNN_Distance {
	
	public double getEuclideanDistance( double[] features1,  double[] features2) {
        double sum = 0;
        int q = 6 ;
        for (int i = 0; i < features1.length; i++)
        {  //System.out.println(features1[i]+" "+features2[i]);
        	//applied Euclidean distance formula
            sum += Math.pow(features1[i] - features2[i], q );
        }return Math.pow(sum, 1.0 / q );
    }
	
	public double getManhattanDistance(final double[] features1, final double[] features2) {
        double sum = 0;
        for (int i = 0; i < features1.length; i++)
        	//Applied Manhattan distance formula
            sum += Math.abs(features1[i] - features2[i]);
        return sum;
    }


}
