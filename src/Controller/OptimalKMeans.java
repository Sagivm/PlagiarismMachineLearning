package Controller;

import java.util.ArrayList;

import Entity.Kmeans;

public class OptimalKMeans {

	public static ArrayList<Integer>Silhouette;
	public int[] findMaxSilhouette (ArrayList<ArrayList<Double>> pai, int Kmax) {
		//double[][] data=(double[][]) pai.toArray();
		double [][]data = new double [pai.size()][pai.get(0).size()] ;
		for(int i=0;i<pai.size();i++)
		{
			for(int j=0;j<pai.get(0).size();j++)
			{
				data[i][j]=pai.get(i).get(j).doubleValue();
			}
		}
		int[] max= new int[2];
		max[0]=-1;
		max[1]=-1;
		for (int i=2;i<Kmax;i++) {
			Kmeans km=new Kmeans(i,data);
			Silhouette.add((int)km.getAverageSilhouetteValue());
			if(km.getAverageSilhouetteValue()>max[0]) {
				max[0]=(int)km.getAverageSilhouetteValue();
				max[1]=i;
			}
		}
		 return max; 
	}
}