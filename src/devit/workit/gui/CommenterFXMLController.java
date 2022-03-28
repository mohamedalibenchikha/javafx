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
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import devit.workit.entites.Commentaire;
import devit.workit.entites.Forum;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.CurseFilterService;
import devit.workit.services.ForumCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */
public class CommenterFXMLController implements Initializable {

    @FXML
    private TextField txfdes;
    @FXML
    private Label label;
    @FXML
    private TableView<Commentaire> tablecom;
    @FXML
    private TableColumn<Commentaire, String> col_des_com;
    @FXML
    private Button btnajouter;
    @FXML
    private Label labe1;
    private int forumId;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button retourbtn;
    @FXML
    private TextField raiting;
    @FXML
    private TableColumn<Commentaire, String> ratingcol;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               //  new animatefx.animation.ZoomInUp(btnajouter).play();
                 // new animatefx.animation.ZoomInUp(btnmodifier).play();
                
                
                
               
                new animatefx.animation.ZoomInUp(retourbtn).play();
                new animatefx.animation.ZoomInUp(btnmodifier).play();
                new animatefx.animation.ZoomInUp(btnajouter).play();
                show();
                    
    }
public void show()
{
     CommenterCRUD c = new CommenterCRUD();
      try {

            ObservableList<Commentaire> ls = c.read();

         
            col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
            ratingcol.setCellValueFactory(new PropertyValueFactory<>("rating"));


            tablecom.setItems(ls);
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
        tablecom.setItems(ls);

    }

    @FXML
    private void ajoutercommentaire(ActionEvent event) throws SQLException {
         if (txfdes.getText().isEmpty() || raiting.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "please fill all the textfields ");
            } else {
       TrayNotification tray = null;
        // commentaire f = new commentaire(Integer.parseInt(txfidcom.getText()),Integer.parseInt(txfidf.getText()),Integer.parseInt(txfidu.getText()), txfdes.getText());
            // Commentaire r = new Commentaire(CurseFilterService.cleanText(txfdes.getText()));
        Commentaire f = new Commentaire( CurseFilterService.cleanText(txfdes.getText()));
        f.setRating(Integer.parseInt(raiting.getText()));
        f.setForum_id(forumId);
     
        CommenterCRUD s = new CommenterCRUD();
        s.Ajouter(f);
        //JOptionPane.showMessageDialog(null, "Ajouter avec sucess");

        

        ObservableList<Commentaire> ls = s.read3(labe1.getText());
 
        tray = new TrayNotification("Commentaire", "Commentaire ajouter avec succces ", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
        col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        tablecom.setItems(ls);

    }}
    @FXML
    private void modifiercommentaire(ActionEvent event) throws SQLException {
         TrayNotification tray = null;
        Commentaire c = (Commentaire) tablecom.getSelectionModel().getSelectedItem();

        CommenterCRUD fo = new CommenterCRUD();
       c.setCommentaire(CurseFilterService.cleanText(txfdes.getText()));
       c.setRating(Integer.parseInt(raiting.getText()));


        fo.Modifier(c);

        ObservableList<Commentaire> ls = fo.read3(labe1.getText());
      
        tray = new TrayNotification("Commentaire", "Commentaire modifier avec succces ", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
        col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        tablecom.setItems(ls);
    }

    @FXML
    private void displaySelected2(javafx.scene.input.MouseEvent event) {
        TrayNotification tray = null;
        if (event.getClickCount() == 2) {
            Commentaire i = tablecom.getSelectionModel().getSelectedItem();
            CommenterCRUD si = new CommenterCRUD();
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Supprimer " + i.getCommentaire()+ " ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                si.Supprimer(i);
                                //JOptionPane.showMessageDialog(null, "Suppression avec sucess");
                                try {
                ObservableList<Commentaire> ls = si.read3(labe1.getText());

                tray = new TrayNotification("Commentaire", "Commentaire supprimer avec succces ", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
                col_des_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
                tablecom.setItems(ls);
            } catch (SQLException ex) {
                Logger.getLogger(CommenterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
                            }
            
            
            
                    

    
            
        } else if (event.getClickCount() == 1) {
            Commentaire c = (Commentaire) tablecom.getSelectionModel().getSelectedItem();
            if (c != null) {

                txfdes.setText(c.getCommentaire());
                raiting.setText(c.getRating() + "");

            }
        }


    }
     @FXML
    public void retour(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));

        Parent root = loader.load();
        ForumController fXMLController = loader.getController();
            fXMLController.setLs(new ForumCRUD().read());
            fXMLController.getLs().forEach((forum) -> {
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
        fXMLController.getTableforum().setItems(fXMLController.getLs());
        retourbtn.getScene().setRoot(root);
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
    
    
    
    
}
