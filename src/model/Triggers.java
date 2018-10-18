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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "triggers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Triggers.findAll", query = "SELECT t FROM Triggers t"),
    @NamedQuery(name = "Triggers.findByTriggerid", query = "SELECT t FROM Triggers t WHERE t.triggerid = :triggerid"),
    @NamedQuery(name = "Triggers.findByExpression", query = "SELECT t FROM Triggers t WHERE t.expression = :expression"),
    @NamedQuery(name = "Triggers.findByDescription", query = "SELECT t FROM Triggers t WHERE t.description = :description"),
    @NamedQuery(name = "Triggers.findByUrl", query = "SELECT t FROM Triggers t WHERE t.url = :url"),
    @NamedQuery(name = "Triggers.findByStatus", query = "SELECT t FROM Triggers t WHERE t.status = :status"),
    @NamedQuery(name = "Triggers.findByValue", query = "SELECT t FROM Triggers t WHERE t.value = :value"),
    @NamedQuery(name = "Triggers.findByPriority", query = "SELECT t FROM Triggers t WHERE t.priority = :priority"),
    @NamedQuery(name = "Triggers.findByLastchange", query = "SELECT t FROM Triggers t WHERE t.lastchange = :lastchange"),
    @NamedQuery(name = "Triggers.findByError", query = "SELECT t FROM Triggers t WHERE t.error = :error"),
    @NamedQuery(name = "Triggers.findByType", query = "SELECT t FROM Triggers t WHERE t.type = :type"),
    @NamedQuery(name = "Triggers.findByState", query = "SELECT t FROM Triggers t WHERE t.state = :state"),
    @NamedQuery(name = "Triggers.findByFlags", query = "SELECT t FROM Triggers t WHERE t.flags = :flags"),
    @NamedQuery(name = "Triggers.findByRecoveryMode", query = "SELECT t FROM Triggers t WHERE t.recoveryMode = :recoveryMode"),
    @NamedQuery(name = "Triggers.findByRecoveryExpression", query = "SELECT t FROM Triggers t WHERE t.recoveryExpression = :recoveryExpression"),
    @NamedQuery(name = "Triggers.findByCorrelationMode", query = "SELECT t FROM Triggers t WHERE t.correlationMode = :correlationMode"),
    @NamedQuery(name = "Triggers.findByCorrelationTag", query = "SELECT t FROM Triggers t WHERE t.correlationTag = :correlationTag"),
    @NamedQuery(name = "Triggers.findByManualClose", query = "SELECT t FROM Triggers t WHERE t.manualClose = :manualClose")})
public class Triggers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "triggerid")
    private Long triggerid;
    @Basic(optional = false)
    @Column(name = "expression")
    private String expression;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "value")
    private int value;
    @Basic(optional = false)
    @Column(name = "priority")
    private int priority;
    @Basic(optional = false)
    @Column(name = "lastchange")
    private int lastchange;
    @Basic(optional = false)
    @Lob
    @Column(name = "comments")
    private String comments;
    @Basic(optional = false)
    @Column(name = "error")
    private String error;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @Column(name = "state")
    private int state;
    @Basic(optional = false)
    @Column(name = "flags")
    private int flags;
    @Basic(optional = false)
    @Column(name = "recovery_mode")
    private int recoveryMode;
    @Basic(optional = false)
    @Column(name = "recovery_expression")
    private String recoveryExpression;
    @Basic(optional = false)
    @Column(name = "correlation_mode")
    private int correlationMode;
    @Basic(optional = false)
    @Column(name = "correlation_tag")
    private String correlationTag;
    @Basic(optional = false)
    @Column(name = "manual_close")
    private int manualClose;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "triggerid", fetch = FetchType.EAGER)
//    private List<Functions> functionsList;
//    @OneToMany(mappedBy = "templateid", fetch = FetchType.EAGER)
//    private List<Triggers> triggersList;
    @JoinColumn(name = "templateid", referencedColumnName = "triggerid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Triggers templateid;

    public Triggers() {
    }

    public Triggers(Long triggerid) {
        this.triggerid = triggerid;
    }

    public Triggers(Long triggerid, String expression, String description, String url, int status, int value, int priority, int lastchange, String comments, String error, int type, int state, int flags, int recoveryMode, String recoveryExpression, int correlationMode, String correlationTag, int manualClose) {
        this.triggerid = triggerid;
        this.expression = expression;
        this.description = description;
        this.url = url;
        this.status = status;
        this.value = value;
        this.priority = priority;
        this.lastchange = lastchange;
        this.comments = comments;
        this.error = error;
        this.type = type;
        this.state = state;
        this.flags = flags;
        this.recoveryMode = recoveryMode;
        this.recoveryExpression = recoveryExpression;
        this.correlationMode = correlationMode;
        this.correlationTag = correlationTag;
        this.manualClose = manualClose;
    }

    public Long getTriggerid() {
        return triggerid;
    }

    public void setTriggerid(Long triggerid) {
        this.triggerid = triggerid;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getLastchange() {
        return lastchange;
    }

    public void setLastchange(int lastchange) {
        this.lastchange = lastchange;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getRecoveryMode() {
        return recoveryMode;
    }

    public void setRecoveryMode(int recoveryMode) {
        this.recoveryMode = recoveryMode;
    }

    public String getRecoveryExpression() {
        return recoveryExpression;
    }

    public void setRecoveryExpression(String recoveryExpression) {
        this.recoveryExpression = recoveryExpression;
    }

    public int getCorrelationMode() {
        return correlationMode;
    }

    public void setCorrelationMode(int correlationMode) {
        this.correlationMode = correlationMode;
    }

    public String getCorrelationTag() {
        return correlationTag;
    }

    public void setCorrelationTag(String correlationTag) {
        this.correlationTag = correlationTag;
    }

    public int getManualClose() {
        return manualClose;
    }

    public void setManualClose(int manualClose) {
        this.manualClose = manualClose;
    }

//    @XmlTransient
//    public List<Functions> getFunctionsList() {
//        return functionsList;
//    }
//
//    public void setFunctionsList(List<Functions> functionsList) {
//        this.functionsList = functionsList;
//    }
//
//    @XmlTransient
//    public List<Triggers> getTriggersList() {
//        return triggersList;
//    }
//
//    public void setTriggersList(List<Triggers> triggersList) {
//        this.triggersList = triggersList;
//    }

    public Triggers getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Triggers templateid) {
        this.templateid = templateid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (triggerid != null ? triggerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Triggers)) {
            return false;
        }
        Triggers other = (Triggers) object;
        if ((this.triggerid == null && other.triggerid != null) || (this.triggerid != null && !this.triggerid.equals(other.triggerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Triggers[ triggerid=" + triggerid + " ]";
    }
    
}
