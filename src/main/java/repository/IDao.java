
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 * @param <E>
 */
public interface IDao<E>{

    public String getSaveStatement();
    public String getUpdateStatement();
    public String getFindByIdStatement();
    public String getFindAllStatement();
    public String getDeleteStatement();
    public void coposeSaveOrUpdateStatement(PreparedStatement pstmt, E e);
    public E extractObject(ResultSet rs);
    public List<E> extractObjects(ResultSet rs);
    public Long saveOrUpdate(E e);
    public E findById(Long id);
    public List<E> fidAll();
    public void delete(Long id);
            
}
