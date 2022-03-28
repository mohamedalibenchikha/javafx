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
import devit.workit.entites.Comment;
import devit.workit.entites.Offre;
import devit.workit.entites.Postuler;
import devit.workit.entites.Random;
import devit.workit.entites.Recruteur;
import devit.workit.entites.SessionWorkit;
import devit.workit.entites.staticEntity;
import static devit.workit.gui.AddOffreController.saveToFileImageNormal;
import devit.workit.services.CommentCRUD;
import devit.workit.services.CommenterCRUD;
import devit.workit.services.ForumCRUD;
import devit.workit.services.OffreCRUD;
import devit.workit.services.PostulerCRUD;
import devit.workit.services.RandomCRUD;
import devit.workit.services.SendMailOffre;
import static devit.workit.services.SendMailOffre.readQRCode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DevitOffreController implements Initializable {

    @FXML
    private Button offre;
    @FXML
    private ScrollPane scroll;
    staticEntity se = new staticEntity();
    @FXML
    private ScrollPane scroll2;
    SendMailOffre send = new SendMailOffre();
    @FXML
    private Button evente;
    @FXML
    private Button forum;
    @FXML
    private Button user;
    @FXML
    private Button freelancer;
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

    public void show1() {
        VBox btn = new VBox();
        ScrollPane Co = new ScrollPane();
        Button btnad = new Button();
        btnad.setText("Ajouter une offre");
        btnad.setStyle("-fx-background-color:#f95151;");
        btn.getChildren().add(btnad);
        scroll2.setContent(btn);
        btnad.setOnAction(e -> {
            try {
                FXMLLoader loader
                        = new FXMLLoader(getClass().getResource("AddOffre.fxml"));
                Parent root = loader.load();
                AddOffreController dc = loader.getController();
  
                btnad.getScene().setRoot(root);
               
            } catch (IOException ex) {
                Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public static String saveToFileImageNormal(Image image) throws SQLException {

        String ext = "jpg";
        File dir = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/QrCode");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        // String name1 = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    public void show() throws FileNotFoundException {
        OffreCRUD of = new OffreCRUD();
        CommentCRUD com = new CommentCRUD();
        List<Offre> liste = of.getAllOffre();
        VBox offres = new VBox();
        offres.setStyle("-fx-border-color: red;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n");
        ScrollPane Co = new ScrollPane();
        VBox comm = new VBox();
        Recruteur rec= SessionWorkit.utilisateur;
        for (Offre oo : liste) {
            if ("recruteur".equals(rec.getType())) {
                show1();
            }
            PostulerCRUD pos = new PostulerCRUD();
            HBox hb = new HBox();
            int a2 = 0;
            
            a2 = pos.returnMail(rec.getId(), oo.getId());

            if (a2 == 1) {
                Button btmail = new Button("Read Qr Code");
                hb.getChildren().add(btmail);
                btmail.setOnMouseClicked(e -> {
                    ImageView imgButton = new ImageView();
                    FileChooser fc = new FileChooser();
                    FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                    FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                    fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                    File selectedFile = fc.showOpenDialog(null);
                    try {
                        BufferedImage bufferedImage = ImageIO.read(selectedFile);
                        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                        imgButton.setImage(image);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    Image image1 = null;
                    image1 = imgButton.getImage();
                    String photo = null;
                    try {
                        photo = saveToFileImageNormal(image1);
                        System.out.println(photo);

                    } catch (SQLException ex) {
                        Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String filePath = "C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/QrCode/" + photo;
                    String charset = "UTF-8";
                    Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    try {
                        Random ran = new Random();
                        RandomCRUD re = new RandomCRUD();
                        int testcode = 0;
                        String QRcode = readQRCode(filePath, charset, hintMap);
                        testcode = re.returnCode(QRcode);
                        //System.out.println(readQRCode(filePath, charset, hintMap));
                        if (testcode == 1) {
                            AlertDialog.showNotification("QrCode", "Offre Confirmée", AlertDialog.image_checked);
                            re.Supprimer(QRcode);
                            Postuler postule = new Postuler();
                            postule.setRecruteur_id(SessionWorkit.utilisateur.getId());
                            postule.setOffre_id(oo.getId());
                            String acc = "Accepter";
                            postule.setAccepte(acc);
                            pos.Modifier(postule);
                            btmail.setVisible(false);
                            //pos.Modifier(postule);
                        } else {
                            AlertDialog.showNotification("Error !", "Vérifier le code Qr", AlertDialog.image_cross);
                        }
                    } catch (IOException ex) {
                        AlertDialog.showNotification("Error !", "Vérifier le code Qr", AlertDialog.image_cross);
                    } catch (NotFoundException ex) {
                        AlertDialog.showNotification("Error !", "Vérifier le code Qr", AlertDialog.image_cross);
                    } catch (SQLException ex) {
                        AlertDialog.showNotification("Error !", "Vérifier le code Qr", AlertDialog.image_cross);
                    }

                });
            }
            VBox ligne = new VBox();
            HBox col = new HBox();
            VBox ll = new VBox();
            ligne.setSpacing(20);
            ll.setSpacing(10);
            col.setSpacing(40);
            Button bt = new Button("Make Offre");
            Button btdetail = new Button("Check Informations");
            btdetail.setStyle("-fx-background-color:#09b2e0;");
            bt.setStyle("-fx-background-color:#8efb56;");
            int nb5 = 0;
            nb5 = pos.returnvalid(rec.getId(), oo.getId());
            if (nb5 == 1) {
                bt.setText("Remove Offre");
                bt.setStyle("-fx-background-color:#E0494b;");
            }

            if (rec.getId() == oo.getIdrecruteur_id()) {
                Button bts = new Button("Delete this offre");
                bts.setStyle("-fx-background-color:#E0494b;");

                hb.getChildren().add(bts);
                hb.getChildren().add(btdetail);

                bts.setOnAction(e -> {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Confirmation");
                    a.setHeaderText(null);
                    a.setContentText("Are you sure to delete this offre");
                    Optional<ButtonType> action = a.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        OffreCRUD offe = new OffreCRUD();
                        boolean test = false;
                        try {
                            test = offe.Supprimer(oo.getId());
                            AlertDialog.showNotification("Offre", "Offre supprimée", AlertDialog.image_checked);
                            acc();
                        } catch (SQLException ex) {
                            Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btdetail.setOnAction(e ->{
                    se.setOffre_name(oo.getNom());
                    se.setId(oo.getId());
                    FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("DetailsOffre.fxml"));
            Parent root;
                    try {
                        root = loader.load();
                        DetailsOffreController dc = loader.getController();
            btdetail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                });
            } else {
                hb.getChildren().add(bt);
            }
            bt.setOnAction(e -> {
                se.setId(oo.getId());
                se.setNom(oo.getNom());
                se.setViews(oo.getAbn());
                se.setDescription(oo.getDescription());
                se.setImage(oo.getLogo());
                se.setAccepte(se.getId());
                boolean test = false;
                try {
                    Offre o = new Offre(oo.getId(), oo.getAbn());
                    test = of.NbViews(o);
                } catch (SQLException ex) {
                }
                FXMLLoader loader
                        = new FXMLLoader(getClass().getResource("MakeOffre.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    MakeOffreController dc = loader.getController();
                    bt.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(DevitOffreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            /**
             * **************************
             */
            Label Titre = new Label("Title : " + oo.getTitle());
            Titre.setPrefSize(600, 15);
            ligne.getChildren().add(Titre);
            ligne.setPadding(new Insets(30, 250, 30, 250));
            ImageView imgButton = new ImageView();
            String ImageUrl = "/picture/";
            Image image2 = new Image(ImageUrl + oo.getLogo());
            imgButton.setImage(image2);
            imgButton.setFitHeight(200);
            imgButton.setFitWidth(300);
            Text desc = new Text("Description :\n" + oo.getDescription());
            Label view = new Label("Views: " + String.valueOf(oo.getAbn()));
            Label nom = new Label("Nom d'offre : " + oo.getNom());
            ll.getChildren().addAll(nom, desc, view);
            col.getChildren().addAll(imgButton, ll);
            ligne.getChildren().addAll(col, hb);
            offres.getChildren().addAll(ligne);
        }
        scroll.setContent(offres);
    }
    private void acc() throws FileNotFoundException {
        show();
    }

    @FXML
    private void checkevent(MouseEvent event) throws IOException {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("Evenement1.fxml"));
        Parent root = loader.load();
        Evenement1Controller dc = loader.getController();
        evente.getScene().setRoot(root);
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
