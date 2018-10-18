/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "history_uint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryUint.findAll", query = "SELECT h FROM HistoryUint h"),
    @NamedQuery(name = "HistoryUint.findByItemid", query = "SELECT h FROM HistoryUint h WHERE h.historyUintPK.itemid = :itemid"),
    @NamedQuery(name = "HistoryUint.findByClock", query = "SELECT h FROM HistoryUint h WHERE h.historyUintPK.clock = :clock"),
    @NamedQuery(name = "HistoryUint.findByValue", query = "SELECT h FROM HistoryUint h WHERE h.value = :value"),
    @NamedQuery(name = "HistoryUint.findByNs", query = "SELECT h FROM HistoryUint h WHERE h.ns = :ns")})
public class HistoryUint implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoryUintPK historyUintPK;
    @Basic(optional = false)
    @Column(name = "value")
    private long value;
    @Basic(optional = false)
    @Column(name = "ns")
    private int ns;

    public HistoryUint() {
    }

    public HistoryUint(HistoryUintPK historyUintPK) {
        this.historyUintPK = historyUintPK;
    }

    public HistoryUint(HistoryUintPK historyUintPK, long value, int ns) {
        this.historyUintPK = historyUintPK;
        this.value = value;
        this.ns = ns;
    }

    public HistoryUint(long itemid, int clock) {
        this.historyUintPK = new HistoryUintPK(itemid, clock);
    }

    public HistoryUintPK getHistoryUintPK() {
        return historyUintPK;
    }

    public void setHistoryUintPK(HistoryUintPK historyUintPK) {
        this.historyUintPK = historyUintPK;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyUintPK != null ? historyUintPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryUint)) {
            return false;
        }
        HistoryUint other = (HistoryUint) object;
        if ((this.historyUintPK == null && other.historyUintPK != null) || (this.historyUintPK != null && !this.historyUintPK.equals(other.historyUintPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HistoryUint[ historyUintPK=" + historyUintPK + " ]";
    }
    
}
