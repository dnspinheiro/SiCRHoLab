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
import modelo.CategoriaDeEvento;

/**
 *
 * @author maycon
 */
public class CategoriaDeEventoJpaController implements Serializable {

    public CategoriaDeEventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaDeEvento categoriaDeEvento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaDeEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaDeEvento categoriaDeEvento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaDeEvento = em.merge(categoriaDeEvento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoriaDeEvento.getCodCatEvento();
                if (findCategoriaDeEvento(id) == null) {
                    throw new NonexistentEntityException("The categoriaDeEvento with id " + id + " no longer exists.");
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
            CategoriaDeEvento categoriaDeEvento;
            try {
                categoriaDeEvento = em.getReference(CategoriaDeEvento.class, id);
                categoriaDeEvento.getCodCatEvento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaDeEvento with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaDeEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaDeEvento> findCategoriaDeEventoEntities() {
        return findCategoriaDeEventoEntities(true, -1, -1);
    }

    public List<CategoriaDeEvento> findCategoriaDeEventoEntities(int maxResults, int firstResult) {
        return findCategoriaDeEventoEntities(false, maxResults, firstResult);
    }

    private List<CategoriaDeEvento> findCategoriaDeEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaDeEvento.class));
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

    public CategoriaDeEvento findCategoriaDeEvento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaDeEvento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaDeEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaDeEvento> rt = cq.from(CategoriaDeEvento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
