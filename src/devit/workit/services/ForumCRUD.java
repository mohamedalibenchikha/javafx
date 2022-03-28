/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import devit.workit.entites.Forum;
import devit.workit.entites.SessionWorkit;
import devit.workit.tools.MyConnection;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP-PC
 */
public class ForumCRUD {
    
    
    
    public void addPerson2(Forum t) {
        
        String requete = "INSERT INTO forum(sujet,probleme,theme,date,recruteur_id)"
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getSujet());
            pst.setString(2, t.getProbleme());
            pst.setDate(4, Date.valueOf(t.getDate().toLocalDate()));
            pst.setString(3, t.getTheme());
            pst.setInt(5, SessionWorkit.utilisateur.getId());
            pst.executeUpdate();
            System.out.println("Sujet de discussion ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    public ObservableList<Forum> read() {
        
       ObservableList<Forum> ls = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `forum`";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                int id = rs.getInt("id");
               
                String sujet = rs.getString("sujet");
                String probleme = rs.getString("probleme");
                String theme = rs.getString("theme");
                String aa = rs.getString("users_ids_views");

                Forum a = new Forum(id, sujet, probleme, theme);
                a.setViews(aa);
                LocalDateTime date = Instant.ofEpochMilli(rs.getDate("date").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                a.setDate(date);
                ls.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForumCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    
       public void Supprimer(Forum t) {
        String requete = "DELETE FROM forum WHERE id=?";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Sujet de discussion supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
       
       public void Modifier(Forum t) {
        String requete = "UPDATE forum set sujet=?,probleme=?,theme=? WHERE id=?";
        try {
            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(4, t.getId());
            // pst.setInt(5, t.getIdU());
            pst.setString(1, t.getSujet());
            pst.setString(2, t.getProbleme());
            pst.setString(3, t.getTheme());
            // pst.executeUpdate();
           
            System.out.println("Sujet de discussion modifier !");
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
        public ObservableList<Forum> filtrer(String nomcat) throws SQLException {

        Connection cnx;
        cnx = MyConnection.getInstance().getCnx();
        String req = "select * from  forum  where theme LIKE '" + nomcat + "%'";
        PreparedStatement pt = cnx.prepareStatement(req);

        ObservableList<Forum> le = FXCollections.observableArrayList();
        ResultSet rs = pt.executeQuery(req);

        while (rs.next()) {

            int id = rs.getInt("id");
           
            String sujet = rs.getString("sujet");
            String probleme = rs.getString("probleme");
           // String date = rs.getString("date");
            String theme = rs.getString("theme");

            Forum C = new Forum( sujet, probleme, theme);

            le.add(C);
        }

        return le;

    }
        public int calcul_soft() {
        int i = 0;

        List<Forum> list = new ArrayList<>();

        String requete = "SELECT * FROM forum  ";
        try {

            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                if ("soft skills".equals(rs.getString("theme"))) {
                    i++;
                };

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }

    public int calcul_finance() {

        int j = 0;

        List<Forum> list = new ArrayList<>();

        String requete = "SELECT * FROM forum ";
        try {

            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {

                if ("finance".equals(rs.getString("theme"))) {
                    j++;
                };

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return j;
    }

    public int calcul_manangement() {

        int k = 0;
        List<Forum> list = new ArrayList<>();

        String requete = "SELECT * FROM forum ";
        try {

            PreparedStatement pst
                    = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {

                if ("manangement".equals(rs.getString("theme"))) {
                    k++;
                };
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return k;
    }
    
     public ObservableList<Forum> getTrierParTheme() throws SQLException {
        ObservableList<Forum> arr = FXCollections.observableArrayList();
        String req = "select * from forum ORDER BY theme DESC ";
        PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

           int id = rs.getInt("id");
           
            String sujet = rs.getString("sujet");
            String probleme = rs.getString("probleme");
           // String date = rs.getString("date");
            String theme = rs.getString("theme");

            Forum C = new Forum( sujet, probleme, theme);

            arr.add(C);
        }

        System.out.println("trié avec succés!! ");
        return arr;
    }
     
     
     public List<Forum> RecherchePardate_pub(String search) {
        String req = "SELECT * FROM forum WHERE date="+(char)34+search+(char)34;
        List<Forum> list = new ArrayList<>();
        try {
            PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
              
                  int id = rs.getInt("id");
               
                String sujet = rs.getString("sujet");
                String probleme = rs.getString("probleme");
                String theme = rs.getString("theme");
                  Forum C = new Forum( sujet, probleme, theme);

            list.add(C);
            }
            System.out.println(" forum trouvé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
}
     
   public int view(int id, String views){

       String requete = "UPDATE forum set users_ids_views=? WHERE id=?";
       try {
           
           PreparedStatement pst
                   = MyConnection.getInstance().getCnx().prepareStatement(requete);
           // pst.setInt(5, t.getIdU());
           pst.setInt(2, id);
           pst.setString(1, views);

           // pst.executeUpdate();

           System.out.println("nbre de vue !");
           pst.executeUpdate();
           return 1;
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
       return 0;
   }
     
}
