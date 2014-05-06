/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.SalaJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Sala;

/**
 *
 * @author maycon
 */
@ManagedBean
@RequestScoped
public class SalaMB {
    
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    
    SalaJpaController daoSala = new SalaJpaController(factory);
    
    private Sala sala = new Sala();
    
    private List<Sala> salas = new ArrayList<Sala>();
    
    private String nomeDaSala;
    
    private String mensagem;
    
    /**
     * Creates a new instance of SalaMB
     */
    public SalaMB() {
        pesquisar();
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
     * @return the salas
     */
    public List<Sala> getSalas() {
        return salas;
    }

    /**
     * @param salas the salas to set
     */
    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
    
    public void inserirSala(){
        try{
            sala.setCodigoSala(null);
            daoSala.create(sala);
            sala = new Sala();
            setMensagem("Cadastro Realizado com Sucesso");
        }catch(Exception ex){
            setMensagem("Cadastro já existente no sistema");
            Logger.getLogger(SalaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void alterarSala() {
        try {
            daoSala.edit(sala);
            sala = new Sala();
            setMensagem("Cadastro alterado com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(SalaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(SalaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void excluirSala() {
        try {
            daoSala.destroy(sala.getCodigoSala());
            sala = new Sala();
            setMensagem("Cadastro excluído com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser excluído");
            Logger.getLogger(SalaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void pesquisar(){
        salas = daoSala.findSalaEntities();
    }
    
    public void pesquisarPorNomeDeSala(){
        salas = new ArrayList<Sala>();
        for(Sala s : daoSala.findSalaEntities()){
            if(s.getNomeSala().toUpperCase().contains(getNomeDaSala().toUpperCase())){
                salas.add(s);
            }
        }
        setNomeDaSala("");
    }

    /**
     * @return the nomeDaSala
     */
    public String getNomeDaSala() {
        return nomeDaSala;
    }

    /**
     * @param nomeDaSala the nomeDaSala to set
     */
    public void setNomeDaSala(String nomeDaSala) {
        this.nomeDaSala = nomeDaSala;
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
}
