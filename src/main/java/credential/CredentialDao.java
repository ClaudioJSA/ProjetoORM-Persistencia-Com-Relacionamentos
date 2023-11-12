/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.Dao;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class CredentialDao extends Dao<Credential>{
    public final String TABLE = "credential";
    private String SALT = "!asdf";
            
    @Override
    public String getSaveStatement() {
        return "INSERT INTO " + TABLE + "(name, email, birthDate) VALUES (?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE " + TABLE + " SET name = ?, email = ?, birthDate = ? WHERE id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT name, email, birthDate FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT name, email, birthDate FROM " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, Credential e) {
        try {
            pstmt.setString(1, e.getUsername());
            pstmt.setString(2, e.getPassword());
            pstmt.setDate(3, Date.valueOf(e.getLastAccess()));
            pstmt.setBoolean(4, e.isEnabled());
            
            if (e.getId() != null) {
                pstmt.setLong(5, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CredentialDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Credential extractObject(ResultSet rs) {
        Credential credential = new Credential();
        try{
            credential.setUsername(rs.getString("username"));
            credential.setPassword(rs.getString("password"));
            credential.setLastAccess(rs.getDate("lastAccess").toLocalDate());
            credential.setEnabled(rs.getBoolean("enabled"));
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }
        return credential;
    }

    @Override
    public List<Credential> extractObjects(ResultSet rs) {
        List<Credential> credentials = new ArrayList<>();
        try{
            while(rs.next()){
                Credential credential = new Credential();
                credential.setUsername(rs.getString("username"));
                credential.setPassword(rs.getString("password"));
                credential.setLastAccess(rs.getDate("lastAccess").toLocalDate());
                credential.setEnabled(rs.getBoolean("enabled"));
                credentials.add(credential);
            }
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }   
        return credentials;
    }

    public CredentialDao() {
    }

    public String getTABLE() {
        return TABLE;
    }

    public String getSALT() {
        return SALT;
    }

    public void setSALT(String SALT) {
        this.SALT = SALT;
    }   
}
