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
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.History;
import model.HistoryPK;

/**
 *
 * @author ove
 */
public class HistoryJpaController implements Serializable {

    public HistoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public HistoryJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }

    public void create(History history) throws PreexistingEntityException, Exception {
        if (history.getHistoryPK() == null) {
            history.setHistoryPK(new HistoryPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(history);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistory(history.getHistoryPK()) != null) {
                throw new PreexistingEntityException("History " + history + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(History history) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            history = em.merge(history);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistoryPK id = history.getHistoryPK();
                if (findHistory(id) == null) {
                    throw new NonexistentEntityException("The history with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistoryPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            History history;
            try {
                history = em.getReference(History.class, id);
                history.getHistoryPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The history with id " + id + " no longer exists.", enfe);
            }
            em.remove(history);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<History> findHistoryEntities() {
        return findHistoryEntities(true, -1, -1);
    }

    public List<History> findHistoryEntities(int maxResults, int firstResult) {
        return findHistoryEntities(false, maxResults, firstResult);
    }

    private List<History> findHistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(History.class));
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

    public History findHistory(HistoryPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(History.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<History> rt = cq.from(History.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<History> getHistoryByItemId(long itemid) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("History.findByItemid");
        q.setParameter("itemid", itemid);

        return q.getResultList();
    }
    
    public List<History> getHistoryByItemIdAndDate(long itemid, long clock_desde, long clock_hasta ){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT h FROM History h WHERE h.historyPK.itemid = :itemid AND (h.historyPK.clock BETWEEN :clock_desde AND :clock_hasta)");
        q.setParameter("itemid", itemid);
        q.setParameter("clock_desde", clock_desde);
        q.setParameter("clock_hasta", clock_hasta);
        return q.getResultList();
    }
}
