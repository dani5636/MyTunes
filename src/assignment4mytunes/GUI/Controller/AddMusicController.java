/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BLL.GenreManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class AddMusicController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtPath;
    @FXML
    private ChoiceBox<String> choiceGenre;
    
    private GenreManager genreManager = new GenreManager();
    

    public AddMusicController() {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> genres
                = FXCollections.observableArrayList(genreManager.loadGenres());
        choiceGenre.setItems(genres);
        
    }    
    
    @FXML
    private void AddGenres(ActionEvent event) {
       
    }
    
   

    @FXML
    private void FindFile(ActionEvent event) {
    }

    @FXML
    private void Cancel(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) {
    }

    
    
}
