/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.AlertDialog;
import devit.workit.entites.BadWords;
import devit.workit.entites.Comment;
import devit.workit.entites.Offre;
import devit.workit.entites.Postuler;
import devit.workit.entites.Random;
import devit.workit.entites.Recruteur;
import devit.workit.entites.SessionWorkit;
import devit.workit.entites.staticEntity;
import devit.workit.services.CommentCRUD;
import devit.workit.services.OffreCRUD;
import devit.workit.services.PostulerCRUD;
import devit.workit.services.RandomCRUD;
import devit.workit.services.SendMailOffre;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MakeOffreController implements Initializable {

    private static int faute = 0;
    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    @FXML
    private Button categorie;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button of;
    staticEntity se = new staticEntity();
    SendMailOffre mailer = new SendMailOffre();
    Random ran = new Random();
    RandomCRUD rancrud = new RandomCRUD();
    Recruteur rec= SessionWorkit.utilisateur;   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        try {
            // TODO
            show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void checkcategorie(MouseEvent event) {
    }

    public void show() throws FileNotFoundException {
        
        OffreCRUD of = new OffreCRUD();
        CommentCRUD com = new CommentCRUD();
        List<Offre> liste = of.getOffre(se.getId());
        VBox exercice = new VBox();
        ScrollPane Co = new ScrollPane();
        VBox comm = new VBox();
        for (Offre oo : liste) {
            se.setOffre_name(oo.getNom());
            VBox ligne = new VBox();
            HBox col = new HBox();
            VBox ll = new VBox();
            ligne.setSpacing(20);
            ll.setSpacing(10);
            col.setSpacing(40);
            List<Comment> liste2 = com.getAll(se.getId());
            for (Comment cc : liste2) {
                HBox hbb = new HBox();
                Label l = new Label(cc.getContent());
                l.setText("Commented At " + cc.getCreated_at() + "\n" + cc.getAuthor_name() + " : " + cc.getContent());
                hbb.getChildren().add(l);
                comm.getChildren().add(hbb);
            }
            Button bt = new Button("Ajouter un commentaire");
            TextArea tf = new TextArea();
            double height = 150; //making a variable called height with a value 150
            double width = 300;  //making a variable called height with a value 300
            tf.setPrefHeight(height);  //sets height of the TextArea to 150 pixels 
            tf.setPrefWidth(width);    //sets width of the TextArea to 300 pixels
            HBox hb = new HBox();
            hb.getChildren().add(tf);
            hb.getChildren().add(bt);
            hb.getChildren().add(comm);

            bt.setOnAction(e -> {
                BadWords.loadConfigs();
                {
                    if (tf.getText().equals("")) {
                        AlertDialog.showNotification("Error !", "champ vide de contenu", AlertDialog.image_cross);

                    } else if (BadWords.filterText(tf.getText())) {
                        faute++;
                        AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);
                    } else {

                        Comment x = new Comment();
                        x.setOffre_id(oo.getId());
                        x.setContent(tf.getText());
                        String author_name = rec.getPrenom();
                        int idrec = rec.getId();
                        java.util.Date datej = new java.util.Date();
                        java.util.Date utilDate = new java.util.Date();
                        //  java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        String created_at = dtf.format(now);
                        x.setCreated_at(created_at);
                        x.setAuthor_name(author_name);
                        x.setIdrecruteur_id(idrec);
                        com.AddComment(x);
                        AlertDialog.showNotification("Commentaire", "Commentaire ajouter", AlertDialog.image_checked);
                    }
                    try {
                        acc();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            Co.setContent(comm);
            /**
             * **************************
             */
            Label Titre = new Label(oo.getTitle());
            Titre.setPrefSize(600, 15);
            Titre.setAlignment(Pos.CENTER);
            ligne.getChildren().add(Titre);
            ligne.setPadding(new Insets(30, 250, 30, 250));
            ImageView imgButton = new ImageView();
            String ImageUrl = "/picture/";
            ImageView tvalid = new ImageView();
            PostulerCRUD pos = new PostulerCRUD();
            int nb1 = 0;
            nb1 = pos.returnRec(rec.getId());
            Image image = new Image("/images/check.png");
            int nb5 = 0;
            nb5 = pos.returnvalid(rec.getId(), se.getId());
            if (nb5 == 1) {

                image = new Image("/images/check.png");
            } else {
                image = new Image("/images/notcheck.png");
            }

            int nb = 0;
            nb = pos.returnOffre(se.getId());
            tvalid.setImage(image);
            tvalid.setFitHeight(50);
            tvalid.setFitWidth(50);
            Label tpost = new Label();
            tpost.setText(String.valueOf(nb) + " ont postuler a ce offre");
            Image image2 = new Image(ImageUrl + oo.getLogo());
            imgButton.setImage(image2);
            imgButton.setFitHeight(200);
            imgButton.setFitWidth(300);
            Text desc = new Text(oo.getDescription());
            Label view = new Label("Views: " + String.valueOf(oo.getAbn()));
            ll.getChildren().addAll(desc, view);
            col.getChildren().addAll(imgButton, ll);
            Label coach = new Label("Auteur : " + oo.getNom());
            ligne.getChildren().addAll(col, coach, tvalid, tpost, hb);
            VBox vb = new VBox(20);             // gap between components is 20
            vb.setAlignment(Pos.CENTER);        // center the components within VBox
            vb.getChildren().addAll(timerLabel);
            vb.setLayoutY(30);
            int t = 0;
            if (faute < 2) {
                vb.setVisible(false);
                vb.setManaged(false);
            } else {
                bt.setVisible(false);
                vb.setVisible(true);
                vb.setManaged(true);
                Label ban = new Label();
                ban.setText("cooldown in progress");
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME + 1),
                                new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
                timeline.setOnFinished(e -> {
                    ban.setText("COUNTDOWN FINISHED");
                    bt.setVisible(true);
                    faute = 0;
                    AlertDialog.showNotification("COUNTDOWN", "COUNTDOWN FINISHED", AlertDialog.image_checked);
                });

                vb.getChildren().add(ban);

            }
            exercice.getChildren().addAll(ligne, vb, comm);
            tvalid.setOnMouseClicked(e -> {
                if (pos.returnRec(rec.getId()) == rec.getId()) {
                    boolean test = false;
                    try {
                        test = pos.Supprimer(rec.getId());
                        acc();
                    } catch (SQLException ex) {
                        Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (test == true) {
                        Image image6 = new Image("/images/check.png");
                        tvalid.setImage(image6);
                    }
                } else {
                    Postuler p = new Postuler();
                    p.setOffre_id(se.getId());
                    p.setRecruteur_id(rec.getId());
                    boolean test;
                    try {
                        pos.addPost(p);
                    } catch (SQLException ex) {
                        Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        acc();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MakeOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Image image5 = new Image("/images/check.png");
                    tvalid.setImage(image5);
                }
            });
        }

        scroll.setContent(exercice);

    }

    @FXML
    private void checkoffre(MouseEvent event) throws IOException {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("DevitOffre.fxml"));
        Parent root = loader.load();
        DevitOffreController dc = loader.getController();
        of.getScene().setRoot(root);
    }

    private void check() throws IOException {
        show();
    }

    private void acc() throws FileNotFoundException {
        show();
    }

}
