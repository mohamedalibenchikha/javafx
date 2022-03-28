/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.gui.FrontUserFXMLController;
import devit.workit.gui.BackUserController;
import devit.workit.entites.Recruteur;
import devit.workit.services.RecruteurCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Wael Belhadj
 */
public class LoginFXMLController implements Initializable {

    private Stage window;
    private BackUserController doccontroller;
    private FrontUserFXMLController frontcontroller;
    private RecruteurCRUD sr = new RecruteurCRUD();
    @FXML
    private TextField emailTf;
    @FXML
    private PasswordField mdpTf;
    @FXML
    private Button connectBtn;
    @FXML
    private Hyperlink signupHl;
    @FXML
    private Hyperlink resetpassHl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        String email = emailTf.getText();
        String pass = mdpTf.getText();
        Recruteur r = sr.existUser(email, pass);
        if (r != null) {
            sr.connect(r);
            if(("admin".equals(pass))&&("admin".equals(email))){
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
            /*FXMLLoader loader;
                loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BackUser.fxml"));
            Parent root = loader.load();

            doccontroller = loader.getController();
            doccontroller.setRecrut(r);
            loader.setController(doccontroller);
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent rootMain = window.getScene().getRoot();

            root.setOnMouseDragged(rootMain.getOnMouseDragged());
            root.setOnMousePressed(rootMain.getOnMousePressed());

            Scene scene = new Scene(root);
            doccontroller.setWindow(window);

            window.setScene(scene);*/
            }else{
              
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
              /*  FXMLLoader loader;
                loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FrontUserFXML.fxml"));
            Parent root = loader.load();

            frontcontroller = loader.getController();
            frontcontroller.setRecrut(r);
            loader.setController(frontcontroller);
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent rootMain = window.getScene().getRoot();

            root.setOnMouseDragged(rootMain.getOnMouseDragged());
            root.setOnMousePressed(rootMain.getOnMousePressed());

            Scene scene = new Scene(root);
            frontcontroller.setWindow(window);

            window.setScene(scene); */
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("invalid Username or Password!");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void signupp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("WorkIt");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void Resetpass(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResetSendCodeFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("WorkIt");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
