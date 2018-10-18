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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "history_text")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryText.findAll", query = "SELECT h FROM HistoryText h"),
    @NamedQuery(name = "HistoryText.findByItemid", query = "SELECT h FROM HistoryText h WHERE h.historyTextPK.itemid = :itemid"),
    @NamedQuery(name = "HistoryText.findByClock", query = "SELECT h FROM HistoryText h WHERE h.historyTextPK.clock = :clock"),
    @NamedQuery(name = "HistoryText.findByNs", query = "SELECT h FROM HistoryText h WHERE h.ns = :ns")})
public class HistoryText implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoryTextPK historyTextPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "ns")
    private int ns;

    public HistoryText() {
    }

    public HistoryText(HistoryTextPK historyTextPK) {
        this.historyTextPK = historyTextPK;
    }

    public HistoryText(HistoryTextPK historyTextPK, String value, int ns) {
        this.historyTextPK = historyTextPK;
        this.value = value;
        this.ns = ns;
    }

    public HistoryText(long itemid, int clock) {
        this.historyTextPK = new HistoryTextPK(itemid, clock);
    }

    public HistoryTextPK getHistoryTextPK() {
        return historyTextPK;
    }

    public void setHistoryTextPK(HistoryTextPK historyTextPK) {
        this.historyTextPK = historyTextPK;
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
        hash += (historyTextPK != null ? historyTextPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryText)) {
            return false;
        }
        HistoryText other = (HistoryText) object;
        if ((this.historyTextPK == null && other.historyTextPK != null) || (this.historyTextPK != null && !this.historyTextPK.equals(other.historyTextPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HistoryText[ historyTextPK=" + historyTextPK + " ]";
    }
    
}
