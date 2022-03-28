/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Test;
import devit.workit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author walid belhadj
 */
public class ServiceTest{
    private Connection cnx = MyConnection.getInstance().getCnx();


    public void AjouterTest(Test t) {
         try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO `test`(`nom`, `q1`, `r1`, `q2`, `r2`, `q3`, `r3`, `q4`, `r4`, `q5`, `r5`) VALUES ('" + t.getNom()+ "','" + t.getQ1()+ "','" +t.getR1() + "','" +t.getQ2() + "','" +t.getR2() + "','" +t.getQ3() + "','" +t.getR3() + "','" +t.getQ4() + "','" +t.getR4() + "','" +t.getQ5() + "','" +t.getR5() + "')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


    public List<Test> AfficherTest() throws SQLException {
         List<Test> tests = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `test`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Test t = new Test();
                t.setId(rst.getInt("id"));
                t.setNom(rst.getString("nom"));
                t.setQ1(rst.getString("q1"));
                t.setR1(rst.getString("r1"));
                t.setQ2(rst.getString("q2"));
                t.setR2(rst.getString("r2"));
                t.setQ3(rst.getString("q3"));
                t.setR3(rst.getString("r3"));
                t.setQ4(rst.getString("q4"));
                t.setR4(rst.getString("r4"));
                t.setQ5(rst.getString("q5"));
                t.setR5(rst.getString("r5"));
              
                tests.add(t);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tests;
    }

    public int SupprimerTest(int idd) {
        try {
            String request = "Delete FROM test where id ="+idd;
            PreparedStatement pst = cnx.prepareStatement(request);
            return pst.executeUpdate(request);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public Test findById(int idd){
        Test rec = null;
        String req = "SELECT * FROM test WHERE id LIKE '"+idd+"'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
              rec = new Test();
            if(resultSet.next())
                rec.setId(resultSet.getInt("id"));
               rec.setNom(resultSet.getString("nom"));
               rec.setQ1(resultSet.getString("q1"));
               rec.setR1(resultSet.getString("r1"));
               rec.setQ2(resultSet.getString("q2"));
               rec.setR2(resultSet.getString("r2"));
               rec.setQ3(resultSet.getString("q3"));
               rec.setR3(resultSet.getString("r3"));
               rec.setQ4(resultSet.getString("q4"));
               rec.setR4(resultSet.getString("r4"));
               rec.setQ5(resultSet.getString("q5"));
               rec.setR5(resultSet.getString("r5"));
               
                        
                
                
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return  null;
        }
        return rec;
    }
    public boolean ModifierTest(Test t) throws SQLException {
       
       
      /*  rec.setNom(t.getNom());
        rec.setQ1(t.getQ1());
        rec.setR1(t.getR1());
        rec.setQ2(t.getQ2());
        rec.setR2(t.getR2());
        rec.setQ3(t.getQ3());
        rec.setR3(t.getR3());
        rec.setQ4(t.getQ4());
        rec.setR4(t.getR4());
        rec.setQ5(t.getQ5());
        rec.setR5(t.getR5());*/
      //probleeemeee heree !!!   
      try {
         /* String requete ="UPDATE  test set Nom = '"+rec.getNom()+"', Q1= '"+rec.getQ1()+"',R1='"+ rec.getR1()+"',"
                  + "Q2='"+rec.getQ2()+"',R2='"+rec.getR2()+"',Q3='"+rec.getQ3()+"',R3='"+rec.getR3()+"',"
                  + "Q4='"+rec.getQ4()+"',R4='"+rec.getR4()+"',Q5='"+rec.getQ5()+"',R5='"+rec.getR5()
                  +"' where id='"+rec.getId()+"'";
          System.out.println("test rec !!"+rec.getId());
          System.out.println(requete);
          //String req = "SELECT * FROM test WHERE id LIKE '"+idd+"'";
            Statement statement = Maconnexion.getInstance().getConnection().createStatement();
            statement.executeQuery(requete);*/
          
          
           String requete ="UPDATE  test set Nom = ? , Q1= ?,R1= ?,Q2=?,R2=?,Q3=?,R3=?,Q4=?,R4=?,Q5=?,R5=?  where id=?";
            PreparedStatement pst =
     cnx.prepareStatement(requete);
    

        pst.setString(1,t.getNom());
        pst.setString(2,t.getQ1());
        pst.setString(3,t.getR1());   
        pst.setString(4,t.getQ2());
        pst.setString(5,t.getR2());
        pst.setString(6,t.getQ3());
        pst.setString(7,t.getR3());
        pst.setString(8,t.getQ4());
        pst.setString(9,t.getR4());
        pst.setString(10,t.getQ5());
        pst.setString(11,t.getR5());
        pst.setInt(12,t.getId()); 
        System.out.println("test rec !!"+t.getId());
         System.out.println(pst);
        
        pst.executeUpdate();
        
         
        
        
       
        System.out.println("test modifiéeeeee !!");
            return true ;
            } catch (SQLException e) {
                return false ;
    }
    }

    public Test rechercherTest(String nom) {
         ServiceTest sr = new ServiceTest();
         Test t = new Test();
            try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM test WHERE Nom LIKE '"+nom+"'";

            ResultSet rst = stm.executeQuery(query);
           
             while (rst.next()) {
               
                t.setId(rst.getInt("id"));
                t.setNom(rst.getString("nom"));
                t.setQ1(rst.getString("Q1"));
                t.setR1(rst.getString("R1"));
                t.setQ2(rst.getString("Q2"));
                t.setR2(rst.getString("R2"));
                t.setQ3(rst.getString("Q3"));
                t.setR3(rst.getString("R3"));
                t.setQ4(rst.getString("Q4"));
                t.setR4(rst.getString("R4"));
                t.setQ5(rst.getString("Q5"));
                t.setR5(rst.getString("R5"));
               
               
                //sr.AjouterTest(t);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return t;
    }

    
    
}
