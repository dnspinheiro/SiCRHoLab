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
 * @author Anderson
 */
@Entity
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cod_disciplina;
    
    private int carga_horaria;
    private String curso;
    
    @Column(unique=true)
    private String nome;
    
    public String getCod_disciplina() {
        return cod_disciplina;
    }

    public void setCod_disciplina(String cod_disciplina) {
        this.cod_disciplina = cod_disciplina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod_disciplina != null ? cod_disciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.cod_disciplina == null && other.cod_disciplina != null) || (this.cod_disciplina != null && !this.cod_disciplina.equals(other.cod_disciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Disciplina[ id=" + cod_disciplina + " ]";
    }

    /**
     * @return the carga_horaria
     */
    public int getCarga_horaria() {
        return carga_horaria;
    }

    /**
     * @param carga_horaria the carga_horaria to set
     */
    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
}