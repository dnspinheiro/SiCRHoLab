/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author maycon
 */
@Entity
public class CategoriaDeEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codCatEvento;
    
    @Column(unique=true)
    private String prioridade;
    
    private String descricao;

    /**
     * @return the codCatEvento
     */
    public Long getCodCatEvento() {
        return codCatEvento;
    }

    /**
     * @param codCatEvento the codCatEvento to set
     */
    public void setCodCatEvento(Long codCatEvento) {
        this.codCatEvento = codCatEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCatEvento != null ? codCatEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaDeEvento)) {
            return false;
        }
        CategoriaDeEvento other = (CategoriaDeEvento) object;
        if ((this.codCatEvento == null && other.codCatEvento != null) || (this.codCatEvento != null && !this.codCatEvento.equals(other.codCatEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CategoriaDeEvento[ id=" + codCatEvento + " ]";
    }

    /**
     * @return the prioridade
     */
    public String getPrioridade() {
        return prioridade;
    }

    /**
     * @param prioridade the prioridade to set
     */
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
