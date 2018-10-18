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
@Table(name = "history_str")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryStr.findAll", query = "SELECT h FROM HistoryStr h"),
    @NamedQuery(name = "HistoryStr.findByItemid", query = "SELECT h FROM HistoryStr h WHERE h.historyStrPK.itemid = :itemid"),
    @NamedQuery(name = "HistoryStr.findByClock", query = "SELECT h FROM HistoryStr h WHERE h.historyStrPK.clock = :clock"),
    @NamedQuery(name = "HistoryStr.findByValue", query = "SELECT h FROM HistoryStr h WHERE h.value = :value"),
    @NamedQuery(name = "HistoryStr.findByNs", query = "SELECT h FROM HistoryStr h WHERE h.ns = :ns")})
public class HistoryStr implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoryStrPK historyStrPK;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "ns")
    private int ns;

    public HistoryStr() {
    }

    public HistoryStr(HistoryStrPK historyStrPK) {
        this.historyStrPK = historyStrPK;
    }

    public HistoryStr(HistoryStrPK historyStrPK, String value, int ns) {
        this.historyStrPK = historyStrPK;
        this.value = value;
        this.ns = ns;
    }

    public HistoryStr(long itemid, int clock) {
        this.historyStrPK = new HistoryStrPK(itemid, clock);
    }

    public HistoryStrPK getHistoryStrPK() {
        return historyStrPK;
    }

    public void setHistoryStrPK(HistoryStrPK historyStrPK) {
        this.historyStrPK = historyStrPK;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
        hash += (historyStrPK != null ? historyStrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryStr)) {
            return false;
        }
        HistoryStr other = (HistoryStr) object;
        if ((this.historyStrPK == null && other.historyStrPK != null) || (this.historyStrPK != null && !this.historyStrPK.equals(other.historyStrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HistoryStr[ historyStrPK=" + historyStrPK + " ]";
    }
    
}
