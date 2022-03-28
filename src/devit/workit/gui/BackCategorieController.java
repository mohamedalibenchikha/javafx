/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import devit.workit.entites.Categorie;
import devit.workit.services.CategorieCRUD;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BackCategorieController implements Initializable {

    @FXML
    private Button categorie;
    @FXML
    private Button offre12;
    @FXML
    private TableView<Categorie> taffiche;
     @FXML
    private TableColumn<Categorie, Integer> tid;
    @FXML
    private TableColumn<Categorie, String> tnom;
    @FXML
    private TableColumn<Categorie, String> tphoto;
    @FXML
    private TableColumn<Categorie, String> thelp;
    CategorieCRUD cat = new CategorieCRUD();
    @FXML
    private JFXTextField ttfid;
    @FXML
    private JFXTextField ttfnom;
    @FXML
    private JFXButton img;
    @FXML
    private JFXTextField ttfhelp;
    @FXML
    private ImageView imgButton;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private Button evente;
    @FXML
    private Button user;
    @FXML
    private Button freelancer;
    @FXML
    private Button forum;
    @FXML
    private Button certificat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ttfid.setDisable(true);
        try {
            refrech();
        } catch (Exception ex) {
        }
    }    

    @FXML
    private void checkcategorie(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackCategorie.fxml"));
            Parent root = loader.load();
            BackCategorieController dc = loader.getController();
            categorie.getScene().setRoot(root);
    }

    @FXML
    private void checkoffre(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackOffre.fxml"));
            Parent root = loader.load();
            BackOffreController dc = loader.getController();
            offre12.getScene().setRoot(root);
    }

    @FXML
    private void affiche(MouseEvent event) {
        Categorie res = taffiche.getItems().get(taffiche.getSelectionModel().getSelectedIndex());
             String ImageUrl = "/picture/";
             img.setText(res.getPhoto());
             
           //  imgButton.setImage(ImageUrl + res.getPhoto());
              Image image = new Image(ImageUrl + res.getPhoto());
            imgButton.setImage(image);
           ttfnom.setText(res.getNom());
            ttfhelp.setText(res.getHelp());
            ttfid.setText(String.valueOf(res.getId()));
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, Exception {
        Alert a = new Alert(Alert.AlertType.NONE);
         a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setHeaderText(null);
        a.setContentText("Are you sure to delete this Categorie");
       // a.showAndWait();
        Optional <ButtonType> action = a.showAndWait();
        if(action.get()== ButtonType.OK)
        {           
            CategorieCRUD cat = new CategorieCRUD();
           boolean test = false ;
            test = cat.Supprimer(Integer.valueOf(ttfid.getText()));
             refrech();
            if(test)
            {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/catsupp.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play(); 
            }
            else
            {
                  File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/erreur.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
            }
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, Exception {
          // SAUVEGARDE DANS LA BASE
            CategorieCRUD cat = new CategorieCRUD();
            Categorie c = new Categorie();
            String tNom = ttfnom.getText();
             Image image1=null;
             image1 = imgButton.getImage();
             String photo = null;
             photo = saveToFileImageNormal(image1);
           // String tPhoto = imgButton.getImage();
            String tHelp = ttfhelp.getText();
            c.setNom(tNom);
            c.setPhoto(photo);
            c.setHelp(tHelp);
            if ("".equals(tNom) || "".equals(photo) || "".equals(tHelp))
            {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/erreur.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
            }
            else
            {
           boolean test = false ;
            test = cat.AddCategorie(c);
             refrech();
            if(test)
            {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/ajoutcat.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play(); 
            }
            }
    }
private void refrech() throws Exception {
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tphoto.setCellValueFactory(new PropertyValueFactory<>("photo"));

        thelp.setCellValueFactory(new PropertyValueFactory<>("help"));
        taffiche.getItems().clear();
        taffiche.setItems(cat.ShowCategorie());

    }
  @FXML
    private void addimage(MouseEvent event) {
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
    }
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:/Users/ASUS/Desktop/Sprint_web/public/picture");
        File dir1 = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/picture");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
       // String name1 = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        File outputFile1 = new File(dir1, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        BufferedImage bImage1 = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
               ImageIO.write(bImage1, "png", outputFile1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    @FXML
    private void modifier(ActionEvent event) throws SQLException, Exception {
          // SAUVEGARDE DANS LA BASE
            CategorieCRUD cat = new CategorieCRUD();
            Categorie c = new Categorie();
            String tNom = ttfnom.getText();
           // String photo = img.getText();
        
            Image image1=null;
             image1 = imgButton.getImage();
             String photo = null;
             photo = saveToFileImageNormal(image1);
            String tHelp = ttfhelp.getText();
            c.setNom(tNom);
            c.setPhoto(photo);
            c.setHelp(tHelp);
            c.setId(Integer.valueOf(ttfid.getText()));
            
            if ("".equals(tNom) || "".equals(photo) || "".equals(tHelp))
            {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/erreur.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
            refrech() ;
            }
            else
            {
           boolean test = false ;
            test = cat.Modifier(c);
             refrech();
            if(test)
            {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/catmodif.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play(); 
            }
            }
    }

    @FXML
    private void checkevente(MouseEvent event) throws IOException {
           FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackEvent.fxml"));
            Parent root = loader.load();
            BackEventController dc = loader.getController();
            evente.getScene().setRoot(root);
    }

    @FXML
    private void checkuser(MouseEvent event) throws IOException {
          FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("BackUser.fxml"));
            Parent root = loader.load();
            BackUserController dc = loader.getController();
            user.getScene().setRoot(root);
    }

    @FXML
    private void checkfreelancer(MouseEvent event) throws IOException {
         FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Back.fxml"));
            Parent root = loader.load();
            Back dc = loader.getController();
            freelancer.getScene().setRoot(root);
    }

    @FXML
    private void checkforum(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("forumBackFXML.fxml"));
            Parent root = loader.load();
            ForumBackFXMLController dc = loader.getController();
            forum.getScene().setRoot(root);
    }

    @FXML
    private void checkcertif(MouseEvent event) throws IOException {
        FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("Certificat.fxml"));
            Parent root = loader.load();
            CertificatController dc = loader.getController();
            certificat.getScene().setRoot(root);
    }

    
}
