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
public class Postuler {
      private int id ;
      private int offre_id;
      private int recruteur_id;
      private String accepte;

    public Postuler() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public void setRecruteur_id(int recruteur_id) {
        this.recruteur_id = recruteur_id;
    }

    public void setAccepte(String accepte) {
        this.accepte = accepte;
    }

    public int getId() {
        return id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public int getRecruteur_id() {
        return recruteur_id;
    }

    public String getAccepte() {
        return accepte;
    }

    public Postuler(int id, int offre_id, int recruteur_id, String accepte) {
        this.id = id;
        this.offre_id = offre_id;
        this.recruteur_id = recruteur_id;
        this.accepte = accepte;
    }
}
