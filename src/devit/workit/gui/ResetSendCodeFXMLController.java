/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import devit.workit.entites.Recruteur;
import devit.workit.services.RecruteurCRUD;
import devit.workit.services.MailSender;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wael Belhadj
 */
public class ResetSendCodeFXMLController implements Initializable {

    int code;
    Recruteur u;
    @FXML
    private TextField mailtf;
    @FXML
    private Pane getcodepane;
    @FXML
    private Button getcode;
    @FXML
    private Pane panreset;
    @FXML
    private TextField codetf;
    @FXML
    private Button resetbtn;
    @FXML
    private Pane panewpass;
    @FXML
    private TextField newpasstf;
    @FXML
    private TextField confirmtf;
    @FXML
    private Button Confirmer;
    @FXML
    private Text errorlb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resetpassbtn(ActionEvent event) throws  SQLException {
        
        if(""==codetf.getText())
        {
           // msglbl.setText("enter Code");
        }
        else{
            String codee=codetf.getText();
            int cd=(Integer.parseInt(codee));
        
        RecruteurCRUD sr=new RecruteurCRUD();
        
        if(code==cd )
        {
            panreset.setVisible(false);
            panewpass.setVisible(true);
        }else{
           // msglbl.setText("Wrong Code!");
            System.out.println("wrong code");
        }
        }
    }

    /*@FXML
    private void closebtn(ActionEvent event) {
         Stage stage = (Stage) closebtn.getScene().getWindow();
         stage.close();
    }
*/
    @FXML
    private void getcode(ActionEvent event) {
        ArrayList<Recruteur> list = new ArrayList();
        u = new Recruteur(mailtf.getText(), "");
        list.add(u);
        code =u.generateCode();
        MailSender m= new MailSender();
        m.envoyerMail(mailtf.getText(), "Reset Password", "Nous avons reçu une demande de réinitialisation du mot de passe pour votre compte.\n"
                + "\n"
                +code +"\n"
                + "Saisissez ce code pour terminer la réinitialisation.\n"
                + "\n"
                + "Merci de nous aider à préserver la sécurité de votre compte.\n"
                + "\n"
                + "L’équipe WorkIt");
        u.setMail(mailtf.getText());
        getcodepane.setVisible(false);
        panreset.setVisible(true);
    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
    
        String password=newpasstf.getText();
        String passcon=confirmtf.getText();
        
        if(!password.equals(passcon))
        {
            errorlb.setText("Verify les champs !");
        }
        else
        
        {
            RecruteurCRUD b=new RecruteurCRUD();
            u.setMdp(password);
            b.resetPass(u);
            Stage stage = (Stage) Confirmer.getScene().getWindow();
            stage.close();
        }
    }

    
    

}
