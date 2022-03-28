/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

/**
 *
 * @author HP-PC
 */
public class Commentaire {
    
    
     private int ref , forum_id,rating  ;
    private String commentaire ;

    public Commentaire(int ref, int forum_id, String commentaire) {
        this.ref = ref;
        this.forum_id = forum_id;
        this.commentaire = commentaire;
    }

    public Commentaire(int ref, int forum_id, int rating, String commentaire) {
        this.ref = ref;
        this.forum_id = forum_id;
        this.rating = rating;
        this.commentaire = commentaire;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Commentaire(String commentaire) {
        this.commentaire = commentaire;
    }

   
     public Commentaire() {
    }

    public Commentaire(int ref) {
        this.ref = ref;
    }

    public Commentaire(String commentaire, int forum_id, int ref) {
        this.commentaire = commentaire;
        this.forum_id = forum_id;
        this.ref = ref;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    

    public Commentaire(int forum_id, String commentaire) {
        this.forum_id = forum_id;
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "ref=" + ref + ", forum_id=" + forum_id + ", commentaire=" + commentaire + '}';
    }

     
    


    
}
