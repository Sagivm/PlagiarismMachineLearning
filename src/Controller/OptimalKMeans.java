package Controller;

import java.util.ArrayList;

import Entity.Kmeans;

public class OptimalKMeans {

	public static ArrayList<Integer>Silhouette;
	
	public ArrayList<Integer> findMaxSilhouette (ArrayList<ArrayList<Double>> pai, int Kmax) {
		double[][] data=(double[][]) pai.toArray();
		for (int i=2;i<Kmax;i++) {
			Kmeans km=new Kmeans(i,data);
			Silhouette.add((int)km.getAverageSilhouetteValue());
		}
		 return Silhouette;
	}
}
