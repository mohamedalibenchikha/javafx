/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.entites;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP-PC
 */
public class Forum implements Comparator<Forum> {
    private int id;
    private String sujet;
    private String probleme;
    private LocalDateTime date;
    private String theme;
    private String views;
    private int nviews;
    private ImageView ratingImg;

    public int getNviews() {
        if(views == null)
            return 0;
        return views.split(",").length;
    }



    public Forum() {
        date=LocalDateTime.now();
        ratingImg = new ImageView();
    }

    public Forum(int id, String sujet, String probleme, String theme) {
        this.id = id;
        this.sujet = sujet;
        this.probleme = probleme;
     date=LocalDateTime.now();
     ratingImg = new ImageView();
     this.theme = theme;
    }

    public Forum(String sujet, String probleme, String theme) {
        this.sujet = sujet;
        this.probleme = probleme;
        this.theme = theme;
        date=LocalDateTime.now();
        ratingImg = new ImageView();
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
   

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ImageView getRatingImg() {
        return ratingImg;
    }

    public void setRatingImg(ImageView ratingImg) {
        this.ratingImg = ratingImg;
    }
    
    

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", sujet=" + sujet + ", probleme=" + probleme + ", date=" + date + ", theme=" + theme + '}';
    }

   @Override
    public int compare(Forum o1, Forum o2) {
        return o1.getTheme().compareTo(o2.getTheme());
    
    }
    
     public void setRatingImage(String imgUrl) {
        File file = new File("src/" + imgUrl);
        try {
            Image img = new Image(file.toURI().toString());
            ratingImg.setImage(img);
            ratingImg.setFitWidth(30);
            ratingImg.setFitHeight(30);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    
    
    
    
}
