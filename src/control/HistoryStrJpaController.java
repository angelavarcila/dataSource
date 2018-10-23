/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.HistoryStr;
import model.HistoryStrPK;

/**
 *
 * @author ove
 */
public class HistoryStrJpaController implements Serializable {

    public HistoryStrJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public HistoryStrJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }

    public void create(HistoryStr historyStr) throws PreexistingEntityException, Exception {
        if (historyStr.getHistoryStrPK() == null) {
            historyStr.setHistoryStrPK(new HistoryStrPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historyStr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoryStr(historyStr.getHistoryStrPK()) != null) {
                throw new PreexistingEntityException("HistoryStr " + historyStr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoryStr historyStr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historyStr = em.merge(historyStr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistoryStrPK id = historyStr.getHistoryStrPK();
                if (findHistoryStr(id) == null) {
                    throw new NonexistentEntityException("The historyStr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistoryStrPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoryStr historyStr;
            try {
                historyStr = em.getReference(HistoryStr.class, id);
                historyStr.getHistoryStrPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historyStr with id " + id + " no longer exists.", enfe);
            }
            em.remove(historyStr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoryStr> findHistoryStrEntities() {
        return findHistoryStrEntities(true, -1, -1);
    }

    public List<HistoryStr> findHistoryStrEntities(int maxResults, int firstResult) {
        return findHistoryStrEntities(false, maxResults, firstResult);
    }

    private List<HistoryStr> findHistoryStrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoryStr.class));
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

    public HistoryStr findHistoryStr(HistoryStrPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoryStr.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoryStrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoryStr> rt = cq.from(HistoryStr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HistoryStr> getHistoryStrByItemId(Long itemid) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("HistoryStr.findByItemid");
        q.setParameter("itemid", itemid);

        return q.getResultList();
    }
    
    public List<HistoryStr> getHistoryStrByItemIdAndDate(long itemid, long clock_desde, long clock_hasta ){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT h FROM HistoryStr h WHERE h.historyStrPK.itemid = :itemid AND (h.historyStrPK.clock BETWEEN :clock_desde AND :clock_hasta)");
        q.setParameter("itemid", itemid);
        q.setParameter("clock_desde", clock_desde);
        q.setParameter("clock_hasta", clock_hasta);
        return q.getResultList();
    }
    
}
