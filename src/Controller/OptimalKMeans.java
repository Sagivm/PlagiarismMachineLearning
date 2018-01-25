package Controller;

import java.util.ArrayList;

import Entity.Kmeans;

public class OptimalKMeans {

	public static ArrayList<Integer>Silhouette;
	public int findMaxSilhouette (ArrayList<Double> pai, int Kmax) {
		double[][] data=(double[][]) pai.toArray();
		int max=-1;
		for (int i=2;i<Kmax;i++) {
			Kmeans km=new Kmeans(i,data);
			Silhouette.add((int)km.getAverageSilhouetteValue());
			if(max<km.getAverageSilhouetteValue())
				max=(int) km.getAverageSilhouetteValue();
		}
		 return max;
	}
}
