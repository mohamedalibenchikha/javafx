/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Cat;
import devit.workit.services.CatCRUD;
import devit.workit.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Categorie3Controller implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<Cat> tableC;
    @FXML
    private TableColumn<Cat , String> col_id;
    @FXML
    private TableColumn<Cat , String> col_nom;
    @FXML
    private Button btnActualiser;
    @FXML
    private TextField rNom;
    
    ObservableList<Cat> obcat = FXCollections.observableArrayList();
    @FXML
    private TextField tfId;
    @FXML
    private Label accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection con = MyConnection.getInstance().getCnx();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from cat ");
            while (rs.next()) {
                obcat.add(new Cat(rs.getString("id"), rs.getString("nom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categorie3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        

        tableC.setItems(obcat);
        RechercherCategorie();
        setCellValueFromTable();
    }  
    
    private void setCellValueFromTable(){
        tableC.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
          
                Cat c = tableC.getItems().get(tableC.getSelectionModel().getSelectedIndex());
                tfId.setText(c.getId());
                tfNom.setText(c.getNom());

        };
        });
    }
        
        

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        
        
        Connection con = MyConnection.getInstance().getCnx();
       
       CatCRUD cc = new CatCRUD();
       Cat c = new Cat();
       c.setNom(tfNom.getText());
       
        if( c.getNom().isEmpty()   ){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setHeaderText(null);
        alert.setContentText("Merci de remplir tous les champs ");
        alert.showAndWait();
        }else {
       
       
       cc.addCategorie(c);
       
       
       
       JOptionPane.showMessageDialog(null, "Categorie ajouté");
       
       tfId.clear();
       tfNom.clear();
       
        }
    }

    @FXML
    private void ModifierCategorie(ActionEvent event) {
        
        
        Connection con = MyConnection.getInstance().getCnx();
        CatCRUD cc = new CatCRUD();
        Cat c  = new Cat();
        
        c.setId(tfId.getText());
        c.setNom(tfNom.getText());
        
        
        cc.UpdateCategorie(c);
        
        JOptionPane.showMessageDialog(null, "Categorie Modifie");
        
        tfId.clear();
        tfNom.clear();
    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) {
        
        
        
          Connection con = MyConnection.getInstance().getCnx();
        
        CatCRUD cc = new CatCRUD();
       
        
       String id = tfId.getText();

        
        cc.DeleteCategorie(id);
        
        JOptionPane.showMessageDialog(null, "Categorie Supprime");
        
        tfId.clear();
        tfNom.clear();
        
    }

    @FXML
    private void ActualiserCategorie(ActionEvent event) {
        
        
        obcat.clear();

        try {

            String requete = "SELECT * FROM cat";
            ResultSet rs;
            try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    obcat.add(new Cat(rs.getString("id"), rs.getString("nom")));
                    tableC.setItems(obcat);
                }
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Categorie3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     private void RechercherCategorie() {
        
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
        FilteredList<Cat> filteredCat = new FilteredList<>(obcat, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rNom.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredCat.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Cat> sortedCat = new SortedList<>(filteredCat);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedCat.comparatorProperty().bind(tableC.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableC.setItems(sortedCat);
        
   
        
        
    }

    @FXML
    private void accueil(MouseEvent event) throws IOException {
          FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackAccueil.fxml"));
            Parent root = loader.load();
            BackAccueilController dc = loader.getController();
            accueil.getScene().setRoot(root);
    }
    
}
