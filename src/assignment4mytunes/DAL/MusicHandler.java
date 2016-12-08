/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.DAL;

import assignment4mytunes.BE.Music;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Mecaa
 */
public class MusicHandler {

    public void saveMusic(Music song) {

    }

    public ArrayList<Music> loadAllMusic() {
        Path dir = Paths.get("DATA/Songs");
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(dir, "*.song")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
            return null;
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
        return null;
    }
}
