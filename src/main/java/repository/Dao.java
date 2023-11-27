/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 * @param <E>
 */
public abstract class Dao<E> implements IDao<E>{
    
    public static final String DB = "projetopoo";

    @Override
    public Long saveOrUpdate(E e) {
        Long id = 0L;
        
        if (((Entity) e).getId() == null|| ((Entity) e).getId() == 0){
            try(PreparedStatement pstmt = DbConnection.getConnection().prepareStatement(getSaveStatement(),Statement.RETURN_GENERATED_KEYS)){
                coposeSaveOrUpdateStatement(pstmt, e);
                System.out.println(pstmt);
                pstmt.executeUpdate();
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if(resultSet.next()){
                    id = resultSet.getLong(1);
                }
            } catch (Exception ex) {
                System.out.println(">> " +ex);
            }
        } else{
            try(PreparedStatement pstmt = DbConnection.getConnection().prepareStatement(getUpdateStatement())){
                coposeSaveOrUpdateStatement(pstmt, e);
                System.out.println(pstmt);
                pstmt.executeUpdate();
                id = ((Entity)e).getId();
            } catch (Exception ex) {
                System.out.println("Ex: "+ ex);
            }
        }
        return id;
    }

    @Override
    public E findById(Long id) {
        try(PreparedStatement pstmt = DbConnection.getConnection().prepareStatement(getFindByIdStatement())){
            pstmt.setLong(1, id);
            System.out.println(pstmt);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next())
                return extractObject(resultSet);
        return null;
        }catch(Exception ex){
            System.out.println("Ex: "+ex);
        }
        return null;
    }

    @Override
    public List<E> findAll() {
        try ( PreparedStatement pstsmt = DbConnection.getConnection().prepareStatement(getFindAllStatement())) {
            System.out.println(pstsmt);
            ResultSet resultSet = pstsmt.executeQuery();
            return extractObjects(resultSet);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement pstmt = DbConnection.getConnection().prepareStatement(getDeleteStatement())){
            pstmt.setLong(1, id);
            System.out.println(pstmt);
            pstmt.executeUpdate();
            System.out.println("Exclusão executada com sucesso.");
        } catch (Exception ex) {
            System.out.println("Ex:" + ex);
        }
    }
    
}