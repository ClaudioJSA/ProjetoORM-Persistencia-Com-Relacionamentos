/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import entity.Entity;
import java.time.LocalDate;
import java.util.Objects;
import user.User;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class Credential extends Entity{
    private String username;
    private String password;
    private LocalDate lastAccess;
    private boolean enabled;
    private User user;

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
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//</editor-fold>

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Credential) {
            Credential otherCredential = (Credential) obj;
            return this.username.equals(otherCredential.username) && this.password.equals(otherCredential.password);
          }
    return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.password);
        return hash;
    }

    public Credential() {
        enabled = true;
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credential{" + "username=" + username + ", password=" + password + ", lastAccess=" + lastAccess + ", enabled=" + enabled + ", user=" + user.getId() + '}';
    }
    
    
}   
