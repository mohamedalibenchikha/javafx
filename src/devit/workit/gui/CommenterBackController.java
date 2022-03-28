/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import devit.workit.entites.Commentaire;
import devit.workit.services.CommenterCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */
public class CommenterBackController implements Initializable {

    @FXML
    private TableView<Commentaire> tablecomBack;
    @FXML
    private TableColumn<Commentaire,String> col_des_com;
    @FXML
    private Label label;
    private int forumId;
    @FXML
    private Label labe1;
    @FXML
    private Button retourbtnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                 
                CommenterCRUD c = new CommenterCRUD();
                new animatefx.animation.ZoomInUp(retourbtnBack).play();
                     try {

            ObservableList<Commentaire> ls = c.read();

         
            col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

            tablecomBack.setItems(ls);

        } catch (Exception ex) {
            Logger.getLogger(CommenterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void showinformation(String b) throws SQLException {
        CommenterCRUD c = new CommenterCRUD();

         labe1.setText(b);
        System.out.println(labe1.getText());
      
      ObservableList<Commentaire> ls = c.read3(labe1.getText());

//            col_idcom.setCellValueFactory(new PropertyValueFactory<>("idCom"));
//            col_idf.setCellValueFactory(new PropertyValueFactory<>("idF"));
       
        col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        tablecomBack.setItems(ls);

    }    

    @FXML
    private void displaySelectedCommBack(javafx.scene.input.MouseEvent event) {
        TrayNotification tray = null;
           if (event.getClickCount() == 2) {
            Commentaire i = tablecomBack.getSelectionModel().getSelectedItem();
            CommenterCRUD si = new CommenterCRUD();
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Supprimer " + i.getCommentaire()+ " ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                si.Supprimer(i);
                               // JOptionPane.showMessageDialog(null, "Suppression avec sucess");
                                try {
                ObservableList<Commentaire> ls = si.read3(labe1.getText());
 tray = new TrayNotification("Commentaire", "Commentaire ajouter avec succces ", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
                
                col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
                tablecomBack.setItems(ls);
            } catch (SQLException ex) {
                Logger.getLogger(CommenterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
                            }
            

        } else if (event.getClickCount() == 1) {
            Commentaire c = (Commentaire) tablecomBack.getSelectionModel().getSelectedItem();
          
        }
    }

    @FXML
    private void retourAdmin(ActionEvent event)throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("forumBackFXML.fxml"));

        Parent root = loader.load();
        retourbtnBack.getScene().setRoot(root);
    }
    
     public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
    
}
