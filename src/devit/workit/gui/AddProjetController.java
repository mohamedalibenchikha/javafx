package devit.workit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.collections.FXCollections;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import devit.workit.entites.Projet;
import devit.workit.entites.SessionWorkit;
import devit.workit.services.ProjetCRUD;
import edu.devit.entities.Freelancer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class AddProjetController implements Initializable {
    @FXML
    public Button modifprojet;
    public TableColumn tab_id;
    public TableColumn tab_userid;
    public TextField t_recherche;

    @FXML
    TableView<Projet> tabprojet = new TableView<>();
    private ObservableList<Projet> masterData = FXCollections.observableArrayList();

    public TextField tfnomp;
    public TextField tfdesc;
    public TextField tftype;
    public TextField tfsalary;
    public TextField tftime;
    public TextField tflogo;
    public TableColumn tab_nomp;
    public TableColumn tab_desc;
    public TableColumn tab_type;
    public TableColumn tab_salary;
    public TableColumn tab_time;
    public TableColumn tab_logo;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button accueil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AfficherProjet();
        } catch (SQLException ex) {
            Logger.getLogger(AddProjetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AfficherProjet() throws SQLException {
        ProjetCRUD pc = new ProjetCRUD();
        ObservableList<Projet> liste = pc.showProjet();
        tab_nomp.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("nom_projet"));
        tab_desc.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("projet_description"));
        tab_type.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("job_type"));
        tab_salary.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("job_salary"));
        tab_time.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("job_time"));
        tab_logo.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("logo"));
        tab_id.setCellValueFactory(new PropertyValueFactory<Freelancer, Integer>("id"));
        tab_userid.setCellValueFactory(new PropertyValueFactory<Freelancer, Integer>("user_id"));



        FilteredList<Projet> filtredData = new FilteredList<>(liste, b -> true);
        t_recherche.textProperty().addListener(((observable, oldValue, newValue) -> {
            filtredData.setPredicate(projet -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (projet.getNom_projet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }
            });

        }));
        SortedList<Projet> sortedData = new SortedList<>(filtredData);
        sortedData.comparatorProperty().bind(tabprojet.comparatorProperty());
        tabprojet.setItems(sortedData);

    }



    @FXML
    public void AjouterProjet(ActionEvent actionEvent) throws SQLException {
        ProjetCRUD pc = new ProjetCRUD();
        Projet p = new Projet();
        String tnomp = tfnomp.getText();
        String tdesc = tfdesc.getText();
        String ttype = tftype.getText();
        String tsalary = tfsalary.getText();
        String ttime = tftime.getText();
        String tlogo = tflogo.getText();

        p.setNom_projet(tnomp);
        p.setProjet_description(tdesc);
        p.setJob_type(ttype);
        p.setJob_salary(tsalary);
        p.setJob_time(ttime);
        p.setLogo(tlogo);

        pc.addProjet(p);

        AfficherProjet();
    }

    @FXML
    public void selectProjet(MouseEvent mouseEvent) {

        Projet p = tabprojet.getSelectionModel().getSelectedItem();
        tfnomp.setText(p.getNom_projet());
        tfdesc.setText(p.getProjet_description());
        tftype.setText(p.getJob_type());
        tfsalary.setText(p.getJob_salary());
        tftime.setText(p.getJob_time());
        tflogo.setText(p.getLogo());


    }


    @FXML
    public void modifprojet(ActionEvent actionEvent) throws SQLException{
        Projet p = tabprojet.getSelectionModel().getSelectedItem();


        p.setNom_projet(tfnomp.getText());
        p.setProjet_description(tfdesc.getText());
        p.setJob_type(tftype.getText());
        p.setJob_salary(tfsalary.getText());
        p.setJob_time(tftime.getText());
        p.setLogo(tflogo.getText());
        System.out.println(p.getId());
        ProjetCRUD pc = new ProjetCRUD();
        if (pc.updateProjet(p)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("La modification de la projet a été effectué avec succées");
            alert.showAndWait();
            AfficherProjet();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La modification de la projet n'a pas été effectué!");
            alert.showAndWait();
            AfficherProjet();
        }
    }


    @FXML
    public void DeleteProjet(ActionEvent actionEvent) throws SQLException {

            Projet p= tabprojet.getSelectionModel().getSelectedItem();
            ProjetCRUD pc = new ProjetCRUD();
            if (pc.deleteprojet(p)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succées");
                alert.setHeaderText(null);
                alert.setContentText("La suppression de la projet a été effectué avec succées");
                alert.showAndWait();
                AfficherProjet();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La suppression de la projet n'a pas été effectué!");
                alert.showAndWait();
                AfficherProjet();
            }
        }

    @FXML
   public void Postuler(ActionEvent actionEvent) {
        ObservableList<Projet> SelectedRows, allpeople;
        allpeople = tabprojet.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows =tabprojet.getSelectionModel().getSelectedItems();

        for(Projet gg:SelectedRows){
            // email = service user : get user by id gg.getUser_id()

            String email = "ayoub.mahou@esprit.tn";
            //free lance get Current user
            String MailFreelance = "ayoub.mahou@esprit.tn";

             String ACCOUNT_SID = "AC68e207c17ed184d8cd034829c9171567";
             String AUTH_TOKEN = "c2e0c292af9a27aeba55e0cf154d3df0";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber("+21655215711"),
                    new PhoneNumber("+13342928900"),
                    "freelance : "+MailFreelance +"vous contacter a propos votre porjet "+gg.getNom_projet()).create();

            System.out.println(message.getSid());
            try {
                String att = gg.getNom_projet()+ " " + gg.getUser_id();
                String qrCodeData = att;

                String filePath = "projet.png";
                String charset = "UTF-8";
                Map< EncodeHintType, ErrorCorrectionLevel > hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel >();
                hintMap.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
                com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, 200, 200 , (Hashtable) hintMap);

                MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));

                System.out.println("QR Code image created successfully!");
            } catch (Exception e) {
                System.err.println(e);
            }

            String to = email;
            String host = "smtp.gmail.com";
            final String mail = "ayoumahouesprit@gmail.com";
            final String password = "12345678aze";

            Properties props = System.getProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail, password);
                }
            });

            try {
                MimeMessage m = new MimeMessage(session);
                try {
                    m.setFrom(mail);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                m.addRecipients(javax.mail.Message.RecipientType.TO, to);
                m.setSubject("Freelancer");
                m.setText("freelance : "+MailFreelance +"vous contacter a propos votre porjet "+gg.getNom_projet());
                m.addRecipients(javax.mail.Message.RecipientType.TO, to);
                m.setSubject("Projet");
                m.setText("Participation Confirmé");
                m.addRecipients(javax.mail.Message.RecipientType.TO, to);

                Multipart emailContent = new MimeMultipart();

                MimeBodyPart TextBodyPArt = new MimeBodyPart();
                TextBodyPArt.setText("freelance : "+MailFreelance +"vous contacter a propos votre porjet "+gg.getNom_projet());

                MimeBodyPart imageBodyPArt = new MimeBodyPart();
                imageBodyPArt.attachFile("/images/projet.png");

                emailContent.addBodyPart(TextBodyPArt);
                emailContent.addBodyPart(imageBodyPArt);
                m.setContent(emailContent);
                Transport.send(m);

            } catch (MessagingException | IOException e) {
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Postuler confirmer");
            alert.setContentText("le proprietaire de l'offre est bien contacter");
            alert.showAndWait();
        }

    }

    @FXML
    private void checkaccueil(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = loader.load();
            AccueilController dc = loader.getController();
            accueil.getScene().setRoot(root);
    }

    
}



