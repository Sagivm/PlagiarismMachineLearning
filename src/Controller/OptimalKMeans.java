package Controller;

import java.util.ArrayList;

import Entity.Kmeans;

public class OptimalKMeans {

	public static ArrayList<Integer>Silhouette;
	public ArrayList<Integer> findMaxSilhouette (ArrayList<ArrayList<Double>> pai, int Kmax) {
		//double[][] data=(double[][]) pai.toArray();
		double [][]data = new double [pai.size()][pai.get(0).size()] ;
		for(int i=0;i<pai.size();i++)
		{
			for(int j=0;j<pai.get(0).size();j++)
			{
				data[i][j]=pai.get(i).get(j).doubleValue();
			}
		}
		int max=-1;
		for (int i=2;i<Kmax;i++) {
			Kmeans km=new Kmeans(i,data);
			Silhouette.add((int)km.getAverageSilhouetteValue());
		}
		 return Silhouette;
	}
}
