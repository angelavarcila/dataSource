/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Functions;
import model.Items;
import model.Triggers;

/**
 *
 * @author ove
 */
public class FunctionsJpaController implements Serializable {

    public FunctionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FunctionsJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }

//    public void create(Functions functions) throws PreexistingEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Items itemid = functions.getItemid();
//            if (itemid != null) {
//                itemid = em.getReference(itemid.getClass(), itemid.getItemid());
//                functions.setItemid(itemid);
//            }
//            Triggers triggerid = functions.getTriggerid();
//            if (triggerid != null) {
//                triggerid = em.getReference(triggerid.getClass(), triggerid.getTriggerid());
//                functions.setTriggerid(triggerid);
//            }
//            em.persist(functions);
//            if (itemid != null) {
//                itemid.getFunctionsList().add(functions);
//                itemid = em.merge(itemid);
//            }
//            if (triggerid != null) {
//                triggerid.getFunctionsList().add(functions);
//                triggerid = em.merge(triggerid);
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findFunctions(functions.getFunctionid()) != null) {
//                throw new PreexistingEntityException("Functions " + functions + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(Functions functions) throws NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Functions persistentFunctions = em.find(Functions.class, functions.getFunctionid());
//            Items itemidOld = persistentFunctions.getItemid();
//            Items itemidNew = functions.getItemid();
//            Triggers triggeridOld = persistentFunctions.getTriggerid();
//            Triggers triggeridNew = functions.getTriggerid();
//            if (itemidNew != null) {
//                itemidNew = em.getReference(itemidNew.getClass(), itemidNew.getItemid());
//                functions.setItemid(itemidNew);
//            }
//            if (triggeridNew != null) {
//                triggeridNew = em.getReference(triggeridNew.getClass(), triggeridNew.getTriggerid());
//                functions.setTriggerid(triggeridNew);
//            }
//            functions = em.merge(functions);
//            if (itemidOld != null && !itemidOld.equals(itemidNew)) {
//                itemidOld.getFunctionsList().remove(functions);
//                itemidOld = em.merge(itemidOld);
//            }
//            if (itemidNew != null && !itemidNew.equals(itemidOld)) {
//                itemidNew.getFunctionsList().add(functions);
//                itemidNew = em.merge(itemidNew);
//            }
//            if (triggeridOld != null && !triggeridOld.equals(triggeridNew)) {
//                triggeridOld.getFunctionsList().remove(functions);
//                triggeridOld = em.merge(triggeridOld);
//            }
//            if (triggeridNew != null && !triggeridNew.equals(triggeridOld)) {
//                triggeridNew.getFunctionsList().add(functions);
//                triggeridNew = em.merge(triggeridNew);
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Long id = functions.getFunctionid();
//                if (findFunctions(id) == null) {
//                    throw new NonexistentEntityException("The functions with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void destroy(Long id) throws NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Functions functions;
//            try {
//                functions = em.getReference(Functions.class, id);
//                functions.getFunctionid();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The functions with id " + id + " no longer exists.", enfe);
//            }
//            Items itemid = functions.getItemid();
//            if (itemid != null) {
//                itemid.getFunctionsList().remove(functions);
//                itemid = em.merge(itemid);
//            }
//            Triggers triggerid = functions.getTriggerid();
//            if (triggerid != null) {
//                triggerid.getFunctionsList().remove(functions);
//                triggerid = em.merge(triggerid);
//            }
//            em.remove(functions);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public List<Functions> findFunctionsEntities() {
//        return findFunctionsEntities(true, -1, -1);
//    }
//
//    public List<Functions> findFunctionsEntities(int maxResults, int firstResult) {
//        return findFunctionsEntities(false, maxResults, firstResult);
//    }
//
//    private List<Functions> findFunctionsEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Functions.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }
//
//    public Functions findFunctions(Long id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Functions.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public int getFunctionsCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Functions> rt = cq.from(Functions.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
// 
    public List<Functions> getFunctionsByHostName(String hostName){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT f FROM Functions f WHERE f.itemid.hostid.host = :hostName");
        q.setParameter("hostName", hostName);
        return q.getResultList();
    }
}
