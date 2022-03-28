/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Certificat;
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
 * @author walid belhadj
 */
public class ServiceCertificat {
    
private Connection cnx = MyConnection.getInstance().getCnx();

    public void AjouterCertificat(Certificat c) {
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO `certificat`(`nom`) VALUES ('" + c.getNom()+ "')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Certificat> AfficherCertificat() throws SQLException {
       List<Certificat> certificats = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `certificat`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Certificat c = new Certificat();
                c.setId(rst.getInt("id"));
                c.setNom(rst.getString("nom"));
               
              
               certificats.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return certificats;
    }

    public int SupprimerCertificat(int idd) {
         try {
            String request = "Delete FROM certificat where id ="+idd;
            PreparedStatement pst = cnx.prepareStatement(request);
            return pst.executeUpdate(request);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
     public Certificat findById(int idd){
       Certificat rec = null;
        String req = "SELECT * FROM certificat WHERE id LIKE '"+idd+"'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
              rec = new Certificat();
            if(resultSet.next())
                rec.setId(resultSet.getInt("id"));
               rec.setNom(resultSet.getString("nom"));
               
               
                        
                
                
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return  null;
        }
        return rec;
    }

    public boolean ModifierCertificat(Certificat c) throws SQLException {
       try {
         
          
          
           String requete ="UPDATE  certificat set Nom = ?  where id=?";
            PreparedStatement pst =
     cnx.prepareStatement(requete);
    

        pst.setString(1,c.getNom());
       
        pst.setInt(2,c.getId()); 
        System.out.println("test rec !!"+c.getId());
         System.out.println(pst);
        
        pst.executeUpdate();
        
         
        
        
       
        System.out.println("certificat modifiéeeeee !!");
            return true ;
            } catch (SQLException e) {
                return false ;
    }
    }
    
}
