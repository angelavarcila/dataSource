/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "events")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e"),
    @NamedQuery(name = "Events.findByEventid", query = "SELECT e FROM Events e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "Events.findBySource", query = "SELECT e FROM Events e WHERE e.source = :source"),
    @NamedQuery(name = "Events.findByObject", query = "SELECT e FROM Events e WHERE e.object = :object"),
    @NamedQuery(name = "Events.findByObjectid", query = "SELECT e FROM Events e WHERE e.objectid = :objectid"),
    @NamedQuery(name = "Events.findByClock", query = "SELECT e FROM Events e WHERE e.clock = :clock"),
    @NamedQuery(name = "Events.findByValue", query = "SELECT e FROM Events e WHERE e.value = :value"),
    @NamedQuery(name = "Events.findByAcknowledged", query = "SELECT e FROM Events e WHERE e.acknowledged = :acknowledged"),
    @NamedQuery(name = "Events.findByNs", query = "SELECT e FROM Events e WHERE e.ns = :ns")})
public class Events implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "eventid")
    private Long eventid;
    @Basic(optional = false)
    @Column(name = "source")
    private int source;
    @Basic(optional = false)
    @Column(name = "object")
    private int object;
    @Basic(optional = false)
    @Column(name = "objectid")
    private long objectid;
    @Basic(optional = false)
    @Column(name = "clock")
    private int clock;
    @Basic(optional = false)
    @Column(name = "value")
    private int value;
    @Basic(optional = false)
    @Column(name = "acknowledged")
    private int acknowledged;
    @Basic(optional = false)
    @Column(name = "ns")
    private int ns;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "events", fetch = FetchType.EAGER)
    private EventRecovery eventRecovery;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rEventid", fetch = FetchType.EAGER)
//    private List<EventRecovery> eventRecoveryList;
//    @OneToMany(mappedBy = "cEventid", fetch = FetchType.EAGER)
//    private List<EventRecovery> eventRecoveryList1;

    public Events() {
    }

    public Events(Long eventid) {
        this.eventid = eventid;
    }

    public Events(Long eventid, int source, int object, long objectid, int clock, int value, int acknowledged, int ns) {
        this.eventid = eventid;
        this.source = source;
        this.object = object;
        this.objectid = objectid;
        this.clock = clock;
        this.value = value;
        this.acknowledged = acknowledged;
        this.ns = ns;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }

    public long getObjectid() {
        return objectid;
    }

    public void setObjectid(long objectid) {
        this.objectid = objectid;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(int acknowledged) {
        this.acknowledged = acknowledged;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public EventRecovery getEventRecovery() {
        return eventRecovery;
    }

    public void setEventRecovery(EventRecovery eventRecovery) {
        this.eventRecovery = eventRecovery;
    }

//    @XmlTransient
//    public List<EventRecovery> getEventRecoveryList() {
//        return eventRecoveryList;
//    }
//
//    public void setEventRecoveryList(List<EventRecovery> eventRecoveryList) {
//        this.eventRecoveryList = eventRecoveryList;
//    }
//
//    @XmlTransient
//    public List<EventRecovery> getEventRecoveryList1() {
//        return eventRecoveryList1;
//    }
//
//    public void setEventRecoveryList1(List<EventRecovery> eventRecoveryList1) {
//        this.eventRecoveryList1 = eventRecoveryList1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventid != null ? eventid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.eventid == null && other.eventid != null) || (this.eventid != null && !this.eventid.equals(other.eventid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Events[ eventid=" + eventid + " ]";
    }
    
}
