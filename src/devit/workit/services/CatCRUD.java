/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Cat;
import devit.workit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CatCRUD {
      private Connection c = MyConnection.getInstance().getCnx();
     public void addCategorie(Cat c) {

        String requete = "INSERT INTO cat (nom) VALUES ('" + c.getNom() + "' )";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addCategorie2(Cat c) {

        String requete = "INSERT INTO cat (nom ) " + " VALUES (?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.executeUpdate();
            System.out.println("Categorie ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Cat> showCategorie() {

        List<Cat> listC = new ArrayList();
        String requete = "SELECT * FROM cat";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Cat c = new Cat();
                c.setId(rs.getString(1));
                c.setNom(rs.getString("nom"));
                listC.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listC;

    }

    public void DeleteCategorie(String id) {

        String requete = "DELETE FROM cat  WHERE id='" + id + "'";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("categorie supprime!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void UpdateCategorie(Cat c){
        
        String requete = "UPDATE cat SET nom=?"+" WHERE id = " + c.getId();
        
        try {
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.executeUpdate();
            System.out.println("categorie modifie!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
     public List<Cat> RechercheCategorie(String nom) {
       
         
         
         List<Cat> listC = new ArrayList<>();
         String requete;
         requete = "SELECT * FROM cat  WHERE nom = ' "+ nom +" ' ";
         
        try {

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            System.out.println("Categorie trouve!");
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                
                Cat c = new Cat();
                c.setId(rs.getString(1));
                c.setNom(rs.getString("nom"));
                
                listC.add(c);

            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listC;

    }
    
}
