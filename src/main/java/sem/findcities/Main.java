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
        ArrayList<String> entries = new ArrayList();
        
        String toCapitalize = "BookSample";
        String toLocationTag = "TaggedFiles";
        
        final File folder = new File("C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\" + toLocationTag);
        entries = fh.listFilesForFolder(folder);
        for(String s : entries)
        {
           fh.readAllCities(s);
//            fh.readAllWordsWithCapitalLetters(s);
        }
    }
}
