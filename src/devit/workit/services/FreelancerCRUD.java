package devit.workit.services;


import devit.workit.tools.MyConnection;
import edu.devit.entities.Freelancer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FreelancerCRUD {
    private Connection c = MyConnection.getInstance().getCnx();
    public void addFreelancer(Freelancer f) {
        try {
            String requete = "INSERT INTO freelancer (email,name,password,title,skills,country,prix,education,experience) "
                    + "VALUES ('" + f.getEmail() + "','" + f.getNom() + "','" + f.getPassword() + "','" + f.getTitle() + "','" + f.getSkills() + "','" + f.getCountry() + "','" + f.getPrix() + "','" + f.getEducation() + "','" + f.getExperience() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            String to = f.getEmail();
            String host = "smtp.gmail.com";
            final String mail = "ayoumahouesprit@gmail.com";
            final String password = "12345678aze";

            Properties props = System.getProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail, password);
                }
            });

            try {
                MimeMessage m = new MimeMessage(session);
                try {
                    m.setFrom(mail);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                m.addRecipients(Message.RecipientType.TO, to);
                m.setSubject("Freelancer");
                m.setText("freelancer confirmed");
                Transport.send(m);

            } catch (MessagingException e) {
            }
            System.out.println("Freelancer ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean updateFreelancer(Freelancer f){
        Connection cnx =null;
        Statement st = null;
        String requette = "UPDATE Freelancer SET email='"+f.getEmail()+"',name ='"+f.getNom()+"',password='"+f.getPassword()+"',title='"+f.getTitle()+"',skills ='"+f.getSkills()+ "',country ='"+f.getCountry()+"',prix ='"+f.getPrix()+ "',education ='"+f.getEducation()+ "',experience ='"+f.getExperience()+ "' WHERE id='"+f.getId()+"'";
        try {
            cnx = MyConnection.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) { /* Ignored */}
            }
        }}
      public boolean deleteFreelancer(Freelancer f){ // naadeha paramétre
    Connection cnx =null;
    Statement st = null;
    String requette = "DELETE FROM freelancer WHERE id="+f.getId()+"";
    try {
        cnx = MyConnection.instance.getCnx();
        st = cnx.createStatement();
        st.executeUpdate(requette);
        return true;


    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }finally {

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) { /* Ignored */}
        }
    }
}



    public List<Freelancer> getFreelancers(){
        List<Freelancer> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM freelancer";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Freelancer f = new Freelancer();
                f.setId(rs.getInt(1)); // l'indice de 1ér colonne de la base
                f.setNom(rs.getString("name"));
                f.setEmail(rs.getString("email"));
                f.setPassword(rs.getString("password"));
                f.setTitle(rs.getString("title"));
                f.setSkills(rs.getString("skills"));
                f.setCountry(rs.getString("country"));
                f.setPrix(rs.getInt("prix"));
                f.setEducation(rs.getString("education"));
                f.setExperience(rs.getString("experience"));
                myList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public ObservableList<Freelancer> showFreelancer(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Freelancer> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM freelancer";

        try {
            cnx = MyConnection.getInstance().getCnx();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Freelancer freelancers;
            while (rs.next()){
                freelancers = new Freelancer(rs.getInt("id"),rs.getString("email"), rs.getString("password"),rs.getString("name"), rs.getString("title"), rs.getString("skills"), rs.getString("country"),rs.getInt("prix"),rs.getString("education"),rs.getString("experience"));
                liste.add(freelancers);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) { /* Ignored */}
            }
        }
        return liste;

    }

}
