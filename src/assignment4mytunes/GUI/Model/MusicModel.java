/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Model;

import assignment4mytunes.BLL.GenreManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mecaa
 */
public class MusicModel {  
    private GenreManager genreManager = new GenreManager();
    public ArrayList<String> loadGenre() throws IOException
    {
        return (ArrayList<String>) genreManager.loadGenres();
    }
    
    public void saveGenre(List<String> genres) throws IOException
    {
        genreManager.saveGenres(genres);
    }
}
