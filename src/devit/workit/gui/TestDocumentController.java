/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.gui;

import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import devit.workit.entites.Test;
import devit.workit.services.ServiceTest;
import devit.workit.tools.MyConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author walid belhadj
 */
public class TestDocumentController implements Initializable {

    @FXML
    private TextField tfn;
    @FXML
    private TextField tfq5;
    @FXML
    private TextField tfq2;
    @FXML
    private TextField tfq3;
    @FXML
    private TextField tfq1;
    @FXML
    private TextField tfr1;
    @FXML
    private TextField tfr2;
    @FXML
    private TextField tfr3;
    @FXML
    private TextField tfq4;
    @FXML
    private TextField tfr4;
    @FXML
    private TextField tfr5;
    @FXML
    private TableColumn<Test, String> colnom;
    @FXML
    private TableColumn<Test, String> colq1;
    @FXML
    private TableColumn<Test, String> colr1;
    @FXML
    private TableColumn<Test, String> colq2;
    @FXML
    private TableColumn<Test, String> colr2;
    @FXML
    private TableColumn<Test, String> colq3;
    @FXML
    private TableColumn<Test, String> colr3;
    @FXML
    private TableColumn<Test, String> colq4;
    @FXML
    private TableColumn<Test, String> colr4;
    @FXML
    private TableColumn<Test, String> colq5;
    @FXML
    private TableColumn<Test, String> colr5;
    @FXML
    private TableView<Test> table1;
    ServiceTest sr;
    @FXML
    private TextField RechTest;
    @FXML
    private Button btncer;
    @FXML
    private Button evente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sr =new ServiceTest();
        ObservableList<Test> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(sr.AfficherTest());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

     
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colq1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        colr1.setCellValueFactory(new PropertyValueFactory<>("R1"));

        
        colq2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        colr2.setCellValueFactory(new PropertyValueFactory<>("R2"));
        colq3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        colr3.setCellValueFactory(new PropertyValueFactory<>("R3"));

        
        colq4.setCellValueFactory(new PropertyValueFactory<>("Q4"));
        colr4.setCellValueFactory(new PropertyValueFactory<>("R4"));
        colq5.setCellValueFactory(new PropertyValueFactory<>("Q5"));

      
        colr5.setCellValueFactory(new PropertyValueFactory<>("R5"));
        

        table1.setItems(list3);
        
    }    
    
    @FXML
    private void AjouterTest(ActionEvent event) {
         ServiceTest st = new ServiceTest();
        if ( tfn.getText().isEmpty() || tfq1.getText().isEmpty() ||  tfr1.getText().isEmpty()|| tfq2.getText().isEmpty()|| tfr2.getText().isEmpty()|| tfq3.getText().isEmpty()|| tfr3.getText().isEmpty()|| tfq4.getText().isEmpty()|| tfr4.getText().isEmpty()|| tfq5.getText().isEmpty()|| tfr5.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
            
            
            JOptionPane.showMessageDialog(null, "Test ajouté");
        
        Test t = new Test();
        t.setNom(tfn.getText());       
        t.setQ1(tfq1.getText());
        t.setR1(tfr1.getText());
        t.setQ2(tfq2.getText());
        t.setR2(tfr2.getText());
        t.setQ3(tfq3.getText());
        t.setR3(tfr3.getText());
        t.setQ4(tfq4.getText());
        t.setR4(tfr4.getText());
        t.setQ5(tfq5.getText());
        t.setR5(tfr5.getText());
        st.AjouterTest(t);
             tfn.clear();
             tfq1.clear();
             tfr1.clear();
             tfq2.clear();
             tfr2.clear();
             tfq3.clear();
             tfr3.clear();
             tfq4.clear();
             tfr4.clear();
             tfq5.clear();
             tfr5.clear();
       

          ObservableList<Test> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(st.AfficherTest());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

     
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colq1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        colr1.setCellValueFactory(new PropertyValueFactory<>("R1"));

        
        colq2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        colr2.setCellValueFactory(new PropertyValueFactory<>("R2"));
        colq3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        colr3.setCellValueFactory(new PropertyValueFactory<>("R3"));

        
        colq4.setCellValueFactory(new PropertyValueFactory<>("Q4"));
        colr4.setCellValueFactory(new PropertyValueFactory<>("R4"));
        colq5.setCellValueFactory(new PropertyValueFactory<>("Q5"));

      
        colr5.setCellValueFactory(new PropertyValueFactory<>("R5"));
       // colid.setCellValueFactory(new PropertyValueFactory<>("id"));

        table1.setItems(list3);
        }
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
        String r1 = colr1.getCellData(index);
        String q2 = colq2.getCellData(index);
        String r2 = colr2.getCellData(index);
        String q3 = colq3.getCellData(index);
        String r3 = colr3.getCellData(index);
        String q4 = colq4.getCellData(index);
        String r4 = colr4.getCellData(index);
        String q5 = colq5.getCellData(index);
        String r5 = colr5.getCellData(index);
      


//   
       
       
        tfn.setText(nom);
        tfq1.setText(q1);
        tfr1.setText(r1);
        tfq2.setText(q2);
        tfr2.setText(r2); 
        tfq3.setText(q3);
        tfr3.setText(r3); 
        tfq4.setText(q4);
        tfr4.setText(r4); 
        tfq5.setText(q5);
        tfr5.setText(r5); 
    }
    private void refreshList(ServiceTest sv){
        ObservableList<Test> list3 = null;
      

        try {
            list3 = FXCollections.observableArrayList(sv.AfficherTest());
            
        } catch (SQLException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colq1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        colr1.setCellValueFactory(new PropertyValueFactory<>("R1"));

        
        colq2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        colr2.setCellValueFactory(new PropertyValueFactory<>("R2"));
        colq3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        colr3.setCellValueFactory(new PropertyValueFactory<>("R3"));

        
        colq4.setCellValueFactory(new PropertyValueFactory<>("Q4"));
        colr4.setCellValueFactory(new PropertyValueFactory<>("R4"));
        colq5.setCellValueFactory(new PropertyValueFactory<>("Q5"));

      
        colr5.setCellValueFactory(new PropertyValueFactory<>("R5"));
        

        table1.setItems(list3);
        
    }

    @FXML
    private void SupprimerTest(ActionEvent event) {
       int index = table1.getSelectionModel().getSelectedItem().getId();
        this.sr.SupprimerTest(index);
        System.out.println("eeee:"+index);
                 JOptionPane.showMessageDialog(null, "test supprimé");
                 
                 refreshList(sr);
    }
    

    @FXML
   private void ModifierTest(ActionEvent event) throws SQLException {
    ServiceTest st = new ServiceTest();
        if ( tfn.getText().isEmpty() || tfq1.getText().isEmpty() ||  tfr1.getText().isEmpty()|| tfq2.getText().isEmpty()|| tfr2.getText().isEmpty()|| tfq3.getText().isEmpty()|| tfr3.getText().isEmpty()|| tfq4.getText().isEmpty()|| tfr4.getText().isEmpty()|| tfq5.getText().isEmpty()|| tfr5.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le formulaire ");
            alert.showAndWait();
        } else {
   
       
       Test r = table1.getSelectionModel().getSelectedItem();
        
        r.setNom(tfn.getText());
        System.out.println("el tfn"+tfn.getText());
        System.out.println("el r "+r.getNom());
       r.setQ1(tfq1.getText());
        r.setR1(tfr1.getText());
        r.setQ2(tfq2.getText());
       r.setR2(tfr2.getText());
        r.setQ3(tfq3.getText());
        r.setR3(tfr3.getText());
        r.setQ4(tfq4.getText());
        r.setR4(tfr4.getText());
        r.setQ5(tfq5.getText());
        r.setR5(tfr5.getText());
        st.ModifierTest(r);
         //System.out.println("eeee:"+table1.getSelectionModel().getSelectedItem().getId());
        refreshList(st);
         JOptionPane.showMessageDialog(null, "test modifé");
         }
   }

    @FXML
    private void RechercherTest(ActionEvent event) {
         
        String nom = RechTest.getText();
        Test t = sr.rechercherTest(nom);
        refreshListbyrech(t);
       /* table1.get*/
        tfn.setText(t.getNom());
        tfq1.setText(t.getQ1());
        tfr1.setText(t.getR1());
        tfq2.setText(t.getQ1());
        tfr2.setText(t.getR1());
        tfq3.setText(t.getQ1());
        tfr3.setText(t.getR1());
        tfq4.setText(t.getQ1());
        tfr4.setText(t.getR1());
        tfq5.setText(t.getQ1());
        tfr5.setText(t.getR1());
       
    }
    
    private void refreshListbyrech(Test t){
        ObservableList<Test> list3 = null;
      

        list3 = FXCollections.observableArrayList(sr.findById(t.getId()));

       
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colq1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        colr1.setCellValueFactory(new PropertyValueFactory<>("R1"));

        
        colq2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        colr2.setCellValueFactory(new PropertyValueFactory<>("R2"));
        colq3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        colr3.setCellValueFactory(new PropertyValueFactory<>("R3"));

        
        colq4.setCellValueFactory(new PropertyValueFactory<>("Q4"));
        colr4.setCellValueFactory(new PropertyValueFactory<>("R4"));
        colq5.setCellValueFactory(new PropertyValueFactory<>("Q5"));

      
        colr5.setCellValueFactory(new PropertyValueFactory<>("R5"));
        

        table1.setItems(list3);
        
    }

    @FXML
    private void clear(ActionEvent event) {
         refreshList(sr);
          tfn.clear();
             tfq1.clear();
             tfr1.clear();
             tfq2.clear();
             tfr2.clear();
             tfq3.clear();
             tfr3.clear();
             tfq4.clear();
             tfr4.clear();
             tfq5.clear();
             tfr5.clear();
    }

    @FXML
    private void btncer(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Certificat.fxml"));
            Stage stage = (Stage) btncer.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CertificatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PdfTest(ActionEvent event) throws FileNotFoundException, Exception {
        
         File out = new File("tabletest.pdf") ; 
        FileOutputStream fos = new FileOutputStream(out) ;
        PDF pdf = new PDF(fos) ; 
        Page page = new Page(pdf, A4.PORTRAIT)  ; 
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD) ;
        Font f2 = new Font(pdf, CoreFont.HELVETICA) ;
        Table table = new Table() ; 
        List<List<Cell>> tabledata = new ArrayList<>() ;       
        List<Cell> tablerow = new ArrayList<>() ; 
        Cell cell = new Cell(f1,colnom.getText());
        tablerow.add(cell) ; 
        
          cell = new Cell(f1,colq1.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,colr1.getText());
        tablerow.add(cell) ;
         cell = new Cell(f1,colq2.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,colr2.getText());
        tablerow.add(cell) ;
         cell = new Cell(f1,colq3.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,colr3.getText());
        tablerow.add(cell) ;
         cell = new Cell(f1,colq4.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,colr4.getText());
        tablerow.add(cell) ;
         cell = new Cell(f1,colq5.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,colr5.getText());
        tablerow.add(cell) ;
        
       
        
        
        
    tabledata.add(tablerow) ; 
    
    
     
    
   Test co = new Test(); 
      co=table1.getSelectionModel().getSelectedItem();  
        Cell nc = new Cell(f2, co.getNom()) ; 
        Cell q1c = new Cell(f2,co.getQ1()) ;
        Cell r1c = new Cell(f2,co.getR1());
        Cell q2c = new Cell(f2,co.getQ2()) ;
        Cell r2c = new Cell(f2,co.getR2());
        Cell q3c = new Cell(f2,co.getQ3()) ;
        Cell r3c = new Cell(f2,co.getR3());
        Cell q4c = new Cell(f2,co.getQ4()) ;
        Cell r4c = new Cell(f2,co.getR4());
        Cell q5c = new Cell(f2,co.getQ5()) ;
        Cell r5c = new Cell(f2,co.getR5());
         
        
        
      
        
        tablerow = new ArrayList<>() ; 
        tablerow.add(nc) ; 
        tablerow.add(q1c) ;
        tablerow.add(r1c) ;
         tablerow.add(q2c) ;
        tablerow.add(r2c) ;
         tablerow.add(q3c) ;
        tablerow.add(r3c) ;
         tablerow.add(q4c) ;
        tablerow.add(r4c) ;
         tablerow.add(q5c) ;
        tablerow.add(r5c) ;
      
        
    tabledata.add(tablerow) ; 
    table.setData(tabledata);
    table.setPosition(10f, 60f);
    table.setColumnWidth(0, 90f); 
    table.setColumnWidth(1, 90f); 
    table.setColumnWidth(2, 90f); 
    table.setColumnWidth(3, 90f); 
    
    
    
    while(true){
    table.drawOn(page) ; 
    if(!table.hasMoreData()){
    table.resetRenderedPagesCount(); 
    break ; 
    
    }
    
    page=new Page(pdf,A4.PORTRAIT) ; 
    
    
    }
    
    pdf.flush();
    fos.close(); 
        System.out.println("saved to "+out.getAbsolutePath());
        
        
    Notifications notificationBuilder = Notifications.create()
            .title("Téléchargement complet")
            .text("Test PDF a été bien téléchargé")
            .hideAfter(Duration.seconds(7))
            .position(Pos.BASELINE_RIGHT)
            .onAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            System.out.println("PDF téléchargé");
            
            }
                
            });
    notificationBuilder.showConfirm();
        
    }

    @FXML
    private void excel(MouseEvent event) {
           try{
        String reqselect = "SELECT * FROM test";
       Connection cnx = MyConnection.getInstance().getCnx();
       PreparedStatement pr = cnx.prepareStatement(reqselect);
       ResultSet rs=pr.executeQuery();
          XSSFWorkbook wb = new XSSFWorkbook();
          XSSFSheet sheet = wb.createSheet("test deitails");
          XSSFRow header = sheet.createRow(0);
          header.createCell(0).setCellValue("Nom");
          header.createCell(1).setCellValue("Q1");
          header.createCell(2).setCellValue("R1");
          header.createCell(3).setCellValue("Q2");
          header.createCell(4).setCellValue("R2");
          header.createCell(5).setCellValue("Q3");
          header.createCell(6).setCellValue("R3");
          header.createCell(7).setCellValue("Q4");
          header.createCell(8).setCellValue("R4");
          header.createCell(9).setCellValue("Q5");
          header.createCell(10).setCellValue("R5");
            int index = 1;
            while (rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("nom"));
                row.createCell(1).setCellValue( rs.getString("q1"));
                row.createCell(2).setCellValue(rs.getString("r1"));
                row.createCell(3).setCellValue(rs.getString("q2"));
                row.createCell(4).setCellValue(rs.getString("r2"));
                row.createCell(5).setCellValue(rs.getString("q3"));
                row.createCell(6).setCellValue(rs.getString("r3"));
                row.createCell(7).setCellValue(rs.getString("q4"));
                row.createCell(8).setCellValue(rs.getString("r4"));
                row.createCell(9).setCellValue(rs.getString("q5"));
                row.createCell(10).setCellValue(rs.getString("r5"));
                index++;
        }
       FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to Excel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Microsoft Office Excel 2010", "*.xlsx"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null){
            FileOutputStream fileout = new FileOutputStream(selectedFile.getAbsoluteFile());
            wb.write(fileout);
            fileout.close();
        }
              }catch (SQLException ex){
              System.out.println(ex.getMessage());
                
            } catch (IOException exd) {
                System.out.println(exd.getMessage());
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


  
    
}
