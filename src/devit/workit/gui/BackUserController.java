/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Recruteur;
import devit.workit.services.RecruteurCRUD;
import devit.workit.entites.SessionWorkit;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Wael Belhadj
 */
public class BackUserController implements Initializable {
    
    private Recruteur recrut;
    private Stage window;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Hyperlink logouthl;
    @FXML
    private TextField rechrec;
    @FXML
    private Button rechbtn;
    @FXML
    private Button reclabtn;

  /*  public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public void setRecrut(Recruteur recrut) {
        this.recrut = recrut;
    }*/
    private Label label;
    @FXML
    private TextField fn;
    @FXML
    private TextField fp;
    @FXML
    private TextField fns;
    @FXML
    private TextField fa;
    @FXML
    private TextField fm;
    @FXML
    private TextField fnum;
    @FXML
    private TextField fmdp;
    @FXML
    private TextField fph;
    @FXML
    private TextField fco;
    @FXML
    private TableColumn<Recruteur, String> no;
    @FXML
    private TableColumn<Recruteur, String> pr;
    @FXML
    private TableColumn<Recruteur, String> nos;
    @FXML
    private TableColumn<Recruteur, String> ad;
    @FXML
    private TableColumn<Recruteur, String> ma;
    @FXML
    private TableColumn<Recruteur, Integer> num;
    @FXML
    private TableColumn<Recruteur, String> mdp;
    @FXML
    private TableColumn<Recruteur, String> ty;
    @FXML
    private TableColumn<Recruteur, String> ph;
    @FXML
    private TableColumn<Recruteur, String> co;
    @FXML
    private ComboBox ft;
    @FXML
    private TableView<Recruteur> table1;
    
    RecruteurCRUD sr;
    @FXML
    private Button offre;
    @FXML
    private Button forum;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RecruteurCRUD sr=new RecruteurCRUD();
        ObservableList<Recruteur> list1 = null;
          try {
            list1 = FXCollections.observableArrayList(sr.AfficherRecruteur());
            
        } catch (SQLException ex) {
            Logger.getLogger(BackUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList<String> list = FXCollections.observableArrayList("....","Recruteur", "Candidat");
        ft.setItems(list);
        
         no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pr.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nos.setCellValueFactory(new PropertyValueFactory<>("nomsociete"));
        ad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ma.setCellValueFactory(new PropertyValueFactory<>("mail"));
       num.setCellValueFactory(new PropertyValueFactory<>("numsociete"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type"));
        ph.setCellValueFactory(new PropertyValueFactory<>("photo"));
        co.setCellValueFactory(new PropertyValueFactory<>("competence"));
        
       
        
        table1.setItems(list1);

        
        
    }   
    
    
    

    @FXML
    private void ajouter_recru(ActionEvent event) {
        if ( fn.getText().isEmpty() || fp.getText().isEmpty() ||  fns.getText().isEmpty()|| fa.getText().isEmpty()|| fm.getText().isEmpty()|| fnum.getText().isEmpty()|| fmdp.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
          RecruteurCRUD sv = new RecruteurCRUD();
        Recruteur p = new Recruteur();
        p.setNom(fn.getText());
        p.setPrenom(fp.getText());
        p.setNomsociete(fns.getText());
        p.setAdresse(fa.getText());
        p.setMail(fm.getText());
        p.setNumsociete(parseInt(fnum.getText()));
        p.setMdp(fmdp.getText());
        p.setType((String) ft.getValue());
        p.setPhoto(fph.getText());
        p.setCompetence(fco.getText());
      
       sv.addrec(p);
       
       refreshList(sv);
    }
    }
    
      private void refreshList(RecruteurCRUD sv) {
        ObservableList<Recruteur> list1 = null;
        try {
            list1 = FXCollections.observableArrayList(sv.AfficherRecruteur());
        } catch (SQLException ex) {
            Logger.getLogger(BackUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pr.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nos.setCellValueFactory(new PropertyValueFactory<>("nomsociete"));
        ad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ma.setCellValueFactory(new PropertyValueFactory<>("mail"));
        num.setCellValueFactory(new PropertyValueFactory<>("numsociete"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type"));
        ph.setCellValueFactory(new PropertyValueFactory<>("photo"));
        co.setCellValueFactory(new PropertyValueFactory<>("competence"));
        
        table1.setItems(list1);
        
        fn.clear();
        fp.clear();
        fns.clear();
        fa.clear();
        fm.clear();
        fnum.clear();
        fmdp.clear();
        ft.setValue("...");
        fph.clear();
        fco.clear();
    }
    
    @FXML
    private void select(MouseEvent event) {
        RecruteurCRUD sv = new RecruteurCRUD();

        int index = table1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        fn.setText(no.getCellData(index));
        fp.setText(pr.getCellData(index));
        fns.setText(nos.getCellData(index));
        fa.setText(ad.getCellData(index));
        fm.setText(ma.getCellData(index));
        fnum.setText(num.getCellData(index).toString());
        fmdp.setText(mdp.getCellData(index));
        ft.setValue(ty.getCellData(index));
        fph.setText(ph.getCellData(index));
        fco.setText(co.getCellData(index));
       // fsupu.setText(id.getCellData(index).toString());
        

    }

    @FXML
    private void modifier_recru(ActionEvent event) throws SQLException {
        if ( fn.getText().isEmpty() || fp.getText().isEmpty() || fns.getText().isEmpty()|| fa.getText().isEmpty()|| fm.getText().isEmpty()  || fmdp.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
   
        RecruteurCRUD  sa = new RecruteurCRUD();
        Recruteur r = new Recruteur();
        
        r.setNom(fn.getText());
       r.setPrenom(fp.getText());
        r.setNomsociete(fns.getText());
        r.setAdresse(fa.getText());
       r.setMail(fm.getText());
        r.setMdp(fmdp.getText());
        r.setType((String) ft.getValue());
        r.setPhoto(fph.getText());
        r.setCompetence(fco.getText());
        sa.updateRecruteur(r);
        refreshList(sa);
         JOptionPane.showMessageDialog(null, "recruteur modifé");
         }
    
    }

    @FXML
    private void supp_recru(ActionEvent event) {
        RecruteurCRUD us = new RecruteurCRUD();
        Recruteur rec= us.findByEmail(fm.getText());
        if(us.deleteRecruteur(rec)) {
            JOptionPane.showMessageDialog(null, "recruteur supprimé");
            refreshList(us); 
        }
        
        /*this.sr.deleteRecruteur(sr.findByEmail(fm.getText()).getId());
                 JOptionPane.showMessageDialog(null, "recruteur supprimé");
                 refreshList(sr);*/
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) logouthl.getScene().getWindow();
            stage.close();
           /* StaticValue.utilisateur=null;
        StaticValue.utilisateur=null;*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stager = new Stage();
        stager.initModality(Modality.APPLICATION_MODAL);
        stager.initStyle(StageStyle.UNDECORATED);
        stager.setTitle("WorkIt");
        stager.setScene(new Scene(root1));
        stager.show();
    }

    @FXML
    private void recherche(ActionEvent event) {
        RecruteurCRUD s = new RecruteurCRUD();
          String nom = rechrec.getText();
           
        Recruteur t = s.rechercherRec(nom);
        System.out.println(t.getNom());
        refreshListbyrech(t);
       /* table1.get*/
        fn.setText(t.getNom());
        fp.setText(t.getPrenom());
        fns.setText(t.getNomsociete());
        fa.setText(t.getAdresse());
        fm.setText(t.getMail());
        fnum.setText(Integer.toString(t.getNumsociete()));
        fmdp.setText(t.getMdp());
        ft.setValue(t.getType());
        fph.setText(t.getPhoto());
        fco.setText(t.getCompetence());
        
    }
    
    private void refreshListbyrech(Recruteur t){
        RecruteurCRUD sa = new RecruteurCRUD();
        ObservableList<Recruteur> list3 = null;
      

        list3 = FXCollections.observableArrayList(sa.findByEmail(t.getMail()));

       
        no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pr.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nos.setCellValueFactory(new PropertyValueFactory<>("nomsociete"));

        
        ad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ma.setCellValueFactory(new PropertyValueFactory<>("mail"));
        num.setCellValueFactory(new PropertyValueFactory<>("numsociete"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));

        
        ty.setCellValueFactory(new PropertyValueFactory<>("type"));
        ph.setCellValueFactory(new PropertyValueFactory<>("photo"));
        co.setCellValueFactory(new PropertyValueFactory<>("competence"));

    
        table1.setItems(list3);
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        RecruteurCRUD sa = new RecruteurCRUD();
        rechrec.clear();
        refreshList(sa);
        
    }

   
   /* private void reclamations(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("reclaFXML.fxml"));
            Parent root = loader.load();
            ReclaFXMLController dc = loader.getController();
            reclabtn.getScene().setRoot(root);
    }*/

   

    @FXML
    private void reclabtn(ActionEvent event) throws IOException {
             FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("ReclaBack.fxml"));
            Parent root = loader.load();
            ReclaBackController dc = loader.getController();
            offre.getScene().setRoot(root);
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
    private void checkforum(MouseEvent event) {
    }

   

    
    
}
