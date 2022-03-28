/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Postuler;
import devit.workit.entites.Recruteur;
import devit.workit.entites.SessionWorkit;
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
 * @author ASUS
 */
public class RecruteurCRUD {
    private Connection c = MyConnection.getInstance().getCnx();
    private ResultSet res;
      public void addrec(Recruteur r) {
       try {
            Statement stm = c.createStatement();
            String password = r.getMdp();
            String query = "INSERT INTO `recruteur`(`nom`, `prenom`, `nomsociete`, `adresse`, `mail`,`numsociete`,`mdp`,`type`,`photo`,`competence`) VALUES ('" + r.getNom()+ "','" + r.getPrenom()+ "','" +r.getNomsociete()+ "','" +r.getAdresse()+ "','" +r.getMail() + "','" +r.getNumsociete()+ "','" +password+ "','" +r.getType()+ "','" +r.getPhoto()+ "','" +r.getCompetence()+ "')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RecruteurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public List<Recruteur> AfficherRecruteur() throws SQLException {
         List<Recruteur> recruteurs = new ArrayList<>();
        try {
            Statement stm = c.createStatement();
            String query = "SELECT * FROM `recruteur`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
               Recruteur c=new Recruteur();
                
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setNomsociete(rst.getString("nomsociete"));
                c.setAdresse(rst.getString("adresse"));
                c.setMail(rst.getString("mail"));
                c.setNumsociete(rst.getInt("numsociete"));
                c.setMdp(rst.getString("mdp"));
                c.setType(rst.getString("type"));
                c.setPhoto(rst.getString("photo"));
                c.setCompetence(rst.getString("competence"));
                recruteurs.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecruteurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recruteurs;

       
    }
       public Recruteur findByEmail(String email){
        Recruteur rec = null;
        String req = "SELECT * FROM recruteur WHERE mail=?";
        try {
            PreparedStatement ps = c.prepareStatement(req);
            ps.setString(1, email);
              rec = new Recruteur();
              res = ps.executeQuery();
            if(res.next()){
               int id = res.getInt("id");
               String nom = res.getString("nom");
               String prenom = res.getString("prenom");
               String nomsociete = res.getString("nomsociete");
               String adresse = res.getString("adresse");
               String mail = res.getString("mail");
               int numsociete = res.getInt("numsociete");
               String mdp = res.getString("mdp");
               String type = res.getString("type");
               String photo = res.getString("photo");
               String competence = res.getString("competence");
               rec = new Recruteur(id,nom,prenom,nomsociete,adresse,mail,numsociete,mdp,type,photo,competence);
                System.out.println(rec.toString());
            }            
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return  null;
        }
        return rec;
    }
       public boolean updateRecruteur(Recruteur r) throws SQLException {
      Recruteur rec = this.findByEmail(r.getMail());
        rec.setAdresse(r.getAdresse());
        rec.setCompetence(r.getCompetence());
        rec.setMail(r.getMail());
        rec.setMdp(r.getMdp());
        rec.setNom(r.getNom());
        rec.setNomsociete(r.getNomsociete());
        rec.setPhoto(r.getPhoto());
        rec.setPrenom(r.getPrenom());
        rec.setType(r.getType());
        
      try {
           String requete ="UPDATE recruteur set Nom = ? , prenom= ?,nomsociete= ?,adresse=?,mail=?,numsociete=?,mdp=?,type=?,photo=?,competence=?  where id=?";
            PreparedStatement pst =
                    c.prepareStatement(requete);
                    

        pst.setString(1,rec.getNom());
        pst.setString(2,rec.getPrenom());
        pst.setString(3,rec.getNomsociete());   
        pst.setString(4,rec.getAdresse());
        pst.setString(5,rec.getMail());
        pst.setInt(6,rec.getNumsociete());
        pst.setString(7,rec.getMdp());
        pst.setString(8,rec.getType());
        pst.setString(9,rec.getPhoto());
        pst.setString(10,rec.getCompetence());
        pst.setInt(11,rec.getId()); 
        pst.executeUpdate();
       
        System.out.println("Recruteur modifié !!");
            return true ;
            } catch (SQLException e) {
                return false ;
   
}
          
    }
     public boolean getPwdByUsername(String mail , String pwd) throws SQLException
     {
         try{
            String request="SELECT mdp FROM recruteur where mail='"+mail+"'";         
            Statement s=c.createStatement();
            ResultSet result=s.executeQuery(request);
          while(result.next())
           {
                if(result.getString("mdp").equals(pwd))
             {
              return true;
             }
           }
         
          
         }
        catch (SQLException ex)
        {
         System.out.println(ex);
        }
            return false;
    }
     public boolean deleteRecruteur(Recruteur r) {
         try {
             System.out.println(r.getId());
            String request = "DELETE FROM recruteur WHERE id=?";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
             System.out.println("deleted!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public boolean userExist(String mail) throws SQLException
     {
                  
         try{
            String request="SELECT mail FROM recruteur where mail='"+mail+"'";         
            Statement s=c.createStatement();
            ResultSet result=s.executeQuery(request);
          while(result.next())
           {
               return true;
           }
          
         }
        catch (SQLException ex)
        {
         System.out.println(ex);
        }
            return false;
    }
   
    public ArrayList<Recruteur> getAllUsers(int id){
        ArrayList<Recruteur> liste = new ArrayList<>();
        try {                    
           String request="SELECT * FROM recruteur where id="+id;
            Statement ste = MyConnection.getInstance().getCnx().createStatement();
            ResultSet result = ste.executeQuery(request);
            while (result.next()) {
                Recruteur r = new Recruteur();
                r.setId(result.getInt("id"));
                r.setMail(result.getString("mail"));
                r.setPhoto(result.getString("photo"));
                liste.add(r);
            }
          
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return liste;
    }
    public Recruteur existUser(String email, String pass) {
        Recruteur rec = null;
        try {
            String sql = "SELECT * FROM recruteur WHERE mail = ? AND mdp = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            //MD5Encrypt md = new MD5Encrypt();
            ps.setString(2, pass);
            res = ps.executeQuery();
            while (res.next()) {
               int id = res.getInt("id");
               String nom = res.getString("nom");
               String prenom = res.getString("prenom");
               String nomsociete = res.getString("nomsociete");
               String adresse = res.getString("adresse");
               String mail = res.getString("mail");
               int numsociete = res.getInt("numsociete");
               String mdp = res.getString("mdp");
               String type = res.getString("type");
               String photo = res.getString("photo");
               String competence = res.getString("competence");
               rec = new Recruteur(id,nom,prenom,nomsociete,adresse,mail,numsociete,mdp,type,photo,competence);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecruteurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return rec;
    }
public void connect(Recruteur r) {
        SessionWorkit.utilisateur = r;
    }
    public boolean checkcode(Recruteur u, int cd) {
        if (u.generateCode()== cd)
        {
            return true;
        }
        return false;
    }

public boolean resetPass(Recruteur u) {
        try {

            String req5 = "UPDATE recruteur SET mdp=? WHERE mail= ?";

            PreparedStatement pre = c.prepareStatement(req5);
            pre.setString(1, u.getMdp());
            pre.setString(2, u.getMail());

            pre.executeUpdate();
            System.out.println("successfully modified!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println(" error modification!!");
        }
        return false;

    }
    public Recruteur rechercherRec(String nom) {
        
         Recruteur t = new Recruteur();
         System.out.println("nom :"+nom);
            try {
            Statement stm = c.createStatement();
            String query = "SELECT * FROM recruteur WHERE Nom LIKE '"+nom+"'";

            ResultSet rst = stm.executeQuery(query);
           
             while (rst.next()) {
               
                t.setId(rst.getInt("id"));
                System.out.println("eee!"+t.getId());
                t.setNom(rst.getString("nom"));
                t.setPrenom(rst.getString("prenom"));
                t.setNomsociete(rst.getString("nomsociete"));
                t.setAdresse(rst.getString("adresse"));
                t.setMail(rst.getString("mail"));
                t.setNumsociete(rst.getInt("numsociete"));
                t.setMdp(rst.getString("mdp"));
                t.setType(rst.getString("type"));
                t.setPhoto(rst.getString("photo"));
                t.setCompetence(rst.getString("competence"));
                
               
               
                //sr.AjouterTest(t);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecruteurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return t;
        
    }
}
