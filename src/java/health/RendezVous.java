/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import conn.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Ericko
 */
public class RendezVous {
    protected String idRdv;
    protected String idMedecin;
    protected String idPatient;
    protected Timestamp dateRdv;

    

    public RendezVous() {
    }

    public RendezVous(String idMedecin, String idPatient, Timestamp dateRdv) {
        this.idMedecin = idMedecin;
        this.idPatient = idPatient;
        this.dateRdv = dateRdv;
    }

    public RendezVous(String idRdv, String idMedecin, String idPatient, Timestamp dateRdv) {
        this.idRdv = idRdv;
        this.idMedecin = idMedecin;
        this.idPatient = idPatient;
        this.dateRdv = dateRdv;
    }
    
    public String getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(String idRdv) {
        this.idRdv = idRdv;
    }

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }
    
    public Timestamp getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Timestamp dateRdv) {
        this.dateRdv = dateRdv;
    }


    public void insert() throws Exception {
        Connection conn = Connexion.connect();
        conn.setAutoCommit(false);
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO rendezVous(idRdv, idMedecin, idPatient, dateRdv) VALUES('rdv-' || nextval('rdv_seq'), ?, ?, ?)")) {
                st.setString(1, this.getIdMedecin());
                st.setString(2, this.getIdPatient());
                st.setTimestamp(3, this.getDateRdv());
                st.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        }
    }
    
    public void update() throws Exception {
        Connection conn = Connexion.connect();
        String qry = "UPDATE rendezVous SET dateRdv=? WHERE idRdv=?";
        try {
            try (PreparedStatement st = conn.prepareStatement(qry)) {
                st.setTimestamp(1, this.getDateRdv());
                st.setString(2, this.getIdRdv());
                int row = st.executeUpdate();
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
    
    
    public void delete() throws Exception{
        Connection conn = Connexion.connect();
        try {
            try (PreparedStatement st = conn.prepareStatement("DELETE FROM rendezVous WHERE idRdv=?")) {
                st.setString(1, this.getIdRdv());
                st.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        }
    }
    
    public ArrayList<RendezVous> find() throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<RendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous WHERE idRdv=? AND idMedecin=? AND idPatient=? AND dateRdv=?";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1,  this.getIdRdv());
            ps.setString(2, this.getIdMedecin());
            ps.setString(3, this.getIdPatient());
            ps.setTimestamp(4, this.getDateRdv());
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous r = new RendezVous();
                r.setIdRdv(rs.getString(1));
                r.setIdMedecin(rs.getString(2));
                r.setIdPatient(rs.getString(3));
                r.setDateRdv(rs.getTimestamp(4));
                
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }


     public static ArrayList<RendezVous> findAll() throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<RendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous r = new RendezVous();
                r.setIdRdv(rs.getString(1));
                r.setIdMedecin(rs.getString(2));
                r.setIdPatient(rs.getString(3));
                r.setDateRdv(rs.getTimestamp(4));
                
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }
     
     
    public ArrayList<RendezVous> findOnDate() throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<RendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous WHERE dateRdv=?";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setTimestamp(1, this.getDateRdv());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous r = new RendezVous();
                r.setIdRdv(rs.getString(1));
                r.setIdMedecin(rs.getString(2));
                r.setIdPatient(rs.getString(3));
                r.setDateRdv(rs.getTimestamp(4));
                
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }
    
    public ArrayList<RendezVous> findSpecificMedOnDate(Medecin med) throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<RendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous WHERE dateRdv=? AND idMedecin IN (SELECT idMedecin FROM medecin WHERE specialisation=?";
        if(med.getUsername() != null)
        {
            qry += " AND username=?";
        }
        qry += ")";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setTimestamp(1, this.getDateRdv());
            ps.setString(2, med.getSpecialisation());
            if(med.getUsername() != null)
            {
              ps.setString(3, med.getUsername());  
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous r = new RendezVous();
                r.setIdRdv(rs.getString(1));
                r.setIdMedecin(rs.getString(2));
                r.setIdPatient(rs.getString(3));
                r.setDateRdv(rs.getTimestamp(4));
                
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }
    
    public static ArrayList<RendezVous> findSpecificMed(Medecin med) throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<RendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous WHERE idMedecin IN (SELECT idMedecin FROM medecin WHERE specialisation=?";
        if(med.getUsername() != null)
        {
            qry += " AND username=?";
        }
        qry += ")";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, med.getSpecialisation());
            if(med.getUsername() != null)
            {
              ps.setString(2, med.getUsername());  
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous r = new RendezVous();
                r.setIdRdv(rs.getString(1));
                r.setIdMedecin(rs.getString(2));
                r.setIdPatient(rs.getString(3));
                r.setDateRdv(rs.getTimestamp(4));
                
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }
    
    public ArrayList<RendezVous> findByMedecin(Connection conn) throws Exception {
        ArrayList<RendezVous> listRdv = new ArrayList<>();
        String qry = "SELECT * FROM rendezVous WHERE idMedecin = '" + this.getIdMedecin();
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
        } finally {
            conn.close();
        }
        return listRdv;
    }

  
   
}
