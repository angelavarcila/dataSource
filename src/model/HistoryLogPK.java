/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ove
 */
@Embeddable
public class HistoryLogPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "itemid")
    private long itemid;
    @Basic(optional = false)
    @Column(name = "clock")
    private int clock;

    public HistoryLogPK() {
    }

    public HistoryLogPK(long itemid, int clock) {
        this.itemid = itemid;
        this.clock = clock;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemid;
        hash += (int) clock;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryLogPK)) {
            return false;
        }
        HistoryLogPK other = (HistoryLogPK) object;
        if (this.itemid != other.itemid) {
            return false;
        }
        if (this.clock != other.clock) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HistoryLogPK[ itemid=" + itemid + ", clock=" + clock + " ]";
    }
    
}
