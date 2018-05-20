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
public class FolderHandler {

    /*
        Nogle af filerne lå inde i seperate foldere. Denne metode hiver filerne
        ud fra disse og placerer dem i en ny mappe. 
    */
    public void findChildFolder() {
        File dir = new File("C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\TempBookSample");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                for (File c : child.listFiles()) {
                    c.renameTo(new File("C:\\Users\\ehn19\\Documents\\Skole\\Softwareudvikling\\Databaser\\Books\\ChildFiles\\" + c.getName()));
                }
            }
        }

    }

    /*
        Bruges i Main, løber filnavnene igennem i den valgte folder. 
     */
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
}
