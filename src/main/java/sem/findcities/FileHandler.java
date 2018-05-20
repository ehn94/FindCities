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

    /*
        Læser filerne igennem og splitter hver gang der er et location tag.
        Kalder getTagValues()
    */
    public void readAllCities(String fileName) {
        FileReader TheFileReader;
        BufferedReader TheBufferedReader;
        String newLine;
        String filename = "C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\stanford-ner-2018-02-27\\Output\\" + fileName;

        try {
            TheFileReader = new FileReader(new File(filename));
            TheBufferedReader = new BufferedReader(TheFileReader);
            while ((newLine = TheBufferedReader.readLine()) != null) {
                
                ArrayList<String> cityArr = new ArrayList();
                String[] words = newLine.split("(?=<LOCATION>)|(?<=</LOCATION>)");
                
                String val;
                for (String w : words) {
                    Matcher m = TAG_PATTERN.matcher(w);
                    if (m.matches()) {
                        val = getTagValues(w);
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
    /*
        Trækker tekst værdien ud fra location tags
    */
    private static String getTagValues(final String str) {
        final String tagValues;
        final Matcher matcher = TAG_PATTERN.matcher(str);
        matcher.find();
        tagValues = matcher.group(1);

        return tagValues;
    }
    /*
        Skriver filen med de færdige resultater. 
    */
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
