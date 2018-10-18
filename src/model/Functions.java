/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "functions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Functions.findAll", query = "SELECT f FROM Functions f"),
    @NamedQuery(name = "Functions.findByFunctionid", query = "SELECT f FROM Functions f WHERE f.functionid = :functionid"),
    @NamedQuery(name = "Functions.findByFunction", query = "SELECT f FROM Functions f WHERE f.function = :function"),
    @NamedQuery(name = "Functions.findByParameter", query = "SELECT f FROM Functions f WHERE f.parameter = :parameter")})
public class Functions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "functionid")
    private Long functionid;
    @Basic(optional = false)
    @Column(name = "function")
    private String function;
    @Basic(optional = false)
    @Column(name = "parameter")
    private String parameter;
    @JoinColumn(name = "itemid", referencedColumnName = "itemid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Items itemid;
    @JoinColumn(name = "triggerid", referencedColumnName = "triggerid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Triggers triggerid;

    public Functions() {
    }

    public Functions(Long functionid) {
        this.functionid = functionid;
    }

    public Functions(Long functionid, String function, String parameter) {
        this.functionid = functionid;
        this.function = function;
        this.parameter = parameter;
    }

    public Long getFunctionid() {
        return functionid;
    }

    public void setFunctionid(Long functionid) {
        this.functionid = functionid;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Items getItemid() {
        return itemid;
    }

    public void setItemid(Items itemid) {
        this.itemid = itemid;
    }

    public Triggers getTriggerid() {
        return triggerid;
    }

    public void setTriggerid(Triggers triggerid) {
        this.triggerid = triggerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (functionid != null ? functionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Functions)) {
            return false;
        }
        Functions other = (Functions) object;
        if ((this.functionid == null && other.functionid != null) || (this.functionid != null && !this.functionid.equals(other.functionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Functions[ functionid=" + functionid + " ]";
    }
    
}
