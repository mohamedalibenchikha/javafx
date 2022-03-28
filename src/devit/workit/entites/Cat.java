/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

import javafx.scene.control.Button;

/**
 *
 * @author Nadia
 */
public class Cat {
    
    String id ;
    String nom;
   

    public Cat() {
    }

    public Cat( String nom) {
      
      
        this.nom = nom;
    }

    

    public Cat(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    


    public String getId(){
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + '}';
    }

  


    
    
    
    
    
}
