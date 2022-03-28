/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Categorie;
import devit.workit.entites.Offre;
import devit.workit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class OffreCRUD {
    
     private Connection c = MyConnection.getInstance().getCnx();
      public ObservableList<Offre> returnOffre(int id){
               ObservableList<Offre> liste = FXCollections.observableArrayList();
        String requete = "SELECT * FROM offre WHERE id="+id;
        try {
             PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery(requete);
            while(rs.next()) {
                Offre offre = new Offre();
                offre.setNom(rs.getString("nom"));
                offre.setDescription(rs.getString("description"));
                offre.setAbn(rs.getInt("abn"));
                offre.setLogo(rs.getString("logo"));
                liste.add(offre);
            }
        } catch (SQLException ex) {
        }
        return liste;
    } 
       public ArrayList<Offre> getOffre(int id){
        ArrayList<Offre> liste = new ArrayList<>();
        try {                    
           String request="SELECT * FROM offre WHERE id="+id;
            Statement ste = MyConnection.getInstance().getCnx().createStatement();
            ResultSet result = ste.executeQuery(request);
            while (result.next()) {
                Offre o = new Offre();
                 o.setId(result.getInt("id"));
                o.setNom(result.getString("nom"));
                o.setEmail(result.getString("email"));
                o.setLogo(result.getString("logo"));
                o.setTitle(result.getString("title"));
                o.setDescription(result.getString("description"));
                o.setIdcategoriy_id(result.getInt("idcategoriy_id"));
                o.setAbn(result.getInt("abn"));
                o.setIdrecruteur_id(result.getInt("idrecruteur_id"));
                liste.add(o);
            }
          
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return liste;
    }
    public ObservableList<Offre> ShowOffre() throws SQLException {
        ObservableList<Offre> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `offre` ";
        try {
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Offre(rs.getInt("ID"),rs.getString("nom"), rs.getString("email"), rs.getString("logo"), rs.getString("title"), rs.getString("description"), rs.getInt("abn"),  rs.getInt("idcategoriy_id"),rs.getInt("idrecruteur_id")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
    public boolean AddOffre(Offre o)
    {
        try {
            String requete = "INSERT INTO offre (nom,email,logo,title,description,idcategoriy_id,abn,idrecruteur_id)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, o.getNom());
            pst.setString(2, o.getEmail());
            pst.setString(3, o.getLogo());
            pst.setString(4, o.getTitle());
            pst.setString(5, o.getDescription());
            pst.setInt(6, o.getIdcategoriy_id());
            pst.setInt(7, o.getAbn());
            pst.setInt(8, o.getIdrecruteur_id());
            pst.executeUpdate();
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             return false;
        }
    }
    public boolean Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM offre WHERE id="+t;
        try {

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
             return true ;
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
             return false ;
        }

    }
      public boolean NbViews(Offre off) throws SQLException {
          int nv = off.getAbn();
          nv ++ ;
       try {
           String requete = "UPDATE `offre` SET `abn`='"+nv+"' WHERE `id`="+off.getId();
                  Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            return true ;
            } catch (SQLException e) {
                return false ;
            }
    
    }
      public ArrayList<Offre> getAllOffre(){
        ArrayList<Offre> liste = new ArrayList<>();
        try {                    
           String request="SELECT * FROM offre order by abn DESC";
            Statement ste = MyConnection.getInstance().getCnx().createStatement();
            ResultSet result = ste.executeQuery(request);
            while (result.next()) {
                Offre o = new Offre();
                 o.setId(result.getInt("id"));
                o.setNom(result.getString("nom"));
                o.setEmail(result.getString("email"));
                o.setLogo(result.getString("logo"));
                o.setTitle(result.getString("title"));
                o.setDescription(result.getString("description"));
                o.setIdcategoriy_id(result.getInt("idcategoriy_id"));
                o.setAbn(result.getInt("abn"));
                o.setIdrecruteur_id(result.getInt("idrecruteur_id"));
                liste.add(o);
            }
          
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return liste;
    }
}
