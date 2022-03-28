/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Categorie;
import devit.workit.entites.Comment;
import devit.workit.entites.Offre;
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
public class CommentCRUD {
      private Connection c = MyConnection.getInstance().getCnx();
       public boolean AddComment(Comment c){
        try {
            String requete = "INSERT INTO comment (author_name,content,created_at,offre_id,idrecruteur_id)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getAuthor_name());
            pst.setString(2, c.getContent());
            pst.setString(3, c.getCreated_at());
            pst.setInt(4, c.getOffre_id());
             pst.setInt(5, c.getIdrecruteur_id());
            pst.executeUpdate();
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             return false;
        }
    }
    public ObservableList<Comment> ShowComment() throws SQLException {
        ObservableList<Comment> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `comment` ";
        try {
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Comment(rs.getInt("ID"),rs.getString("author_name"), rs.getString("content"), rs.getString("created_at"),rs.getInt("offre_id"),rs.getInt("idrecruteur_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
    public boolean Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM comment WHERE id="+t;
        try {

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
             return true ;
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
             return false ;
        }

    }
      public ArrayList<Comment> getAll(int x){
        ArrayList<Comment> commentaires = new ArrayList<>();
        try {
            String request="SELECT * FROM comment where offre_id="+x;
            Statement ste= MyConnection.getInstance().getCnx().createStatement();
            ResultSet result = ste.executeQuery(request);
            while (result.next()) {
                //  System.out.println(result.getLong("id")+result.getString("type")+result.getString("url")+result.getString("Description"));
                Comment c = new Comment();
                c.setId(result.getInt("id"));
                c.setAuthor_name(result.getString("author_name"));
                c.setContent(result.getString("content"));
                c.setCreated_at(result.getString("created_at"));
                c.setOffre_id(result.getInt("offre_id"));
                c.setIdrecruteur_id(result.getInt("idrecruteur_id"));
                commentaires.add(c);
           //  publications.add(new Publication(result.getLong("id"),result.getString("type"),result.getString("url"),result.getString("Description")));
            }
          
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return commentaires;
    }
}
