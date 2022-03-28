/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.ForumCRUD;
import devit.workit.gui.ForumBackFXMLController;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */
public class StatController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private Button forum_admin;
    @FXML
    private Label accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ForumCRUD s = new ForumCRUD(); 
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("soft skills: " + s.calcul_soft(), s.calcul_soft()),
                new PieChart.Data("finance: " + s.calcul_finance(), s.calcul_finance()),
                new PieChart.Data("manangement: " + s.calcul_manangement(), s.calcul_manangement())
        );
        piechart.setData(pieChartData);
    }

   /* @FXML
     public void retourBack(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("forumBackFXML.fxml"));

        Parent root = loader.load();
        ForumBackFXMLController fXMLController = loader.getController();
            fXMLController.setLs(new ForumCRUD().read());
            fXMLController.getLs().forEach((forum) -> {
            int count = new CommenterCRUD().countCommentersByForumId(forum.getId());
            int sum = new CommenterCRUD().ratingSum(forum.getId());
            int moy = 0;
            if (count != 0) {
                moy = sum / count;
            }
            
            switch (moy) {
                case 0: case 1:
                    forum.setRatingImage("StarVide.png");
                break;
                
                case 2: case 3: case 4:
                    forum.setRatingImage("demiStar.png");
                break;
                
                default:
                    forum.setRatingImage("Star.png");
                break;

                                    }
        });
        fXMLController.getTableforum().setItems(fXMLController.getLs());
        btnretour.getScene().setRoot(root);
    }
    */

    @FXML
    private void accueil(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackAccueil.fxml"));
            Parent root = loader.load();
            BackAccueilController dc = loader.getController();
            accueil.getScene().setRoot(root);
    }
}
