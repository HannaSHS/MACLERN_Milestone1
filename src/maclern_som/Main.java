/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Codes after initialize SOM input came from:
 * http://www.programcreek.com/java-api-examples/index.php?source_dir=encog-java-examples-master/src/main/java/org/encog/examples/neural/som/SimpleSOM.java
 */
package maclern_som;

import java.util.ArrayList;

import org.encog.Encog;
import org.encog.mathutil.matrices.Matrix;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.basic.BasicTrainSOM;
import org.encog.neural.som.training.basic.neighborhood.NeighborhoodSingle;

/**
 *
 * @author Hanna Sha
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
//        SOM som = new SOM();
//        som.SOM();

        //Read CSV file
        CSVcontroller cc = new CSVcontroller();
        cc.readCSV("E:\\10th_Term\\MACLERN\\MP1\\MACLERN_Milestone1\\TSE Data Normalized and Cleaned.csv");

        //initialize SOM input
        ArrayList<Example> samples = cc.getSample();
        double[][] input = new double[5820][23];

        for (int r = 0; r < input.length; r++) {
            for (int c = 0; c < input.length; c++) {
                switch (c) {
                    case 0:
                        input[r][c] = samples.get(r).getAttendance();
                        break;
                    case 1:
                        input[r][c] = samples.get(r).getDifficulty();
                        break;
                    case 2:
                        input[r][c] = samples.get(r).getQ2();
                        break;
                    case 3:
                        input[r][c] = samples.get(r).getQ3();
                        break;
                    case 4:
                        input[r][c] = samples.get(r).getQ4();
                        break;
                    case 5:
                        input[r][c] = samples.get(r).getQ5();
                        break;
                    case 6:
                        input[r][c] = samples.get(r).getQ6();
                        break;
                    case 7:
                        input[r][c] = samples.get(r).getQ8();
                        break;
                    case 8:
                        input[r][c] = samples.get(r).getQ9();
                        break;
                    case 9:
                        input[r][c] = samples.get(r).getQ11();
                        break;
                    case 10:
                        input[r][c] = samples.get(r).getQ13();
                        break;
                    case 11:
                        input[r][c] = samples.get(r).getQ14();
                        break;
                    case 12:
                        input[r][c] = samples.get(r).getQ15();
                        break;
                    case 13:
                        input[r][c] = samples.get(r).getQ16();
                        break;
                    case 14:
                        input[r][c] = samples.get(r).getQ17();
                        break;
                    case 15:
                        input[r][c] = samples.get(r).getQ18();
                        break;
                    case 16:
                        input[r][c] = samples.get(r).getQ20();
                        break;
                    case 17:
                        input[r][c] = samples.get(r).getQ21();
                        break;
                    case 18:
                        input[r][c] = samples.get(r).getQ22();
                        break;
                    case 19:
                        input[r][c] = samples.get(r).getQ24();
                        break;
                    case 20:
                        input[r][c] = samples.get(r).getQ25();
                        break;
                    case 21:
                        input[r][c] = samples.get(r).getQ27();
                        break;
                    case 22:
                        input[r][c] = samples.get(r).getQ28();
                        break;
                }
            }
        }

        // create the training set 
        MLDataSet training = new BasicMLDataSet(input, null);

        // Create the neural network. 
        SOM network = new SOM(23, 23);
        network.reset();

        BasicTrainSOM train = new BasicTrainSOM(
                network,
                0.7,
                training,
                new NeighborhoodSingle());

        int iteration = 0;

        for (iteration = 0; iteration < 10000; iteration++) {
            train.iteration();
        }

        //get weight of the network
        Matrix m = network.getWeights();
        for (int i = 0; i < m.getRows(); i++) {
            System.out.print("[");
            for (int j = 0; j < m.getCols(); j++) {
                System.out.print(m.get(i, j) + "\t");
            }
            System.out.print("]");
            System.out.println();
        }
        Encog.getInstance().shutdown();
    }
}
