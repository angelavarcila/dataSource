/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Hosts;
import model.Items;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Interface;

/**
 *
 * @author ove
 */
public class InterfaceJpaController implements Serializable {

    public InterfaceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
//
//    public void create(Interface interface1) throws PreexistingEntityException, Exception {
//        if (interface1.getItemsList() == null) {
//            interface1.setItemsList(new ArrayList<Items>());
//        }
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Hosts hostid = interface1.getHostid();
//            if (hostid != null) {
//                hostid = em.getReference(hostid.getClass(), hostid.getHostid());
//                interface1.setHostid(hostid);
//            }
//            List<Items> attachedItemsList = new ArrayList<Items>();
//            for (Items itemsListItemsToAttach : interface1.getItemsList()) {
//                itemsListItemsToAttach = em.getReference(itemsListItemsToAttach.getClass(), itemsListItemsToAttach.getItemid());
//                attachedItemsList.add(itemsListItemsToAttach);
//            }
//            interface1.setItemsList(attachedItemsList);
//            em.persist(interface1);
//            if (hostid != null) {
//                hostid.getInterfaceList().add(interface1);
//                hostid = em.merge(hostid);
//            }
//            for (Items itemsListItems : interface1.getItemsList()) {
//                Interface oldInterfaceidOfItemsListItems = itemsListItems.getInterfaceid();
//                itemsListItems.setInterfaceid(interface1);
//                itemsListItems = em.merge(itemsListItems);
//                if (oldInterfaceidOfItemsListItems != null) {
//                    oldInterfaceidOfItemsListItems.getItemsList().remove(itemsListItems);
//                    oldInterfaceidOfItemsListItems = em.merge(oldInterfaceidOfItemsListItems);
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findInterface(interface1.getInterfaceid()) != null) {
//                throw new PreexistingEntityException("Interface " + interface1 + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(Interface interface1) throws NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Interface persistentInterface = em.find(Interface.class, interface1.getInterfaceid());
//            Hosts hostidOld = persistentInterface.getHostid();
//            Hosts hostidNew = interface1.getHostid();
//            List<Items> itemsListOld = persistentInterface.getItemsList();
//            List<Items> itemsListNew = interface1.getItemsList();
//            if (hostidNew != null) {
//                hostidNew = em.getReference(hostidNew.getClass(), hostidNew.getHostid());
//                interface1.setHostid(hostidNew);
//            }
//            List<Items> attachedItemsListNew = new ArrayList<Items>();
//            for (Items itemsListNewItemsToAttach : itemsListNew) {
//                itemsListNewItemsToAttach = em.getReference(itemsListNewItemsToAttach.getClass(), itemsListNewItemsToAttach.getItemid());
//                attachedItemsListNew.add(itemsListNewItemsToAttach);
//            }
//            itemsListNew = attachedItemsListNew;
//            interface1.setItemsList(itemsListNew);
//            interface1 = em.merge(interface1);
//            if (hostidOld != null && !hostidOld.equals(hostidNew)) {
//                hostidOld.getInterfaceList().remove(interface1);
//                hostidOld = em.merge(hostidOld);
//            }
//            if (hostidNew != null && !hostidNew.equals(hostidOld)) {
//                hostidNew.getInterfaceList().add(interface1);
//                hostidNew = em.merge(hostidNew);
//            }
//            for (Items itemsListOldItems : itemsListOld) {
//                if (!itemsListNew.contains(itemsListOldItems)) {
//                    itemsListOldItems.setInterfaceid(null);
//                    itemsListOldItems = em.merge(itemsListOldItems);
//                }
//            }
//            for (Items itemsListNewItems : itemsListNew) {
//                if (!itemsListOld.contains(itemsListNewItems)) {
//                    Interface oldInterfaceidOfItemsListNewItems = itemsListNewItems.getInterfaceid();
//                    itemsListNewItems.setInterfaceid(interface1);
//                    itemsListNewItems = em.merge(itemsListNewItems);
//                    if (oldInterfaceidOfItemsListNewItems != null && !oldInterfaceidOfItemsListNewItems.equals(interface1)) {
//                        oldInterfaceidOfItemsListNewItems.getItemsList().remove(itemsListNewItems);
//                        oldInterfaceidOfItemsListNewItems = em.merge(oldInterfaceidOfItemsListNewItems);
//                    }
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Long id = interface1.getInterfaceid();
//                if (findInterface(id) == null) {
//                    throw new NonexistentEntityException("The interface with id " + id + " no longer exists.");
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
//            Interface interface1;
//            try {
//                interface1 = em.getReference(Interface.class, id);
//                interface1.getInterfaceid();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The interface1 with id " + id + " no longer exists.", enfe);
//            }
//            Hosts hostid = interface1.getHostid();
//            if (hostid != null) {
//                hostid.getInterfaceList().remove(interface1);
//                hostid = em.merge(hostid);
//            }
//            List<Items> itemsList = interface1.getItemsList();
//            for (Items itemsListItems : itemsList) {
//                itemsListItems.setInterfaceid(null);
//                itemsListItems = em.merge(itemsListItems);
//            }
//            em.remove(interface1);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public List<Interface> findInterfaceEntities() {
//        return findInterfaceEntities(true, -1, -1);
//    }
//
//    public List<Interface> findInterfaceEntities(int maxResults, int firstResult) {
//        return findInterfaceEntities(false, maxResults, firstResult);
//    }
//
//    private List<Interface> findInterfaceEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Interface.class));
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
//    public Interface findInterface(Long id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Interface.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public int getInterfaceCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Interface> rt = cq.from(Interface.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
    
}
