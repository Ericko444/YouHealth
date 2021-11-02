/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

/**
 *
 * @author Ericko
 */
public class Personne {
    protected String username;
    protected String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Personne() {
    }

    public Personne(String username) {
        this.username = username;
    }
    
    

    public Personne(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
}
