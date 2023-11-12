/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarian;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reader.ReaderDao;
import repository.Dao;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class LibrarianDao extends Dao<Librarian>{
    public final String TABLE = "librarian";

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
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, Librarian e) {
        try {
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getEmail());
            pstmt.setDate(3, Date.valueOf(e.getBirthDate()));
            
            if (e.getId() != null) {
                pstmt.setLong(4, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Librarian extractObject(ResultSet rs) {
        Librarian librarian = new Librarian();
        try{
            librarian.setName(rs.getString("name"));
            librarian.setEmail(rs.getString("email"));
            librarian.setBirthDate(rs.getDate("birthdate").toLocalDate());
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }
        return librarian;
    }

    @Override
    public List<Librarian> extractObjects(ResultSet rs) {
        List<Librarian> librarians = new ArrayList<>();
        try{
            while(rs.next()){
                Librarian librarian = new Librarian();
                librarian.setName(rs.getString("name"));
                librarian.setEmail(rs.getString("email"));
                librarian.setBirthDate(rs.getDate("birthdate").toLocalDate());
                librarians.add(librarian);
            }
        }catch(Exception ex){
                System.out.println("Ex: " + ex);      
        }   
        return librarians;
    }
    
    
    
}
