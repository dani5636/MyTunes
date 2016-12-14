/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.DAL;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author gudla
 */
public class PlaylistHandler {

    private String fileName;

    public PlaylistHandler() {

    }

    public void savePlaylist(Playlist p) {
        fileName = p.getName() + ".pll";
        File file = new File("DATA/Playlist/" + fileName);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PlaylistHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        savePlaylistMethod(p, file);

    }

    public void savePlaylistMethod(Playlist p, File file) {
        ArrayList<Music> songs = p.getPlaylist();

        String plstString = "";
        plstString += p.getName() + String.format("%n");;
        if (songs != null) {
            for (Music song : songs) {
                plstString += song.getTitle()
                        + ","
                        + song.getArtist()
                        + ","
                        + song.getGenre()
                        + ","
                        + song.getPath()
                        + ","
                        + song.getTime()
                        + String.format("%n");
            }
        }

        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(file.getAbsoluteFile()))) {
            bw.write(plstString);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PlaylistHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Playlist> loadPlaylists() throws IOException {
        File dir1 = new File("DATA/Playlist");
        if (!dir1.exists()) {
            dir1.mkdir();
        }
        List<Playlist> playlists = new ArrayList<>();
        Path dir = Paths.get("DATA/Playlist/");
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(dir, "*.pll")) {
            for (Path entry : stream) {
                try (BufferedReader plFile
                        = new BufferedReader(new FileReader("DATA/Playlist/" + entry.getFileName()))) {
                    String dataLine = plFile.readLine();
                    Playlist playlist = new Playlist(dataLine);
                    dataLine = plFile.readLine();
                    while (dataLine != null) {
                        String[] splitData = dataLine.split(",");
                        String title = splitData[0];
                        String artist = splitData[1];
                        String genre = splitData[2];
                        String path = splitData[3];
                        String time = splitData[4];
                        Music music = new Music(title, artist, genre, path, time);
                        playlist.addSong(music);
                        dataLine = plFile.readLine();
                    }

                    playlists.add(playlist);

                }

            }

        }
        return playlists;
    }

    public void removePlaylist(Playlist playlist) {
        String fileName = playlist.getName() + ".pll";
        File file = new File("DATA/Playlist/" + fileName);
        if (file.exists()) {
            file.delete();
            
        }
      }
    
    public void renamePlaylist (Playlist playlist)
      {
        String fileName = playlist.getName() + ".pll";
        File file = new File("DATA/Playlist/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PlaylistHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
      }
}
