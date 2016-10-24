/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.ArrayList;
import java.util.List;
import org.encog.mathutil.matrices.Matrix;

/**
 *
 * @author Jasmin
 */
public class Algorithm {

    private double[][] input;
    private double[] centers;
    private ArrayList<List<Double>> clusterList;
    private final int numClusters;

    public Algorithm(double[][] input, int k) {
        this.numClusters = k;
        this.clusterList = new ArrayList();
        this.input = input;
    }

    public void execute() {
        generateCenters();
        
        for(int a = 0; a < numClusters; a++) {
            List<Double> cluster = new ArrayList();
            clusterList.add(cluster);
        }
        
        boolean z = true;

        while (z) {
            for(int i = 0; i < input.length; i++) {
                for(int j = 0; j < input.length; j++) {
                    double weight = input[i][j];
                    
                    //find the center such that it is the center closest to weight
                    double distance = Math.abs(centers[0] - weight);
                    int index = 0;
                    for(int k = 1; k < centers.length; k++) {
                        double cdistance = Math.abs(centers[k] - weight);
                        if(cdistance < distance) {
                            index = k;
                            distance = cdistance;
                        }
                    }
                    clusterList.get(index).add(weight);
                }
            }

            if (recalculateCenters()) {
                z = false;
            }
        }
        
        for(int a = 0; a < clusterList.size(); a++) {
            System.out.print("Cluster " + a + ": \t\t");
            for(int b = 0; b < clusterList.get(a).size(); b++) {
                System.out.print(clusterList.get(a).get(b) + "\t");
            }
            System.out.println();
        }
    }

    public void generateCenters() {
        double min = 0, max = 0;
        double interval;

        for (double[] input1 : input) {
            for (int j = 0; j < input.length - 1; j++) {
                min = Math.min(input1[j], input1[j + 1]);
                max = Math.max(input1[j], input1[j + 1]);
            }
        }

        centers = new double[numClusters];	//initialize centers array

        interval = ((double) (max - min)) / numClusters;

        centers[0] = (min + (interval / 2));	//make this as the first center

        for (int j = 1; j < centers.length; j++) {
            centers[j] = centers[j - 1] + interval;
        }
    }

    public boolean recalculateCenters() {
        boolean flag = true;

        for (int i = 0; i < clusterList.size(); i++) {
            double sum = 0;
            double average = 0;
            
            for(int r = 0; r < input.length; r++) {
                for(int c = 0; c < input.length; c++) {
                    sum += input[r][c];
                }
            }
            
            average = sum / ((double) clusterList.get(i).size());
            
            if(average != centers[i] && average == Double.NaN) {
                centers[i] = average;
                flag = false;
            }
        }
        return flag;
    }
}
