/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import conn.Connexion;
import health.Medecin;
import health.RendezVous;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import util.Util;

/**
 *
 * @author Ericko
 */
public class MedecinService {
    public static ArrayList<Medecin> findMedecinBySpecialisation(String specialisation, Connection conn)throws Exception {
        ArrayList<Medecin> listMed;
        Medecin m = new Medecin();
        m.setSpecialisation(specialisation);
        listMed = m.findBySpecialisation(conn);
        return listMed;
    }
    
    public static ArrayList<Medecin> findFreeMedecinOnADate(String specialisation, String dateTime) throws Exception {
        ArrayList<Medecin> listMed;
        Medecin med = new Medecin();
        if (!"".equals(specialisation)) {
            med.setSpecialisation(specialisation);
        }
        RendezVous rdv = new RendezVous();
        if (dateTime.length() > 16) {
            rdv.setDateRdv(Timestamp.valueOf(dateTime));
        } else if (dateTime.length() == 16) {
            rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
        }
        listMed = med.findAvailable(rdv);
        return listMed;
    }
    
    public static boolean verifLogin(String username, String password, Connection conn) throws Exception {
        Medecin med = new Medecin();
        med.setUsername(username);
        med.setPassword(password);
        return med.login(conn);
    }
    
    public static ArrayList<String> listAllSpec() throws Exception {
        ArrayList<String> valiny = new ArrayList<>();
        try {
            Connection con1 = Connexion.connect();
            PreparedStatement ps1 = con1.prepareStatement("SELECT DISTINCT specialisation FROM medecin ORDER BY specialisation");
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                valiny.add(rs1.getString("specialisation"));
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return valiny;
    }
    
    public static int countItems(HashMap<String, String> filtre) throws Exception {
        int valiny = 0;
        try {
            Connection con1 = Connexion.connect();
            String query = "SELECT  COUNT(*) FROM medecin";
            if (filtre != null) {
                query += " WHERE ";
                for (String key : filtre.keySet()) {
                    query += (key + " =?");
                }
            }
            PreparedStatement ps1 = con1.prepareStatement(query);
            int counter = 1;
            if (filtre != null) {
                for (String key : filtre.keySet()) {
                    ps1.setString(counter, filtre.get(key));
                    counter++;
                }
            }
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                valiny = rs1.getInt(1);
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return valiny;
    }
    
    public static ArrayList<Medecin> listAllMedecin(int limit, int offset, HashMap<String, String> filtre) throws Exception {
        ArrayList<Medecin> list = new ArrayList();
        try {
            Connection con1 = Connexion.connect();
            String query = "SELECT  * FROM medecin";
            if (filtre != null) {
                query += " WHERE ";
                for (String key : filtre.keySet()) {
                    query += (key + " =?");
                }
            }
            query += " LIMIT ? OFFSET ?";
            PreparedStatement ps1 = con1.prepareStatement(query);
            int counter = 1;
            if (filtre != null) {
                for (String key : filtre.keySet()) {
                    ps1.setString(counter, filtre.get(key));
                    counter++;
                }
            }
            ps1.setInt(counter, limit);
            counter++;
            ps1.setInt(counter, offset);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Medecin e1 = new Medecin();
                e1.setIdMedecin(rs1.getString(1));
                e1.setUsername(rs1.getString(2));
                e1.setPassword(rs1.getString(3));
                e1.setSpecialisation(rs1.getString(4));
                list.add(e1);
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static ArrayList<Medecin> findAvailable(String specialisation, String dateTime, int limit, int offset) throws Exception {
        Connection conn = Connexion.connect();
        ArrayList<Medecin> valiny = new ArrayList<>();
        RendezVous rdv = new RendezVous();
        System.out.println("Length = " + dateTime.length());
        if (dateTime.length() > 16) {
            rdv.setDateRdv(Timestamp.valueOf(dateTime));
        } else if (dateTime.length() == 16) {
            rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
        }
        Medecin med = new Medecin();
        if (!"".equals(specialisation)) {
            med.setSpecialisation(specialisation);
        }
        String qry = "SELECT * FROM medecin WHERE idMedecin NOT IN (SELECT idMedecin FROM rendezVous WHERE dateRdv=?)";
        if (med.getSpecialisation() != null) {
            qry += " AND specialisation=? ";
            qry += "LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setTimestamp(1, rdv.getDateRdv());
            ps.setString(2, specialisation);
            ps.setInt(3, limit);
            ps.setInt(4, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medecin m = new Medecin();
                m.setIdMedecin(rs.getString("idMedecin"));
                m.setUsername(rs.getString("username"));
                m.setPassword(rs.getString("password"));
                m.setSpecialisation(rs.getString("specialisation"));
                valiny.add(m);
            }
        } else {
            qry += "LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setTimestamp(1, rdv.getDateRdv());
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medecin m = new Medecin();
                m.setIdMedecin(rs.getString("idMedecin"));
                m.setUsername(rs.getString("username"));
                m.setPassword(rs.getString("password"));
                m.setSpecialisation(rs.getString("specialisation"));
                valiny.add(m);
            }
        }

        return valiny;
    }
}
