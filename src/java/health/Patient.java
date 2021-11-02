/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import conn.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import pers.Personne;

/**
 *
 * @author Ericko
 */
public class Patient extends Personne {
    
    protected String idPatient;
    protected Date dateDeNaissance;
    protected int age;
    
    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }
    
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Patient(String idPatient, Date dateDeNaissance, String username) {
        super(username);
        this.idPatient = idPatient;
        this.dateDeNaissance = dateDeNaissance;
    }

    public Patient(Date dateDeNaissance, String username, String password) {
        super(username, password);
        this.dateDeNaissance = dateDeNaissance;
    }
    
    

    public Patient(String idPatient, String username,String password,Date dateDeNaissance  ) {
        super(username, password);
        this.idPatient = idPatient;
        this.dateDeNaissance = dateDeNaissance;
    }
    
    public Patient(String idPatient)
    {
        this.idPatient = idPatient;
    }

    public Patient() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    public void insert() throws Exception{
        Connection conn = Connexion.connect();
        conn.setAutoCommit(false);
        try{
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO patient(idPatient, username, password, dateDeNaissance) VALUES('patient - ' || nextval('patient_seq'), ?, ?, ?)")) {
                st.setString(1, this.getUsername());
                st.setString(2, this.getPassword());
                st.setDate(3, this.getDateDeNaissance());
                st.executeUpdate();
            }
            conn.commit();
        }catch(SQLException e)
        {
            conn.rollback();
        }
        finally{
            conn.close();
        }  
    }
    
    public ArrayList<Patient> find(Connection conn) throws Exception{
        ArrayList<Patient> listPat = new ArrayList<>();
        String qry = "SELECT * FROM patient WHERE idPatient = '"+this.getIdPatient();
        qry += "'";
        try
        {
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            
            while(rs.next())
            {
               Patient newS = new Patient(rs.getString("idPatient"), rs.getString("username"), rs.getString("password"), rs.getDate("dateDeNaissance"));
               listPat.add(newS);
            }
            
            rs.close();
            st.close();
            
        }
        catch(Exception e)
        {
            System.out.println("Error "+e.getMessage());
        }
        finally{
            conn.close();
        }
        return listPat; 
    }
    
    public boolean login(Connection conn) throws Exception {
        String qry = "SELECT * FROM patient WHERE username='"+this.getUsername();
        qry += "' AND password='"+this.getPassword();
        qry += "'";
        boolean valiny = false;
        int count = 0;
         try
        {
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            while(rs.next())
            {
                count++;
            }
            
            if(count > 0)
            {
                valiny = true;
            }
            
            System.out.println("count : "+count);
            System.out.println(rs.wasNull());
        }
         catch(Exception e)
         {
          System.out.println("Error log"+e.getMessage());
        }
        finally{
            conn.close();
        }
        return valiny;
    }
    
    public ArrayList<RendezVous> findAllRendezVous() throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<RendezVous> listRdv = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous WHERE idPatient = '" + this.getIdPatient();
        qry += "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);

            while (rs.next()) {
                RendezVous newR = new RendezVous(rs.getString("idRdv"), rs.getString("idMedecin"), rs.getString("idPatient"), rs.getTimestamp("dateRdv"));
                listRdv.add(newR);
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return listRdv;
    }
}
