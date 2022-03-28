package devit.workit.gui;

import devit.workit.services.FreelancerCRUD;
import edu.devit.entities.Freelancer;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Back implements Initializable {


    @FXML
    TableView<Freelancer> tabfreelancer = new TableView<Freelancer>();
    private ObservableList<Freelancer> masterData = FXCollections.observableArrayList();
    public TableColumn tab_id;
    public TableColumn tab_email;
    public TableColumn tab_title;
    public TableColumn tab_skills;
    public TableColumn tab_country;
    public TableColumn tab_name;
    public TableColumn tab_password;
    public TableColumn tab_prix;
    public TableColumn tab_education;
    public TableColumn tab_experience;
    @FXML
    private Button delete;
    @FXML
    private Button projet;
    @FXML
    private TableColumn<?, ?> tab_contact;
    @FXML
    private TextField tfchercher;
    @FXML
    private Button user;
    @FXML
    private Button offre;
    @FXML
    private Button forum;
    @FXML
    private Button certificat;
    @FXML
    private Button evente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AfficherFreelancer();
    }


    private void AfficherFreelancer(){
        FreelancerCRUD fc1 = new FreelancerCRUD();
        ObservableList<Freelancer> liste = fc1.showFreelancer();
        tab_id.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("id"));
        tab_email.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("email"));
        tab_title.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("title"));
        tab_skills.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("skills"));
        tab_country.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("country"));
        tab_name.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("name"));
        tab_password.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("password"));
        tab_prix.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("prix"));
        tab_education.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("education"));
        tab_experience.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("experience"));

        tabfreelancer.setItems(liste);
    }
    @FXML
    public void deletefree(ActionEvent actionEvent) {

            Freelancer f= tabfreelancer.getSelectionModel().getSelectedItem();
            FreelancerCRUD fc = new FreelancerCRUD();
            if (fc.deleteFreelancer(f)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succées");
                alert.setHeaderText(null);
                alert.setContentText("La suppression de la freelancer a été effectué avec succées");
                alert.showAndWait();
                AfficherFreelancer();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La suppression de la freelancer n'a pas été effectué!");
                alert.showAndWait();
                AfficherFreelancer();
            }

    }

    @FXML
    private void projet(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackProjet.fxml"));
            Parent root = loader.load();
            BackProjet dc = loader.getController();
            projet.getScene().setRoot(root);
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
    private void checkcertif(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Certificat.fxml"));
            Parent root = loader.load();
            CertificatController dc = loader.getController();
            certificat.getScene().setRoot(root);
    }



}
