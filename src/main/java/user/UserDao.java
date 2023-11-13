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
import role.Role;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class UserDao extends Dao<User>{
    public final String TABLE = "user";
    
    @Override
    public String getSaveStatement() {
        return "INSERT INTO " + TABLE + "(name, email, birthDate, user) VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE " + TABLE + " SET name = ?, email = ?, birthDate = ?, user = ? WHERE id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT name, email, birthDate, user FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT name, email, user birthDate FROM " + TABLE;
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
            pstmt.setLong(4, e.getRole().getId());
            
            if (e.getId() != null) {
                pstmt.setLong(5, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User extractObject(ResultSet rs) {
        User user = new User();
        Role role = new Role();
        try{
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setBirthDate(rs.getDate("birthdate").toLocalDate());
            role.setId(rs.getLong("role"));
            user.setRole(role);
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
                Role role = new Role();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setBirthDate(rs.getDate("birthdate").toLocalDate());
                role.setId(rs.getLong("role"));
                user.setRole(role);
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
