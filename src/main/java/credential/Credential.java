/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import entity.Entity;
import java.time.LocalDate;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class Credential extends Entity{
    private String username;
    private String password;
    private LocalDate lastAccess;
    private boolean enabled;

//<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if(username.length() > 15 || username == null){
            throw new Exception("O usuário não pode ter mais de 150 caraceteres.");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if(password.length() > 32 || password == null){
            throw new Exception("A senha não pode ter mais de 32 caraceteres.");
        }
        this.password = password;
    }

    public LocalDate getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDate lastAccess) {
        this.lastAccess = lastAccess;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
//</editor-fold>

    public Credential() {
        enabled = true;
    } 
}   
