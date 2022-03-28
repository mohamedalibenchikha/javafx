package devit.workit.entites;

public class Projet {
    private int id;
    private int user_id;
    private String nom_projet;
    private String projet_description;
    private String job_type;
    private String job_salary;
    private String job_time;
    private String logo;

    public Projet() {
    }


    public Projet(int id, String nom_projet, String projet_description, String job_type, String job_salary, String job_time, String logo) {

        this.id = id;
        this.nom_projet = nom_projet;
        this.projet_description = projet_description;
        this.job_type = job_type;
        this.job_salary = job_salary;
        this.job_time = job_time;
        this.logo = logo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNom_projet() {
        return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

    public String getProjet_description() {
        return projet_description;
    }

    public void setProjet_description(String projet_description) {
        this.projet_description = projet_description;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getJob_time() {
        return job_time;
    }

    public void setJob_time(String job_time) {
        this.job_time = job_time;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", nom_projet='" + nom_projet + '\'' +
                ", projet_description='" + projet_description + '\'' +
                ", job_type='" + job_type + '\'' +
                ", job_salary='" + job_salary + '\'' +
                ", job_time='" + job_time + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
