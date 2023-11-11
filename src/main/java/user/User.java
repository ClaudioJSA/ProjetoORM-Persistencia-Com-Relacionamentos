/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import entity.Entity;
import java.time.LocalDate;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class User extends Entity{
    private String name;
    private String email;
    private LocalDate birthDate;

//<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name.length()>150 || name == null){
            throw new Exception("O nome não pode ter mais de 150 caracteres ou ser nulo.");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if(email.length()>255 || email == null){
            throw new Exception("O email não pode ter mais de 255 caracteres ou ser nulo.");
        }
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws Exception {
        if(birthDate == null){
            throw new Exception("A data de aniversario nao pode ser nula.");
        }
        this.birthDate = birthDate;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Contructors">
    public User() {
    } 
//</editor-fold>
}
