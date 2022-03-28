package devit.workit.gui;


import devit.workit.entites.Projet;
import devit.workit.services.ProjetCRUD;
import edu.devit.entities.Freelancer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackProjet implements Initializable {


    public TableColumn tab_id;
    public TableColumn tab_userid;
    @FXML
    TableView<Projet> tabprojet = new TableView<>();
    private ObservableList<Projet> masterData = FXCollections.observableArrayList();


    public TableColumn tab_nomp;
    public TableColumn tab_desc;
    public TableColumn tab_type;
    public TableColumn tab_salary;
    public TableColumn tab_time;
    public TableColumn tab_logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AfficherProjet();
        } catch (SQLException ex) {
            Logger.getLogger(BackProjet.class.getName()).log(Level.SEVERE, null, ex);
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

        tabprojet.setItems(liste);
    }

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
}
