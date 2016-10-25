/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maclern_som;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hanna Sha
 */
public class CSVcontroller {

    private Example[][] input;
    private ArrayList<Example> examples;

    public CSVcontroller() {
        this.input = new Example[5820][23];
        examples = new ArrayList<>();
    }

    public void readCSV(String filePath) {
        BufferedReader br = null;
        try {
            String delimiter = ",";
            int line = 0;
            br = new BufferedReader(new FileReader(filePath));
            String header = br.readLine();

            String lineText = "";
            while ((lineText = br.readLine()) != null) {
                String[] fxRatesAsString = lineText.split(delimiter);
                Example example = new Example();

                for (int i = 0; i < fxRatesAsString.length; i++) {
                    switch (i) {
                        case 0:
                            example.setAttendance(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 1:
                            example.setDifficulty(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 2:
                            example.setQ2(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 3:
                            example.setQ3(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 4:
                            example.setQ4(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 5:
                            example.setQ5(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 6:
                            example.setQ6(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 7:
                            example.setQ8(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 8:
                            example.setQ9(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 9:
                            example.setQ11(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 10:
                            example.setQ13(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 11:
                            example.setQ14(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 12:
                            example.setQ15(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 13:
                            example.setQ16(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 14:
                            example.setQ17(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 15:
                            example.setQ18(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 16:
                            example.setQ20(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 17:
                            example.setQ21(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 18:
                            example.setQ22(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 19:
                            example.setQ24(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 20:
                            example.setQ25(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 21:
                            example.setQ27(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 22:
                            example.setQ28(Double.parseDouble(fxRatesAsString[i]));
                            break;
                        case 23:
                            example.setInstr(Double.parseDouble(fxRatesAsString[i]));
                            break;
                    }
                }
                examples.add(example);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVcontroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSVcontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(CSVcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Example> getSample() {
        return this.examples;
    }

    public void writeCSV(double [][] weights) {
        //Delimiter used in CSV file
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("Nodes.csv");
            
            fileWriter.append("Node, Weight");
            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            int i = 1;
            //Write to the CSV file
            for(int r = 0; r < weights.length; r++) {
                for(int c = 0; c < weights.length; c++) {
                    fileWriter.append("Node " + i + ":");
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(weights[r][c]));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    i++;
                }
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
}
