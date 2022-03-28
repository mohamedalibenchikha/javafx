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
public class staticEntity {
    private static int id ;
     private static String nom ;
      private static String description ;
       private static int views ;
       private static String image ;
       private static int accepte ;
       private static String offre_name ;
       private static String client ;
       private static int idclient ;
       private static int idoffre ;

    public static int getIdoffre() {
        return idoffre;
    }

    public static void setIdoffre(int idoffre) {
        staticEntity.idoffre = idoffre;
    }
       
    public static int getIdclient() {
        return idclient;
    }

    public static void setIdclient(int idclient) {
        staticEntity.idclient = idclient;
    }
       
    public static String getOffre_name() {
        return offre_name;
    }

    public static String getClient() {
        return client;
    }

    public static void setOffre_name(String offre_name) {
        staticEntity.offre_name = offre_name;
    }

    public static void setClient(String client) {
        staticEntity.client = client;
    }
       
    public static void setImage(String image) {
        staticEntity.image = image;
    }

    public static String getImage() {
        return image;
    }

    public static String getNom() {
        return nom;
    }

    public static String getDescription() {
        return description;
    }

    public static int getViews() {
        return views;
    }

    public static void setNom(String nom) {
        staticEntity.nom = nom;
    }

    public static void setAccepte(int accepte) {
        staticEntity.accepte = accepte;
    }

    public static int getAccepte() {
        return accepte;
    }

    public static void setDescription(String description) {
        staticEntity.description = description;
    }

    public static void setViews(int views) {
        staticEntity.views = views;
    }

    public staticEntity() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        staticEntity.id = id;
    }
    
}
