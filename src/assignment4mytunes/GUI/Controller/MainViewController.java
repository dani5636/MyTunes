/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Music;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainViewController implements Initializable {

    @FXML
    private Button btnPrevSong;
    @FXML
    private ToggleButton btnPlaySong;
    @FXML
    private Button btnNextSong;
    @FXML
    private Slider sliderVolume;
    @FXML
    private ListView<?> listViewPlaylists;
    @FXML
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
    @FXML
    private Button btnDeletePlaylist;
    @FXML
    private Label lblCurrentSong;
    @FXML
    private Button btnMoveUpSong;
    @FXML
    private Button btnMoveDownSong;
    @FXML
    private Button btnDeleteSong;
    @FXML
    private TextField textFieldFilter;
    @FXML
    private Button btnFilterSearch;
    @FXML
    private Button btnNewSong;
    @FXML
    private Button btnEditSong;
    @FXML
    private Button btnCloseProgram;
    @FXML
    private Button btnAddSongsToPlaylist;
    @FXML
    private TableView<?> tableViewSongsOnPlaylist;
    @FXML
    private TableColumn<?, ?> tableViewSongsOnPlaylistArtist;
    @FXML
    private TableColumn<?, ?> tableViewSongsOnPlaylistSongName;
    @FXML
    private TableView<Music> tableViewAllSongs;
    @FXML
    private TableColumn<Music, String> tableViewAllSongsArtist;
    @FXML
    private TableColumn<Music, String> tableViewAllSongsSongName;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
   ObservableList<String> songs = FXCollections.observableArrayList(
    "acdc",
    "Justin Bieber"
    );
    
    }    
    
    
    @FXML
    private void newMusic(ActionEvent event)
    {
        try   
        {
            windowloader("/assignment4mytunes/GUI/View/AddMusic.fxml");
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void newPlaylist(ActionEvent event)
    {
        try   
        {
            windowloader("/assignment4mytunes/GUI/View/AddPlaylist.fxml");
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void windowloader(String p)  throws IOException{
     Stage primaryStage = (Stage)btnNewSong.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(p));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));
        
        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(primaryStage);
        
        subStage.show();
    }
    
    
    
}
