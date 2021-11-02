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
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Ericko
 */
public class DetailsRendezVous {
        String idRdv;
        Timestamp dateRdv;
        String idPatient;
        String patientName;
        int patientAge;
        String idMedecin;
        String medecinName;
        String specialisation;

    public String getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(String idRdv) {
        this.idRdv = idRdv;
    }

    public Timestamp getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Timestamp dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getMedecinName() {
        return medecinName;
    }

    public void setMedecinName(String medecinName) {
        this.medecinName = medecinName;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    

    public DetailsRendezVous() {
    }

    public DetailsRendezVous(String idRdv, Timestamp dateRdv, String idPatient, String patientName, int patientAge, String idMedecin, String medecinName, String specialisation) {
        this.idRdv = idRdv;
        this.dateRdv = dateRdv;
        this.idPatient = idPatient;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.idMedecin = idMedecin;
        this.medecinName = medecinName;
        this.specialisation = specialisation;
    }
    
    

    public ArrayList<DetailsRendezVous> findByPatient() throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<DetailsRendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM detailsRdv WHERE idPatient=?";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, this.getIdPatient());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetailsRendezVous r = new DetailsRendezVous(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }
    
    public ArrayList<DetailsRendezVous> findByMedecin() throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<DetailsRendezVous> valiny = new ArrayList<>();
        String qry = "SELECT * FROM detailsRdv WHERE idMedecin=?";
        try{
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, this.getIdMedecin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetailsRendezVous r = new DetailsRendezVous(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                valiny.add(r);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return valiny;
    }
}
