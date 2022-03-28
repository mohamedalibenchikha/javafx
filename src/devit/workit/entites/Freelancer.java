package edu.devit.entities;

import javafx.scene.control.Button;

public class Freelancer {
    private int id;
    private String email;
    private String nom;
    private String password;
    private String title;
    private String skills;
    private String country;
    private int prix;
    private String education;
    private String experience;


    public Freelancer() {
    }

    public Freelancer(String email, String nom, String password, String title, String skills, String country, int prix, String education, String experience) {
        this.email = email;
        this.nom = nom;
        this.password = password;
        this.title = title;
        this.skills = skills;
        this.country = country;
        this.prix = prix;
        this.education = education;
        this.experience = experience;
    }

    public Freelancer(int id, String email, String nom, String password, String title, String skills, String country, int prix, String education, String experience) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.password = password;
        this.title = title;
        this.skills = skills;
        this.country = country;
        this.prix = prix;
        this.education = education;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }



    @Override
    public String toString() {
        return "Freelancer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", skills='" + skills + '\'' +
                ", country='" + country + '\'' +
                ", prix=" + prix +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
