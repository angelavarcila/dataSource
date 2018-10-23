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
import model.EventRecovery;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Events;

/**
 *
 * @author ove
 */
public class EventsJpaController implements Serializable {

    public EventsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EventsJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }

////    public void create(Events events) throws PreexistingEntityException, Exception {
////        if (events.getEventRecoveryList() == null) {
////            events.setEventRecoveryList(new ArrayList<EventRecovery>());
////        }
////        if (events.getEventRecoveryList1() == null) {
////            events.setEventRecoveryList1(new ArrayList<EventRecovery>());
////        }
////        EntityManager em = null;
////        try {
////            em = getEntityManager();
////            em.getTransaction().begin();
////            EventRecovery eventRecovery = events.getEventRecovery();
////            if (eventRecovery != null) {
////                eventRecovery = em.getReference(eventRecovery.getClass(), eventRecovery.getEventid());
////                events.setEventRecovery(eventRecovery);
////            }
////            List<EventRecovery> attachedEventRecoveryList = new ArrayList<EventRecovery>();
////            for (EventRecovery eventRecoveryListEventRecoveryToAttach : events.getEventRecoveryList()) {
////                eventRecoveryListEventRecoveryToAttach = em.getReference(eventRecoveryListEventRecoveryToAttach.getClass(), eventRecoveryListEventRecoveryToAttach.getEventid());
////                attachedEventRecoveryList.add(eventRecoveryListEventRecoveryToAttach);
////            }
////            events.setEventRecoveryList(attachedEventRecoveryList);
////            List<EventRecovery> attachedEventRecoveryList1 = new ArrayList<EventRecovery>();
////            for (EventRecovery eventRecoveryList1EventRecoveryToAttach : events.getEventRecoveryList1()) {
////                eventRecoveryList1EventRecoveryToAttach = em.getReference(eventRecoveryList1EventRecoveryToAttach.getClass(), eventRecoveryList1EventRecoveryToAttach.getEventid());
////                attachedEventRecoveryList1.add(eventRecoveryList1EventRecoveryToAttach);
////            }
////            events.setEventRecoveryList1(attachedEventRecoveryList1);
////            em.persist(events);
////            if (eventRecovery != null) {
////                Events oldEventsOfEventRecovery = eventRecovery.getEvents();
////                if (oldEventsOfEventRecovery != null) {
////                    oldEventsOfEventRecovery.setEventRecovery(null);
////                    oldEventsOfEventRecovery = em.merge(oldEventsOfEventRecovery);
////                }
////                eventRecovery.setEvents(events);
////                eventRecovery = em.merge(eventRecovery);
////            }
////            for (EventRecovery eventRecoveryListEventRecovery : events.getEventRecoveryList()) {
////                Events oldREventidOfEventRecoveryListEventRecovery = eventRecoveryListEventRecovery.getREventid();
////                eventRecoveryListEventRecovery.setREventid(events);
////                eventRecoveryListEventRecovery = em.merge(eventRecoveryListEventRecovery);
////                if (oldREventidOfEventRecoveryListEventRecovery != null) {
////                    oldREventidOfEventRecoveryListEventRecovery.getEventRecoveryList().remove(eventRecoveryListEventRecovery);
////                    oldREventidOfEventRecoveryListEventRecovery = em.merge(oldREventidOfEventRecoveryListEventRecovery);
////                }
////            }
////            for (EventRecovery eventRecoveryList1EventRecovery : events.getEventRecoveryList1()) {
////                Events oldCEventidOfEventRecoveryList1EventRecovery = eventRecoveryList1EventRecovery.getCEventid();
////                eventRecoveryList1EventRecovery.setCEventid(events);
////                eventRecoveryList1EventRecovery = em.merge(eventRecoveryList1EventRecovery);
////                if (oldCEventidOfEventRecoveryList1EventRecovery != null) {
////                    oldCEventidOfEventRecoveryList1EventRecovery.getEventRecoveryList1().remove(eventRecoveryList1EventRecovery);
////                    oldCEventidOfEventRecoveryList1EventRecovery = em.merge(oldCEventidOfEventRecoveryList1EventRecovery);
////                }
////            }
////            em.getTransaction().commit();
////        } catch (Exception ex) {
////            if (findEvents(events.getEventid()) != null) {
////                throw new PreexistingEntityException("Events " + events + " already exists.", ex);
////            }
////            throw ex;
////        } finally {
////            if (em != null) {
////                em.close();
////            }
////        }
////    }
////
////    public void edit(Events events) throws IllegalOrphanException, NonexistentEntityException, Exception {
////        EntityManager em = null;
////        try {
////            em = getEntityManager();
////            em.getTransaction().begin();
////            Events persistentEvents = em.find(Events.class, events.getEventid());
////            EventRecovery eventRecoveryOld = persistentEvents.getEventRecovery();
////            EventRecovery eventRecoveryNew = events.getEventRecovery();
////            List<EventRecovery> eventRecoveryListOld = persistentEvents.getEventRecoveryList();
////            List<EventRecovery> eventRecoveryListNew = events.getEventRecoveryList();
////            List<EventRecovery> eventRecoveryList1Old = persistentEvents.getEventRecoveryList1();
////            List<EventRecovery> eventRecoveryList1New = events.getEventRecoveryList1();
////            List<String> illegalOrphanMessages = null;
////            if (eventRecoveryOld != null && !eventRecoveryOld.equals(eventRecoveryNew)) {
////                if (illegalOrphanMessages == null) {
////                    illegalOrphanMessages = new ArrayList<String>();
////                }
////                illegalOrphanMessages.add("You must retain EventRecovery " + eventRecoveryOld + " since its events field is not nullable.");
////            }
////            for (EventRecovery eventRecoveryListOldEventRecovery : eventRecoveryListOld) {
////                if (!eventRecoveryListNew.contains(eventRecoveryListOldEventRecovery)) {
////                    if (illegalOrphanMessages == null) {
////                        illegalOrphanMessages = new ArrayList<String>();
////                    }
////                    illegalOrphanMessages.add("You must retain EventRecovery " + eventRecoveryListOldEventRecovery + " since its REventid field is not nullable.");
////                }
////            }
////            if (illegalOrphanMessages != null) {
////                throw new IllegalOrphanException(illegalOrphanMessages);
////            }
////            if (eventRecoveryNew != null) {
////                eventRecoveryNew = em.getReference(eventRecoveryNew.getClass(), eventRecoveryNew.getEventid());
////                events.setEventRecovery(eventRecoveryNew);
////            }
////            List<EventRecovery> attachedEventRecoveryListNew = new ArrayList<EventRecovery>();
////            for (EventRecovery eventRecoveryListNewEventRecoveryToAttach : eventRecoveryListNew) {
////                eventRecoveryListNewEventRecoveryToAttach = em.getReference(eventRecoveryListNewEventRecoveryToAttach.getClass(), eventRecoveryListNewEventRecoveryToAttach.getEventid());
////                attachedEventRecoveryListNew.add(eventRecoveryListNewEventRecoveryToAttach);
////            }
////            eventRecoveryListNew = attachedEventRecoveryListNew;
////            events.setEventRecoveryList(eventRecoveryListNew);
////            List<EventRecovery> attachedEventRecoveryList1New = new ArrayList<EventRecovery>();
////            for (EventRecovery eventRecoveryList1NewEventRecoveryToAttach : eventRecoveryList1New) {
////                eventRecoveryList1NewEventRecoveryToAttach = em.getReference(eventRecoveryList1NewEventRecoveryToAttach.getClass(), eventRecoveryList1NewEventRecoveryToAttach.getEventid());
////                attachedEventRecoveryList1New.add(eventRecoveryList1NewEventRecoveryToAttach);
////            }
////            eventRecoveryList1New = attachedEventRecoveryList1New;
////            events.setEventRecoveryList1(eventRecoveryList1New);
////            events = em.merge(events);
////            if (eventRecoveryNew != null && !eventRecoveryNew.equals(eventRecoveryOld)) {
////                Events oldEventsOfEventRecovery = eventRecoveryNew.getEvents();
////                if (oldEventsOfEventRecovery != null) {
////                    oldEventsOfEventRecovery.setEventRecovery(null);
////                    oldEventsOfEventRecovery = em.merge(oldEventsOfEventRecovery);
////                }
////                eventRecoveryNew.setEvents(events);
////                eventRecoveryNew = em.merge(eventRecoveryNew);
////            }
////            for (EventRecovery eventRecoveryListNewEventRecovery : eventRecoveryListNew) {
////                if (!eventRecoveryListOld.contains(eventRecoveryListNewEventRecovery)) {
////                    Events oldREventidOfEventRecoveryListNewEventRecovery = eventRecoveryListNewEventRecovery.getREventid();
////                    eventRecoveryListNewEventRecovery.setREventid(events);
////                    eventRecoveryListNewEventRecovery = em.merge(eventRecoveryListNewEventRecovery);
////                    if (oldREventidOfEventRecoveryListNewEventRecovery != null && !oldREventidOfEventRecoveryListNewEventRecovery.equals(events)) {
////                        oldREventidOfEventRecoveryListNewEventRecovery.getEventRecoveryList().remove(eventRecoveryListNewEventRecovery);
////                        oldREventidOfEventRecoveryListNewEventRecovery = em.merge(oldREventidOfEventRecoveryListNewEventRecovery);
////                    }
////                }
////            }
////            for (EventRecovery eventRecoveryList1OldEventRecovery : eventRecoveryList1Old) {
////                if (!eventRecoveryList1New.contains(eventRecoveryList1OldEventRecovery)) {
////                    eventRecoveryList1OldEventRecovery.setCEventid(null);
////                    eventRecoveryList1OldEventRecovery = em.merge(eventRecoveryList1OldEventRecovery);
////                }
////            }
////            for (EventRecovery eventRecoveryList1NewEventRecovery : eventRecoveryList1New) {
////                if (!eventRecoveryList1Old.contains(eventRecoveryList1NewEventRecovery)) {
////                    Events oldCEventidOfEventRecoveryList1NewEventRecovery = eventRecoveryList1NewEventRecovery.getCEventid();
////                    eventRecoveryList1NewEventRecovery.setCEventid(events);
////                    eventRecoveryList1NewEventRecovery = em.merge(eventRecoveryList1NewEventRecovery);
////                    if (oldCEventidOfEventRecoveryList1NewEventRecovery != null && !oldCEventidOfEventRecoveryList1NewEventRecovery.equals(events)) {
////                        oldCEventidOfEventRecoveryList1NewEventRecovery.getEventRecoveryList1().remove(eventRecoveryList1NewEventRecovery);
////                        oldCEventidOfEventRecoveryList1NewEventRecovery = em.merge(oldCEventidOfEventRecoveryList1NewEventRecovery);
////                    }
////                }
////            }
////            em.getTransaction().commit();
////        } catch (Exception ex) {
////            String msg = ex.getLocalizedMessage();
////            if (msg == null || msg.length() == 0) {
////                Long id = events.getEventid();
////                if (findEvents(id) == null) {
////                    throw new NonexistentEntityException("The events with id " + id + " no longer exists.");
////                }
////            }
////            throw ex;
////        } finally {
////            if (em != null) {
////                em.close();
////            }
////        }
////    }
////
////    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
////        EntityManager em = null;
////        try {
////            em = getEntityManager();
////            em.getTransaction().begin();
////            Events events;
////            try {
////                events = em.getReference(Events.class, id);
////                events.getEventid();
////            } catch (EntityNotFoundException enfe) {
////                throw new NonexistentEntityException("The events with id " + id + " no longer exists.", enfe);
////            }
////            List<String> illegalOrphanMessages = null;
////            EventRecovery eventRecoveryOrphanCheck = events.getEventRecovery();
////            if (eventRecoveryOrphanCheck != null) {
////                if (illegalOrphanMessages == null) {
////                    illegalOrphanMessages = new ArrayList<String>();
////                }
////                illegalOrphanMessages.add("This Events (" + events + ") cannot be destroyed since the EventRecovery " + eventRecoveryOrphanCheck + " in its eventRecovery field has a non-nullable events field.");
////            }
////            List<EventRecovery> eventRecoveryListOrphanCheck = events.getEventRecoveryList();
////            for (EventRecovery eventRecoveryListOrphanCheckEventRecovery : eventRecoveryListOrphanCheck) {
////                if (illegalOrphanMessages == null) {
////                    illegalOrphanMessages = new ArrayList<String>();
////                }
////                illegalOrphanMessages.add("This Events (" + events + ") cannot be destroyed since the EventRecovery " + eventRecoveryListOrphanCheckEventRecovery + " in its eventRecoveryList field has a non-nullable REventid field.");
////            }
////            if (illegalOrphanMessages != null) {
////                throw new IllegalOrphanException(illegalOrphanMessages);
////            }
////            List<EventRecovery> eventRecoveryList1 = events.getEventRecoveryList1();
////            for (EventRecovery eventRecoveryList1EventRecovery : eventRecoveryList1) {
////                eventRecoveryList1EventRecovery.setCEventid(null);
////                eventRecoveryList1EventRecovery = em.merge(eventRecoveryList1EventRecovery);
////            }
////            em.remove(events);
////            em.getTransaction().commit();
////        } finally {
////            if (em != null) {
////                em.close();
////            }
////        }
////    }
////
////    public List<Events> findEventsEntities() {
////        return findEventsEntities(true, -1, -1);
////    }
////
////    public List<Events> findEventsEntities(int maxResults, int firstResult) {
////        return findEventsEntities(false, maxResults, firstResult);
////    }
////
////    private List<Events> findEventsEntities(boolean all, int maxResults, int firstResult) {
////        EntityManager em = getEntityManager();
////        try {
////            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
////            cq.select(cq.from(Events.class));
////            Query q = em.createQuery(cq);
////            if (!all) {
////                q.setMaxResults(maxResults);
////                q.setFirstResult(firstResult);
////            }
////            return q.getResultList();
////        } finally {
////            em.close();
////        }
////    }
////
////    public Events findEvents(Long id) {
////        EntityManager em = getEntityManager();
////        try {
////            return em.find(Events.class, id);
////        } finally {
////            em.close();
////        }
////    }
////
////    public int getEventsCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Events> rt = cq.from(Events.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
    
    public List<Events> getEventsByTriggers(long triggerId){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT e FROM Events e WHERE e.objectid = :triggerId");
        q.setParameter("triggerId", triggerId);
        return q.getResultList();
    }
    
    public List<Events> getEventsByTriggersAndDate(long triggerId, long clock_desde, long clock_hasta ){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT e FROM Events e WHERE e.objectid = :triggerId AND (e.clock BETWEEN :clock_desde AND :clock_hasta)");
        q.setParameter("triggerId", triggerId);
        q.setParameter("clock_desde", clock_desde);
        q.setParameter("clock_hasta", clock_hasta);
        return q.getResultList();
    }
}
