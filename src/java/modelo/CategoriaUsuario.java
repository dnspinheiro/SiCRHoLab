/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author tulio
 */
@Entity
public class CategoriaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codCatUser;
    
    @Column(unique=true)
    private String prioridade;
    
    @Column(unique=true)
    private String descricao;
    
    @OneToMany (mappedBy="categoriaUsuario")
    private List<Usuario> usuarios; 

    public Long getCodCatUser() {
        return codCatUser;
    }

    public void setCodCatUser(Long codCatUser) {
        this.codCatUser = codCatUser;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }  
}
