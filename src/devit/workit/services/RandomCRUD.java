/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Random;
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
public class RandomCRUD {
      private Connection c = MyConnection.getInstance().getCnx();
      public boolean AddRandom(Random r){
        try {
            String requete = "INSERT INTO random (code)"
                    + "VALUES (?)";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,r.getCode());
            pst.executeUpdate();
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             return false;
        }
    }
      public ObservableList<Random> ShowRandom() throws SQLException {
        ObservableList<Random> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `random` LIMIT 1";
        try {
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Random(rs.getInt("ID"),rs.getString("code")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
         public String ShowRandom1() throws SQLException 
         {
        String requete = "SELECT * FROM `random` LIMIT 1";
        try {
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               return  rs.getString("code") ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "hi";
         }
      public boolean Supprimer(String code) throws SQLException {

        String requete = "DELETE FROM random WHERE code="+code;
        try {

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
             return true ;
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
             return false ;
        }

    }
 public ArrayList<Random> getRandom(String code){
        ArrayList<Random> liste = new ArrayList<>();
        try {                    
           String request="SELECT * FROM random WHERE code="+code;
            Statement ste = MyConnection.getInstance().getCnx().createStatement();
            ResultSet result = ste.executeQuery(request);
            while (result.next()) {
                Random r = new Random();
                 r.setId(result.getInt("id"));
                r.setCode(result.getString("code"));
                liste.add(r);
            }
          
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return liste;
    }
  public int returnCode(String code){
         int nb = 0 ;
        String requete = "SELECT * FROM random where code='"+code+"'";
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
  public String getAlphaNumericString(int n) 
    { 
  
        
        String AlphaNumericString = //"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    /*+ */"0123456789";
                                   // + "abcdefghijklmnopqrstuvxyz"; 
  
       
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
}
