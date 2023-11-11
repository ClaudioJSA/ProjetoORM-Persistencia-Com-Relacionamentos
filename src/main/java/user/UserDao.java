/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

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
public class UserDao extends Dao<User>{
    public final String TABLE = "user";
    
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
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, User e) {
        try {
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getEmail());
            pstmt.setDate(3, Date.valueOf(e.getBirthDate()));
            
            if (e.getId() != null) {
                pstmt.setLong(4, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User extractObject(ResultSet rs) {
        User user = new User();
        try{
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setBirthDate(rs.getDate("birthdate").toLocalDate());
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }
        return user;
    }

    @Override
    public List<User> extractObjects(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try{
            while(rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setBirthDate(rs.getDate("birthdate").toLocalDate());
                users.add(user);
            }
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }   
        return users;
    }

    public UserDao() {
    }

    public String getTABLE() {
        return TABLE;
    }
}
