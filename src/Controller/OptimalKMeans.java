package Controller;

import java.util.ArrayList;

public class OptimalKMeans {

	public int findMaxSilhouette (ArrayList<Double> pai, int Kmax) {
		double[][] data=(double[][]) pai.toArray();
		int max=-1;
		for (int i=2;i<Kmax;i++) {
			KmeansController km=new KmeansController(i,data);
			if(max<km.getAverageSilhouetteValue())
				max=(int) km.getAverageSilhouetteValue();
		}
		 return max;
	}
}
