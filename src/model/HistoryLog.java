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
@Table(name = "history_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryLog.findAll", query = "SELECT h FROM HistoryLog h"),
    @NamedQuery(name = "HistoryLog.findByItemid", query = "SELECT h FROM HistoryLog h WHERE h.historyLogPK.itemid = :itemid"),
    @NamedQuery(name = "HistoryLog.findByClock", query = "SELECT h FROM HistoryLog h WHERE h.historyLogPK.clock = :clock"),
    @NamedQuery(name = "HistoryLog.findByTimestamp", query = "SELECT h FROM HistoryLog h WHERE h.timestamp = :timestamp"),
    @NamedQuery(name = "HistoryLog.findBySource", query = "SELECT h FROM HistoryLog h WHERE h.source = :source"),
    @NamedQuery(name = "HistoryLog.findBySeverity", query = "SELECT h FROM HistoryLog h WHERE h.severity = :severity"),
    @NamedQuery(name = "HistoryLog.findByLogeventid", query = "SELECT h FROM HistoryLog h WHERE h.logeventid = :logeventid"),
    @NamedQuery(name = "HistoryLog.findByNs", query = "SELECT h FROM HistoryLog h WHERE h.ns = :ns")})
public class HistoryLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoryLogPK historyLogPK;
    @Basic(optional = false)
    @Column(name = "timestamp")
    private int timestamp;
    @Basic(optional = false)
    @Column(name = "source")
    private String source;
    @Basic(optional = false)
    @Column(name = "severity")
    private int severity;
    @Basic(optional = false)
    @Lob
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "logeventid")
    private int logeventid;
    @Basic(optional = false)
    @Column(name = "ns")
    private int ns;

    public HistoryLog() {
    }

    public HistoryLog(HistoryLogPK historyLogPK) {
        this.historyLogPK = historyLogPK;
    }

    public HistoryLog(HistoryLogPK historyLogPK, int timestamp, String source, int severity, String value, int logeventid, int ns) {
        this.historyLogPK = historyLogPK;
        this.timestamp = timestamp;
        this.source = source;
        this.severity = severity;
        this.value = value;
        this.logeventid = logeventid;
        this.ns = ns;
    }

    public HistoryLog(long itemid, int clock) {
        this.historyLogPK = new HistoryLogPK(itemid, clock);
    }

    public HistoryLogPK getHistoryLogPK() {
        return historyLogPK;
    }

    public void setHistoryLogPK(HistoryLogPK historyLogPK) {
        this.historyLogPK = historyLogPK;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLogeventid() {
        return logeventid;
    }

    public void setLogeventid(int logeventid) {
        this.logeventid = logeventid;
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
        hash += (historyLogPK != null ? historyLogPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryLog)) {
            return false;
        }
        HistoryLog other = (HistoryLog) object;
        if ((this.historyLogPK == null && other.historyLogPK != null) || (this.historyLogPK != null && !this.historyLogPK.equals(other.historyLogPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HistoryLog[ historyLogPK=" + historyLogPK + " ]";
    }
    
}
