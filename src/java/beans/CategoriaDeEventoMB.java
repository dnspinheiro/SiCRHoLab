/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.CategoriaDeEventoJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.CategoriaDeEvento;

/**
 *
 * @author maycon
 */
@ManagedBean
@RequestScoped
public class CategoriaDeEventoMB {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    
    CategoriaDeEventoJpaController daoCatEvento = new CategoriaDeEventoJpaController(factory);
    
    private CategoriaDeEvento catEvento = new CategoriaDeEvento();
    
    private List<CategoriaDeEvento> catEventos = new ArrayList<CategoriaDeEvento>();
    
    private String nome;
    
    private String mensagem;
    
    /**
     * Creates a new instance of SalaMB
     */
    public CategoriaDeEventoMB() {
        pesquisar();
    }

    /**
     * @return the catEvento
     */
    public CategoriaDeEvento getCatEvento() {
        return catEvento;
    }

    /**
     * @param catEvento the catEvento to set
     */
    public void setCatEvento(CategoriaDeEvento catEvento) {
        this.catEvento = catEvento;
    }

    /**
     * @return the catEventos
     */
    public List<CategoriaDeEvento> getCatEventos() {
        return catEventos;
    }

    /**
     * @param catEventos the catEventos to set
     */
    public void setCatEventos(List<CategoriaDeEvento> catEventos) {
        this.catEventos = catEventos;
    }
    
    public void inserirCategoriaDeEvento(){
        try{
            catEventos = new ArrayList<CategoriaDeEvento>();
            catEvento.setCodCatEvento(null);
            daoCatEvento.create(catEvento);
            catEvento = new CategoriaDeEvento();
            setMensagem("Cadastro realizado com sucesso");
        }catch(Exception ex){
            setMensagem("Cadastro já existente no sistema");
            Logger.getLogger(CategoriaDeEventoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void alterarCategoriaDeEvento() {
        try {
            catEventos = new ArrayList<CategoriaDeEvento>();
            daoCatEvento.edit(catEvento);
            catEvento = new CategoriaDeEvento();
            setMensagem("Cadastro alterado com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(CategoriaDeEventoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            setMensagem("Cadastro não pode ser alterado");
            Logger.getLogger(CategoriaDeEventoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }
    
    public void excluirCategoriaDeEvento() {
        try {
            catEventos = new ArrayList<CategoriaDeEvento>();
            daoCatEvento.destroy(catEvento.getCodCatEvento());
            catEvento = new CategoriaDeEvento();
            setMensagem("Cadastro excluido com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Cadastro não pode ser excluído");
            Logger.getLogger(CategoriaDeEventoMB.class.getName()).log(Level.SEVERE, null, ex);
        } 
        pesquisar();
    }
    
    public void pesquisar(){
        catEventos = daoCatEvento.findCategoriaDeEventoEntities();
    }
    
    public void pesquisarPorNome(){
        catEventos = new ArrayList<CategoriaDeEvento>();
        for(CategoriaDeEvento c : daoCatEvento.findCategoriaDeEventoEntities()){
            if(c.getDescricao().toUpperCase().contains(getNome().toUpperCase())){
                catEventos.add(c);
            }
        }
        setNome("");
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
}
