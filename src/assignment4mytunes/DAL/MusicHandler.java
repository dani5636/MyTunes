/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.DAL;

import assignment4mytunes.BE.Music;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        File file = new File("DATA/Playlist/" + fileName);
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
                + song.getPath();

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName)
        )) {
            bw.write(mscString);

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Music> loadAllMusic() {
        ArrayList<Music> songs = null;
        Path dir = Paths.get("DATA/Songs");
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(dir, "*.song")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
            return songs;
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
        return songs;
    }
}
