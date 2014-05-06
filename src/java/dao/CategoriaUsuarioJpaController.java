/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.CategoriaUsuario;

/**
 *
 * @author maycon
 */
public class CategoriaUsuarioJpaController implements Serializable {

    public CategoriaUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaUsuario categoriaUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaUsuario categoriaUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaUsuario = em.merge(categoriaUsuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoriaUsuario.getCodCatUser();
                if (findCategoriaUsuario(id) == null) {
                    throw new NonexistentEntityException("The categoriaUsuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaUsuario categoriaUsuario;
            try {
                categoriaUsuario = em.getReference(CategoriaUsuario.class, id);
                categoriaUsuario.getCodCatUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaUsuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaUsuario> findCategoriaUsuarioEntities() {
        return findCategoriaUsuarioEntities(true, -1, -1);
    }

    public List<CategoriaUsuario> findCategoriaUsuarioEntities(int maxResults, int firstResult) {
        return findCategoriaUsuarioEntities(false, maxResults, firstResult);
    }

    private List<CategoriaUsuario> findCategoriaUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaUsuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CategoriaUsuario findCategoriaUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaUsuario> rt = cq.from(CategoriaUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
