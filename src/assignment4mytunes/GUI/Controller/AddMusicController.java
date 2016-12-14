/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Music;
import assignment4mytunes.GUI.Model.MusicModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class AddMusicController implements Initializable
{

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

    @FXML
    private Button btnGenre;

    private Music thisTitle;
    private Music thisArtist;
    private Music thisGenre;
    private Music thisTime;
    private Music thisFile;
    private MusicModel musicModel;

    public AddMusicController()
      {

      }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        updateGenres();

      }

    @FXML
    private void AddGenres(ActionEvent event) throws IOException
      {
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
    private void FindFile(ActionEvent event)
      {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Find your music!");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("mp3 Files (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null)
          {
            String path = file.getAbsolutePath();
            path = path.replace("\\", "/");
            txtTitle.setText(file.getName());
            txtPath.setText(path);
            getDuration();
          }
      }

    @FXML
    private void CancelMusic(ActionEvent event)
      {
        Stage stage = (Stage) txtPath.getScene().getWindow();
        stage.close();
      }

    @FXML
    private void Save(ActionEvent event)
      {
        if (!txtArtist.getText().isEmpty() && !txtPath.getText().isEmpty() && !txtTime.getText().isEmpty() && !txtTitle.getText().isEmpty() && !choiceGenre.getSelectionModel().getSelectedItem().isEmpty())
          {
            

            try
              {
                getTextAndAddSong();

              } catch (IOException ex)
              {

              }

          }
        else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You need to fill out all the fields");

            alert.showAndWait();
        }
      }

    public void deleteSong()
      {

        MainViewController mainView = musicModel.getMainView();
        mainView.doDelete();

      }

    public void updateGenres()
      {
        MusicModel musicModel = MusicModel.getMusicModel();
        ObservableList<String> genres = null;
        try
          {
            genres = FXCollections.observableArrayList(musicModel.loadGenre());
          } catch (IOException ex)
          {
            Logger.getLogger(AddMusicController.class.getName()).log(Level.SEVERE, null, ex);
          }
        choiceGenre.setItems(genres);
      }

    public void getDuration()
      {
        File filestring = new File(txtPath.getText());
        Media file = new Media(filestring.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(file);

        mediaPlayer.setOnReady(new Runnable()
        {

            @Override
            public void run()
              {
                int minutes = (int) file.getDuration().toSeconds() / 60;
                int calculation = minutes * 60;
                int seconds = (int) file.getDuration().toSeconds() - calculation;
                txtTime.setText(String.valueOf(minutes + ":" + seconds));
              }
        });
      }

    public void getTextAndAddSong() throws IOException
      {
        String name = txtTitle.getText();
        String artist = txtArtist.getText();
        String genre = choiceGenre.getSelectionModel().getSelectedItem();
        String duration = txtTime.getText();
        String path = txtPath.getText();
        //path = path.replace(" ", "%20");
        path = path.replace("\\", "/");
        MusicModel musicModel = MusicModel.getMusicModel();
        Music song = new Music(name, artist, genre, path, duration);
        musicModel.saveSongs(song);
        MainViewController mainView = musicModel.getMainView();
        mainView.updater();
        /*
        UpdateListModel uList = UpdateListModel.getUpdateList();
        uList.updateMainList();  */
        Stage stage = (Stage) txtPath.getScene().getWindow();
        stage.close();

      }

    public void setTitle(Music title)
      {
        thisTitle = title;
        txtTitle.setText(thisTitle.getTitle());
      }

    public void setArtist(Music artist)
      {
        thisArtist = artist;
        txtArtist.setText(thisArtist.getArtist());
      }

    public void setGenre(Music genre)
      {
        thisGenre = genre;

      }

    public void setTime(Music time)
      {
        thisTime = time;
        txtTime.setText(thisTime.getTime());
      }

    public void setFile(Music file)
      {
        thisFile = file;
        txtPath.setText(thisFile.getPath());
      }

}
