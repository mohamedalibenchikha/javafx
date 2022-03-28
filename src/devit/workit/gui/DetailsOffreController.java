/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import devit.workit.entites.AlertDialog;
import devit.workit.entites.Offre;
import devit.workit.entites.Postuler;
import devit.workit.entites.Random;
import devit.workit.entites.Recruteur;
import devit.workit.entites.SessionWorkit;
import devit.workit.entites.staticEntity;
import static devit.workit.gui.DevitOffreController.saveToFileImageNormal;
import devit.workit.services.CommentCRUD;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.ForumCRUD;
import devit.workit.services.OffreCRUD;
import devit.workit.services.PostulerCRUD;
import devit.workit.services.RandomCRUD;
import devit.workit.services.RecruteurCRUD;
import devit.workit.services.SendMailOffre;
import static devit.workit.services.SendMailOffre.readQRCode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailsOffreController implements Initializable {

    private Button of;
    @FXML
    private ScrollPane scroll;
    staticEntity se = new staticEntity();
    SendMailOffre mailer = new SendMailOffre();
    Random ran = new Random();
    RandomCRUD rancrud = new RandomCRUD();
    @FXML
    private Button user;
    @FXML
    private Button evente;
    @FXML
    private Button freelancer;
    @FXML
    private Button offre;
    @FXML
    private Button forum;
    @FXML
    private Button test;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            show();
            // TODO
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    

    public void show() throws FileNotFoundException {
        PostulerCRUD pos = new PostulerCRUD();
        RecruteurCRUD rec = new RecruteurCRUD();
        List<Postuler> liste = pos.getAllPos(se.getId());
        
        VBox postule = new VBox();
      /*  postule.setStyle("-fx-border-color: red;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n");*/
        ScrollPane Co = new ScrollPane();
        for (Postuler pp : liste) {
            List<Recruteur> clients = rec.getAllUsers(pp.getRecruteur_id());
            HBox hb = new HBox();
            HBox ligne = new HBox();
            HBox col = new HBox();
            VBox ll = new VBox();
            ligne.setSpacing(20);
            ll.setSpacing(10);
            col.setSpacing(40);
            Button bt = new Button("Confirmation "+pp.getAccepte());
              if(pp.getAccepte()=="envoyé")
                    {
                        bt.setStyle("-fx-background-color:#f50010;");
                    }
                    else if (pp.getAccepte()=="en_cours")
                    {
                      bt.setStyle("-fx-background-color:#fbff00;");  
                    }
                    else
                    {
                        bt.setStyle("-fx-background-color:#8efb56;");
                    }
            hb.getChildren().add(bt);
         for (Recruteur rr : clients) {
             
             ImageView imgButton = new ImageView();
            String ImageUrl = "/picture/";
            Image image2 = new Image(ImageUrl + rr.getPhoto());
            imgButton.setImage(image2);
            imgButton.setFitHeight(200);
            imgButton.setFitWidth(300);
             bt.setOnAction(e->{
                String random = rancrud.getAlphaNumericString(5);
                se.setIdclient(rr.getId());
                se.setIdoffre(se.getId());
                    ran.setCode(random);
                    rancrud.AddRandom(ran);
                    mailer.generate_qr(random, random);
                    try {
                        mailer.sendMail(rr.getMail());
                        System.out.println(rr.getMail());
                        AlertDialog.showNotification("Mail", "Mailer envoyée pour l'offre", AlertDialog.image_checked);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
            });
            col.getChildren().addAll(imgButton, ll);
            ligne.getChildren().addAll(col,hb);
           
         }
            //ll.getChildren().addAll(nom, desc, view);
            
           // Label rec = new Label("By  : " + SessionWorkit.getPrenom());
            //ligne.getChildren().addAll(col, rec, hb);
            postule.getChildren().addAll(ligne);
        }
        
        scroll.setContent(postule);
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
