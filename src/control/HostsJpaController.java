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
import model.Hosts;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Interface;
import model.Items;

/**
 *
 * @author ove
 */
public class HostsJpaController implements Serializable {

    public HostsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
//
//    public void create(Hosts hosts) throws PreexistingEntityException, Exception {
//        if (hosts.getHostsList() == null) {
//            hosts.setHostsList(new ArrayList<Hosts>());
//        }
//        if (hosts.getHostsList1() == null) {
//            hosts.setHostsList1(new ArrayList<Hosts>());
//        }
//        if (hosts.getInterfaceList() == null) {
//            hosts.setInterfaceList(new ArrayList<Interface>());
//        }
//        if (hosts.getItemsList() == null) {
//            hosts.setItemsList(new ArrayList<Items>());
//        }
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Hosts proxyHostid = hosts.getProxyHostid();
//            if (proxyHostid != null) {
//                proxyHostid = em.getReference(proxyHostid.getClass(), proxyHostid.getHostid());
//                hosts.setProxyHostid(proxyHostid);
//            }
//            Hosts templateid = hosts.getTemplateid();
//            if (templateid != null) {
//                templateid = em.getReference(templateid.getClass(), templateid.getHostid());
//                hosts.setTemplateid(templateid);
//            }
//            List<Hosts> attachedHostsList = new ArrayList<Hosts>();
//            for (Hosts hostsListHostsToAttach : hosts.getHostsList()) {
//                hostsListHostsToAttach = em.getReference(hostsListHostsToAttach.getClass(), hostsListHostsToAttach.getHostid());
//                attachedHostsList.add(hostsListHostsToAttach);
//            }
//            hosts.setHostsList(attachedHostsList);
//            List<Hosts> attachedHostsList1 = new ArrayList<Hosts>();
//            for (Hosts hostsList1HostsToAttach : hosts.getHostsList1()) {
//                hostsList1HostsToAttach = em.getReference(hostsList1HostsToAttach.getClass(), hostsList1HostsToAttach.getHostid());
//                attachedHostsList1.add(hostsList1HostsToAttach);
//            }
//            hosts.setHostsList1(attachedHostsList1);
//            List<Interface> attachedInterfaceList = new ArrayList<Interface>();
//            for (Interface interfaceListInterfaceToAttach : hosts.getInterfaceList()) {
//                interfaceListInterfaceToAttach = em.getReference(interfaceListInterfaceToAttach.getClass(), interfaceListInterfaceToAttach.getInterfaceid());
//                attachedInterfaceList.add(interfaceListInterfaceToAttach);
//            }
//            hosts.setInterfaceList(attachedInterfaceList);
//            List<Items> attachedItemsList = new ArrayList<Items>();
//            for (Items itemsListItemsToAttach : hosts.getItemsList()) {
//                itemsListItemsToAttach = em.getReference(itemsListItemsToAttach.getClass(), itemsListItemsToAttach.getItemid());
//                attachedItemsList.add(itemsListItemsToAttach);
//            }
//            hosts.setItemsList(attachedItemsList);
//            em.persist(hosts);
//            if (proxyHostid != null) {
//                proxyHostid.getHostsList().add(hosts);
//                proxyHostid = em.merge(proxyHostid);
//            }
//            if (templateid != null) {
//                templateid.getHostsList().add(hosts);
//                templateid = em.merge(templateid);
//            }
//            for (Hosts hostsListHosts : hosts.getHostsList()) {
//                Hosts oldProxyHostidOfHostsListHosts = hostsListHosts.getProxyHostid();
//                hostsListHosts.setProxyHostid(hosts);
//                hostsListHosts = em.merge(hostsListHosts);
//                if (oldProxyHostidOfHostsListHosts != null) {
//                    oldProxyHostidOfHostsListHosts.getHostsList().remove(hostsListHosts);
//                    oldProxyHostidOfHostsListHosts = em.merge(oldProxyHostidOfHostsListHosts);
//                }
//            }
//            for (Hosts hostsList1Hosts : hosts.getHostsList1()) {
//                Hosts oldTemplateidOfHostsList1Hosts = hostsList1Hosts.getTemplateid();
//                hostsList1Hosts.setTemplateid(hosts);
//                hostsList1Hosts = em.merge(hostsList1Hosts);
//                if (oldTemplateidOfHostsList1Hosts != null) {
//                    oldTemplateidOfHostsList1Hosts.getHostsList1().remove(hostsList1Hosts);
//                    oldTemplateidOfHostsList1Hosts = em.merge(oldTemplateidOfHostsList1Hosts);
//                }
//            }
//            for (Interface interfaceListInterface : hosts.getInterfaceList()) {
//                Hosts oldHostidOfInterfaceListInterface = interfaceListInterface.getHostid();
//                interfaceListInterface.setHostid(hosts);
//                interfaceListInterface = em.merge(interfaceListInterface);
//                if (oldHostidOfInterfaceListInterface != null) {
//                    oldHostidOfInterfaceListInterface.getInterfaceList().remove(interfaceListInterface);
//                    oldHostidOfInterfaceListInterface = em.merge(oldHostidOfInterfaceListInterface);
//                }
//            }
//            for (Items itemsListItems : hosts.getItemsList()) {
//                Hosts oldHostidOfItemsListItems = itemsListItems.getHostid();
//                itemsListItems.setHostid(hosts);
//                itemsListItems = em.merge(itemsListItems);
//                if (oldHostidOfItemsListItems != null) {
//                    oldHostidOfItemsListItems.getItemsList().remove(itemsListItems);
//                    oldHostidOfItemsListItems = em.merge(oldHostidOfItemsListItems);
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findHosts(hosts.getHostid()) != null) {
//                throw new PreexistingEntityException("Hosts " + hosts + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(Hosts hosts) throws IllegalOrphanException, NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Hosts persistentHosts = em.find(Hosts.class, hosts.getHostid());
//            Hosts proxyHostidOld = persistentHosts.getProxyHostid();
//            Hosts proxyHostidNew = hosts.getProxyHostid();
//            Hosts templateidOld = persistentHosts.getTemplateid();
//            Hosts templateidNew = hosts.getTemplateid();
//            List<Hosts> hostsListOld = persistentHosts.getHostsList();
//            List<Hosts> hostsListNew = hosts.getHostsList();
//            List<Hosts> hostsList1Old = persistentHosts.getHostsList1();
//            List<Hosts> hostsList1New = hosts.getHostsList1();
//            List<Interface> interfaceListOld = persistentHosts.getInterfaceList();
//            List<Interface> interfaceListNew = hosts.getInterfaceList();
//            List<Items> itemsListOld = persistentHosts.getItemsList();
//            List<Items> itemsListNew = hosts.getItemsList();
//            List<String> illegalOrphanMessages = null;
//            for (Interface interfaceListOldInterface : interfaceListOld) {
//                if (!interfaceListNew.contains(interfaceListOldInterface)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Interface " + interfaceListOldInterface + " since its hostid field is not nullable.");
//                }
//            }
//            for (Items itemsListOldItems : itemsListOld) {
//                if (!itemsListNew.contains(itemsListOldItems)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Items " + itemsListOldItems + " since its hostid field is not nullable.");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            if (proxyHostidNew != null) {
//                proxyHostidNew = em.getReference(proxyHostidNew.getClass(), proxyHostidNew.getHostid());
//                hosts.setProxyHostid(proxyHostidNew);
//            }
//            if (templateidNew != null) {
//                templateidNew = em.getReference(templateidNew.getClass(), templateidNew.getHostid());
//                hosts.setTemplateid(templateidNew);
//            }
//            List<Hosts> attachedHostsListNew = new ArrayList<Hosts>();
//            for (Hosts hostsListNewHostsToAttach : hostsListNew) {
//                hostsListNewHostsToAttach = em.getReference(hostsListNewHostsToAttach.getClass(), hostsListNewHostsToAttach.getHostid());
//                attachedHostsListNew.add(hostsListNewHostsToAttach);
//            }
//            hostsListNew = attachedHostsListNew;
//            hosts.setHostsList(hostsListNew);
//            List<Hosts> attachedHostsList1New = new ArrayList<Hosts>();
//            for (Hosts hostsList1NewHostsToAttach : hostsList1New) {
//                hostsList1NewHostsToAttach = em.getReference(hostsList1NewHostsToAttach.getClass(), hostsList1NewHostsToAttach.getHostid());
//                attachedHostsList1New.add(hostsList1NewHostsToAttach);
//            }
//            hostsList1New = attachedHostsList1New;
//            hosts.setHostsList1(hostsList1New);
//            List<Interface> attachedInterfaceListNew = new ArrayList<Interface>();
//            for (Interface interfaceListNewInterfaceToAttach : interfaceListNew) {
//                interfaceListNewInterfaceToAttach = em.getReference(interfaceListNewInterfaceToAttach.getClass(), interfaceListNewInterfaceToAttach.getInterfaceid());
//                attachedInterfaceListNew.add(interfaceListNewInterfaceToAttach);
//            }
//            interfaceListNew = attachedInterfaceListNew;
//            hosts.setInterfaceList(interfaceListNew);
//            List<Items> attachedItemsListNew = new ArrayList<Items>();
//            for (Items itemsListNewItemsToAttach : itemsListNew) {
//                itemsListNewItemsToAttach = em.getReference(itemsListNewItemsToAttach.getClass(), itemsListNewItemsToAttach.getItemid());
//                attachedItemsListNew.add(itemsListNewItemsToAttach);
//            }
//            itemsListNew = attachedItemsListNew;
//            hosts.setItemsList(itemsListNew);
//            hosts = em.merge(hosts);
//            if (proxyHostidOld != null && !proxyHostidOld.equals(proxyHostidNew)) {
//                proxyHostidOld.getHostsList().remove(hosts);
//                proxyHostidOld = em.merge(proxyHostidOld);
//            }
//            if (proxyHostidNew != null && !proxyHostidNew.equals(proxyHostidOld)) {
//                proxyHostidNew.getHostsList().add(hosts);
//                proxyHostidNew = em.merge(proxyHostidNew);
//            }
//            if (templateidOld != null && !templateidOld.equals(templateidNew)) {
//                templateidOld.getHostsList().remove(hosts);
//                templateidOld = em.merge(templateidOld);
//            }
//            if (templateidNew != null && !templateidNew.equals(templateidOld)) {
//                templateidNew.getHostsList().add(hosts);
//                templateidNew = em.merge(templateidNew);
//            }
//            for (Hosts hostsListOldHosts : hostsListOld) {
//                if (!hostsListNew.contains(hostsListOldHosts)) {
//                    hostsListOldHosts.setProxyHostid(null);
//                    hostsListOldHosts = em.merge(hostsListOldHosts);
//                }
//            }
//            for (Hosts hostsListNewHosts : hostsListNew) {
//                if (!hostsListOld.contains(hostsListNewHosts)) {
//                    Hosts oldProxyHostidOfHostsListNewHosts = hostsListNewHosts.getProxyHostid();
//                    hostsListNewHosts.setProxyHostid(hosts);
//                    hostsListNewHosts = em.merge(hostsListNewHosts);
//                    if (oldProxyHostidOfHostsListNewHosts != null && !oldProxyHostidOfHostsListNewHosts.equals(hosts)) {
//                        oldProxyHostidOfHostsListNewHosts.getHostsList().remove(hostsListNewHosts);
//                        oldProxyHostidOfHostsListNewHosts = em.merge(oldProxyHostidOfHostsListNewHosts);
//                    }
//                }
//            }
//            for (Hosts hostsList1OldHosts : hostsList1Old) {
//                if (!hostsList1New.contains(hostsList1OldHosts)) {
//                    hostsList1OldHosts.setTemplateid(null);
//                    hostsList1OldHosts = em.merge(hostsList1OldHosts);
//                }
//            }
//            for (Hosts hostsList1NewHosts : hostsList1New) {
//                if (!hostsList1Old.contains(hostsList1NewHosts)) {
//                    Hosts oldTemplateidOfHostsList1NewHosts = hostsList1NewHosts.getTemplateid();
//                    hostsList1NewHosts.setTemplateid(hosts);
//                    hostsList1NewHosts = em.merge(hostsList1NewHosts);
//                    if (oldTemplateidOfHostsList1NewHosts != null && !oldTemplateidOfHostsList1NewHosts.equals(hosts)) {
//                        oldTemplateidOfHostsList1NewHosts.getHostsList1().remove(hostsList1NewHosts);
//                        oldTemplateidOfHostsList1NewHosts = em.merge(oldTemplateidOfHostsList1NewHosts);
//                    }
//                }
//            }
//            for (Interface interfaceListNewInterface : interfaceListNew) {
//                if (!interfaceListOld.contains(interfaceListNewInterface)) {
//                    Hosts oldHostidOfInterfaceListNewInterface = interfaceListNewInterface.getHostid();
//                    interfaceListNewInterface.setHostid(hosts);
//                    interfaceListNewInterface = em.merge(interfaceListNewInterface);
//                    if (oldHostidOfInterfaceListNewInterface != null && !oldHostidOfInterfaceListNewInterface.equals(hosts)) {
//                        oldHostidOfInterfaceListNewInterface.getInterfaceList().remove(interfaceListNewInterface);
//                        oldHostidOfInterfaceListNewInterface = em.merge(oldHostidOfInterfaceListNewInterface);
//                    }
//                }
//            }
//            for (Items itemsListNewItems : itemsListNew) {
//                if (!itemsListOld.contains(itemsListNewItems)) {
//                    Hosts oldHostidOfItemsListNewItems = itemsListNewItems.getHostid();
//                    itemsListNewItems.setHostid(hosts);
//                    itemsListNewItems = em.merge(itemsListNewItems);
//                    if (oldHostidOfItemsListNewItems != null && !oldHostidOfItemsListNewItems.equals(hosts)) {
//                        oldHostidOfItemsListNewItems.getItemsList().remove(itemsListNewItems);
//                        oldHostidOfItemsListNewItems = em.merge(oldHostidOfItemsListNewItems);
//                    }
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Long id = hosts.getHostid();
//                if (findHosts(id) == null) {
//                    throw new NonexistentEntityException("The hosts with id " + id + " no longer exists.");
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
//    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Hosts hosts;
//            try {
//                hosts = em.getReference(Hosts.class, id);
//                hosts.getHostid();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The hosts with id " + id + " no longer exists.", enfe);
//            }
//            List<String> illegalOrphanMessages = null;
//            List<Interface> interfaceListOrphanCheck = hosts.getInterfaceList();
//            for (Interface interfaceListOrphanCheckInterface : interfaceListOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Hosts (" + hosts + ") cannot be destroyed since the Interface " + interfaceListOrphanCheckInterface + " in its interfaceList field has a non-nullable hostid field.");
//            }
//            List<Items> itemsListOrphanCheck = hosts.getItemsList();
//            for (Items itemsListOrphanCheckItems : itemsListOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Hosts (" + hosts + ") cannot be destroyed since the Items " + itemsListOrphanCheckItems + " in its itemsList field has a non-nullable hostid field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            Hosts proxyHostid = hosts.getProxyHostid();
//            if (proxyHostid != null) {
//                proxyHostid.getHostsList().remove(hosts);
//                proxyHostid = em.merge(proxyHostid);
//            }
//            Hosts templateid = hosts.getTemplateid();
//            if (templateid != null) {
//                templateid.getHostsList().remove(hosts);
//                templateid = em.merge(templateid);
//            }
//            List<Hosts> hostsList = hosts.getHostsList();
//            for (Hosts hostsListHosts : hostsList) {
//                hostsListHosts.setProxyHostid(null);
//                hostsListHosts = em.merge(hostsListHosts);
//            }
//            List<Hosts> hostsList1 = hosts.getHostsList1();
//            for (Hosts hostsList1Hosts : hostsList1) {
//                hostsList1Hosts.setTemplateid(null);
//                hostsList1Hosts = em.merge(hostsList1Hosts);
//            }
//            em.remove(hosts);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public List<Hosts> findHostsEntities() {
//        return findHostsEntities(true, -1, -1);
//    }
//
//    public List<Hosts> findHostsEntities(int maxResults, int firstResult) {
//        return findHostsEntities(false, maxResults, firstResult);
//    }
//
//    private List<Hosts> findHostsEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Hosts.class));
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
//    public Hosts findHosts(Long id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Hosts.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public int getHostsCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Hosts> rt = cq.from(Hosts.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
//    
}
