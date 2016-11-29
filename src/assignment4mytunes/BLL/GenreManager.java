/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.BLL;

import assignment4mytunes.DAL.GenreHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mecaa
 */
public class GenreManager {
    private final GenreHandler genreHandler = new GenreHandler();
    public ArrayList<String> loadGenres()
    {
        return (ArrayList<String>) genreHandler.loadGenres();
    }
    public void saveGenres(List<String> genres)
    {
        genreHandler.saveGenres(genres);
    }
}
