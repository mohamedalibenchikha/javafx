/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Categorie;
import devit.workit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author ASUS
 */
public class CategorieCRUD {
        private Connection c = MyConnection.getInstance().getCnx();

     public boolean AddCategorie(Categorie c){
        try {
            String requete = "INSERT INTO categorie (nom,photo,help)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPhoto());
            pst.setString(3, c.getHelp());
            pst.executeUpdate();
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             return false;
        }
    }
    public ObservableList<Categorie> ShowCategorie() throws SQLException {
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `categorie` ";
        try {
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Categorie(rs.getInt("ID"),rs.getString("nom"), rs.getString("photo"), rs.getString("help")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
    public boolean Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM categorie WHERE id="+t;
        try {

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
             return true ;
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
             return false ;
        }

    }
    public boolean Modifier(Categorie c) throws SQLException {

       try {
           String requete = "UPDATE `categorie` SET `nom`=? ,`photo`=?,`help`=? WHERE `id`=?";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1,c.getNom());
        pst.setString(2,c.getPhoto());
        pst.setString(3,c.getHelp());   
        pst.setInt(4,c.getId()); 
        pst.executeUpdate();
            return true ;
            } catch (SQLException e) {
                return false ;
            }
    
    }
}
