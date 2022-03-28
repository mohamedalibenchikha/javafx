package devit.workit.services;



import devit.workit.entites.Projet;
import devit.workit.entites.SessionWorkit;
import devit.workit.tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetCRUD {
    private Connection cnx = devit.workit.tools.MyConnection.getInstance().getCnx();
    public void addProjet(Projet p) {
        
        try {
            String requete = "INSERT INTO projet (nom_projet,user_id,projet_description,job_type,job_salary,job_time,logo) "
                    + "VALUES ('" + p.getNom_projet() + "','" + SessionWorkit.utilisateur.getId()+ "','" + p.getProjet_description() + "','" + p.getJob_type() + "','" + p.getJob_salary() + "','" + p.getJob_time() + "','" + p.getLogo() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Projet ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Projet> getProjets(){
        List<Projet> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM projet";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Projet p = new Projet();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt("user_id"));
                p.setNom_projet(rs.getString("nom_projet"));
                p.setProjet_description(rs.getString("projet_description"));
                p.setJob_type(rs.getString("job_type"));
                p.setJob_salary(rs.getString("job_salary"));
                p.setJob_time(rs.getString("job_time"));
                p.setLogo(rs.getString("logo"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public ObservableList<Projet> showProjet() throws SQLException{
        ObservableList<Projet> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM projet";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requette);
        try {
            Projet projets;
            while (rs.next()){
                projets = new Projet(rs.getInt("id"),rs.getString("nom_projet"), rs.getString("projet_description"),rs.getString("job_type"), rs.getString("job_salary"), rs.getString("job_time"), rs.getString("logo"));
               projets.setUser_id(rs.getInt("user_id"));
                liste.add(projets);
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

    public boolean updateProjet(Projet p) throws SQLException{
        try {
           String requete = "UPDATE `projet` SET `nom_projet`=? ,`projet_description`=?,`job_type`=?,`job_salary`=?,`job_time`=?,`logo`=? WHERE `id`=?";
            PreparedStatement pst =
                    MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1,p.getNom_projet());
        pst.setString(2,p.getProjet_description());
        pst.setString(3,p.getJob_type());
        pst.setString(4,p.getJob_salary());   
        pst.setString(5,p.getJob_time());   
        pst.setString(6,p.getLogo());   
        pst.setInt(7,p.getId()); 
        pst.executeUpdate();
            return true ;
            } catch (SQLException e) {
                return false ;
            }    
       /* try {
            Statement st = cnx.createStatement();
             String requette = "UPDATE Projet SET nom_projet='"+p.getNom_projet()+"',projet_description ='"+p.getProjet_description()+"',job_type='"+p.getJob_type()+"',job_salary='"+p.getJob_salary()+"',job_time ='"+p.getJob_time()+ "',logo ='"+p.getLogo()+ "' WHERE id='"+p.getId()+"'";
            ResultSet rs = st.executeQuery(requette);
            return true;
        } catch (SQLException ex) {
            return false;
        }*/
    }
    public boolean deleteprojet(Projet p) throws SQLException{ // naadeha paramétre
        Statement st = cnx.createStatement();
        String requette = "DELETE FROM projet WHERE id="+p.getId()+"";
        try {
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

}
