/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "hosts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hosts.findAll", query = "SELECT h FROM Hosts h"),
    @NamedQuery(name = "Hosts.findByHostid", query = "SELECT h FROM Hosts h WHERE h.hostid = :hostid"),
    @NamedQuery(name = "Hosts.findByHost", query = "SELECT h FROM Hosts h WHERE h.host = :host"),
    @NamedQuery(name = "Hosts.findByStatus", query = "SELECT h FROM Hosts h WHERE h.status = :status"),
    @NamedQuery(name = "Hosts.findByDisableUntil", query = "SELECT h FROM Hosts h WHERE h.disableUntil = :disableUntil"),
    @NamedQuery(name = "Hosts.findByError", query = "SELECT h FROM Hosts h WHERE h.error = :error"),
    @NamedQuery(name = "Hosts.findByAvailable", query = "SELECT h FROM Hosts h WHERE h.available = :available"),
    @NamedQuery(name = "Hosts.findByErrorsFrom", query = "SELECT h FROM Hosts h WHERE h.errorsFrom = :errorsFrom"),
    @NamedQuery(name = "Hosts.findByLastaccess", query = "SELECT h FROM Hosts h WHERE h.lastaccess = :lastaccess"),
    @NamedQuery(name = "Hosts.findByIpmiAuthtype", query = "SELECT h FROM Hosts h WHERE h.ipmiAuthtype = :ipmiAuthtype"),
    @NamedQuery(name = "Hosts.findByIpmiPrivilege", query = "SELECT h FROM Hosts h WHERE h.ipmiPrivilege = :ipmiPrivilege"),
    @NamedQuery(name = "Hosts.findByIpmiUsername", query = "SELECT h FROM Hosts h WHERE h.ipmiUsername = :ipmiUsername"),
    @NamedQuery(name = "Hosts.findByIpmiPassword", query = "SELECT h FROM Hosts h WHERE h.ipmiPassword = :ipmiPassword"),
    @NamedQuery(name = "Hosts.findByIpmiDisableUntil", query = "SELECT h FROM Hosts h WHERE h.ipmiDisableUntil = :ipmiDisableUntil"),
    @NamedQuery(name = "Hosts.findByIpmiAvailable", query = "SELECT h FROM Hosts h WHERE h.ipmiAvailable = :ipmiAvailable"),
    @NamedQuery(name = "Hosts.findBySnmpDisableUntil", query = "SELECT h FROM Hosts h WHERE h.snmpDisableUntil = :snmpDisableUntil"),
    @NamedQuery(name = "Hosts.findBySnmpAvailable", query = "SELECT h FROM Hosts h WHERE h.snmpAvailable = :snmpAvailable"),
    @NamedQuery(name = "Hosts.findByMaintenanceid", query = "SELECT h FROM Hosts h WHERE h.maintenanceid = :maintenanceid"),
    @NamedQuery(name = "Hosts.findByMaintenanceStatus", query = "SELECT h FROM Hosts h WHERE h.maintenanceStatus = :maintenanceStatus"),
    @NamedQuery(name = "Hosts.findByMaintenanceType", query = "SELECT h FROM Hosts h WHERE h.maintenanceType = :maintenanceType"),
    @NamedQuery(name = "Hosts.findByMaintenanceFrom", query = "SELECT h FROM Hosts h WHERE h.maintenanceFrom = :maintenanceFrom"),
    @NamedQuery(name = "Hosts.findByIpmiErrorsFrom", query = "SELECT h FROM Hosts h WHERE h.ipmiErrorsFrom = :ipmiErrorsFrom"),
    @NamedQuery(name = "Hosts.findBySnmpErrorsFrom", query = "SELECT h FROM Hosts h WHERE h.snmpErrorsFrom = :snmpErrorsFrom"),
    @NamedQuery(name = "Hosts.findByIpmiError", query = "SELECT h FROM Hosts h WHERE h.ipmiError = :ipmiError"),
    @NamedQuery(name = "Hosts.findBySnmpError", query = "SELECT h FROM Hosts h WHERE h.snmpError = :snmpError"),
    @NamedQuery(name = "Hosts.findByJmxDisableUntil", query = "SELECT h FROM Hosts h WHERE h.jmxDisableUntil = :jmxDisableUntil"),
    @NamedQuery(name = "Hosts.findByJmxAvailable", query = "SELECT h FROM Hosts h WHERE h.jmxAvailable = :jmxAvailable"),
    @NamedQuery(name = "Hosts.findByJmxErrorsFrom", query = "SELECT h FROM Hosts h WHERE h.jmxErrorsFrom = :jmxErrorsFrom"),
    @NamedQuery(name = "Hosts.findByJmxError", query = "SELECT h FROM Hosts h WHERE h.jmxError = :jmxError"),
    @NamedQuery(name = "Hosts.findByName", query = "SELECT h FROM Hosts h WHERE h.name = :name"),
    @NamedQuery(name = "Hosts.findByFlags", query = "SELECT h FROM Hosts h WHERE h.flags = :flags"),
    @NamedQuery(name = "Hosts.findByTlsConnect", query = "SELECT h FROM Hosts h WHERE h.tlsConnect = :tlsConnect"),
    @NamedQuery(name = "Hosts.findByTlsAccept", query = "SELECT h FROM Hosts h WHERE h.tlsAccept = :tlsAccept"),
    @NamedQuery(name = "Hosts.findByTlsIssuer", query = "SELECT h FROM Hosts h WHERE h.tlsIssuer = :tlsIssuer"),
    @NamedQuery(name = "Hosts.findByTlsSubject", query = "SELECT h FROM Hosts h WHERE h.tlsSubject = :tlsSubject"),
    @NamedQuery(name = "Hosts.findByTlsPskIdentity", query = "SELECT h FROM Hosts h WHERE h.tlsPskIdentity = :tlsPskIdentity"),
    @NamedQuery(name = "Hosts.findByTlsPsk", query = "SELECT h FROM Hosts h WHERE h.tlsPsk = :tlsPsk")})
public class Hosts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hostid")
    private Long hostid;
    @Basic(optional = false)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "disable_until")
    private int disableUntil;
    @Basic(optional = false)
    @Column(name = "error")
    private String error;
    @Basic(optional = false)
    @Column(name = "available")
    private int available;
    @Basic(optional = false)
    @Column(name = "errors_from")
    private int errorsFrom;
    @Basic(optional = false)
    @Column(name = "lastaccess")
    private int lastaccess;
    @Basic(optional = false)
    @Column(name = "ipmi_authtype")
    private int ipmiAuthtype;
    @Basic(optional = false)
    @Column(name = "ipmi_privilege")
    private int ipmiPrivilege;
    @Basic(optional = false)
    @Column(name = "ipmi_username")
    private String ipmiUsername;
    @Basic(optional = false)
    @Column(name = "ipmi_password")
    private String ipmiPassword;
    @Basic(optional = false)
    @Column(name = "ipmi_disable_until")
    private int ipmiDisableUntil;
    @Basic(optional = false)
    @Column(name = "ipmi_available")
    private int ipmiAvailable;
    @Basic(optional = false)
    @Column(name = "snmp_disable_until")
    private int snmpDisableUntil;
    @Basic(optional = false)
    @Column(name = "snmp_available")
    private int snmpAvailable;
    @Column(name = "maintenanceid")
    private BigInteger maintenanceid;
    @Basic(optional = false)
    @Column(name = "maintenance_status")
    private int maintenanceStatus;
    @Basic(optional = false)
    @Column(name = "maintenance_type")
    private int maintenanceType;
    @Basic(optional = false)
    @Column(name = "maintenance_from")
    private int maintenanceFrom;
    @Basic(optional = false)
    @Column(name = "ipmi_errors_from")
    private int ipmiErrorsFrom;
    @Basic(optional = false)
    @Column(name = "snmp_errors_from")
    private int snmpErrorsFrom;
    @Basic(optional = false)
    @Column(name = "ipmi_error")
    private String ipmiError;
    @Basic(optional = false)
    @Column(name = "snmp_error")
    private String snmpError;
    @Basic(optional = false)
    @Column(name = "jmx_disable_until")
    private int jmxDisableUntil;
    @Basic(optional = false)
    @Column(name = "jmx_available")
    private int jmxAvailable;
    @Basic(optional = false)
    @Column(name = "jmx_errors_from")
    private int jmxErrorsFrom;
    @Basic(optional = false)
    @Column(name = "jmx_error")
    private String jmxError;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "flags")
    private int flags;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "tls_connect")
    private int tlsConnect;
    @Basic(optional = false)
    @Column(name = "tls_accept")
    private int tlsAccept;
    @Basic(optional = false)
    @Column(name = "tls_issuer")
    private String tlsIssuer;
    @Basic(optional = false)
    @Column(name = "tls_subject")
    private String tlsSubject;
    @Basic(optional = false)
    @Column(name = "tls_psk_identity")
    private String tlsPskIdentity;
    @Basic(optional = false)
    @Column(name = "tls_psk")
    private String tlsPsk;
//    @OneToMany(mappedBy = "proxyHostid", fetch = FetchType.EAGER)
//    private List<Hosts> hostsList;
    @JoinColumn(name = "proxy_hostid", referencedColumnName = "hostid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Hosts proxyHostid;
//    @OneToMany(mappedBy = "templateid", fetch = FetchType.EAGER)
//    private List<Hosts> hostsList1;
    @JoinColumn(name = "templateid", referencedColumnName = "hostid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Hosts templateid;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hostid", fetch = FetchType.EAGER)
//    private List<Interface> interfaceList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hostid", fetch = FetchType.EAGER)
//    private List<Items> itemsList;

    public Hosts() {
    }

    public Hosts(Long hostid) {
        this.hostid = hostid;
    }

    public Hosts(Long hostid, String host, int status, int disableUntil, String error, int available, int errorsFrom, int lastaccess, int ipmiAuthtype, int ipmiPrivilege, String ipmiUsername, String ipmiPassword, int ipmiDisableUntil, int ipmiAvailable, int snmpDisableUntil, int snmpAvailable, int maintenanceStatus, int maintenanceType, int maintenanceFrom, int ipmiErrorsFrom, int snmpErrorsFrom, String ipmiError, String snmpError, int jmxDisableUntil, int jmxAvailable, int jmxErrorsFrom, String jmxError, String name, int flags, String description, int tlsConnect, int tlsAccept, String tlsIssuer, String tlsSubject, String tlsPskIdentity, String tlsPsk) {
        this.hostid = hostid;
        this.host = host;
        this.status = status;
        this.disableUntil = disableUntil;
        this.error = error;
        this.available = available;
        this.errorsFrom = errorsFrom;
        this.lastaccess = lastaccess;
        this.ipmiAuthtype = ipmiAuthtype;
        this.ipmiPrivilege = ipmiPrivilege;
        this.ipmiUsername = ipmiUsername;
        this.ipmiPassword = ipmiPassword;
        this.ipmiDisableUntil = ipmiDisableUntil;
        this.ipmiAvailable = ipmiAvailable;
        this.snmpDisableUntil = snmpDisableUntil;
        this.snmpAvailable = snmpAvailable;
        this.maintenanceStatus = maintenanceStatus;
        this.maintenanceType = maintenanceType;
        this.maintenanceFrom = maintenanceFrom;
        this.ipmiErrorsFrom = ipmiErrorsFrom;
        this.snmpErrorsFrom = snmpErrorsFrom;
        this.ipmiError = ipmiError;
        this.snmpError = snmpError;
        this.jmxDisableUntil = jmxDisableUntil;
        this.jmxAvailable = jmxAvailable;
        this.jmxErrorsFrom = jmxErrorsFrom;
        this.jmxError = jmxError;
        this.name = name;
        this.flags = flags;
        this.description = description;
        this.tlsConnect = tlsConnect;
        this.tlsAccept = tlsAccept;
        this.tlsIssuer = tlsIssuer;
        this.tlsSubject = tlsSubject;
        this.tlsPskIdentity = tlsPskIdentity;
        this.tlsPsk = tlsPsk;
    }

    public Long getHostid() {
        return hostid;
    }

    public void setHostid(Long hostid) {
        this.hostid = hostid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDisableUntil() {
        return disableUntil;
    }

    public void setDisableUntil(int disableUntil) {
        this.disableUntil = disableUntil;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getErrorsFrom() {
        return errorsFrom;
    }

    public void setErrorsFrom(int errorsFrom) {
        this.errorsFrom = errorsFrom;
    }

    public int getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(int lastaccess) {
        this.lastaccess = lastaccess;
    }

    public int getIpmiAuthtype() {
        return ipmiAuthtype;
    }

    public void setIpmiAuthtype(int ipmiAuthtype) {
        this.ipmiAuthtype = ipmiAuthtype;
    }

    public int getIpmiPrivilege() {
        return ipmiPrivilege;
    }

    public void setIpmiPrivilege(int ipmiPrivilege) {
        this.ipmiPrivilege = ipmiPrivilege;
    }

    public String getIpmiUsername() {
        return ipmiUsername;
    }

    public void setIpmiUsername(String ipmiUsername) {
        this.ipmiUsername = ipmiUsername;
    }

    public String getIpmiPassword() {
        return ipmiPassword;
    }

    public void setIpmiPassword(String ipmiPassword) {
        this.ipmiPassword = ipmiPassword;
    }

    public int getIpmiDisableUntil() {
        return ipmiDisableUntil;
    }

    public void setIpmiDisableUntil(int ipmiDisableUntil) {
        this.ipmiDisableUntil = ipmiDisableUntil;
    }

    public int getIpmiAvailable() {
        return ipmiAvailable;
    }

    public void setIpmiAvailable(int ipmiAvailable) {
        this.ipmiAvailable = ipmiAvailable;
    }

    public int getSnmpDisableUntil() {
        return snmpDisableUntil;
    }

    public void setSnmpDisableUntil(int snmpDisableUntil) {
        this.snmpDisableUntil = snmpDisableUntil;
    }

    public int getSnmpAvailable() {
        return snmpAvailable;
    }

    public void setSnmpAvailable(int snmpAvailable) {
        this.snmpAvailable = snmpAvailable;
    }

    public BigInteger getMaintenanceid() {
        return maintenanceid;
    }

    public void setMaintenanceid(BigInteger maintenanceid) {
        this.maintenanceid = maintenanceid;
    }

    public int getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(int maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public int getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(int maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public int getMaintenanceFrom() {
        return maintenanceFrom;
    }

    public void setMaintenanceFrom(int maintenanceFrom) {
        this.maintenanceFrom = maintenanceFrom;
    }

    public int getIpmiErrorsFrom() {
        return ipmiErrorsFrom;
    }

    public void setIpmiErrorsFrom(int ipmiErrorsFrom) {
        this.ipmiErrorsFrom = ipmiErrorsFrom;
    }

    public int getSnmpErrorsFrom() {
        return snmpErrorsFrom;
    }

    public void setSnmpErrorsFrom(int snmpErrorsFrom) {
        this.snmpErrorsFrom = snmpErrorsFrom;
    }

    public String getIpmiError() {
        return ipmiError;
    }

    public void setIpmiError(String ipmiError) {
        this.ipmiError = ipmiError;
    }

    public String getSnmpError() {
        return snmpError;
    }

    public void setSnmpError(String snmpError) {
        this.snmpError = snmpError;
    }

    public int getJmxDisableUntil() {
        return jmxDisableUntil;
    }

    public void setJmxDisableUntil(int jmxDisableUntil) {
        this.jmxDisableUntil = jmxDisableUntil;
    }

    public int getJmxAvailable() {
        return jmxAvailable;
    }

    public void setJmxAvailable(int jmxAvailable) {
        this.jmxAvailable = jmxAvailable;
    }

    public int getJmxErrorsFrom() {
        return jmxErrorsFrom;
    }

    public void setJmxErrorsFrom(int jmxErrorsFrom) {
        this.jmxErrorsFrom = jmxErrorsFrom;
    }

    public String getJmxError() {
        return jmxError;
    }

    public void setJmxError(String jmxError) {
        this.jmxError = jmxError;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTlsConnect() {
        return tlsConnect;
    }

    public void setTlsConnect(int tlsConnect) {
        this.tlsConnect = tlsConnect;
    }

    public int getTlsAccept() {
        return tlsAccept;
    }

    public void setTlsAccept(int tlsAccept) {
        this.tlsAccept = tlsAccept;
    }

    public String getTlsIssuer() {
        return tlsIssuer;
    }

    public void setTlsIssuer(String tlsIssuer) {
        this.tlsIssuer = tlsIssuer;
    }

    public String getTlsSubject() {
        return tlsSubject;
    }

    public void setTlsSubject(String tlsSubject) {
        this.tlsSubject = tlsSubject;
    }

    public String getTlsPskIdentity() {
        return tlsPskIdentity;
    }

    public void setTlsPskIdentity(String tlsPskIdentity) {
        this.tlsPskIdentity = tlsPskIdentity;
    }

    public String getTlsPsk() {
        return tlsPsk;
    }

    public void setTlsPsk(String tlsPsk) {
        this.tlsPsk = tlsPsk;
    }

//    @XmlTransient
//    public List<Hosts> getHostsList() {
//        return hostsList;
//    }
//
//    public void setHostsList(List<Hosts> hostsList) {
//        this.hostsList = hostsList;
//    }

    public Hosts getProxyHostid() {
        return proxyHostid;
    }

    public void setProxyHostid(Hosts proxyHostid) {
        this.proxyHostid = proxyHostid;
    }

//    @XmlTransient
//    public List<Hosts> getHostsList1() {
//        return hostsList1;
//    }
//
//    public void setHostsList1(List<Hosts> hostsList1) {
//        this.hostsList1 = hostsList1;
//    }

    public Hosts getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Hosts templateid) {
        this.templateid = templateid;
    }

//    @XmlTransient
//    public List<Interface> getInterfaceList() {
//        return interfaceList;
//    }
//
//    public void setInterfaceList(List<Interface> interfaceList) {
//        this.interfaceList = interfaceList;
//    }
//
//    @XmlTransient
//    public List<Items> getItemsList() {
//        return itemsList;
//    }
//
//    public void setItemsList(List<Items> itemsList) {
//        this.itemsList = itemsList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hostid != null ? hostid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hosts)) {
            return false;
        }
        Hosts other = (Hosts) object;
        if ((this.hostid == null && other.hostid != null) || (this.hostid != null && !this.hostid.equals(other.hostid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Hosts[ hostid=" + hostid + " ]";
    }
    
}
