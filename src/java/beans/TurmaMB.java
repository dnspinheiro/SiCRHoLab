/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.DisciplinaJpaController;
import dao.TurmaJpaController;
import dao.UsuarioJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Disciplina;
import modelo.Turma;
import modelo.Usuario;

/**
 *
 * @author Anderson
 */
@ManagedBean
@RequestScoped
public class TurmaMB {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    TurmaJpaController turmaDAO = new TurmaJpaController(factory);
    UsuarioJpaController usuarioDAO = new UsuarioJpaController(factory);
    DisciplinaJpaController disciplinaDAO = new DisciplinaJpaController(factory);
    
    private Turma turma = new Turma();
    private List<Turma> turmas = new ArrayList<Turma>();
    private String turmaPesquisada;
    private String mensagem;
    private int anoLetivo;
    private int numeroTurma;
    
    private Usuario usuario = new Usuario();
    private Disciplina disciplina = new Disciplina();
    
    public TurmaMB() {
        pesquisar();
    }
    
    public void inserirTurma(){
        try{
            turma.setCod_turma(null);
            turma.setUsuario(usuario);
            turma.setDisciplina(disciplina);
            turmaDAO.create(turma);
            turma = new Turma();
            usuario = new Usuario();
            setMensagem("Cadastro realizado com sucesso");
        }catch(Exception ex){
           setMensagem("Cadastro já existente no sistema");
           Logger.getLogger(DisciplinaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void alterarTurma() {
        try {
            turma.setUsuario(usuario);
            turma.setDisciplina(disciplina);
            turmaDAO.edit(turma);
            turma = new Turma();
            usuario = new Usuario();
            setMensagem("Cadastro alterado com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(DisciplinaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(DisciplinaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void excluirTurma() {
        try {
            turmaDAO.destroy(turma.getCod_turma());
            turma = new Turma();
            setMensagem("Cadastro excluído com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser excluído");
            Logger.getLogger(DisciplinaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public List<Usuario> listaUsuarios() {
        return usuarioDAO.findUsuarioEntities();
    }

    public List<Disciplina> listaDisciplinas() {
        return disciplinaDAO.findDisciplinaEntities();
    }
    
    public void pesquisarTurmaPorAno(){
        turmas = new ArrayList<Turma>();
        for(Turma t : turmaDAO.findTurmaEntities()){
            if(t.getAno_letivo() == getAnoLetivo()){
                turmas.add(t);
            }
        }
        setAnoLetivo(0);
    }
    
    public void pesquisarTurmaPorNumero(){
        turmas = new ArrayList<Turma>();
        for(Turma t : turmaDAO.findTurmaEntities()){
            if(t.getNumero_turma() == getNumeroTurma()){
                turmas.add(t);
            }
        }
        setNumeroTurma(0);
    }
    
    public void pesquisar() {
        turmas = turmaDAO.findTurmaEntities();
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
     * @return the turmas
     */
    public List<Turma> getTurmas() {
        return turmas;
    }

    /**
     * @param turmas the turmas to set
     */
    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    /**
     * @return the turmaPesquisada
     */
    public String getTurmaPesquisada() {
        return turmaPesquisada;
    }

    /**
     * @param turmaPesquisada the turmaPesquisada to set
     */
    public void setTurmaPesquisada(String turmaPesquisada) {
        this.turmaPesquisada = turmaPesquisada;
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

    /**
     * @return the anoLetivo
     */
    public int getAnoLetivo() {
        return anoLetivo;
    }

    /**
     * @param anoLetivo the anoLetivo to set
     */
    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    /**
     * @return the numeroTurma
     */
    public int getNumeroTurma() {
        return numeroTurma;
    }

    /**
     * @param numeroTurma the numeroTurma to set
     */
    public void setNumeroTurma(int numeroTurma) {
        this.numeroTurma = numeroTurma;
    }
}
