/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.gui.CertificatController;
import devit.workit.entites.Test;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.ForumCRUD;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author walid belhadj
 */
public class FrontFXMLController implements Initializable {

     private ServiceTest sr = new ServiceTest();
    private Test test;
    @FXML
    private Pane stestpane;
   
    @FXML
    private TableView<Test> table1;
    @FXML
    private Pane testpane;
    @FXML
    private Text q1t;
    @FXML
    private TextField r1tf;
    @FXML
    private Text q2t;
    @FXML
    private TextField r2tf;
    @FXML
    private Text q3t;
    @FXML
    private TextField r3tf;
    @FXML
    private Text q4t;
    @FXML
    private TextField r4tf;
    @FXML
    private Text q5t;
    @FXML
    private TextField r5tf;
    @FXML
    private Button confirmerbtn;
    @FXML
    private Text resulttext;
    @FXML
    private Pane resultpane;
    @FXML
    private TableColumn<Test, String> colnom;
    @FXML
    private TableColumn<Test, String> colq1;
    @FXML
    private TableColumn<Test, String> colq2;
    @FXML
    private TableColumn<Test, String> colq3;
    @FXML
    private TableColumn<Test, String> colq4;
    @FXML
    private TableColumn<Test, String> colq5;
    @FXML
    private Button user;
    @FXML
    private Button freelancer;
    @FXML
    private Button offre;
    @FXML
    private Button forum;
    @FXML
    private Button evente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        ObservableList<Test> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(sr.AfficherTest());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

     
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colq1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        colq2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        colq3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        colq4.setCellValueFactory(new PropertyValueFactory<>("Q4"));
        colq5.setCellValueFactory(new PropertyValueFactory<>("Q5"));
      
        table1.setItems(list3);
        
        
    }    

    @FXML
    private void SelectTest(MouseEvent event) {
         ServiceTest st = new ServiceTest();

        int index = table1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
       
        String nom = colnom.getCellData(index);
        String q1 = colq1.getCellData(index);
        
        String q2 = colq2.getCellData(index);
        
        String q3 = colq3.getCellData(index);
        
        String q4 = colq4.getCellData(index);
        
        String q5 = colq5.getCellData(index);
        
        test=st.findById(table1.getSelectionModel().getSelectedItem().getId());
        q1t.setText(test.getQ1());
        q2t.setText(test.getQ2());
        q3t.setText(test.getQ3());
        q4t.setText(test.getQ4());
        q5t.setText(test.getQ5());
        
        stestpane.setVisible(false);
        testpane.setVisible(true);
    }

    @FXML
    private void confirmertest(ActionEvent event) {
        int note=0;
        String r1=r1tf.getText();
        String r2=r2tf.getText();
        String r3=r3tf.getText();
        String r4=r4tf.getText();
        String r5=r5tf.getText();
        Test reponses = new Test(r1,r2,r3,r4,r5);
        if(reponses.getR1().equals(test.getR1()))
        {
            note++;
        }
        if(reponses.getR2().equals(test.getR2()))
        {
            note++;
        }
        if(reponses.getR3().equals(test.getR3()))
        {
            note++;
        }
        if(reponses.getR4().equals(test.getR4()))
        {
            note++;
        }
        if(reponses.getR5().equals(test.getR5()))
        {
            note++;
        }
        resultpane.setVisible(true);
        testpane.setVisible(false);
        if(note>=3)
        {
            resulttext.setText("Félicitation! vous avez bien passé le test: votre note est "+note+"/5");
        }else{
            resulttext.setText(" vous avez échoué le test: votre note est "+note+"/5");
        }
        
    }

    @FXML
    private void test(MouseEvent event) {
        stestpane.setVisible(true);
        testpane.setVisible(false);
        resultpane.setVisible(false);
        test=null;
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
    private void checkevent(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Evenement1.fxml"));
            Parent root = loader.load();
            Evenement1Controller dc = loader.getController();
            evente.getScene().setRoot(root);
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

   