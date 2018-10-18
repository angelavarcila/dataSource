/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.HistoryUint;
import model.HistoryUintPK;

/**
 *
 * @author ove
 */
public class HistoryUintJpaController implements Serializable {

    public HistoryUintJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoryUint historyUint) throws PreexistingEntityException, Exception {
        if (historyUint.getHistoryUintPK() == null) {
            historyUint.setHistoryUintPK(new HistoryUintPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historyUint);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoryUint(historyUint.getHistoryUintPK()) != null) {
                throw new PreexistingEntityException("HistoryUint " + historyUint + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoryUint historyUint) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historyUint = em.merge(historyUint);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistoryUintPK id = historyUint.getHistoryUintPK();
                if (findHistoryUint(id) == null) {
                    throw new NonexistentEntityException("The historyUint with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistoryUintPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoryUint historyUint;
            try {
                historyUint = em.getReference(HistoryUint.class, id);
                historyUint.getHistoryUintPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historyUint with id " + id + " no longer exists.", enfe);
            }
            em.remove(historyUint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoryUint> findHistoryUintEntities() {
        return findHistoryUintEntities(true, -1, -1);
    }

    public List<HistoryUint> findHistoryUintEntities(int maxResults, int firstResult) {
        return findHistoryUintEntities(false, maxResults, firstResult);
    }

    private List<HistoryUint> findHistoryUintEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoryUint.class));
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

    public HistoryUint findHistoryUint(HistoryUintPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoryUint.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoryUintCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoryUint> rt = cq.from(HistoryUint.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
