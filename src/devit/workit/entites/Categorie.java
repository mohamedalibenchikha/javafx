/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

/**
 *
 * @author ASUS
 */
public class Categorie {
    private int id;
    private String nom;
    private String photo;
    private String help;

    public Categorie() {
    }

    @Override
    public String toString() {
        return "Categorie{" + "nom=" + nom + ", photo=" + photo + ", help=" + help + '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getPhoto() {
        return photo;
    }

    public String getHelp() {
        return help;
    }

    public Categorie(int id, String nom, String photo, String help) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.help = help;
    }

}
