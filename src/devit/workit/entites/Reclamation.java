/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

/**
 *
 * @author Wael Belhadj
 */
public class Reclamation {
    private int id;
    private int recruteur_id;
    private String nom;
    private String description;

    public Reclamation() {
    }

    public Reclamation(int id, int recruteur_id, String nom, String description) {
        this.id = id;
        this.recruteur_id = recruteur_id;
        this.nom = nom;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecruteur_id() {
        return recruteur_id;
    }

    public void setRecruteur_id(int recruteur_id) {
        this.recruteur_id = recruteur_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", recruteur_id=" + recruteur_id + ", nom=" + nom + ", description=" + description + '}';
    }
    
}
