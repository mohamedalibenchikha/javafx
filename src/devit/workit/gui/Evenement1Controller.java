/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import com.jfoenix.controls.JFXButton;
import com.teknikindustries.bulksms.SMS;
import devit.workit.services.Mails;
import devit.workit.entites.Evenement;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.EvenementCrud;
import devit.workit.services.ForumCRUD;
import devit.workit.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Evenement1Controller implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
   
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfEmail;

    @FXML
    private TableView<Evenement> tableE;
    @FXML
    private TableColumn<Evenement, String> col_id;
    @FXML
    private TableColumn<Evenement, String> col_nom;
    @FXML
    private TableColumn<Evenement, String> col_date;
    @FXML
    private TableColumn<Evenement, String> col_description;
    @FXML
    private TableColumn<Evenement, String> col_email;

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnemail;
    @FXML
    private Button btnactualiser;
    @FXML
    private TextField rNom;
    ObservableList<Evenement> obevent = FXCollections.observableArrayList();
    @FXML
    private BarChart<String , Double > stat;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;

    @FXML
    private TextField tfDate;
    @FXML
    private Button btnImprim;
    @FXML
    private ComboBox<?> catC;
    @FXML
    private TableColumn<Evenement , Integer> col_cat;
   
    @FXML
    private ComboBox<?> eventC;
    @FXML
    private TextField tfTel;
    @FXML
    private Button btnParticipe;
    @FXML
    private ComboBox<?> recC;
    @FXML
    private ListView<String> listEvent;
    @FXML
    private Button user;
    @FXML
    private Button freelancer;
    @FXML
    private Button offre;
    @FXML
    private Button forum;
    @FXML
    private Button test;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listEvent.getItems().add("Mr/Mme 4 participe à l'evenement startup ");

        
        try {
            
            Connection cnx = MyConnection.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            String id_ath_id = null;
            String query = "SELECT DISTINCT nom , sum(id) AS 'CAL' FROM evenement GROUP BY nom  ";
            XYChart.Series<String, Double> series = new XYChart.Series<>();

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {

                series.getData().add(new XYChart.Data<>(rst.getString("nom"), rst.getDouble("CAL")));

            }
            stat.getData().add(series);

        } catch (SQLException ex) {
            Logger.getLogger(Evenement1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* XYChart.Series set1 = new XYChart.Series<>();
//        set1.getData().add(new XYChart.Data("senior",200));
//        set1.getData().add(new XYChart.Data("junior",150));
        set1.getData().add(new XYChart.Data("Entreprenariat",3));
        set1.getData().add(new XYChart.Data("Stage",1));
        set1.getData().add(new XYChart.Data("Embauche",4));
        stat.getData().addAll(set1); */
        
        
        EvenementCrud ec = new EvenementCrud();
       catC.setItems((ObservableList)ec.selectIDP());
       recC.setItems((ObservableList)ec.selectIDR());
       eventC.setItems((ObservableList)ec.selectIDE());
            
          
        try {
            // TODO

            Connection con = MyConnection.getInstance().getCnx();
            
            
            ResultSet rs = con.createStatement().executeQuery("select id , nom , date , description , email , idcat_id  from evenement ");
            while (rs.next()) {
               
                
                obevent.add(new Evenement(rs.getString("id"), rs.getString("nom"), rs.getString("description"), rs.getString("date"), rs.getString("email"), rs.getInt("idcat_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Evenement1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("idcat_id"));
        
        //col_recruteur.setCellValueFactory(new PropertyValueFactory<>("idrecruteur_id"));
        
        
       
        
        
       /* colaction.setCellValueFactory((param) -> {
            
            SimpleObjectProperty property = new SimpleObjectProperty();
            JFXButton rep = new JFXButton("Participer");
            rep.setOnAction((event) -> {
                
//                int a = tvevent.getSelectionModel().getSelectedItem().getMaxp();
//                int b = a+1;
//                System.out.println(b);

            //int a= Integer.parseInt(tfParticipants.getText());
           Evenement e = new Evenement(tableE.getSelectionModel().getSelectedItem().getId(), tfNom.getText(),taDescription.getText()  , Integer.parseInt(tfParticipants.getText())   );
            
           
            
          //EvenementCrud ec= new EvenementCrud();
          
          ec.ParticiperEvent(e);
          
          System.out.println("Participation ajoute");
            
          
         
                
            //ec.UpdateEvent(e);   
            });
            
            property.setValue(rep);
            
            
            
            return property;
       });
        
        */

        tableE.setItems(obevent);
        RechercherEvenment();
        setCellValueFromTable();

    }
    
  
    private void setCellValueFromTable() {
        tableE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Evenement e = tableE.getItems().get(tableE.getSelectionModel().getSelectedIndex());
                tfId.setText(e.getId());
                tfNom.setText(e.getNom());
                tfDate.setText(e.getDate());
                //Date.valueOf(eventDate.setText(e.getDate()));
                //Date.valueOf(tfDate.setText(e.getDate()));
                
                taDescription.setText(e.getDescription());
                tfEmail.setText(e.getEmail());
               // tfParticipants.setText(String.valueOf(e.getNbp()));

            }

        });
    }
    
    @FXML
    private void ActualiserEvenement(ActionEvent event) {
        
         obevent.clear();

        try {

            String requete = "SELECT id,nom,date,description,email,idcat_id FROM evenement ";
            ResultSet rs;
            try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    obevent.add(new Evenement(rs.getString("id"), rs.getString("nom"), rs.getString("description"), rs.getString("date") , rs.getString("email"),rs.getInt("idcat_id")));
                    tableE.setItems(obevent);
                }
            }
            rs.close();
          

        } catch (SQLException ex) {
            Logger.getLogger(Evenement1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void RechercherEvenment() {

        /* Connection con = MyConnection.getInstance().getCnx();
        
        EvenementCrud ec = new EvenementCrud();
        
        String nom = rNom.getText();
        
        ec.RechercheEvenement(nom); */
 /*col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_participant.setCellValueFactory(new PropertyValueFactory<>("participants")); */
 /* Employee emp1 = new Employee(112, "AMIT", "ams@gmail.com", "Finance", 30000);
        Employee emp2 = new Employee( 115, "Peter", "peter@gmail.com", "Defence System", 40000);
        Employee emp3 = new Employee( 116, "SAM", "sam@gmail.com", "Radar Anaysist", 80000);
        Employee emp4 = new Employee(117, "Jhon", "jhon@gmail.com", "Ground Staff", 80000);   */
        //dataList.addAll(emp1,emp2, emp3, emp4);
        // Wrap the ObservableList in a FilteredList (initially display all data).
        
        FilteredList<Evenement> filteredEvent = new FilteredList<>(obevent, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rNom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredEvent.setPredicate(evenement -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (evenement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (evenement.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evenement> sortedEvent = new SortedList<>(filteredEvent);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedEvent.comparatorProperty().bind(tableE.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableE.setItems(sortedEvent);

    }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
        
        
           /*EvenementCrud ec = new EvenementCrud();

        Evenement e = new Evenement(tfNom.getText(),tfDate.getText(),taDescription.getText(),tfEmail.getText());

        ec.addEvent2(e);

        tableE.getItems().add(e);
        
        tfNom.clear();
        tfDate.clear();
        taDescription.clear();
        tfEmail.clear();*/
           
           
        Connection con = MyConnection.getInstance().getCnx();
        
  

      EvenementCrud ec = new EvenementCrud();
        Evenement e = new Evenement();
        
        e.setNom(tfNom.getText());
         
        e.setDate(tfDate.getText());
        
        e.setDescription(taDescription.getText());
        e.setEmail(tfEmail.getText());
        //e.setNbp(Integer.parseInt(tfParticipants.getText()));
        e.setIdcat_id(Integer.parseInt(catC.getValue().toString()));

        if (e.getNom().isEmpty() || e.getDate().isEmpty() || e.getDescription().isEmpty() || e.getEmail().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir tous les champs ");
            alert.showAndWait();
        } else {

            ec.addEvent(e);
            JOptionPane.showMessageDialog(null, "Evenement Ajoute");
            
                  //Mails ms = new Mails();
                  
                  
       
            
            Mails.sendMail1(tfEmail.getText(), "Ajout avec succes", "Votre evenement est bien ajoute");

            tfNom.clear();
            //tfDate.clear();
            taDescription.clear();
            tfEmail.clear();


            
            //Mails ms = new Mails();
            
            //ms.sendMail1(tfEmail.getText(), "Ajout avec succes", "Votre evenement est bien ajoute");
            
            

            

            //JOptionPane.showMessageDialog(null, "evenement ajouté");
        } 

    }

  @FXML
    private void ModifierEvenement(ActionEvent event) {
        
         Connection con = MyConnection.getInstance().getCnx();
        EvenementCrud ec = new EvenementCrud();
        Evenement e = new Evenement();

        e.setId(tfId.getText());
        e.setNom(tfNom.getText());
        e.setDate(tfDate.getText());
        e.setDescription(taDescription.getText());
        e.setEmail(tfEmail.getText());
        
        //e.setIdcat_id(Integer.parseInt(catC.getValue().toString()));
        

        ec.UpdateEvent(e);
         
        tfId.clear();
        tfNom.clear();
        tfDate.clear();
        taDescription.clear();
        tfEmail.clear();
        //tfParticipants.clear();

        JOptionPane.showMessageDialog(null, "evenement Modifie");
    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
        
        Connection con = MyConnection.getInstance().getCnx();

        EvenementCrud ec = new EvenementCrud();

        String id = tfId.getText();

        ec.DeleteEvent(id);

        tfId.clear();
        tfNom.clear();
        tfDate.clear();
        taDescription.clear();
        tfEmail.clear();
        //tfParticipants.clear();
        JOptionPane.showMessageDialog(null, "evenement Supprime");
    }

    @FXML
    private void EnvoyerEmail(ActionEvent event) {
        
         //Evenement e = new Evenement();
        Mails ms = new Mails();

        ms.sendMail1(tfEmail.getText(), "Alerte", "Bonjour vous participez dans l'evenement ");
        
        tfId.clear();
        tfNom.clear();
        tfDate.clear();
        taDescription.clear();
        tfEmail.clear();
        //tfParticipants.clear();
    }

    private void StatEvenement(ActionEvent event) {
        
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stat.fxml"));
          Parent root1;
        try {
            root1 = (Parent) fxmlLoader.load();
        
          Stage stage = new Stage();
          stage.initModality(Modality.APPLICATION_MODAL);
          
          
          stage.setTitle("ABC");
          stage.setScene(new Scene(root1));
          stage.show();
          
          } catch (IOException ex) {
            Logger.getLogger(Evenement1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
      @FXML
    private void ImprimerPDF(ActionEvent event) throws SQLException {
        
        EvenementCrud ec = new EvenementCrud();

        ec.imprimer();
    }

    @FXML
    private void ParticiperEvenement(ActionEvent event) {
        
        Evenement e = new Evenement();
        
       //Mails.sendMail1(tfTel.getText(), "Participation ajoute", "Mr/Mme "+ Integer.parseInt(recC.getValue().toString())+" votre participation a l'evenement "+eventC.getValue()+" ");
       loadList();
       
          System.out.println("preparing to send sms");
        SMS send = new SMS();
        send.SendSMS("naddd", "Syrine2003","Mme/Mr "+ Integer.parseInt(recC.getValue().toString())+"  Votre Participation est bien enregistrer a l'evenement  "+eventC.getValue()+" Merci ! ",tfTel.getText() , "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        System.out.println("sms is sent");
       
    }
    
    private void loadList(){
        
        Evenement  e = new Evenement();
        
        
        
        
        String a = "Mr/Mme "+ Integer.parseInt(recC.getValue().toString())+" participe à l'evenement "+eventC.getValue()+" ";
        
        listEvent.getItems().add(a);
        e.getNbp();
        
         
        
    
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
    private void checkuser(MouseEvent event) throws IOException {
          FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("FrontUserFXML.fxml"));
            Parent root = loader.load();
            FrontUserFXMLController dc = loader.getController();
            user.getScene().setRoot(root);
    }
    @FXML
    private void checktest(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("FrontFXML.fxml"));
            Parent root = loader.load();
            FrontFXMLController dc = loader.getController();
            test.getScene().setRoot(root);
    }

    @FXML
    private void checkfree(MouseEvent event) throws IOException {
                 FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("AddFreelancer.fxml"));
            Parent root = loader.load();
            AddFreelancerController dc = loader.getController();
            freelancer.getScene().setRoot(root);
    }

}
