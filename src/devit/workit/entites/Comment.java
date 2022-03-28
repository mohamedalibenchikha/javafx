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
public class Comment {
     private int id ;
    private String author_name;
    private String content;
    private String created_at;
    private int offre_id ;
    private int idrecruteur_id ;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public void setIdrecruteur_id(int idrecruteur_id) {
        this.idrecruteur_id = idrecruteur_id;
    }

    public Comment(int id, String author_name, String content, String created_at, int offre_id, int idrecruteur_id) {
        this.id = id;
        this.author_name = author_name;
        this.content = content;
        this.created_at = created_at;
        this.offre_id = offre_id;
        this.idrecruteur_id = idrecruteur_id;
    }

   

    public String getAuthor_name() {
        return author_name;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public int getIdrecruteur_id() {
        return idrecruteur_id;
    }
}
