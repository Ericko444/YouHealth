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
import java.util.ArrayList;
import pers.Personne;

/**
 *
 * @author Ericko
 */



public class Medecin extends Personne{
    protected String idMedecin;
    protected String specialisation;

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public Medecin(String username, String password, String specialisation) {
        super(username, password);
        this.specialisation = specialisation;
    }
    
    

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    
    public void insert() throws Exception{
        Connection conn = Connexion.connect();
         conn.setAutoCommit(false);
        try{
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO medecin(idMedecin, username, password, specialisation) VALUES('medecin-' || nextval('medecin_seq'), ?, ?, ?)")) {
                st.setString(1, this.getUsername());
                st.setString(2, this.getPassword());
                st.setString(3, specialisation);
                
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

    public Medecin(String idMedecin, String specialisation) {
        super();
        this.idMedecin = idMedecin;
        this.specialisation = specialisation;
    }
    
    public Medecin() {
    }

    public Medecin(String idMedecin, String specialisation, String username, String password) {
        super(username, password);
        this.idMedecin = idMedecin;
        this.specialisation = specialisation;
    }
    
    
    
    public ArrayList<Medecin> findBySpecialisation(Connection conn) throws Exception{
        ArrayList<Medecin> listMed = new ArrayList<>();
        String qry = "SELECT * FROM medecin WHERE specialisation='"+getSpecialisation();
        qry += "'";
        try
        {
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            
            while(rs.next())
            {
                Medecin newM = new Medecin(rs.getString("username"),rs.getString("idMedecin"),rs.getString("specialisation"));
                listMed.add(newM);
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
        return listMed;
    }
    
    public boolean login(Connection conn) throws Exception {
        String qry = "SELECT * FROM medecin WHERE username='"+this.getUsername();
        qry += "' AND password='"+this.getPassword();
        qry += "'";
        boolean valiny = false;
         try
        {
            try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(qry)) {
                if(!rs.wasNull())
                {
                    valiny = true;
                }
                
            }
        }
         catch(SQLException e)
         {
          System.out.println("Error "+e.getMessage());
        }
        return valiny;
    }
    
    public ArrayList<Medecin> findAvailable(RendezVous rdv) throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<Medecin> valiny = new ArrayList<>();
        String qry = "SELECT * FROM medecin WHERE idMedecin NOT IN (SELECT idMedecin FROM rendezVous WHERE dateRdv=?)";
        if(this.getSpecialisation() != null)
        {
            qry += " AND specialisation=?";
        }
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setTimestamp(1, rdv.getDateRdv());
        if(this.getSpecialisation() != null)
        {
            ps.setString(2, this.getSpecialisation());
        }
        
         ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medecin med = new Medecin();
                med.setIdMedecin(rs.getString("idMedecin"));
                med.setUsername(rs.getString("username"));
                med.setPassword(rs.getString("password"));
                med.setSpecialisation(rs.getString("specialisation"));
                valiny.add(med);
            }
        return valiny;
    }
    
}
