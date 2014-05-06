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
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoSala;

    @Column(unique=true)
    private String nomeSala;
    
    private String localizacao;
    
    private String descricao;
    
    private String capacidade;
    
    private String status;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoSala != null ? codigoSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.codigoSala == null && other.codigoSala != null) || (this.codigoSala != null && !this.codigoSala.equals(other.codigoSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ManterSala[ id=" + codigoSala + " ]";
    }

    /**
     * @return the codigoSala
     */
    public Long getCodigoSala() {
        return codigoSala;
    }

    /**
     * @param codigoSala the codigoSala to set
     */
    public void setCodigoSala(Long codigoSala) {
        this.codigoSala = codigoSala;
    }

    /**
     * @return the nomeSala
     */
    public String getNomeSala() {
        return nomeSala;
    }

    /**
     * @param nomeSala the nomeSala to set
     */
    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    /**
     * @return the localizacao
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    /**
     * @return the capacidade
     */
    public String getCapacidade() {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
