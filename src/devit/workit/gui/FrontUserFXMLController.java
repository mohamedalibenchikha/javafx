/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Recruteur;
import devit.workit.entites.SessionWorkit;
import devit.workit.services.RecruteurCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Wael Belhadj
 */
public class FrontUserFXMLController implements Initializable {

    private Recruteur recrut;
    private Stage window;
    @FXML
    private Hyperlink logouthl;
    @FXML
    private Button reclafront;
    @FXML
    private Button usrbtn;
    @FXML
    private Button app;
    @FXML
    private Button offre;

   /* public Recruteur getRecrut() {
        return recrut;
    }

    public void setRecrut(Recruteur recrut) {
        this.recrut = recrut;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }*/
    @FXML
    private Pane profilepan;
    @FXML
    private TextField fn;
    @FXML
    private TextField fp;
    @FXML
    private TextField fns;
    @FXML
    private TextField fa;
    @FXML
    private TextField fm;
    @FXML
    private TextField fnum;
    private TextField fmdp;
    @FXML
    private TextField fco;
    @FXML
    private Button supprimerbtn;

    private Recruteur r = SessionWorkit.utilisateur;
    private RecruteurCRUD sr= new RecruteurCRUD();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fn.setText(r.getNom());
       fp.setText(r.getPrenom());
       fns.setText(r.getNomsociete());
       fa.setText(r.getAdresse());
       fm.setText(r.getMail());
       fnum.setText(Integer.toString(r.getNumsociete()));
       fco.setText(r.getCompetence());
       
    }    

    @FXML
    private void modifier_recru(ActionEvent event) throws SQLException {
         
        RecruteurCRUD  sa = new RecruteurCRUD();
        Recruteur r = new Recruteur();
        
        r.setNom(fn.getText());
        r.setPrenom(fp.getText());
        r.setNomsociete(fns.getText());
        r.setAdresse(fa.getText());
        r.setMail(fm.getText());
        r.setCompetence(fco.getText());
        r.setNumsociete(Integer.parseInt(fnum.getText()));
        r.setCompetence(fco.getText());
        
        sr.updateRecruteur(r);
       
         JOptionPane.showMessageDialog(null, "recruteur modifé");
         
    }

    @FXML
    private void supp_recru(ActionEvent event) throws IOException {
        RecruteurCRUD us = new RecruteurCRUD();
        Recruteur rec= SessionWorkit.utilisateur;
        if(us.deleteRecruteur(rec)) {
            Stage stage = (Stage) supprimerbtn.getScene().getWindow();
            stage.close();
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
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
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
    private void usrbtn(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("frontFXML.fxml"));
            Stage stage = (Stage) usrbtn.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void app(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("webFXML.fxml"));
            Stage stage = (Stage) app.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WebFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void checkrecla(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("reclafrontFXML.fxml"));
            Parent root = loader.load();
            ReclafrontFXMLController dc = loader.getController();
            reclafront.getScene().setRoot(root);
    }
}
