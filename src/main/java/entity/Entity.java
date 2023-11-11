/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public abstract class Entity {
    private Long id;

//<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Construtor">
    public Entity() {
    }
//</editor-fold>
}
