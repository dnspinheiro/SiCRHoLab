/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.ReservaJpaController;
import dao.SalaJpaController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Reserva;
import modelo.Sala;

/**
 *
 * @author tulio
 */
@ManagedBean
@SessionScoped
public class HorarioMB {

    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    
    private SalaJpaController daoSala = new SalaJpaController(factory);
    
    private ReservaJpaController daoReserva = new ReservaJpaController(factory);
    
    private List<Reserva> reservas = new ArrayList<Reserva>();
    
    private List<Reserva> result = new ArrayList<Reserva>();
    
    private List<Reserva> listaFiltrada = new ArrayList<Reserva>();
    
    private Reserva reserva = new Reserva();
     
    private Long idSala;
    
    private Sala sala = new Sala();
    
    private int dia;
    
    private int mes;
    
    private Date data;
    
    private String mensagem = "";
    
    
    public HorarioMB() {
         
    }

    public List<Reserva> getCidades() {
        return reservas;
    }

    public Long getIdSala() {
        return idSala;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Sala getSala() {
        return sala;
    }

    public ReservaJpaController getDaoReserva() {
        return daoReserva;
    }

    public SalaJpaController getDaoSala() {
        return daoSala;
    }

    public void setCidades(List<Reserva> cidades) {
        this.setCidades(cidades);
    }

    /**
     * @param daoSala the daoSala to set
     */
    public void setDaoSala(SalaJpaController daoSala) {
        this.daoSala = daoSala;
    }

    /**
     * @param daoReserva the daoReserva to set
     */
    public void setDaoReserva(ReservaJpaController daoReserva) {
        this.daoReserva = daoReserva;
    }

    /**
     * @param cidades the cidades to set
     *

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @param idSala the idSala to set
     */
    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public List<Reserva> buscarReservas(){
        result = new ArrayList<Reserva>();
        for(Reserva c: daoReserva.findReservaEntities()){
            if(c.getSala().getCodigoSala().equals(sala.getCodigoSala())){
                //if(c.getDataHoraInicioEvento().getDay() == dia){
                    result.add(c);
                }
                    
        //    }
            Collections.sort(result,new Comparator(){
             
                public int compare(Object o1, Object o2) {  
                    Reserva p1 = (Reserva) o1;  
                    Reserva p2 = (Reserva) o2;  
                    return p1.getDataHoraInicioEvento().getHours() < p2.getDataHoraInicioEvento().getHours() ? -1 : (p1.getDataHoraInicioEvento().getHours() > p2.getDataHoraInicioEvento().getHours() ? +1 : 0);  
                }  
        });
    }
        
        return result;
  }
    
    
    public List<Sala> buscarSalas(){
        return daoSala.findSalaEntities();
    }
    
    public String listarHorarios(){
        
        return "/usuario/horarios.xhtml";
    }

    public List<Reserva> getResult() {
        return result;
    }

    public void setResult(List<Reserva> result) {
        this.result = result;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    

    public List<Reserva> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Reserva> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
       
    
}
