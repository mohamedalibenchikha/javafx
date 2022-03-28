package devit.workit.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.ForumCRUD;
import devit.workit.services.FreelancerCRUD;
import devit.workit.tools.MyConnection;
import edu.devit.entities.Freelancer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddFreelancerController implements Initializable {
    @FXML
    public TextField tfname;
    @FXML
    public TextField tfpassword;
    @FXML
    public TextField tftitle;
    @FXML
    public TextField tfskills;
    @FXML
    public TextField tfcountry;
    @FXML
    public TextField tfprix;
    @FXML
    public TextField tfemail;
    @FXML
    public TextField tfexperience;
    @FXML
    public TextField tfeducation;
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
    public TextField tfchercher;
    public TextField tfnomp;
    public TextField tfdesc;
    public TextField tftype;
    public TextField tfsalary;
    public TextField tfphoto;
    public TextField tftime;

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;

    @FXML
     TableView<Freelancer> tabfreelancer = new TableView<>();
    private ObservableList<Freelancer> masterData = FXCollections.observableArrayList();
    @FXML
    private Button btndelete;
    @FXML
    private Button print;
    @FXML
    private Button projet;
    @FXML
    private TableColumn<?, ?> tab_contact;
    @FXML
    private Button users;
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
        FreelancerCRUD fc = new FreelancerCRUD();
        ObservableList<Freelancer> liste = fc.showFreelancer();
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
    public void ajouterfreelancer(javafx.event.ActionEvent actionEvent) {
    // sauvegarde dans la base
        FreelancerCRUD fc = new FreelancerCRUD();
        Freelancer f = new Freelancer();
        String tEmail = tfemail.getText();
        String tName = tfname.getText();
        String tPassword = tfpassword.getText();
        String tTitle = tftitle.getText();
        String tSkills = tfskills.getText();
        String tCountry = tfcountry.getText();
        int tPrix = Integer.parseInt(tfprix.getText());
        String tEducation = tfeducation.getText();
        String tExperience = tfexperience.getText();
        f.setEmail(tEmail);
        f.setNom(tName);
        f.setPassword(tPassword);
        f.setTitle(tTitle);
        f.setSkills(tSkills);
        f.setCountry(tCountry);
        f.setPrix(tPrix);
        f.setEducation(tEducation);
        f.setExperience(tExperience);

        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(tfemail.getText());
        if (!controler.matches()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("invalid mail");
            alert.showAndWait();
        }

        else if (tfname.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("nom is Empty");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else if (tfpassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password is Empty");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else if (tftitle.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("title is Empty");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else if (tfskills.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("skills is Empty");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else if (tfcountry.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("country is Empty");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
        fc.addFreelancer(f);
        }

        AfficherFreelancer();

    }

        @FXML
        void pdfs(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {

            try {

                Connection conx = MyConnection.getInstance().getCnx();

                ResultSet rs = conx.createStatement().executeQuery("select * from freelancer");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Liste freelancer.pdf"));
                my_pdf_report.open();
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(6);
                //create a cell object
                PdfPCell table_cell;

                while (rs.next()) {
                    String id = rs.getString("id");
                    table_cell = new PdfPCell(new Phrase(id));
                    my_report_table.addCell(table_cell);

                    String email = rs.getString("email");
                    table_cell = new PdfPCell(new Phrase(email));
                    my_report_table.addCell(table_cell);

                    String name = rs.getString("name");
                    table_cell = new PdfPCell(new Phrase(name));
                    my_report_table.addCell(table_cell);

                    String manager_id = rs.getString("password");
                    table_cell = new PdfPCell(new Phrase(manager_id));
                    my_report_table.addCell(table_cell);

                    String title = rs.getString("title");
                    table_cell = new PdfPCell(new Phrase(title));
                    my_report_table.addCell(table_cell);

                    String skills = rs.getString("skills");
                    table_cell = new PdfPCell(new Phrase(skills));
                    my_report_table.addCell(table_cell);

                    String datef = rs.getString("country");
                    table_cell = new PdfPCell(new Phrase(datef));
                    my_report_table.addCell(table_cell);

                    int prix = rs.getInt("prix");
                    table_cell = new PdfPCell(new Phrase(prix));
                    my_report_table.addCell(table_cell);

                    String education = rs.getString("education");
                    table_cell = new PdfPCell(new Phrase(education));
                    my_report_table.addCell(table_cell);

                    String experience = rs.getString("experience");
                    table_cell = new PdfPCell(new Phrase(experience));
                    my_report_table.addCell(table_cell);
                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);
                my_pdf_report.close();

                /* Close all DB related objects */
                rs.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            String title = "Succes! ";
            String message = "Le fichier PDF est generé";

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));

        }

    @FXML
    public void modifierfreelancer(javafx.event.ActionEvent actionEvent) {
        Freelancer f = tabfreelancer.getSelectionModel().getSelectedItem();

        f.setEmail(tfemail.getText());
        f.setNom(tfname.getText());
        f.setPassword(tfpassword.getText());
        f.setTitle(tftitle.getText());
        f.setSkills(tfskills.getText());
        f.setCountry(tfcountry.getText());
        f.setPrix(Integer.parseInt(tfprix.getText()));
        f.setEducation(tfeducation.getText());
        f.setExperience(tfexperience.getText());

        FreelancerCRUD fc = new FreelancerCRUD();
        if (fc.updateFreelancer(f)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("La modification de la freelancer a été effectué avec succées");
            alert.showAndWait();
            AfficherFreelancer();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La modification de la freelancer n'a pas été effectué!");
            alert.showAndWait();
            AfficherFreelancer();
        }
    }

    @FXML
    public void deletefreelancer(ActionEvent actionEvent) {
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
    public void selectFreelancer(MouseEvent mouseEvent) {

        Freelancer f= tabfreelancer.getSelectionModel().getSelectedItem();
        tfname.setText(f.getNom());
        tfemail.setText(f.getEmail());
        tfcountry.setText(f.getCountry());
        tfexperience.setText(f.getExperience());
        tftitle.setText(String.valueOf(f.getTitle()));
        tfpassword.setText(f.getPassword());
        tfeducation.setText(f.getEducation());
        tfexperience.setText(f.getExperience());
        tfskills.setText(f.getSkills());

    }

    public void displaySelected(MouseEvent mouseEvent) {
    }

    @FXML
    public void sendToScene2(ActionEvent actionEvent) throws IOException{

         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("AddProjet.fxml"));
            Parent root = loader.load();
            AddProjetController dc = loader.getController();
            projet.getScene().setRoot(root);
        }

    @FXML
    private void checkuser(MouseEvent event) throws IOException {
           FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("FrontUserFXML.fxml"));
            Parent root = loader.load();
            FrontUserFXMLController dc = loader.getController();
            users.getScene().setRoot(root);
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
    private void checkcertificat(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("FrontFXML.fxml"));
            Parent root = loader.load();
            FrontFXMLController dc = loader.getController();
            certificat.getScene().setRoot(root);
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



