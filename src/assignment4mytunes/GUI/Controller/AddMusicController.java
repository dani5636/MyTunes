/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.GUI.Model.MusicModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private MusicModel musicModel = new MusicModel();

    @FXML
    private Button btnGenre;

    public AddMusicController() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateGenres();

    }

    @FXML
    private void AddGenres(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGenre.getScene().getWindow();
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("/assignment4mytunes/GUI/View/AddGenre.fxml"));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));
        AddGenreController gController
                = loader.getController();
        gController.setAddGenreController(this);
        //set modal window
        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(primaryStage);
        subStage.show();
    }

    @FXML
    private void FindFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Find your music!");
        fileChooser.showOpenDialog(null);
    }

    @FXML
    private void Cancel(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) {
    }

    public void updateGenres() {
        ObservableList<String> genres
                = FXCollections.observableArrayList(musicModel.loadGenre());
        choiceGenre.setItems(genres);
    }

}
