/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.GUI.Model.MusicModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class AddGenreController implements Initializable {

    @FXML
    private TextField txtField;
    private MusicModel musicModel = new MusicModel();
    private boolean isRunning;
    private AddMusicController addMusicController;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        isRunning = true;
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) txtField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addGenre(ActionEvent event) {
        Stage stage = (Stage) txtField.getScene().getWindow();
        ArrayList<String> genres = musicModel.loadGenre();
        genres.add(txtField.getText());
        musicModel.saveGenre(genres);
        isRunning = false;
        addMusicController.updateGenres();
        stage.close();

    }

    public void setAddGenreController(AddMusicController addMusicController) {
        this.addMusicController = addMusicController;
    }

   

}
