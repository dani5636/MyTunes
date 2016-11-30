/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.DAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mecaa
 */
public class GenreHandler {

    public List<String> loadGenres() throws IOException {
        ArrayList<String> genres = new ArrayList();

        try (BufferedReader CSVFile
                = new BufferedReader(new FileReader("DATA/genres.txt"))) {

            String dataLine = CSVFile.readLine();
            while (dataLine != null) { //process all lines
                genres.add(dataLine);

                dataLine = CSVFile.readLine();
            }
            return genres;
            //read the file
        }
    }

    //saves a txt file contained all the genres
    public void saveGenres(List<String> genres) throws IOException {
        String csvString = "";
        for (String genre : genres) {

            csvString += genre
                    + String.format("%n");

        }
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("DATA/genres.txt")
        )) {
            bw.write(csvString);

        }
    }
}
