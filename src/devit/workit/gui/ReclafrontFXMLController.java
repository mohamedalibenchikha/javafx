/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Reclamation;
import devit.workit.entites.Recruteur;
import devit.workit.entites.SessionWorkit;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.ForumCRUD;
import devit.workit.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Wael Belhadj
 */
public class ReclafrontFXMLController implements Initializable {

    @FXML
    private Pane profilepan;
    @FXML
    private Hyperlink logouthl;
    @FXML
    private TextField fname;
    @FXML
    private TextArea descf;
    @FXML
    private Button addbtn;
    @FXML
    private Button userbtn;
    @FXML
    private Button recfr;
    @FXML
    private Button offre;
    @FXML
    private Button forum;
    @FXML
    private Button evente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout1(ActionEvent event) throws IOException {
         Stage stage = (Stage) logouthl.getScene().getWindow();
            stage.close();
            SessionWorkit.utilisateur=null;
        SessionWorkit.utilisateur=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stager = new Stage();
        stager.initModality(Modality.APPLICATION_MODAL);
        stager.initStyle(StageStyle.UNDECORATED);
        stager.setTitle("WorkIt");
        stager.setScene(new Scene(root1));
        stager.show();
    }

    @FXML
    private void addbtn(ActionEvent event) {
        ServiceReclamation sv = new ServiceReclamation();
        Recruteur rec= SessionWorkit.utilisateur;
        Reclamation p = new Reclamation();
        p.setRecruteur_id(rec.getId());
        p.setNom(fname.getText());
        p.setDescription(descf.getText());
        
       
      
       sv.addrecla(p);
    }

    @FXML
    private void userbtn(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("frontFXML.fxml"));
            Stage stage = (Stage) userbtn.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recfr(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("reclafrontFXML.fxml"));
            Stage stage = (Stage) recfr.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclafrontFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void checkoffre(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("DevitOffre.fxml"));
            Parent root = loader.load();
            DevitOffreController dc = loader.getController();
            offre.getScene().setRoot(root);
    }

    @FXML
    private void checkforum(MouseEvent event) throws IOException {
          FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Forum.fxml"));
            Parent root = loader.load();
            ForumController dc = loader.getController();
            dc.setLs(new ForumCRUD().read());
            dc.getLs().forEach((forum) -> {
            int count = new CommenterCRUD().countCommentersByForumId(forum.getId());
            int sum = new CommenterCRUD().ratingSum(forum.getId());
            int moy = 0;
            if (count != 0) {
                moy = sum / count;
            }
            
            switch (moy) {
                case 0: case 1:
                    forum.setRatingImage("/images/StarVide.png");
                break;
                
                case 2: case 3: case 4:
                    forum.setRatingImage("/images/demiStar.png");
                break;
                
                default:
                    forum.setRatingImage("/images/Star.png");
                break;

                                    }
        });
            forum.getScene().setRoot(root);
    }

    @FXML
    private void checkevente(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Evenement1.fxml"));
            Parent root = loader.load();
            Evenement1Controller dc = loader.getController();
            evente.getScene().setRoot(root);
    }
    
}
