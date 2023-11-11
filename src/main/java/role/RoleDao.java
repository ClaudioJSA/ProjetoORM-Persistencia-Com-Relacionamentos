/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package role;

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
public class RoleDao extends Dao<Role>{
    public final String TABLE = "role";

    @Override
    public String getSaveStatement() {
        return "INSERT INTO " + TABLE + "(name) VALUES (?)";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE " + TABLE + " SET name = ? WHERE id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT name FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT name FROM " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, Role e) {
        try {
            pstmt.setString(1, e.getName());
            
            if (e.getId() != null) {
                pstmt.setLong(4, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Role extractObject(ResultSet rs) {
        Role role = new Role();
        try{
            role.setName(rs.getString("name"));
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }
        return role;
    }

    @Override
    public List<Role> extractObjects(ResultSet rs) {
        List<Role> roles = new ArrayList<>();
        try{
            while(rs.next()){
                Role role = new Role();
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }   
        return roles;
    }

    public RoleDao() {
    }

    public String getTABLE() {
        return TABLE;
    }
}
