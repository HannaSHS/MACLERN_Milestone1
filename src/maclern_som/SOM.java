/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maclern_som;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

/**
 *
 * @author Hanna Sha
 */
public class SOM {

    //Delimiter used in CSV file
    final String COMMA_DELIMITER = ",";
    final String NEW_LINE_SEPARATOR = "\n";

    int mapSizeX = 5;
    int mapSizeY = 5;
    //double[] learningRate = new double[10];
    double learningRate = 0.9;
    double initialLearningRate = 0.9;
    double finalLearningRate = 0.1;
    int radius;
    int iterations = 10;
    double nearestNode = 0.0;
    int nearestNodeIndexX = 0;
    int nearestNodeIndexY = 0;
    int x, y;
    double sampleData = 0.0;
    double currAbs = 0.0;
    double abs = 0.0;

    public void run() {
        //double[][] map = new double[mapSizeX][mapSizeY];
        double[][] map = {{0.381, 0.683, 0.582, 0.327, 0.51},
        {0.393, 0.992, 0.422, 0.303, 0.991},
        {0.485, 0.642, 0.945, 0.435, 0.243},
        {0.766, 0.06, 0.676, 0.475, 0.43},
        {0.367, 0.612, 0.021, 0.937, 0.713}};

        double[] sample = {0.418, 0.999, 0.661, 0.594, 0.02,
            0.866, 0.092, 0.29, 0.123, 0.361,
            0.174, 0.255, 0.557, 0.774, 0.768,
            0.192, 0.571, 0.217, 0.425, 0.732};

        int[] sampleOrder = {18, 11, 5, 12, 2,
            20, 14, 16, 6, 10,
            7, 15, 19, 1, 8,
            9, 3, 17, 13, 4};

        /*for(int i = 0; i< 10; i++){
         for(int n = 0; n<10;n++){
         Random rand = new Random();

         double ret = rand.nextInt(1000);
         ret = (ret / 1000.0);
                
         //double roundOff = (double) Math.round(ret * 1000) / 1000;
         map[i][n] = ret;

         System.out.print(map[i][n] + ", ");
         }
         System.out.println("");
         }*/
        /*for(int i = 0; i< 20; i++){
         Random rand = new Random();

         double ret = rand.nextInt(1000);
         ret = (ret / 1000.0);
         sample[i] = ret;
            
         System.out.print(sample[i] + ", ");
         }*/
        for (int iteration = 0; iteration < iterations; iteration++) {
            if (iteration > 0 && iteration % 2 == 0) {
                radius--;
            }
            if (iteration == iterations - 1) {
                radius = 0;
            }

  //System.out.println(computeRadius(iteration));
            for (int n = 0; n < 20; n++) {
                // determine BMU
                sampleData = sample[sampleOrder[n] - 1];
                for (int i = 0; i < 5; i++) {
                    for (int m = 0; m < 5; m++) {
                        abs = Math.abs(sampleData - map[i][m]);
                        if (i == 0 && m == 0) {
                            currAbs = abs;
                            nearestNode = map[i][m];
                        } else if (abs < currAbs) {
                            currAbs = abs;
                            nearestNode = map[i][m];
                            nearestNodeIndexX = i;
                            nearestNodeIndexY = m;
                        }
                    }
                }

                System.out.println("sampleData: " + sampleData);
                System.out.println("nearestNode: " + nearestNode);

                //Update current node and neighborhood
                map[nearestNodeIndexX][nearestNodeIndexY] = map[nearestNodeIndexX][nearestNodeIndexY] + learningRate * (sampleData - map[nearestNodeIndexX][nearestNodeIndexY]);

                //upper left
                x = nearestNodeIndexX - radius;
                y = nearestNodeIndexY - radius;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //above
                x = nearestNodeIndexX;
                y = nearestNodeIndexY - radius;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //left
                x = nearestNodeIndexX - radius;
                y = nearestNodeIndexY;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //upper right
                x = nearestNodeIndexX + radius;
                y = nearestNodeIndexY - radius;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //right
                x = nearestNodeIndexX + radius;
                y = nearestNodeIndexY;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //lower right
                x = nearestNodeIndexX + radius;
                y = nearestNodeIndexY + radius;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //bottom
                x = nearestNodeIndexX;
                y = nearestNodeIndexY + radius;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }
                //lower left
                x = nearestNodeIndexX - radius;
                y = nearestNodeIndexY + radius;
                if (x >= 0 && x <= map.length - 1) {
                    if (y >= 0 && y <= map.length - 1) {
                        double d = map[x][y] + learningRate * (sampleData - map[x][y]);
                        map[x][y] = roundOff(d);
                    }
                }

                //Decrement learning rate
                double dec;
                dec = initialLearningRate - finalLearningRate / iterations - 1;
                learningRate = learningRate - dec;

                /*for(int i = 0; i< 5; i++){
                 for(int m = 0; m<5;m++){
                 System.out.print("[" + map[i][m] + "] ");
                 }
                 System.out.println("");
                 }*/
            }

            System.out.println("Iteration " + iteration);
            System.out.println("Radius " + radius + "\n");

            for (int i = 0; i < 5; i++) {
                for (int n = 0; n < 5; n++) {
                    System.out.print("[" + map[i][n] + "] ");
                }
                System.out.println("");
            }
        }
    }

    /*public double computeRadius(int iteration){
     double mapRadius = mapSizeX/2;
     double timeConstant = iterations/log(mapRadius);
     double neighbourhoodRadius = mapRadius * exp(-(double)iteration/timeConstant);
     return neighbourhoodRadius;
     }*/
    private double roundOff(double d) {
        /*BigDecimal bd = new BigDecimal(d);
         bd = bd.round(new MathContext(4));
         double rounded = bd.doubleValue();
         return rounded;*/

        double roundOff = (double) Math.round(d * 1000) / 1000;
        return roundOff;
    }
    /*    
     private static void WriteCSV(){
     FileWriter fileWriter = null;	
     try {
     fileWriter = new FileWriter(fileName);

     //Write the CSV file header
     //fileWriter.append(FILE_HEADER.toString());
			
     //Add a new line separator after the header
     //fileWriter.append(NEW_LINE_SEPARATOR);
			
     //Write a new student object list to the CSV file
     for (Student student : students) {
     fileWriter.append(String.valueOf(student.getId()));
     fileWriter.append(COMMA_DELIMITER);
     }

     System.out.println("CSV file was created successfully !!!");
			
     } catch (Exception e) {
     System.out.println("Error in CsvFileWriter !!!");
     e.printStackTrace();
     } finally {
			
     try {
     fileWriter.flush();
     fileWriter.close();
     } catch (IOException e) {
     System.out.println("Error while flushing/closing fileWriter !!!");
     e.printStackTrace();
     }
			
     }
     }
     */
}
