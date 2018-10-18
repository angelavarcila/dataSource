/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "event_recovery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventRecovery.findAll", query = "SELECT e FROM EventRecovery e"),
    @NamedQuery(name = "EventRecovery.findByEventid", query = "SELECT e FROM EventRecovery e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "EventRecovery.findByCorrelationid", query = "SELECT e FROM EventRecovery e WHERE e.correlationid = :correlationid"),
    @NamedQuery(name = "EventRecovery.findByUserid", query = "SELECT e FROM EventRecovery e WHERE e.userid = :userid")})
public class EventRecovery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "eventid")
    private Long eventid;
    @Column(name = "correlationid")
    private BigInteger correlationid;
    @Column(name = "userid")
    private BigInteger userid;
    @JoinColumn(name = "eventid", referencedColumnName = "eventid", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Events events;
    @JoinColumn(name = "r_eventid", referencedColumnName = "eventid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Events rEventid;
    @JoinColumn(name = "c_eventid", referencedColumnName = "eventid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Events cEventid;

    public EventRecovery() {
    }

    public EventRecovery(Long eventid) {
        this.eventid = eventid;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public BigInteger getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(BigInteger correlationid) {
        this.correlationid = correlationid;
    }

    public BigInteger getUserid() {
        return userid;
    }

    public void setUserid(BigInteger userid) {
        this.userid = userid;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Events getREventid() {
        return rEventid;
    }

    public void setREventid(Events rEventid) {
        this.rEventid = rEventid;
    }

    public Events getCEventid() {
        return cEventid;
    }

    public void setCEventid(Events cEventid) {
        this.cEventid = cEventid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventid != null ? eventid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventRecovery)) {
            return false;
        }
        EventRecovery other = (EventRecovery) object;
        if ((this.eventid == null && other.eventid != null) || (this.eventid != null && !this.eventid.equals(other.eventid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EventRecovery[ eventid=" + eventid + " ]";
    }
    
}
