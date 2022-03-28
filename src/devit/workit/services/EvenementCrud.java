/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Cat;
import devit.workit.entites.Evenement;
import devit.workit.tools.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.aspose.pdf.Document;
//import com.aspose.pdf.HtmlLoadOptions;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import devit.workit.entites.SessionWorkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author Nadia
 */
public class EvenementCrud {

   

    public void addEvent(Evenement e) {

        String requete = "INSERT INTO evenement (nom,date,description,email,idcat_id,idrecruteur_id) VALUES ('" + e.getNom() + "','" + e.getDate() + "','" + e.getDescription() + "','" + e.getEmail() + "','" + e.getIdcat_id() + "','" + SessionWorkit.utilisateur.getId() + "')";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addEvent2(Evenement e) {

        String requete = "INSERT INTO evenement (nom,date,description,email,) VALUES ('" + e.getNom() + "','" + e.getDate() + "','" + e.getDescription() + "','" + e.getEmail() + "')";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, e.getNom());
            //pst.setString(2,e.getDate() );
            //pst.setString(2, e.getDate());
            pst.setString(3, e.getDescription());
            pst.setString(4, e.getEmail());

            pst.executeUpdate();
            System.out.println("Evenement ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Evenement> showEvent() {

        List<Evenement> listC = new ArrayList();
        String requete = "SELECT * FROM evenement";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                Evenement e = new Evenement();
                e.setId(rs.getString("id"));
                e.setNom(rs.getString("nom"));
                //e.setDate(rs.getString("date"));
                e.setDate(rs.getString("date"));
                e.setDescription(rs.getString("description"));
                e.setEmail(rs.getString("email"));
                e.setNbp(rs.getInt("nbp"));

                listC.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listC;

    }

    public void DeleteEvent(String id) {

        String requete = "DELETE FROM evenement  WHERE id='" + id + "'";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement supprime!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   public void UpdateEvent(Evenement e) {

        String requete = "UPDATE evenement SET nom=?,date=?,description=?,email=?"+" WHERE id = " + e.getId();
        
        try {
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDate());
            pst.setString(3, e.getDescription());
            pst.setString(4, e.getEmail());
            pst.executeUpdate();
            System.out.println("categorie modifie!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    

       

    public List<Evenement> RechercheEvenement(String nom) {

        List<Evenement> listE = new ArrayList<>();

        try {

            Evenement e = new Evenement();

            Statement st = MyConnection.getInstance().getCnx().createStatement();

            String requete = "SELECT * FROM evenement WHERE nom = '" + nom + "%'";

            System.out.println("Evenement trouve!");

            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                e.setId(rs.getString(1));
                e.setNom(rs.getString("nom"));

                listE.add(e);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listE;

    }

    /* public List<Evenement> read2(){
        List<Evenement> listE = new ArrayList<>();
        try{
         String req = "SELECT * FROM evenement";
         Statement st =  MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                listE.add(new Evenement(rs.getString("id"), rs.getString("nom"), rs.getString("description"),rs.getString("date"),rs.getString("email"),rs.getInt("nbp")));
            }
         
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        
        return listE;
    } */
    
    
    public void imprimer() throws SQLException {
        try {
            
             Statement stm = MyConnection.getInstance().getCnx().createStatement();
            /* Define the SQL query */
            ResultSet query_set = stm.executeQuery("SELECT nom,date,description,email,idcat_id FROM evenement");
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document() {};
            try {
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Liste Evenement.pdf"));
            } catch (FileNotFoundException | DocumentException ex) {
                Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            my_pdf_report.open();
            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(5);
            //create a cell object
            PdfPCell table_cell;
            
                table_cell=new PdfPCell(new Phrase("nom"));
                table_cell.setBackgroundColor((BaseColor.GREEN));
                my_report_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase("date"));
                table_cell.setBackgroundColor((BaseColor.GREEN));
                my_report_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase("description"));
                table_cell.setBackgroundColor((BaseColor.GREEN));
                my_report_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase("email"));
                table_cell.setBackgroundColor((BaseColor.GREEN));
                my_report_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase("idcat_id"));
                table_cell.setBackgroundColor((BaseColor.GREEN));
                my_report_table.addCell(table_cell);
            
            while (query_set.next()) {
               
                String dept_id = query_set.getString("nom");
                table_cell=new PdfPCell(new Phrase(dept_id));
                my_report_table.addCell(table_cell);
                String dept_name=query_set.getString("date");
                table_cell=new PdfPCell(new Phrase(dept_name));
                my_report_table.addCell(table_cell);
                String manager_id=query_set.getString("description");
                table_cell=new PdfPCell(new Phrase(manager_id));
                my_report_table.addCell(table_cell);
                String location_id=query_set.getString("email");
                table_cell=new PdfPCell(new Phrase(location_id));
                my_report_table.addCell(table_cell);
                String location_niveau=query_set.getString("idcat_id");
                table_cell=new PdfPCell(new Phrase(location_niveau));
                my_report_table.addCell(table_cell);
            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            
        } catch (DocumentException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public List<Evenement> selectIDP() {
         
             ObservableList listid = FXCollections.observableArrayList();
             try {
           
             
             Statement stm = MyConnection.getInstance().getCnx().createStatement();
             int nb =0;
             
             String query = "SELECT id  FROM cat ";
             ResultSet rst = stm.executeQuery(query);
             while(rst.next()){
                 listid.add(rst.getString("id"));
                 nb++;
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
        return listid;
    }
    
    public List<Evenement> selectIDR() {
         
             ObservableList listid = FXCollections.observableArrayList();
             try {
           
             
             Statement stm = MyConnection.getInstance().getCnx().createStatement();
             int nb =0;
             
             String query = "SELECT id  FROM recruteur ";
             ResultSet rst = stm.executeQuery(query);
             while(rst.next()){
                 listid.add(rst.getString("id"));
                 nb++;
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
        return listid;
    }
    
    public List<Evenement> selectIDE() {
         
             ObservableList listid = FXCollections.observableArrayList();
             try {
           
             
             Statement stm = MyConnection.getInstance().getCnx().createStatement();
             int nb =0;
             
             String query = "SELECT nom  FROM evenement ";
             ResultSet rst = stm.executeQuery(query);
             while(rst.next()){
                 listid.add(rst.getString("nom"));
                 nb++;
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
        return listid;
    }
    
    
    
    
    
}

