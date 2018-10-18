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
@Table(name = "history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findByItemid", query = "SELECT h FROM History h WHERE h.historyPK.itemid = :itemid"),
    @NamedQuery(name = "History.findByClock", query = "SELECT h FROM History h WHERE h.historyPK.clock = :clock"),
    @NamedQuery(name = "History.findByValue", query = "SELECT h FROM History h WHERE h.value = :value"),
    @NamedQuery(name = "History.findByNs", query = "SELECT h FROM History h WHERE h.ns = :ns")})
public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoryPK historyPK;
    @Basic(optional = false)
    @Column(name = "value")
    private double value;
    @Basic(optional = false)
    @Column(name = "ns")
    private int ns;

    public History() {
    }

    public History(HistoryPK historyPK) {
        this.historyPK = historyPK;
    }

    public History(HistoryPK historyPK, double value, int ns) {
        this.historyPK = historyPK;
        this.value = value;
        this.ns = ns;
    }

    public History(long itemid, int clock) {
        this.historyPK = new HistoryPK(itemid, clock);
    }

    public HistoryPK getHistoryPK() {
        return historyPK;
    }

    public void setHistoryPK(HistoryPK historyPK) {
        this.historyPK = historyPK;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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
        hash += (historyPK != null ? historyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.historyPK == null && other.historyPK != null) || (this.historyPK != null && !this.historyPK.equals(other.historyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.History[ historyPK=" + historyPK + " ]";
    }
    
}
