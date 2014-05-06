/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author maycon
 */
@Entity
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoReserva;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraInicioEvento;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraFimEvento;
    
    private String periodicidade;
    
    private String status;
    
    @OneToOne
    private Sala sala;
    
    @OneToOne
    private Usuario usuario;
    
    @OneToOne
    private Turma turma;
    
    @OneToOne
    private CategoriaDeEvento categoriaEvento;
    
    private String descricao;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoReserva != null ? codigoReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codigoReserva == null && other.codigoReserva != null) || (this.codigoReserva != null && !this.codigoReserva.equals(other.codigoReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reserva[ id=" + codigoReserva + " ]";
    }

    /**
     * @return the codigoReserva
     */
    public Long getCodigoReserva() {
        return codigoReserva;
    }

    /**
     * @param codigoReserva the codigoReserva to set
     */
    public void setCodigoReserva(Long codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    /**
     * @return the dataHora
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * @return the dataHoraInicioEvento
     */
    public Date getDataHoraInicioEvento() {
        return dataHoraInicioEvento;
    }

    /**
     * @param dataHoraInicioEvento the dataHoraInicioEvento to set
     */
    public void setDataHoraInicioEvento(Date dataHoraInicioEvento) {
        this.dataHoraInicioEvento = dataHoraInicioEvento;
    }

    /**
     * @return the dataHoraFimEvento
     */
    public Date getDataHoraFimEvento() {
        return dataHoraFimEvento;
    }

    /**
     * @param dataHoraFimEvento the dataHoraFimEvento to set
     */
    public void setDataHoraFimEvento(Date dataHoraFimEvento) {
        this.dataHoraFimEvento = dataHoraFimEvento;
    }

    /**
     * @return the periodicidade
     */
    public String getPeriodicidade() {
        return periodicidade;
    }

    /**
     * @param periodicidade the periodicidade to set
     */
    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
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

    /**
     * @return the sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * @return the matricula
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the codigoTurma
     */
    public Turma getTurma() {
        return turma;
    }

    /**
     * @param codigoTurma the codigoTurma to set
     */
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    /**
     * @return the codigoCategoriaEvento
     */
    public CategoriaDeEvento getCategoriaEvento() {
        return categoriaEvento;
    }

    /**
     * @param codigoCategoriaEvento the codigoCategoriaEvento to set
     */
    public void setCategoriaEvento(CategoriaDeEvento categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
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
