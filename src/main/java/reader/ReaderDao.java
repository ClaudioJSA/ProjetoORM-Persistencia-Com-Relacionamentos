/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

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
public class ReaderDao extends Dao<Reader>{
    public final String TABLE = "reader";

    @Override
    public String getSaveStatement() {
        return "INSERT INTO " + TABLE + "(id) VALUES (?)";
    }

    @Override
    public String getUpdateStatement() {
        return "INSERT INTO TABLE (id) VALUES (?) ON DUPLICATE KEY UPDATE id = COALESCE(?, id)";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT id FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT id FROM " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM " + TABLE + " WHERE id = ?";
    }

    @Override
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, Reader e) {
        try {
            pstmt.setLong(1, e.getId());
            pstmt.setLong(2, e.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Reader extractObject(ResultSet rs) {
        Reader reader = new Reader();
        try{
            reader.setId(rs.getLong("id"));
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }
        return reader;
    }

    @Override
    public List<Reader> extractObjects(ResultSet rs) {
        List<Reader> readers = new ArrayList<>();
        try{
            while(rs.next()){
                Reader reader = new Reader();
                reader.setId(rs.getLong("id"));
                readers.add(reader);
            }
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }   
        return readers;
    }

    public ReaderDao() {
    }

    public String getTABLE() {
        return TABLE;
    }

    public static String getDB() {
        return DB;
    }
    
    
}
