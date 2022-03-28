/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import devit.workit.entites.Categorie;
import devit.workit.entites.Offre;
import devit.workit.services.CategorieCRUD;
import devit.workit.services.OffreCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BackOffreController implements Initializable {

    @FXML
    private Button categorie;
    @FXML
    private TableView<Offre> taffiche;
    @FXML
    private TableColumn<Offre, Integer> tid;
    @FXML
    private TableColumn<Offre, String> tnom;
    @FXML
    private TableColumn<Offre, String> temail;
    @FXML
    private TableColumn<Offre, String> ttitle;
    @FXML
    private TableColumn<Offre, String> tdescription;
    @FXML
    private TableColumn<Offre, Integer> tabn;
    @FXML
    private JFXTextField ttfid;
    private JFXButton btnlike;
    private JFXButton btncomment;
    OffreCRUD offre1 = new OffreCRUD();
    Offre o = new Offre();
    @FXML
    private JFXTextField rech;
        private ObservableList<Offre> RecData = FXCollections.observableArrayList();
    @FXML
    private Button offre12;
    @FXML
    private Button evente;
    @FXML
    private Button user;
    @FXML
    private Button freelancer;
    @FXML
    private Button forum;
    @FXML
    private Button certificat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ttfid.setDisable(true);
             tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        temail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ttitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabn.setCellValueFactory(new PropertyValueFactory<>("abn"));
        List<Offre> listRec= new ArrayList<Offre>();
        OffreCRUD rs =  new OffreCRUD();
        try {
            listRec = rs.ShowOffre();
        } catch (SQLException ex) {
        }
        RecData.clear();
        RecData.addAll(listRec);
        taffiche.setItems(RecData);
        RechercheAV();
        } catch (Exception ex) {
            Logger.getLogger(BackOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }    

 
public void refrech() throws Exception {
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        temail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ttitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabn.setCellValueFactory(new PropertyValueFactory<>("abn"));
        taffiche.getItems().clear();
        taffiche.setItems(offre1.ShowOffre());
     /*  List<Offre> listRec= new ArrayList<Offre>();
        OffreCRUD rs =  new OffreCRUD();
        try {
            listRec = rs.ShowOffre();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RecData.clear();
        RecData.addAll(listRec);
        taffiche.setItems(RecData);*/
    }
    @FXML
    private void checkcategorie(MouseEvent event)throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackCategorie.fxml"));
            Parent root = loader.load();
            BackCategorieController dc = loader.getController();
            categorie.getScene().setRoot(root);
    }


   

    @FXML
    private void affiche(MouseEvent event) {
          Offre res = taffiche.getItems().get(taffiche.getSelectionModel().getSelectedIndex());
            ttfid.setText(String.valueOf(res.getId()));
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, Exception {
          Alert a = new Alert(Alert.AlertType.NONE);
         a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setHeaderText(null);
        a.setContentText("Are you sure to delete this offre");
       // a.showAndWait();
        Optional <ButtonType> action = a.showAndWait();
        if(action.get()== ButtonType.OK)
        {
        OffreCRUD off = new OffreCRUD();
           boolean test = false ;
            test = off.Supprimer(Integer.valueOf(ttfid.getText()));
            if(test)
            {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/offresup.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play(); 
              refrech();
            }
            else
            {
                  File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/erreur.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
            }
        }
    }
  public void RechercheAV(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Offre> filteredData = new FilteredList<>(RecData, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(offre -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (offre.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (offre.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Offre> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(taffiche.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		taffiche.setItems(sortedData);
        //
    }

    @FXML
    private void checkoffre(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackOffre.fxml"));
            Parent root = loader.load();
            BackOffreController dc = loader.getController();
            categorie.getScene().setRoot(root);
    }
    @FXML
    private void checkevente(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackEvent.fxml"));
            Parent root = loader.load();
            BackEventController dc = loader.getController();
            evente.getScene().setRoot(root);
    }

    @FXML
    private void checkuser(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackUser.fxml"));
            Parent root = loader.load();
            BackUserController dc = loader.getController();
            user.getScene().setRoot(root);
    }

    @FXML
    private void checkforum(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("forumBackFXML.fxml"));
            Parent root = loader.load();
            ForumBackFXMLController dc = loader.getController();
            forum.getScene().setRoot(root);
    }

    @FXML
    private void checkcertif(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Certificat.fxml"));
            Parent root = loader.load();
            CertificatController dc = loader.getController();
            certificat.getScene().setRoot(root);
    }

    @FXML
    private void checkfreelancer(MouseEvent event) throws IOException {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Back.fxml"));
            Parent root = loader.load();
            Back dc = loader.getController();
            freelancer.getScene().setRoot(root);
    }
    
}
