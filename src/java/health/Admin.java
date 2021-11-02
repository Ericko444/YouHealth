/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import pers.Personne;

/**
 *
 * @author Ericko
 */
public class Admin extends Personne{
    int idAdmin;

    public Admin(int idAdmin, String username, String password) {
        super(username, password);
        this.idAdmin = idAdmin;
    }
    
}
