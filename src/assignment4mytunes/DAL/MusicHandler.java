/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.DAL;

import assignment4mytunes.BE.Music;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mecaa
 */
public class MusicHandler {

    public void saveMusic(Music song) {
        String mscString = "";
        String fileName = song.getTitle() + ".sng";
        File file = new File("DATA/Songs/" + fileName);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PlaylistHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        mscString += song.getTitle()
                + ","
                + song.getArtist()
                + ","
                + song.getGenre()
                + ","
                + song.getPath()
                + ","
                + song.getTime();

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("DATA/Songs/" + fileName
                )
        )) {
            bw.write(mscString);

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Music> loadAllMusic() throws IOException {
        List<Music> allSongs = new ArrayList<>();
        Path dir = Paths.get("DATA/Songs/");
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(dir, "*.sng")) {
            for (Path entry : stream) {
                try (BufferedReader CSVFile
                        = new BufferedReader(new FileReader("DATA/Songs/" + entry.getFileName()))) {
                    String dataLine = CSVFile.readLine();
                    String[] splitData = dataLine.split(",");

                    String title = splitData[0];
                    String artist = splitData[1];
                    String genre = splitData[2];
                    String path = splitData[3];
                    String time = splitData[4];

                    Music music = new Music(title, artist, genre, path, time);
                    allSongs.add(music);

                }

            }

        }
        return allSongs;
    }
    
    public void removeSong(Music song)
      {
        String fileName = song.getTitle() + ".sng";
        File file = new File("DATA/Songs/" + fileName);
        if (file.exists()) {
            file.delete();
        }
      }
    
   
       

}
