/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem.findcities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public class FileHandler {

    public void readAllWordsWithCapitalLetters(String fileName) {
        FileReader TheFileReader;
        BufferedReader TheBufferedReader;
        String newLine;
        String filename = "C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\BookSample\\" + fileName;
        try {
            TheFileReader = new FileReader(new File(filename));
            TheBufferedReader = new BufferedReader(TheFileReader);
            while ((newLine = TheBufferedReader.readLine()) != null) {

                ArrayList<String> cityArr = new ArrayList();
                String[] words = newLine.split("\\W+");
                for (String w : words) {
                    if (w.matches("([A-Z][a-z']*)(\\\\s[A-Z][a-z']*)*")) {
                        cityArr.add(w);
                    }
                }
                writeFile(cityArr, "SortedByCapitalLetter\\" + fileName);
            }
            TheBufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
        }
    }

    public void readAllCities(String fileName) {
        FileReader TheFileReader;
        BufferedReader TheBufferedReader;
        String newLine;
        String filename = "C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\Test\\" + fileName;
        
        try {
            TheFileReader = new FileReader(new File(filename));
            TheBufferedReader = new BufferedReader(TheFileReader);
            while ((newLine = TheBufferedReader.readLine()) != null) {
                
                ArrayList<String> cityArr = new ArrayList();
                String[] words = newLine.split("\\r?\\n");
                for (String w : words) {
                    if (w.matches("Should match <LOCATION>")) {
                        System.out.println("Matches!");
                        cityArr.add(w);
                    }else
                    {
                        System.out.println("Does not match!");
                    }
                }
                writeFile(cityArr, "Final\\" + fileName);
            }
            TheBufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
        }
        
    }

    public ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> entries = new ArrayList();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                entries.add(fileEntry.getName());
            }
        }
        return entries;
    }

    public void writeFile(ArrayList<String> strArr, String filename) {
        String fileName = "C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\" + filename;
        try (FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bw = new BufferedWriter(fw);) {

            for (String s : strArr) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.toString();
        }
    }
}
