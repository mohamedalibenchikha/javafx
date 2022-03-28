/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import devit.workit.entites.SessionWorkit;
import devit.workit.services.RecruteurCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField tfmail;
    @FXML
    private JFXPasswordField tfmdp;
    @FXML
    private JFXButton btncon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void connecter(MouseEvent event) throws IOException {
       /* boolean verifUs = false;
        boolean verifpwd = false;
        RecruteurCRUD rs = new RecruteurCRUD();
        try {
            verifUs = rs.userExist(tfmail.getText());
            verifpwd = rs.getPwdByUsername(tfmail.getText(), tfmdp.getText());
        } catch (SQLException ex) {
        }
        if (verifUs && verifpwd) {
            boolean con = false;
            con = rs.returnuser(tfmail.getText(), tfmdp.getText());
            if (con == true) {
                if (("recruteur".equals(SessionWorkit.getType())))
                {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                AccueilController Acc = loader.getController();
                Stage stageAcceuil = new Stage();
                stageAcceuil.setTitle("Work It");
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stageAcceuil.setScene(scene);
                stageAcceuil.show();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();  
                }
                else if (("candidat".equals(SessionWorkit.getType())))
                {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                AccueilController Acc = loader.getController();
                Stage stageAcceuil = new Stage();
                stageAcceuil.setTitle("Work It");
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stageAcceuil.setScene(scene);
                stageAcceuil.show();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();     
                }
                else
                {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("BackAccueil.fxml"));
                Parent root = loader.load();
                BackAccueilController BAcc = loader.getController();
                Stage stageAcceuil = new Stage();
                stageAcceuil.setTitle("Work It");
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stageAcceuil.setScene(scene);
                stageAcceuil.show();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                }
                
            }

        }
*/
    }
}
