/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Reclamation;
import devit.workit.entites.SessionWorkit;
import devit.workit.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wael Belhadj
 */
public class ReclaBackController implements Initializable {

    @FXML
    private Hyperlink logouthl;
    @FXML
    private TableView<Reclamation> table1;
    @FXML
    private TableColumn<Reclamation, String> no;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
    private Button supprimerbtn;
    @FXML
    private TextField fn;
    @FXML
    private TextField fdesc;
    @FXML
    private Button userbtn;
    @FXML
    private Button reclabnt;
    @FXML
    private Button offre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation sv = new ServiceReclamation();
         ObservableList<Reclamation> list1 = null;
        try {
            list1 = FXCollections.observableArrayList(sv.AfficherRecla());
        } catch (SQLException ex) {
            Logger.getLogger(BackUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
       
        
        table1.setItems(list1);
        
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
    private void select(MouseEvent event) {
          ServiceReclamation sv = new ServiceReclamation();

        int index = table1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        
  
        fn.setText((String) no.getCellData(index));
        fdesc.setText((String) desc.getCellData(index));
       
    }

    @FXML
    private void supp_recla(ActionEvent event) {
         ServiceReclamation us = new ServiceReclamation();
        Reclamation rec= us.findByName(fn.getText());
        if(us.deleteRecruteur(rec)) {
            JOptionPane.showMessageDialog(null, "reclamation supprimé");
            //refreshList(us); 
    }

  
    
}

    @FXML
    private void userbtn(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = (Stage) reclabnt.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reclabtn(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ReclaBack.fxml"));
            Stage stage = (Stage) reclabnt.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclaBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void checkoffre(MouseEvent event) throws IOException {

           FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackOffre.fxml"));
            Parent root = loader.load();
            BackOffreController dc = loader.getController();
            offre.getScene().setRoot(root);
    }
    

}
