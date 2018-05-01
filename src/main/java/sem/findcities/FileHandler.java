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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ehn19
 */
public class FileHandler {

    private static final String TAG_REGEX = "<LOCATION>(.*?)</LOCATION>";
    private static final Pattern TAG_PATTERN = Pattern.compile(TAG_REGEX);

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
                writeCapitalFile(cityArr, "SortedByCapitalLetter\\" + fileName);
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
        String filename = "C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\TaggedFiles\\" + fileName;

        try {
            TheFileReader = new FileReader(new File(filename));
            TheBufferedReader = new BufferedReader(TheFileReader);
            while ((newLine = TheBufferedReader.readLine()) != null) {

                ArrayList<String> cityArr = new ArrayList();
                String[] words = newLine.split(",");
                String val;
                for (String w : words) {
                    Matcher m = TAG_PATTERN.matcher(w);
                    if (m.matches()) {
                        val = getTagValues(w);
                        System.out.println(val);
                        cityArr.add(val);
                    }
                }
                writeFinalFile(cityArr, "Final\\" + fileName);
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

    private static String getTagValues(final String str) {
        final String tagValues;
        final Matcher matcher = TAG_PATTERN.matcher(str);
        matcher.find();
        tagValues = matcher.group(1);

        return tagValues;
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

    public void writeCapitalFile(ArrayList<String> strArr, String filename) {
        String fileName = "C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\" + filename;
        try (FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bw = new BufferedWriter(fw);) {

            for (String s : strArr) {
                bw.write(s + ",");
            }

        } catch (IOException ex) {
            ex.toString();
        }
    }

    public void writeFinalFile(ArrayList<String> strArr, String filename) {
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
