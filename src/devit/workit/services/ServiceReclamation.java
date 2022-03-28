/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Reclamation;
import devit.workit.entites.Recruteur;
import devit.workit.services.RecruteurCRUD;
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

/**
 *
 * @author Wael Belhadj
 */
public class ServiceReclamation{
     private Connection cnx = MyConnection.getInstance().getCnx();
      private ResultSet res;

  

    public void addrecla(Reclamation r) {
         try {
            String requete = "INSERT INTO reclamation (recruteur_id,nom,description)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getRecruteur_id());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getDescription());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public List<Reclamation> AfficherRecla() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `reclamation`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
               Reclamation c=new Reclamation();
               
                c.setNom(rst.getString("nom"));
                c.setDescription(rst.getString("description"));
                
                reclamations.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reclamations;
       
    }

    public boolean deleteRecruteur(Reclamation r) {
         try {
             System.out.println(r.getId());
            String request = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
             System.out.println("deleted!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
       
    }
    
    public Reclamation findByName(String nomm){
        Reclamation rec = null;
        String req = "SELECT * FROM reclamation WHERE nom=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nomm);
              rec = new Reclamation();
              res = ps.executeQuery();
            if(res.next()){
               int id = res.getInt("id");
               int recruteur_id = res.getInt("recruteur_id");
               String nom = res.getString("nom");
               String description = res.getString("description");
              
               rec = new Reclamation(id,recruteur_id,nom,description);
                System.out.println(rec.toString());
            }            
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return  null;
        }
        return rec;
    }
    
    
}
