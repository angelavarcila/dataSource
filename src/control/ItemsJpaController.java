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
import model.Items;
import model.Interface;
import model.Functions;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ove
 */
public class ItemsJpaController implements Serializable {

    public ItemsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ItemsJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultasZabbixPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Items> getItemsByHostName(String hostName){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT i FROM Items i WHERE i.hostid.host = :hostName");
        q.setParameter("hostName", hostName);
        return q.getResultList();
    }
}
