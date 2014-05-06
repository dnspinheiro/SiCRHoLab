/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.CategoriaUsuarioJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.CategoriaUsuario;

/**
 *
 * @author tulio
 */
@ManagedBean
@RequestScoped
public class CategoriaUsuarioMB {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    CategoriaUsuarioJpaController daoCategoriaUsuario = new CategoriaUsuarioJpaController(factory);
    private CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
    private List<CategoriaUsuario> categorias = new ArrayList<CategoriaUsuario>();
    private String mensagem;
    private String categoriaPesquisada;

    public CategoriaUsuarioMB() {
        pesquisar();
    }

    public void inserirCategoriaUsuario() {
        try{
            categoriaUsuario.setCodCatUser(null);
            daoCategoriaUsuario.create(categoriaUsuario);
            categoriaUsuario = new CategoriaUsuario();
            setMensagem("Categoria incluida com sucesso");
        }catch(Exception ex){
            setMensagem("Categoria já cadastrada no sistema");
        }
        pesquisar();
    }

    public void alterarCategoriaUsuario() {
        try {
            daoCategoriaUsuario.edit(categoriaUsuario);
            categoriaUsuario = new CategoriaUsuario();
            setMensagem("Categoria alterada com sucesso");
            FacesMessage msg = new FacesMessage("Categoria alterada com sucesso");
            FacesContext.getCurrentInstance().addMessage("Erro", msg);
        } catch (NonexistentEntityException ex) {
            setMensagem("Categoria não pode ser alterada");
            FacesMessage msg = new FacesMessage("Categoria não pode ser alterada");
            FacesContext.getCurrentInstance().addMessage("Erro", msg);
            Logger.getLogger(CategoriaUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            setMensagem("Erro");
            Logger.getLogger(CategoriaUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }

    public void excluirCategoriaUsuario() {
        try {
            daoCategoriaUsuario.destroy(categoriaUsuario.getCodCatUser());
            categoriaUsuario = new CategoriaUsuario();
            setMensagem("Categoria excluida com sucesso");
            FacesMessage msg = new FacesMessage("Categoria excluida com sucesso");
            FacesContext.getCurrentInstance().addMessage("Erro", msg);
        } catch (NonexistentEntityException ex) {
            setMensagem("Categoria não pode ser excluída");
            Logger.getLogger(CategoriaUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }

    public void pesquisarPorCategoria() {
        categorias = new ArrayList<CategoriaUsuario>();
        for (CategoriaUsuario u : daoCategoriaUsuario.findCategoriaUsuarioEntities()) {
            if ((u.getDescricao().toLowerCase().contains(categoriaPesquisada.toLowerCase()))) {
                categorias.add(u);
            }
        }
        setCategoriaPesquisada(""); 
    }

    public List<CategoriaUsuario> pesquisarCategoriaUsuario() {
        return daoCategoriaUsuario.findCategoriaUsuarioEntities();
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setCategoriaUsuario(CategoriaUsuario categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }

    public CategoriaUsuario getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public List<CategoriaUsuario> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaUsuario> categorias) {
        this.categorias = categorias;
    }

    public void pesquisar() {
        categorias = daoCategoriaUsuario.findCategoriaUsuarioEntities();
    }

    public String getCategoriaPesquisada() {
        return categoriaPesquisada;
    }

    public void setCategoriaPesquisada(String categoriaPesquisada) {
        this.categoriaPesquisada = categoriaPesquisada;
    }
}
