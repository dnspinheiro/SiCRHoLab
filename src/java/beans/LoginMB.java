/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.UsuarioJpaController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;


@ManagedBean
@SessionScoped
public class LoginMB {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("SICHROLABPU");
    
    UsuarioJpaController daoUsuario = new UsuarioJpaController(factory);

    private Usuario usuario = new Usuario();
    
    private String mensagem;
    
    private boolean logado;
    
    private String pagina = null;
            
    private String ultimaPag = null;

    public LoginMB() {
        logado = !(usuario.getMatricula() == null);
    }
    
    public String login(){
        //acessar o banco de dados e validar;
        usuario = daoUsuario.findUsuario(this.usuario);
        
        if (usuario!=null){
            logado = true;
            setMensagem("");
            return "index.xhtml";
        }
        
        usuario = new Usuario();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorreto!", "");
        fc.addMessage(pagina, msg);        
        setMensagem("Login ou senha incorreto!");
        return "login.xhtml";
       
    }
    
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
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    
    public boolean veioDeOutraPagina(){
        return getUltimaPag() != null;
    }
    
    public void setUltimaPag(String u){
        ultimaPag = u;
    }
    
    public boolean logado()
    {
        return logado;
        
    }

    /**
     * @return the ultimaPag
     */
    public String getUltimaPag() {
        
        if ((ultimaPag == null) || (ultimaPag.contains("icon")) || (ultimaPag.contains("img")) || (ultimaPag.contains("index")))
        {
            return "../index.xhtml";
        }
        return ultimaPag;
    }
    
    public String logout(){
        usuario = new Usuario();
        logado = false;
        
        return "../index.xhtml";
    }
}
