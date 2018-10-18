/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.IllegalOrphanException;
import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Events;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.EventRecovery;

/**
 *
 * @author ove
 */
public class EventRecoveryJpaController implements Serializable {

    public EventRecoveryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventRecovery eventRecovery) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Events eventsOrphanCheck = eventRecovery.getEvents();
        if (eventsOrphanCheck != null) {
            EventRecovery oldEventRecoveryOfEvents = eventsOrphanCheck.getEventRecovery();
            if (oldEventRecoveryOfEvents != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Events " + eventsOrphanCheck + " already has an item of type EventRecovery whose events column cannot be null. Please make another selection for the events field.");
            }
        }
        Events REventidOrphanCheck = eventRecovery.getREventid();
        if (REventidOrphanCheck != null) {
            EventRecovery oldEventRecoveryOfREventid = REventidOrphanCheck.getEventRecovery();
            if (oldEventRecoveryOfREventid != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Events " + REventidOrphanCheck + " already has an item of type EventRecovery whose REventid column cannot be null. Please make another selection for the REventid field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Events events = eventRecovery.getEvents();
            if (events != null) {
                events = em.getReference(events.getClass(), events.getEventid());
                eventRecovery.setEvents(events);
            }
            Events REventid = eventRecovery.getREventid();
            if (REventid != null) {
                REventid = em.getReference(REventid.getClass(), REventid.getEventid());
                eventRecovery.setREventid(REventid);
            }
            Events CEventid = eventRecovery.getCEventid();
            if (CEventid != null) {
                CEventid = em.getReference(CEventid.getClass(), CEventid.getEventid());
                eventRecovery.setCEventid(CEventid);
            }
            em.persist(eventRecovery);
            if (events != null) {
                events.setEventRecovery(eventRecovery);
                events = em.merge(events);
            }
            if (REventid != null) {
                REventid.setEventRecovery(eventRecovery);
                REventid = em.merge(REventid);
            }
            if (CEventid != null) {
                EventRecovery oldEventRecoveryOfCEventid = CEventid.getEventRecovery();
                if (oldEventRecoveryOfCEventid != null) {
                    oldEventRecoveryOfCEventid.setCEventid(null);
                    oldEventRecoveryOfCEventid = em.merge(oldEventRecoveryOfCEventid);
                }
                CEventid.setEventRecovery(eventRecovery);
                CEventid = em.merge(CEventid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEventRecovery(eventRecovery.getEventid()) != null) {
                throw new PreexistingEntityException("EventRecovery " + eventRecovery + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventRecovery eventRecovery) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventRecovery persistentEventRecovery = em.find(EventRecovery.class, eventRecovery.getEventid());
            Events eventsOld = persistentEventRecovery.getEvents();
            Events eventsNew = eventRecovery.getEvents();
            Events REventidOld = persistentEventRecovery.getREventid();
            Events REventidNew = eventRecovery.getREventid();
            Events CEventidOld = persistentEventRecovery.getCEventid();
            Events CEventidNew = eventRecovery.getCEventid();
            List<String> illegalOrphanMessages = null;
            if (eventsNew != null && !eventsNew.equals(eventsOld)) {
                EventRecovery oldEventRecoveryOfEvents = eventsNew.getEventRecovery();
                if (oldEventRecoveryOfEvents != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Events " + eventsNew + " already has an item of type EventRecovery whose events column cannot be null. Please make another selection for the events field.");
                }
            }
            if (REventidNew != null && !REventidNew.equals(REventidOld)) {
                EventRecovery oldEventRecoveryOfREventid = REventidNew.getEventRecovery();
                if (oldEventRecoveryOfREventid != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Events " + REventidNew + " already has an item of type EventRecovery whose REventid column cannot be null. Please make another selection for the REventid field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (eventsNew != null) {
                eventsNew = em.getReference(eventsNew.getClass(), eventsNew.getEventid());
                eventRecovery.setEvents(eventsNew);
            }
            if (REventidNew != null) {
                REventidNew = em.getReference(REventidNew.getClass(), REventidNew.getEventid());
                eventRecovery.setREventid(REventidNew);
            }
            if (CEventidNew != null) {
                CEventidNew = em.getReference(CEventidNew.getClass(), CEventidNew.getEventid());
                eventRecovery.setCEventid(CEventidNew);
            }
            eventRecovery = em.merge(eventRecovery);
            if (eventsOld != null && !eventsOld.equals(eventsNew)) {
                eventsOld.setEventRecovery(null);
                eventsOld = em.merge(eventsOld);
            }
            if (eventsNew != null && !eventsNew.equals(eventsOld)) {
                eventsNew.setEventRecovery(eventRecovery);
                eventsNew = em.merge(eventsNew);
            }
            if (REventidOld != null && !REventidOld.equals(REventidNew)) {
                REventidOld.setEventRecovery(null);
                REventidOld = em.merge(REventidOld);
            }
            if (REventidNew != null && !REventidNew.equals(REventidOld)) {
                REventidNew.setEventRecovery(eventRecovery);
                REventidNew = em.merge(REventidNew);
            }
            if (CEventidOld != null && !CEventidOld.equals(CEventidNew)) {
                CEventidOld.setEventRecovery(null);
                CEventidOld = em.merge(CEventidOld);
            }
            if (CEventidNew != null && !CEventidNew.equals(CEventidOld)) {
                EventRecovery oldEventRecoveryOfCEventid = CEventidNew.getEventRecovery();
                if (oldEventRecoveryOfCEventid != null) {
                    oldEventRecoveryOfCEventid.setCEventid(null);
                    oldEventRecoveryOfCEventid = em.merge(oldEventRecoveryOfCEventid);
                }
                CEventidNew.setEventRecovery(eventRecovery);
                CEventidNew = em.merge(CEventidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eventRecovery.getEventid();
                if (findEventRecovery(id) == null) {
                    throw new NonexistentEntityException("The eventRecovery with id " + id + " no longer exists.");
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
            EventRecovery eventRecovery;
            try {
                eventRecovery = em.getReference(EventRecovery.class, id);
                eventRecovery.getEventid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventRecovery with id " + id + " no longer exists.", enfe);
            }
            Events events = eventRecovery.getEvents();
            if (events != null) {
                events.setEventRecovery(null);
                events = em.merge(events);
            }
            Events REventid = eventRecovery.getREventid();
            if (REventid != null) {
                REventid.setEventRecovery(null);
                REventid = em.merge(REventid);
            }
            Events CEventid = eventRecovery.getCEventid();
            if (CEventid != null) {
                CEventid.setEventRecovery(null);
                CEventid = em.merge(CEventid);
            }
            em.remove(eventRecovery);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventRecovery> findEventRecoveryEntities() {
        return findEventRecoveryEntities(true, -1, -1);
    }

    public List<EventRecovery> findEventRecoveryEntities(int maxResults, int firstResult) {
        return findEventRecoveryEntities(false, maxResults, firstResult);
    }

    private List<EventRecovery> findEventRecoveryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventRecovery.class));
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

    public EventRecovery findEventRecovery(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventRecovery.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventRecoveryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventRecovery> rt = cq.from(EventRecovery.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
