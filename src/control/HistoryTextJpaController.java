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
import model.HistoryText;
import model.HistoryTextPK;

/**
 *
 * @author ove
 */
public class HistoryTextJpaController implements Serializable {

    public HistoryTextJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public HistoryTextJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }

    public void create(HistoryText historyText) throws PreexistingEntityException, Exception {
        if (historyText.getHistoryTextPK() == null) {
            historyText.setHistoryTextPK(new HistoryTextPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historyText);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoryText(historyText.getHistoryTextPK()) != null) {
                throw new PreexistingEntityException("HistoryText " + historyText + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoryText historyText) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historyText = em.merge(historyText);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistoryTextPK id = historyText.getHistoryTextPK();
                if (findHistoryText(id) == null) {
                    throw new NonexistentEntityException("The historyText with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistoryTextPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoryText historyText;
            try {
                historyText = em.getReference(HistoryText.class, id);
                historyText.getHistoryTextPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historyText with id " + id + " no longer exists.", enfe);
            }
            em.remove(historyText);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoryText> findHistoryTextEntities() {
        return findHistoryTextEntities(true, -1, -1);
    }

    public List<HistoryText> findHistoryTextEntities(int maxResults, int firstResult) {
        return findHistoryTextEntities(false, maxResults, firstResult);
    }

    private List<HistoryText> findHistoryTextEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoryText.class));
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

    public HistoryText findHistoryText(HistoryTextPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoryText.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoryTextCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoryText> rt = cq.from(HistoryText.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HistoryText> getHistoryTextByItemId(Long itemid) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("HistoryText.findByItemid");
        q.setParameter("itemid", itemid);

        return q.getResultList();
    }
    
    public List<HistoryText> getHistoryTextByItemIdAndDate(long itemid, long clock_desde, long clock_hasta ){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT h FROM HistoryText h WHERE h.historyTextPK.itemid = :itemid AND (h.historyTextPK.clock BETWEEN :clock_desde AND :clock_hasta)");
        q.setParameter("itemid", itemid);
        q.setParameter("clock_desde", clock_desde);
        q.setParameter("clock_hasta", clock_hasta);
        return q.getResultList();
    }
    
}
