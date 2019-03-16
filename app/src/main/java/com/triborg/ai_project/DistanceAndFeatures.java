package com.triborg.ai_project;

/*
 * This class is for storing distance value and corresponding Label for all features.   
 */
@SuppressWarnings("rawtypes")
public class DistanceAndFeatures implements Comparable{
	double dist;
	
	public double getDist() {
		return dist;
	}
	public String getLabel() {
		return label;
	}
	String label;
	
	public DistanceAndFeatures()
	{
		
	}
	public DistanceAndFeatures(double dist, String label)
	{
		this.dist=dist;
		this.label=label;
		
	}
	
//Overriding to string method to show distance and label together separated by ;
	
	public String toString()
		{
		return dist+";"+label;
	}
	
//implementing compareTO method for customized sorting  based on distance. 	
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		double distance1=this.dist;
		DistanceAndFeatures df=(DistanceAndFeatures)obj;
		double distance2=df.dist;
		 if(distance1<distance2)
			 return -1;
		 else if(distance1>distance2)
			 return 1;
		 else
			 return -1;
		
		
	}

}
