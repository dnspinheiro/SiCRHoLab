/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import beans.LoginMB;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Convidado
 */
public class AutorizacaoPL implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        
        System.out.println("página: " + viewId);
        
        
               
        if(viewId.startsWith("/index.xhtml") || viewId.startsWith("/login.xhtml") || viewId.startsWith("/cadastroUsuario.xhtml")){
            return;
        }        
        
        if(viewId.startsWith("/publico/")){
            return;
        }
        Application app = context.getApplication();
        
        LoginMB lmb = app.evaluateExpressionGet(context, "#{loginMB}", LoginMB.class);
        
        lmb.setPagina(viewId);
        if (lmb.logado())
        {
            String tipo = lmb.getUsuario().getCategoriaUsuario().getDescricao();
            tipo = tipo.toLowerCase();
            if (tipo.equals("adm")) {
                return;
            }
            
            else if (viewId.startsWith("/usuario/")) {
                return;
            }   
        }  
        
            
        ViewHandler viewHandler = app.getViewHandler();
        UIViewRoot novaPag = viewHandler.createView(context, "/login.xhtml");
        context.setViewRoot(novaPag);
        
        lmb.setUltimaPag(viewId);       
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
    
    
}

