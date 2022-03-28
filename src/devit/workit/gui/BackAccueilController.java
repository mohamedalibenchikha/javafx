/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BackAccueilController implements Initializable {

    @FXML
    private Button categorie;
    @FXML
    private Button offre;
    @FXML
    private Button evente;
    @FXML
    private Button cat;
    @FXML
    private Button user;
    @FXML
    private Button forum;
    @FXML
    private Button test;
    @FXML
    private Button certificat;
    @FXML
    private Button freelancer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void checkcategorie(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackCategorie.fxml"));
            Parent root = loader.load();
            BackCategorieController dc = loader.getController();
            categorie.getScene().setRoot(root);
    }


    @FXML
    private void checkoffre(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackOffre.fxml"));
            Parent root = loader.load();
            BackOffreController dc = loader.getController();
            offre.getScene().setRoot(root);
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
    private void checkcat(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Categorie3.fxml"));
            Parent root = loader.load();
            Categorie3Controller dc = loader.getController();
            cat.getScene().setRoot(root);
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
    private void checktest(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("TestDocument.fxml"));
            Parent root = loader.load();
            TestDocumentController dc = loader.getController();
            test.getScene().setRoot(root);
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
