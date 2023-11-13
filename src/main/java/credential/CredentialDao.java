/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import repository.Dao;
import repository.DbConnection;
import user.User;
import user.UserDao;


/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class CredentialDao extends Dao<Credential>{
    public final String TABLE = "credential";
    private String SALT = "!asdf";
            
    @Override
    public String getSaveStatement() {
        return "INSERT INTO " + TABLE + "(username, email, birthDate, user) VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE " + TABLE + " SET username = ?, email = ?, birthDate = ?, user = ? WHERE id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT username, email, birthDate, user FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT username, email, birthDate, user FROM " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, Credential e) {
        try {
            pstmt.setString(1, e.getUsername());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((e.getPassword() + SALT).getBytes());
            byte[] hash = md.digest();           
            pstmt.setString(2, String.format("%032x", new BigInteger(1, hash)));
            pstmt.setDate(3, Date.valueOf(e.getLastAccess()));
            pstmt.setBoolean(4, e.isEnabled());
            pstmt.setLong(5, e.getUser().getId());
            
            if (e.getId() != null) {
                pstmt.setLong(6, e.getId());
            }
        } catch (NoSuchAlgorithmException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Credential extractObject(ResultSet rs) {
        Credential credential = new Credential();
        credential.setUser(new User());
        try{
            credential.setUsername(rs.getString("username"));
            credential.setPassword(rs.getString("password"));
            credential.setLastAccess(rs.getDate("lastAccess").toLocalDate());
            credential.setEnabled(rs.getBoolean("enabled"));
            credential.getUser().setId(rs.getLong("user"));
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
                credential.setUser(new User());
                credential.setUsername(rs.getString("username"));
                credential.setPassword(rs.getString("password"));
                credential.setLastAccess(rs.getDate("lastAccess").toLocalDate());
                credential.setEnabled(rs.getBoolean("enabled"));
                credential.getUser().setId(rs.getLong("user"));
                credentials.add(credential);
            }
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }   
        return credentials;
    }
    
    public User authenticate(Credential credential){
        try(PreparedStatement pstmt = DbConnection.getConnection().prepareStatement("SELECT id, username, password, lastAccess, enabled, user FROM " + TABLE +" WHERE username = ?")){
            pstmt.setString(1, credential.getUsername());
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                Credential auxCredential = extractObject(resultSet);
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update((credential.getPassword() + SALT).getBytes());
                byte[] hash = md.digest();
                String senhaMd5 = String.format("%032x", new BigInteger(1, hash));
                if(senhaMd5.equals(auxCredential.getPassword())){
                    credential.setId(resultSet.getLong("id"));
                    credential.setEnabled(auxCredential.isEnabled());
                    credential.setLastAccess(auxCredential.getLastAccess());
                    return new UserDao().findById(auxCredential.getUser().getId());
                }
            }
        return null;
        }catch(Exception ex){
            System.out.println("Ex: "+ex);
        }
        return null;
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
