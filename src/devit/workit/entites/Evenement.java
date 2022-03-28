/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Button;


/**
 *
 * @author Nadia
 */
public class Evenement {

    String id;
    String nom;
    String date ;
    String description;
    String email;
    int nbp ;
    int idcat_id  ;
    int idrecruteur_id;
  
    String tel;
    

    public Evenement() {
    }
    
     public Evenement( String nom, String description,String date, String email, int idcat_id ) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        this.idcat_id  = idcat_id ;
    }
      public Evenement( String nom, String description,  String email , int nbp) {
        this.nom = nom;
        this.description = description;
        //this.date = date;
        this.email = email;
        //this.idcategorie_id = idcategorie_id;
        this.nbp=nbp;
    }
     
     public Evenement(String id , String nom, String description,String date, String email, int idcat_id ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        this.idcat_id  = idcat_id ;
    }
     
     public Evenement(String id , String nom, String description,String date, String email ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        
    }

    public Evenement(String id, String nom, String description, String date, String email, int idcat_id  , int nbp) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        this.idcat_id  = idcat_id ;
        this.nbp=nbp;
    }


    
      /*public Evenement(String id, String nom, String description, String date, String email,int nbp) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        this.nbp=nbp;
        
    }*/

         public Evenement( String nom, String description,String date, String email) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        
        
    }
         
   /*        public Evenement(String id, String nom, String description, String date, String email) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.email = email;
        
    } */

    /* public Evenement(String string, String string0, String string1, String string2, String string3, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */

    

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public int getIdcat_id () {
        return idcat_id ;
    }
    
    public int getNbp(){
        return nbp;
    }

    public int getIdrecruteur_id() {
        return idrecruteur_id;
    }

    public String getTel() {
        return tel;
    }

    

  
    
  

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdcat_id (int idcat_id ) {
        this.idcat_id  = idcat_id ;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    public void setIdrecruteur_id(int idrecruteur_id) {
        this.idrecruteur_id = idrecruteur_id;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

  
    
    

    
    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", description=" + description + ", email=" + email + ", nbp=" + nbp + ", idcategorie_id=" + idcat_id  + '}';
    }

    
    
    
    

   

  

}

