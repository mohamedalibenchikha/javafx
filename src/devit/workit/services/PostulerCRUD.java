/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Offre;
import devit.workit.entites.Postuler;
import devit.workit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class PostulerCRUD {
     private Connection c = MyConnection.getInstance().getCnx();
    public ObservableList<Postuler> ShowPostuler() throws SQLException {
        ObservableList<Postuler> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `postuler` ";
        try {
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Postuler(rs.getInt("ID"),rs.getInt("offre_id"), rs.getInt("recruteur_id"), rs.getString("accepte")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
      public ArrayList<Postuler> getAllPos(int id){
        ArrayList<Postuler> liste = new ArrayList<>();
        try {                    
           String request="SELECT * FROM postuler where offre_id="+id;
            Statement ste = MyConnection.getInstance().getCnx().createStatement();
            ResultSet result = ste.executeQuery(request);
            while (result.next()) {
                Postuler p = new Postuler();
                 p.setId(result.getInt("id"));
                p.setOffre_id(result.getInt("offre_id"));
                p.setRecruteur_id(result.getInt("recruteur_id"));
                p.setAccepte(result.getString("accepte"));
                liste.add(p);
            }
          
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return liste;
    }
    public boolean Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM postuler WHERE recruteur_id="+t;
        try {

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
             return true ;
        } catch (SQLException ex) {
          //  System.out.println(ex.getMessage());
             return false ;
        }

    }
    public int returnOffre(int id){
               int nb = 0 ;
        String requete = "SELECT * FROM postuler WHERE offre_id="+id;
        try {
             PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery(requete);
            while(rs.next()) {
                nb++ ;
            }
        } catch (SQLException ex) {
        }
        return nb;
    } 
     public String Verif(int id,String Code){
               String nb=null ;
        String requete = "SELECT * FROM postuler WHERE offre_id="+id;
        try {
             PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery(requete);
            while(rs.next()) {
                nb ="hi";
            }
        } catch (SQLException ex) {
        }
        return nb;
    } 
     public int returnMail(int idrec , int idoff){
         String code ="envoyé" ;
         int nb = 0 ;
        String requete = "SELECT * FROM postuler where offre_id='"+idoff+"' AND recruteur_id='"+idrec+"' AND accepte='"+code+"'";
        try {
             PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery(requete);
            while(rs.next()) {
                nb = 1 ;
            }
        } catch (SQLException ex) {
        }
        return nb;
    } 
     public int returnvalid(int idrec , int idoff){
         int nb = 0 ;
        String requete = "SELECT * FROM postuler where offre_id='"+idoff+"' AND recruteur_id='"+idrec+"'";
        
        try {
             PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery(requete);
            while(rs.next()) {
                nb = 1 ;
            }
        } catch (SQLException ex) {
        }
        return nb;
    } 
     public int returnRec(int id){
               int nb = 0 ;
        String requete = "SELECT * FROM postuler WHERE recruteur_id="+id;
        try {
             PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery(requete);
            while(rs.next()) {
                nb = rs.getInt("recruteur_id");
            }
        } catch (SQLException ex) {
        }
        return nb;
    } 
    public void addPost(Postuler p) throws SQLException{
        try {
            String requete = "INSERT INTO postuler (offre_id,recruteur_id)"
                    + "VALUES (?,?)";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getOffre_id());
            pst.setInt(2, p.getRecruteur_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public boolean Modifier(Postuler p) throws SQLException {

       try {
           String requete = "UPDATE `postuler` SET `accepte`=? WHERE `offre_id`=? AND `recruteur_id`=?";
                PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
                 pst.setString(1,p.getAccepte()); 
                 pst.setInt(2,p.getOffre_id()); 
                 pst.setInt(3,p.getRecruteur_id());
                pst.executeUpdate();
            return true ;
            } catch (SQLException e) {
                return false ;
            }
    }
    
    
}
