/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

/**
 *
 * @author walid belhadj
 */
public class Certificat {
    private int id;
    private String nom;
    private int test_id;
    private int idrecruteur_id;

    public Certificat() {
    }

    public Certificat(int id, String nom, int test_id, int idrecruteur_id) {
        this.id = id;
        this.nom = nom;
        this.test_id = test_id;
        this.idrecruteur_id = idrecruteur_id;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getIdrecruteur_id() {
        return idrecruteur_id;
    }

    public void setIdrecruteur_id(int idrecruteur_id) {
        this.idrecruteur_id = idrecruteur_id;
    }

    @Override
    public String toString() {
        return "Certificat{" + "id=" + id + ", nom=" + nom + ", test_id=" + test_id + ", idrecruteur_id=" + idrecruteur_id + '}';
    }
    
    
}
