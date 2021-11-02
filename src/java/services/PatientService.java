/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import conn.Connexion;
import health.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ericko
 */
public class PatientService {
     public static boolean verifLogin(String username, String password, Connection conn) throws Exception {
        Patient med = new Patient();
        med.setUsername(username);
        med.setPassword(password);
        return med.login(conn);
    }
     
     public static int countItems(HashMap<String, String> filtre) throws Exception {
        int valiny = 0;
        try {
            Connection con1 = Connexion.connect();
            String query = "SELECT  COUNT(*) FROM patientV";
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
     
     public static ArrayList<Patient> listAllPatient(int limit, int offset, HashMap<String, String> filtre) throws Exception {
        ArrayList<Patient> list = new ArrayList();
        try {
            Connection con1 = Connexion.connect();
            String query = "SELECT  * FROM patientV";
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
                Patient e1 = new Patient();
                e1.setIdPatient(rs1.getString(1));
                e1.setUsername(rs1.getString(2));
                e1.setPassword(rs1.getString(3));
                e1.setAge(rs1.getInt(4));
                list.add(e1);
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
