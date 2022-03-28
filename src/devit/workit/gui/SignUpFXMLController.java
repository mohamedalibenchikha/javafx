/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Recruteur;
import devit.workit.services.RecruteurCRUD;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wael Belhadj
 */
public class SignUpFXMLController implements Initializable {

    private RecruteurCRUD sr = new RecruteurCRUD();
    private FileChooser fileChooser;
    private File file;
    @FXML
    private TextField NomTf;
    @FXML
    private TextField prenomTf;
    @FXML
    private TextField NomSocTf;
    @FXML
    private TextField adresseTf;
    @FXML
    private TextField EmailTf;
    @FXML
    private TextField NumSocTf;
    @FXML
    private PasswordField PasswrdTf;
    @FXML
    private ComboBox<String> TypeTf;
    @FXML
    private Button uploadImgbtn;
    @FXML
    private TextField CompTf;
    @FXML
    private Button Signupbtn;
    @FXML
    private Pane stackpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("....","Recruteur", "Candidat");
        TypeTf.setItems(list);
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", Arrays.asList("*.png","*.jpeg","*.jpg")));
        
        uploadImgbtn.setOnAction((Recruteur)->{
         file = fileChooser.showOpenDialog(stackpane.getScene().getWindow());
         if(file!=null)
                {
                    String path = file.getAbsolutePath();
                    Image image = new Image(file.toURI().toString(),100,150,true,true);
                    System.err.println(file.toURI().toString());
                    
                }
        }
        );
    }    

    @FXML
    private void upload(ActionEvent event) {
    }
    
    @FXML
    private void Signup(ActionEvent event) {
        if ( NomTf.getText().isEmpty() || prenomTf.getText().isEmpty() ||  NomSocTf.getText().isEmpty()|| adresseTf.getText().isEmpty()|| EmailTf.getText().isEmpty()|| NumSocTf.getText().isEmpty()|| PasswrdTf.getText().isEmpty()|| CompTf.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
        boolean error= true;
        String nom = NomTf.getText();
        String prenom = prenomTf.getText();
        String nomsoc = NomSocTf.getText();
        String adresse = adresseTf.getText();
        String email = EmailTf.getText();
        String numero = NumSocTf.getText();
        String password =PasswrdTf.getText();
        String type = TypeTf.getValue();
        String comp = CompTf.getText();
        int numsoc = Integer.parseInt(numero);
        
        Recruteur rec= new Recruteur(nom,prenom,nomsoc,adresse,email,numsoc,password,type,comp);
        if(file!=null){
            rec.setPhoto(file.toURI().toString());
        }
        
        sr.addrec(rec);
        System.out.println("Recrueur ajouter");
        Stage stage = (Stage) Signupbtn.getScene().getWindow();
        stage.close();
    }
    
}
    
}
