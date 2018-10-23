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
import model.HistoryLog;
import model.HistoryLogPK;

/**
 *
 * @author ove
 */
public class HistoryLogJpaController implements Serializable {

    public HistoryLogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public HistoryLogJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }
    
    public void create(HistoryLog historyLog) throws PreexistingEntityException, Exception {
        if (historyLog.getHistoryLogPK() == null) {
            historyLog.setHistoryLogPK(new HistoryLogPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historyLog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoryLog(historyLog.getHistoryLogPK()) != null) {
                throw new PreexistingEntityException("HistoryLog " + historyLog + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoryLog historyLog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historyLog = em.merge(historyLog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistoryLogPK id = historyLog.getHistoryLogPK();
                if (findHistoryLog(id) == null) {
                    throw new NonexistentEntityException("The historyLog with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistoryLogPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoryLog historyLog;
            try {
                historyLog = em.getReference(HistoryLog.class, id);
                historyLog.getHistoryLogPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historyLog with id " + id + " no longer exists.", enfe);
            }
            em.remove(historyLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoryLog> findHistoryLogEntities() {
        return findHistoryLogEntities(true, -1, -1);
    }

    public List<HistoryLog> findHistoryLogEntities(int maxResults, int firstResult) {
        return findHistoryLogEntities(false, maxResults, firstResult);
    }

    private List<HistoryLog> findHistoryLogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoryLog.class));
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

    public HistoryLog findHistoryLog(HistoryLogPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoryLog.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoryLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoryLog> rt = cq.from(HistoryLog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HistoryLog> getHistoryLogByItemId(Long itemid) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("HistoryLog.findByItemid");
        q.setParameter("itemid", itemid);

        return q.getResultList();   
    }
    
    public List<HistoryLog> getHistoryLogByItemIdAndDate(long itemid, long clock_desde, long clock_hasta ){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT h FROM HistoryLog h WHERE h.historyLogPK.itemid = :itemid AND (h.historyLogPK.clock BETWEEN :clock_desde AND :clock_hasta)");
        q.setParameter("itemid", itemid);
        q.setParameter("clock_desde", clock_desde);
        q.setParameter("clock_hasta", clock_hasta);
        return q.getResultList();
    }
    
}
