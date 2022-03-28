/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import devit.workit.entites.Commentaire;
import devit.workit.entites.SessionWorkit;
import devit.workit.tools.MyConnection;

/**
 *
 * @author HP-PC
 */
public class CommenterCRUD {
    
public void Ajouter(Commentaire t) {
        String requete = "INSERT INTO commenter(commentaire,forum_id,ref,rating,recruteur_id)"
                + "VALUES (?,?,?,?,?) ";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getCommentaire());
            pst.setInt(2, t.getForum_id());
            pst.setInt(3, t.getRef());
            pst.setInt(4, t.getRating());
           pst.setInt(5, SessionWorkit.utilisateur.getId());
            pst.executeUpdate();
            System.out.println("commentaire ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

public ObservableList<Commentaire> read() throws SQLException {
        ObservableList<Commentaire> l = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `commenter` ";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                String commentaire = rs.getString("commentaire");
                int forum_id = rs.getInt("forum_id");
                int ref = rs.getInt("ref");
              
               

                Commentaire a = new Commentaire(commentaire, forum_id, ref);
                a.setRating(rs.getInt("rating"));

                l.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommenterCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

public ObservableList<Commentaire> read3(String x) throws SQLException {
        ObservableList<Commentaire> l = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `commenter` WHERE forum_id = '" + x + "'";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                String commentaire = rs.getString("commentaire");
                int forum_id = rs.getInt("forum_id");
                int ref = rs.getInt("ref");
                

                Commentaire a = new Commentaire(commentaire, forum_id, ref);
                a.setRating(rs.getInt("rating"));

                l.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommenterCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

public void Supprimer(Commentaire c) {
        String requete = "DELETE FROM commenter WHERE ref=?";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getRef());
            pst.executeUpdate();
            System.out.println("Commentaire supprimer supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

public void Modifier(Commentaire t) {
        String requete = "UPDATE commenter SET forum_id=? ,commentaire=?, rating = ? WHERE ref=?";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(4, t.getRef());
            pst.setInt(1, t.getForum_id());
  
           
            pst.setString(2, t.getCommentaire());
            pst.setInt(3, t.getRating());
            pst.executeUpdate();
            System.out.println("commentaire modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

 public int countCommentersByForumId( int id)
    {
    try {
        int i=0 ;
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM commenter where forum_id ='"+id+"';");
        while (rs.next()) {
            rs.getInt("ref");
            rs.getString("commentaire");
            rs.getInt("rating");
            i++;
        }
        return i;
    } catch (SQLException ex) {
        return 0;
    }
    }
 
 
  public int ratingSum( int id)
    {
    try {
        int sum=0 ;
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM commenter where forum_id ='"+id+"';");
        while (rs.next()) {
            rs.getInt("ref");
            rs.getString("commentaire");
            int s=rs.getInt("rating");
            sum+=s;

        }
        return sum;
    } catch (SQLException ex) {
        return 0;
    }
    } 


    
}
