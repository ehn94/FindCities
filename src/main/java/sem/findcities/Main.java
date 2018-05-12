/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem.findcities;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public class Main {
    public static void main(String[] args) {
        FileHandler fh = new FileHandler();
        FindCapitalLetters fc = new FindCapitalLetters();
        ArrayList<String> entries = new ArrayList();
        
        String toCapitalize = "BookSample";
        String toLocationTag = "TaggedFiles";
        
//        final File folder = new File("C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\" + toLocationTag);
        final File taggedFolder = new File("C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\stanford-ner-2018-02-27\\Output");
        entries = fh.listFilesForFolder(taggedFolder);
        for(String s : entries)
        {
//            fc.readAllWordsWithCapitalLetters(s);
           fh.readAllCities(s);
//            fh.readAllWordsWithCapitalLetters(s);
        }
    }
}
