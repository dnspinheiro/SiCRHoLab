/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.CategoriaDeEventoJpaController;
import dao.ReservaJpaController;
import dao.SalaJpaController;
import dao.TurmaJpaController;
import dao.UsuarioJpaController;
import dao.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.CategoriaDeEvento;
import modelo.Reserva;
import modelo.Sala;
import modelo.Turma;
import modelo.Usuario;

/**
 *
 * @author maycon
 */
@ManagedBean
@RequestScoped
public class ReservaMB {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    ReservaJpaController reservaDAO = new ReservaJpaController(factory);
    UsuarioJpaController usuarioDAO = new UsuarioJpaController(factory);
    SalaJpaController salaDAO = new SalaJpaController(factory);
    TurmaJpaController turmaDAO = new TurmaJpaController(factory);
    CategoriaDeEventoJpaController categoriaDeEventoDAO = new CategoriaDeEventoJpaController(factory);
    
    private Reserva reserva;
    private Turma turma = new Turma();
    private Usuario usuario = new Usuario();
    private Sala sala = new Sala();
    private CategoriaDeEvento categoriaDeEvento = new CategoriaDeEvento();
    private String mensagem;
    private List<Reserva> reservas = new ArrayList<Reserva>();
    private Date inicioEvento = null;
    private Date fimEvento = null;
    private String horaInicio = "";
    private String horaFim = "";
    private String reservaPesquisada;
    
    /**
     * Creates a new instance of ReservaMB
     */
    public ReservaMB() {
        reserva = new Reserva();
        reserva.setStatus("Reserva Pendente");
        pesquisar();
    }

    public void inserirReserva(){
        try{
            reserva.setCodigoReserva(null);
            reserva.setDataHora(new Date());
            reserva.setDataHoraInicioEvento(inicioEvento);
            reserva.setDataHoraFimEvento(fimEvento);
            reserva.setTurma(turma);
            reserva.setCategoriaEvento(categoriaDeEvento);
            reserva.setUsuario(usuario);
            reserva.setSala(sala);
            reservaDAO.create(reserva);
            reserva = new Reserva();
            turma = new Turma();
            usuario = new Usuario();
            sala = new Sala();
            categoriaDeEvento = new CategoriaDeEvento();
            inicioEvento = null;
            fimEvento = null;
            horaInicio = "";
            horaFim = "";
            setMensagem("Cadastro realizado com sucesso");
        }catch(Exception ex){
           setMensagem(ex.getMessage());
           Logger.getLogger(ReservaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void alterarReserva() {
        try {
            reserva.setDataHora(new Date());
            reserva.setDataHoraInicioEvento(inicioEvento);
            reserva.setDataHoraFimEvento(fimEvento);
            reserva.setTurma(turma);
            reserva.setCategoriaEvento(categoriaDeEvento);
            reserva.setUsuario(usuario);
            reserva.setSala(sala);
            reservaDAO.edit(reserva);
            reserva = new Reserva();
            turma = new Turma();
            usuario = new Usuario();
            sala = new Sala();
            categoriaDeEvento = new CategoriaDeEvento();
            inicioEvento = null;
            fimEvento = null;
            horaInicio = "";
            horaFim = "";
            setMensagem("Cadastro alterado com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(ReservaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(ReservaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void excluirReserva() {
        try {
            reservaDAO.destroy(reserva.getCodigoReserva());
            reserva = new Reserva();
            turma = new Turma();
            usuario = new Usuario();
            sala = new Sala();
            categoriaDeEvento = new CategoriaDeEvento();
            inicioEvento = null;
            fimEvento = null;
            horaInicio = "";
            horaFim = "";
            setMensagem("Cadastro excluído com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser excluído");
            Logger.getLogger(ReservaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public String retornaDataHoraAtual(Reserva re){
        String dataHora = "";
        for(Reserva r : reservas){
            if(r.getCodigoReserva() == re.getCodigoReserva()){
                dataHora = formatarData(r.getDataHora());
                reserva.setDataHora(r.getDataHora());
            }
        }
        return dataHora;
    }
    
    public String retornaDataHoraInicio(Reserva re){
        String dataHora = "";
        for(Reserva r : reservas){
            if(r.getCodigoReserva() == re.getCodigoReserva()){
                dataHora = formatarData(r.getDataHoraInicioEvento());
                reserva.setDataHoraInicioEvento(r.getDataHoraInicioEvento());
            }
        }
        return dataHora;
    }
    
    public String retornaDataHoraFim(Reserva re){
        String dataHora = "";
        for(Reserva r : reservas){
            if(r.getCodigoReserva() == re.getCodigoReserva()){
                dataHora = formatarData(r.getDataHoraFimEvento());
                reserva.setDataHoraFimEvento(r.getDataHoraFimEvento());
            }
        }
        return dataHora;
    }
    
    public String formatarData(Date d){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = formatador.format(d);
        return data;
    }
    
    public void pesquisar() {
        setReservas(reservaDAO.findReservaEntities());
    }
    
    public void pesquisarPorReserva() {
        reservas = new ArrayList<Reserva>();
        for (Reserva r : reservaDAO.findReservaEntities()) {
            if ((r.getStatus().toLowerCase().contains(reservaPesquisada) || (String.valueOf(r.getTurma().getNumero_turma()).contains(reservaPesquisada) || (r.getDescricao().toLowerCase().contains(reservaPesquisada))))) {
                reservas.add(r);
            }
        }
        setReservaPesquisada("");
    }
    
    public List<Reserva> listaReservas() {
        return reservaDAO.findReservaEntities();
    }
    
    public List<Usuario> listaUsuarios() {
        return usuarioDAO.findUsuarioEntities();
    }
    
    public List<Turma> listaTurmas() {
        return turmaDAO.findTurmaEntities();
    }
    
    public List<CategoriaDeEvento> listaCategoriaDeEventos() {
        return categoriaDeEventoDAO.findCategoriaDeEventoEntities();
    }
    
    public List<Sala> listaSalas() {
        return salaDAO.findSalaEntities();
    }
    
    /**
     * @return the reserva
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        setSala(reserva.getSala());
        setTurma(reserva.getTurma());
        setUsuario(reserva.getUsuario());
        setCategoriaDeEvento(reserva.getCategoriaEvento());
        setInicioEvento(reserva.getDataHoraInicioEvento());
        setFimEvento(reserva.getDataHoraFimEvento());
        if(reserva.getDataHoraInicioEvento().getMinutes() == 0){
            setHoraInicio(reserva.getDataHoraInicioEvento().getHours()+":"+reserva.getDataHoraInicioEvento().getMinutes()+"0");
        }
        else{
            setHoraInicio(reserva.getDataHoraInicioEvento().getHours()+":"+reserva.getDataHoraInicioEvento().getMinutes());
        }
        setHoraFim(reserva.getDataHoraFimEvento().getHours()+":"+reserva.getDataHoraFimEvento().getMinutes());
        this.reserva = reserva;
    }

    /**
     * @return the turma
     */
    public Turma getTurma() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(Turma turma) {
        this.turma = turma;
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
     * @return the categoriaDeEvento
     */
    public CategoriaDeEvento getCategoriaDeEvento() {
        return categoriaDeEvento;
    }

    /**
     * @param categoriaDeEvento the categoriaDeEvento to set
     */
    public void setCategoriaDeEvento(CategoriaDeEvento categoriaDeEvento) {
        this.categoriaDeEvento = categoriaDeEvento;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the reservas
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the inicioEvento
     */
    public Date getInicioEvento() {
        return inicioEvento;
    }

    /**
     * @param inicioEvento the inicioEvento to set
     */
    public void setInicioEvento(Date inicioEvento) {
        this.inicioEvento = inicioEvento;
    }

    /**
     * @return the fimEvento
     */
    public Date getFimEvento() {
        return fimEvento;
    }

    /**
     * @param fimEvento the fimEvento to set
     */
    public void setFimEvento(Date fimEvento) {
        this.fimEvento = fimEvento;
    }

    /**
     * @return the reservaPesquisada
     */
    public String getReservaPesquisada() {
        return reservaPesquisada;
    }

    /**
     * @param reservaPesquisada the reservaPesquisada to set
     */
    public void setReservaPesquisada(String reservaPesquisada) {
        this.reservaPesquisada = reservaPesquisada;
    }

    /**
     * @return the horaInicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
        String array[] = horaInicio.split(":"); 
        inicioEvento.setHours(Integer.parseInt(array[0]));
        inicioEvento.setMinutes(Integer.parseInt(array[1]));
    }

    /**
     * @return the horaFim
     */
    public String getHoraFim() {
        return horaFim;
    }

    /**
     * @param horaFim the horaFim to set
     */
    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
        String array[] = horaFim.split(":"); 
        fimEvento.setHours(Integer.parseInt(array[0]));
        fimEvento.setMinutes(Integer.parseInt(array[1]));
    }
}