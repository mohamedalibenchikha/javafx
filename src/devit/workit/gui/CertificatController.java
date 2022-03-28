/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Certificat;
import devit.workit.entites.Test;
import devit.workit.services.ServiceCertificat;
import devit.workit.services.ServiceTest;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
//import sprint_java2.FXMLDocumentController;

/**
 * CertificatController Controller class
 *
 * @author walid belhadj
 */
public class CertificatController implements Initializable {

    
    @FXML
    private TableColumn<Certificat, String> colnomCer;
    @FXML
    private TextField tfnc;
    @FXML
    private TableView<Certificat> table2;
    ServiceCertificat sc;
    @FXML
    private Button btntest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceCertificat sc =new ServiceCertificat();
        ObservableList<Certificat> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(sc.AfficherCertificat());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

     
        colnomCer.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       
        

        table2.setItems(list3);
        
    }    

   

    @FXML
    private void AjouterCertificat(ActionEvent event) {
         ServiceCertificat sc = new ServiceCertificat();
        if ( tfnc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
            
            
            JOptionPane.showMessageDialog(null, "Certificat ajouté");
        
       Certificat c = new Certificat();
        c.setNom(tfnc.getText());       
       
        sc.AjouterCertificat(c);
             tfnc.clear();
       

          ObservableList<Certificat> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(sc.AfficherCertificat());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

     
        colnomCer.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       
        table2.setItems(list3);
        }
    }

    @FXML
    private void SelectCertificat(MouseEvent event) {
         ServiceCertificat sc = new ServiceCertificat();

        int index = table2.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
       
        String nom = colnomCer.getCellData(index);
       
      


//   
       
       
        tfnc.setText(nom);
        
    }
    
    private void refreshList(ServiceCertificat sv){
        ObservableList<Certificat> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(sv.AfficherCertificat());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        colnomCer.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       
        

        table2.setItems(list3);
        
    }

    @FXML
    private void SupprimerCertificat(ActionEvent event) {
        int index = table2.getSelectionModel().getSelectedItem().getId();
        this.sc.SupprimerCertificat(index);
        System.out.println("eeee:"+index);
                 JOptionPane.showMessageDialog(null, "Certificat supprimé");
                 
                 refreshList(sc);
    }

    @FXML
    private void ModifierCertificat(ActionEvent event) throws SQLException {
         ServiceCertificat st = new ServiceCertificat();
        if ( tfnc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
   
       
      Certificat c = table2.getSelectionModel().getSelectedItem();
        
        c.setNom(tfnc.getText());
        System.out.println("el tfn"+tfnc.getText());
        System.out.println("el r "+c.getNom());
        st.ModifierCertificat(c);
         //System.out.println("eeee:"+table1.getSelectionModel().getSelectedItem().getId());
        refreshList(st);
         JOptionPane.showMessageDialog(null, "certificat modifé");
         }
    }

    @FXML
    private void btntest(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("TestDocument.fxml"));
            Stage stage = (Stage) btntest.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TestDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
