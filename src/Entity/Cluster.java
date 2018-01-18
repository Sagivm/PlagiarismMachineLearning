package Entity;

import java.util.ArrayList;

import org.apache.commons.math3.ml.distance.EuclideanDistance;


public class Cluster {

	private ArrayList<Integer> indices;

	private double[] centroid;

	private EuclideanDistance distanceFunction;

	public Cluster() {
		indices = new ArrayList<Integer>();
		// defaulting to Euclidean distance.
		distanceFunction = new EuclideanDistance();
	}

	public Cluster(double[] centroid) {
		this();
		this.centroid = centroid;
	}

	public Cluster(double[] centroid, EuclideanDistance distanceFunction) {
		this(centroid);
		this.distanceFunction = distanceFunction;
	}

	public ArrayList<Integer> getIndices() {
		return indices;
	}

	public void calculateCentroid(double[][] data) {
        // for all instances in this cluster
		for (int instance = 0; instance < indices.size(); instance++) {
            // for all attributes in an instance | from 0 to the length of the given instance in the data set.
			for (int attributeIndex = 0; attributeIndex < data[indices.get(instance)].length; attributeIndex++) {

                // if it's the first instance set the attribute value to 0
                if (instance == 0) {
					centroid[attributeIndex] = 0;
				}

                // increase the cluster attribute with (the attribute value of the current instance divided by the number of indices in this cluster)
				centroid[attributeIndex] += data[indices.get(instance)][attributeIndex] / indices.size();
			}
		}
	}

	// Removes the instance according to the given index. 
	public void removeInstanceIndex(int instanceIndex) {
		indices.remove(new Integer(instanceIndex));
	}

	public void addIndex(int index) {
		indices.add(new Integer(index));
	}

	public void setCentroid(double[] centroid) {
		this.centroid = centroid;
	}

	public double[] getCentroid() {
		return centroid.clone();
	}

	public int getNumberOfInstances() {
		return indices.size();
	}

	public double[][] getInstances(double[][] data) {
		double[][] instances = new double[indices.size()][data[0].length];
		for (int i = 0; i < indices.size(); i++) {
			instances[i] = data[indices.get(i)];
		}
		return instances;
	}

	public double SSE(double[][] data) {
		double sse = 0d;

        // for all instances
        for(int i = 0; i < this.indices.size(); i++){
       
            // from lecture slides
                // sum of (distance from m(i) to x )^2
            // increment get the distance from centroid to given instance.
            sse += Math.pow( distanceFunction.compute(this.centroid, data[this.indices.get(i)]), 2);
        }

		return sse;
	}

	public double SSB(double[] globalCentroid) {
        // from lecture slide
        // number if instances * (distance from local centroid to global centroid)^2
		return this.getIndices().size() * Math.pow( getDistanceToCentroid(globalCentroid), 2 );
	}

	public double averageSSE(double[][] data) {
		return SSE(data) / this.getNumberOfInstances();
	}

	public double getDistanceToCentroid(double[] instance) {
		return distanceFunction.compute(centroid, instance);
	}

	
	public int getInstanceIndexWithMaxSSE(double[][] data) {
		int index = -1;
		double maxSSE = Double.NEGATIVE_INFINITY;
		double currentSSE = 0;

		for (int i = 0; i < indices.size(); i++) {
			currentSSE = distanceFunction.compute(data[indices.get(i)], centroid);

			if (currentSSE > maxSSE) {
				maxSSE = currentSSE;
				index = indices.get(i);
			}

		}
		return index;
	}

    // returns the average distance from all instances in this cluster to the given instance(index)
	public double getAverageDistanceOfPointToAllPoints(double[][] data, int index) {
		double sumDistance = 0d;
		// this one we compare to all others
		double[] vector = data[index];
		for (int i = 0; i < indices.size(); i++) {
			double distance = distanceFunction.compute(data[indices.get(i)], vector);
			sumDistance += distance;
		}
		// return the average
		return sumDistance / (indices.size() - 1);
	}

    // gets the average distance from point(index) to all other points in this cluster.
	public double getAverageDistanceOfPoint(double[][] data, int index) {
		double sumDistance = 0d;
		// this one we compare to all others
		double[] vector = data[index];
        // for all instances
		for (int i = 0; i < indices.size(); i++) {
            if ( i != index ){
                // increment the distance
				double distance = distanceFunction.compute(data[indices.get(i)], vector);
				sumDistance += distance;
            }
		}
		// return the average
		return sumDistance / (indices.size() - 1);
	}
}