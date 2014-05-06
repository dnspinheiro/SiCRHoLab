/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Anderson
 */
@Entity
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod_turma;
    
    private int ano_letivo;
    private int numero_turma;
    
    @OneToOne
    private Usuario usuario;
    
    @OneToOne
    private Disciplina disciplina;
    
    public Long getCod_turma() {
        return cod_turma;
    }

    public void setCod_turma(Long cod_turma) {
        this.cod_turma = cod_turma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod_turma != null ? cod_turma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.cod_turma == null && other.cod_turma != null) || (this.cod_turma != null && !this.cod_turma.equals(other.cod_turma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Turma[ id=" + cod_turma + " ]";
    }

    /**
     * @return the ano_letivo
     */
    public int getAno_letivo() {
        return ano_letivo;
    }

    /**
     * @param ano_letivo the ano_letivo to set
     */
    public void setAno_letivo(int ano_letivo) {
        this.ano_letivo = ano_letivo;
    }

    /**
     * @return the numero_turma
     */
    public int getNumero_turma() {
        return numero_turma;
    }

    /**
     * @param numero_turma the numero_turma to set
     */
    public void setNumero_turma(int numero_turma) {
        this.numero_turma = numero_turma;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the disciplina
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
}
