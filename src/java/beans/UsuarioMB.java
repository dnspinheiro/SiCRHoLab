/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.CategoriaUsuarioJpaController;
import dao.UsuarioJpaController;
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
import modelo.Usuario;

/**
 *
 * @author Túlio
 */
@ManagedBean
@RequestScoped
public class UsuarioMB {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    UsuarioJpaController daoUsuario = new UsuarioJpaController(factory);
    CategoriaUsuarioJpaController daoCategoria = new CategoriaUsuarioJpaController(factory);
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private String mensagem;
    private String usuarioPesquisado;
    private String confirmacao;
    private CategoriaUsuario categoria = new CategoriaUsuario();

    public UsuarioMB() {
        pesquisar();
    }

    public void inserirUsuario() {

        try{
            if(usuario.getSenha().equals(confirmacao)){
                usuario.setCategoriaUsuario(categoria);
                daoUsuario.create(usuario);
                usuario = new Usuario();
                setMensagem("Usuário cadastrado com sucesso");
            }else{
            setMensagem("Senhas não conferem");
            }
        }catch(Exception ex){
            setMensagem("Usuário já cadastrado no sistema");
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //   setMensagem("Usuário já cadastrado no sistema");

        // }
        pesquisar();
    }

    public void alterarUsuario() {
        try {
            usuario.setCategoriaUsuario(categoria);
            daoUsuario.edit(usuario);
            usuario = new Usuario();
            setMensagem("Usuário alterado com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Usuário não pode ser alterado");
            FacesMessage msg = new FacesMessage("Usuário alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage("Erro", msg);
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            setMensagem("Usuário já existente");
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }

    public void excluirUsuario() {
        try {
            daoUsuario.destroy(usuario.getMatricula());
            usuario = new Usuario();
            setMensagem("Usúario excluído com sucesso");
        } catch (NonexistentEntityException ex) {
            setMensagem("Usuário não pode ser excluido");
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisar();
    }

    public List<Usuario> pesquisarUsuario() {
        return daoUsuario.findUsuarioEntities();
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }


    public CategoriaUsuario buscarCategoria(CategoriaUsuario categoria) {
        List<CategoriaUsuario> result = daoCategoria.findCategoriaUsuarioEntities();
        for (CategoriaUsuario c : result) {
            if (c.equals(categoria)) {
                return c;
            }
        }
        return new CategoriaUsuario();
    }

    public void pesquisarPorUsuario() {
        usuarios = new ArrayList<Usuario>();
        for (Usuario u : daoUsuario.findUsuarioEntities()) {
            if ((u.getMatricula().toLowerCase().contains(usuarioPesquisado) || (u.getNome().toLowerCase().contains(usuarioPesquisado) || (u.getCategoriaUsuario().getDescricao().toLowerCase().contains(usuarioPesquisado))))) {
               // u.setCategoriaUsuario(categoria);
                usuarios.add(u);
            }
        }
        setUsuarioPesquisado("");
    }

    public List<CategoriaUsuario> categorias() {
        return daoCategoria.findCategoriaUsuarioEntities();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CategoriaUsuario getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaUsuario categoria) {
        this.categoria = categoria;
    }

    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }

    public void pesquisar() {
        usuarios = daoUsuario.findUsuarioEntities();
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getUsuarioPesquisado() {
        return usuarioPesquisado;
    }

    public void setUsuarioPesquisado(String usuarioPesquisado) {
        this.usuarioPesquisado = usuarioPesquisado;
    }
    
    public String cadastrarUsuario(){
        return "../cadastroUsuario.xhtml";
    }
}
