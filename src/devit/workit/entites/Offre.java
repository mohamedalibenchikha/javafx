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
public class Offre {
    private int id ;
    private String nom;
    private String email;
    private String logo;
    private String title;
    private String description;
    private int abn;
    private int idcategoriy_id;
    private int idrecruteur_id;
    public Offre()
    {
    }

    public Offre(int id, int abn) {
        this.id = id;
        this.abn = abn;
    }
    
    public Offre(int id, String nom, String email, String logo, String title, String description, int abn, int idcategoriy_id, int idrecruteur_id) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.logo = logo;
        this.title = title;
        this.description = description;
        this.abn = abn;
        this.idcategoriy_id = idcategoriy_id;
        this.idrecruteur_id = idrecruteur_id;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", logo=" + logo + ", title=" + title + ", description=" + description + ", abn=" + abn + ", idcategoriy_id=" + idcategoriy_id + ", idrecruteur_id=" + idrecruteur_id + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdcategoriy_id(int idcategoriy_id) {
        this.idcategoriy_id = idcategoriy_id;
    }

    public void setIdrecruteur_id(int idrecruteur_id) {
        this.idrecruteur_id = idrecruteur_id;
    }

    public int getIdcategoriy_id() {
        return idcategoriy_id;
    }

    public int getIdrecruteur_id() {
        return idrecruteur_id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAbn(int abn) {
        this.abn = abn;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getLogo() {
        return logo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAbn() {
        return abn;
    }
}
