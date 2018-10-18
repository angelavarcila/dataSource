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
@Table(name = "items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findByItemid", query = "SELECT i FROM Items i WHERE i.itemid = :itemid"),
    @NamedQuery(name = "Items.findByType", query = "SELECT i FROM Items i WHERE i.type = :type"),
    @NamedQuery(name = "Items.findBySnmpCommunity", query = "SELECT i FROM Items i WHERE i.snmpCommunity = :snmpCommunity"),
    @NamedQuery(name = "Items.findBySnmpOid", query = "SELECT i FROM Items i WHERE i.snmpOid = :snmpOid"),
    @NamedQuery(name = "Items.findByName", query = "SELECT i FROM Items i WHERE i.name = :name"),
    @NamedQuery(name = "Items.findByKey", query = "SELECT i FROM Items i WHERE i.key = :key"),
    @NamedQuery(name = "Items.findByDelay", query = "SELECT i FROM Items i WHERE i.delay = :delay"),
    @NamedQuery(name = "Items.findByHistory", query = "SELECT i FROM Items i WHERE i.history = :history"),
    @NamedQuery(name = "Items.findByTrends", query = "SELECT i FROM Items i WHERE i.trends = :trends"),
    @NamedQuery(name = "Items.findByStatus", query = "SELECT i FROM Items i WHERE i.status = :status"),
    @NamedQuery(name = "Items.findByValueType", query = "SELECT i FROM Items i WHERE i.valueType = :valueType"),
    @NamedQuery(name = "Items.findByTrapperHosts", query = "SELECT i FROM Items i WHERE i.trapperHosts = :trapperHosts"),
    @NamedQuery(name = "Items.findByUnits", query = "SELECT i FROM Items i WHERE i.units = :units"),
    @NamedQuery(name = "Items.findBySnmpv3Securityname", query = "SELECT i FROM Items i WHERE i.snmpv3Securityname = :snmpv3Securityname"),
    @NamedQuery(name = "Items.findBySnmpv3Securitylevel", query = "SELECT i FROM Items i WHERE i.snmpv3Securitylevel = :snmpv3Securitylevel"),
    @NamedQuery(name = "Items.findBySnmpv3Authpassphrase", query = "SELECT i FROM Items i WHERE i.snmpv3Authpassphrase = :snmpv3Authpassphrase"),
    @NamedQuery(name = "Items.findBySnmpv3Privpassphrase", query = "SELECT i FROM Items i WHERE i.snmpv3Privpassphrase = :snmpv3Privpassphrase"),
    @NamedQuery(name = "Items.findByFormula", query = "SELECT i FROM Items i WHERE i.formula = :formula"),
    @NamedQuery(name = "Items.findByError", query = "SELECT i FROM Items i WHERE i.error = :error"),
    @NamedQuery(name = "Items.findByLastlogsize", query = "SELECT i FROM Items i WHERE i.lastlogsize = :lastlogsize"),
    @NamedQuery(name = "Items.findByLogtimefmt", query = "SELECT i FROM Items i WHERE i.logtimefmt = :logtimefmt"),
    @NamedQuery(name = "Items.findByValuemapid", query = "SELECT i FROM Items i WHERE i.valuemapid = :valuemapid"),
    @NamedQuery(name = "Items.findByIpmiSensor", query = "SELECT i FROM Items i WHERE i.ipmiSensor = :ipmiSensor"),
    @NamedQuery(name = "Items.findByAuthtype", query = "SELECT i FROM Items i WHERE i.authtype = :authtype"),
    @NamedQuery(name = "Items.findByUsername", query = "SELECT i FROM Items i WHERE i.username = :username"),
    @NamedQuery(name = "Items.findByPassword", query = "SELECT i FROM Items i WHERE i.password = :password"),
    @NamedQuery(name = "Items.findByPublickey", query = "SELECT i FROM Items i WHERE i.publickey = :publickey"),
    @NamedQuery(name = "Items.findByPrivatekey", query = "SELECT i FROM Items i WHERE i.privatekey = :privatekey"),
    @NamedQuery(name = "Items.findByMtime", query = "SELECT i FROM Items i WHERE i.mtime = :mtime"),
    @NamedQuery(name = "Items.findByFlags", query = "SELECT i FROM Items i WHERE i.flags = :flags"),
    @NamedQuery(name = "Items.findByPort", query = "SELECT i FROM Items i WHERE i.port = :port"),
    @NamedQuery(name = "Items.findByInventoryLink", query = "SELECT i FROM Items i WHERE i.inventoryLink = :inventoryLink"),
    @NamedQuery(name = "Items.findByLifetime", query = "SELECT i FROM Items i WHERE i.lifetime = :lifetime"),
    @NamedQuery(name = "Items.findBySnmpv3Authprotocol", query = "SELECT i FROM Items i WHERE i.snmpv3Authprotocol = :snmpv3Authprotocol"),
    @NamedQuery(name = "Items.findBySnmpv3Privprotocol", query = "SELECT i FROM Items i WHERE i.snmpv3Privprotocol = :snmpv3Privprotocol"),
    @NamedQuery(name = "Items.findByState", query = "SELECT i FROM Items i WHERE i.state = :state"),
    @NamedQuery(name = "Items.findBySnmpv3Contextname", query = "SELECT i FROM Items i WHERE i.snmpv3Contextname = :snmpv3Contextname"),
    @NamedQuery(name = "Items.findByEvaltype", query = "SELECT i FROM Items i WHERE i.evaltype = :evaltype"),
    @NamedQuery(name = "Items.findByJmxEndpoint", query = "SELECT i FROM Items i WHERE i.jmxEndpoint = :jmxEndpoint")})
public class Items implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "itemid")
    private Long itemid;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @Column(name = "snmp_community")
    private String snmpCommunity;
    @Basic(optional = false)
    @Column(name = "snmp_oid")
    private String snmpOid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "key_")
    private String key;
    @Basic(optional = false)
    @Column(name = "delay")
    private String delay;
    @Basic(optional = false)
    @Column(name = "history")
    private String history;
    @Basic(optional = false)
    @Column(name = "trends")
    private String trends;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "value_type")
    private int valueType;
    @Basic(optional = false)
    @Column(name = "trapper_hosts")
    private String trapperHosts;
    @Basic(optional = false)
    @Column(name = "units")
    private String units;
    @Basic(optional = false)
    @Column(name = "snmpv3_securityname")
    private String snmpv3Securityname;
    @Basic(optional = false)
    @Column(name = "snmpv3_securitylevel")
    private int snmpv3Securitylevel;
    @Basic(optional = false)
    @Column(name = "snmpv3_authpassphrase")
    private String snmpv3Authpassphrase;
    @Basic(optional = false)
    @Column(name = "snmpv3_privpassphrase")
    private String snmpv3Privpassphrase;
    @Basic(optional = false)
    @Column(name = "formula")
    private String formula;
    @Basic(optional = false)
    @Column(name = "error")
    private String error;
    @Basic(optional = false)
    @Column(name = "lastlogsize")
    private long lastlogsize;
    @Basic(optional = false)
    @Column(name = "logtimefmt")
    private String logtimefmt;
    @Column(name = "valuemapid")
    private BigInteger valuemapid;
    @Basic(optional = false)
    @Lob
    @Column(name = "params")
    private String params;
    @Basic(optional = false)
    @Column(name = "ipmi_sensor")
    private String ipmiSensor;
    @Basic(optional = false)
    @Column(name = "authtype")
    private int authtype;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "publickey")
    private String publickey;
    @Basic(optional = false)
    @Column(name = "privatekey")
    private String privatekey;
    @Basic(optional = false)
    @Column(name = "mtime")
    private int mtime;
    @Basic(optional = false)
    @Column(name = "flags")
    private int flags;
    @Basic(optional = false)
    @Column(name = "port")
    private String port;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "inventory_link")
    private int inventoryLink;
    @Basic(optional = false)
    @Column(name = "lifetime")
    private String lifetime;
    @Basic(optional = false)
    @Column(name = "snmpv3_authprotocol")
    private int snmpv3Authprotocol;
    @Basic(optional = false)
    @Column(name = "snmpv3_privprotocol")
    private int snmpv3Privprotocol;
    @Basic(optional = false)
    @Column(name = "state")
    private int state;
    @Basic(optional = false)
    @Column(name = "snmpv3_contextname")
    private String snmpv3Contextname;
    @Basic(optional = false)
    @Column(name = "evaltype")
    private int evaltype;
    @Basic(optional = false)
    @Column(name = "jmx_endpoint")
    private String jmxEndpoint;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemid", fetch = FetchType.EAGER)
//    private List<Functions> functionsList;
    @JoinColumn(name = "hostid", referencedColumnName = "hostid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Hosts hostid;
//    @OneToMany(mappedBy = "templateid", fetch = FetchType.EAGER)
//    private List<Items> itemsList;
    @JoinColumn(name = "templateid", referencedColumnName = "itemid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Items templateid;
    @JoinColumn(name = "interfaceid", referencedColumnName = "interfaceid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Interface interfaceid;
//    @OneToMany(mappedBy = "masterItemid", fetch = FetchType.EAGER)
//    private List<Items> itemsList1;
    @JoinColumn(name = "master_itemid", referencedColumnName = "itemid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Items masterItemid;

    public Items() {
    }

    public Items(Long itemid) {
        this.itemid = itemid;
    }

    public Items(Long itemid, int type, String snmpCommunity, String snmpOid, String name, String key, String delay, String history, String trends, int status, int valueType, String trapperHosts, String units, String snmpv3Securityname, int snmpv3Securitylevel, String snmpv3Authpassphrase, String snmpv3Privpassphrase, String formula, String error, long lastlogsize, String logtimefmt, String params, String ipmiSensor, int authtype, String username, String password, String publickey, String privatekey, int mtime, int flags, String port, String description, int inventoryLink, String lifetime, int snmpv3Authprotocol, int snmpv3Privprotocol, int state, String snmpv3Contextname, int evaltype, String jmxEndpoint) {
        this.itemid = itemid;
        this.type = type;
        this.snmpCommunity = snmpCommunity;
        this.snmpOid = snmpOid;
        this.name = name;
        this.key = key;
        this.delay = delay;
        this.history = history;
        this.trends = trends;
        this.status = status;
        this.valueType = valueType;
        this.trapperHosts = trapperHosts;
        this.units = units;
        this.snmpv3Securityname = snmpv3Securityname;
        this.snmpv3Securitylevel = snmpv3Securitylevel;
        this.snmpv3Authpassphrase = snmpv3Authpassphrase;
        this.snmpv3Privpassphrase = snmpv3Privpassphrase;
        this.formula = formula;
        this.error = error;
        this.lastlogsize = lastlogsize;
        this.logtimefmt = logtimefmt;
        this.params = params;
        this.ipmiSensor = ipmiSensor;
        this.authtype = authtype;
        this.username = username;
        this.password = password;
        this.publickey = publickey;
        this.privatekey = privatekey;
        this.mtime = mtime;
        this.flags = flags;
        this.port = port;
        this.description = description;
        this.inventoryLink = inventoryLink;
        this.lifetime = lifetime;
        this.snmpv3Authprotocol = snmpv3Authprotocol;
        this.snmpv3Privprotocol = snmpv3Privprotocol;
        this.state = state;
        this.snmpv3Contextname = snmpv3Contextname;
        this.evaltype = evaltype;
        this.jmxEndpoint = jmxEndpoint;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public void setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public void setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTrends() {
        return trends;
    }

    public void setTrends(String trends) {
        this.trends = trends;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public String getTrapperHosts() {
        return trapperHosts;
    }

    public void setTrapperHosts(String trapperHosts) {
        this.trapperHosts = trapperHosts;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSnmpv3Securityname() {
        return snmpv3Securityname;
    }

    public void setSnmpv3Securityname(String snmpv3Securityname) {
        this.snmpv3Securityname = snmpv3Securityname;
    }

    public int getSnmpv3Securitylevel() {
        return snmpv3Securitylevel;
    }

    public void setSnmpv3Securitylevel(int snmpv3Securitylevel) {
        this.snmpv3Securitylevel = snmpv3Securitylevel;
    }

    public String getSnmpv3Authpassphrase() {
        return snmpv3Authpassphrase;
    }

    public void setSnmpv3Authpassphrase(String snmpv3Authpassphrase) {
        this.snmpv3Authpassphrase = snmpv3Authpassphrase;
    }

    public String getSnmpv3Privpassphrase() {
        return snmpv3Privpassphrase;
    }

    public void setSnmpv3Privpassphrase(String snmpv3Privpassphrase) {
        this.snmpv3Privpassphrase = snmpv3Privpassphrase;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getLastlogsize() {
        return lastlogsize;
    }

    public void setLastlogsize(long lastlogsize) {
        this.lastlogsize = lastlogsize;
    }

    public String getLogtimefmt() {
        return logtimefmt;
    }

    public void setLogtimefmt(String logtimefmt) {
        this.logtimefmt = logtimefmt;
    }

    public BigInteger getValuemapid() {
        return valuemapid;
    }

    public void setValuemapid(BigInteger valuemapid) {
        this.valuemapid = valuemapid;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIpmiSensor() {
        return ipmiSensor;
    }

    public void setIpmiSensor(String ipmiSensor) {
        this.ipmiSensor = ipmiSensor;
    }

    public int getAuthtype() {
        return authtype;
    }

    public void setAuthtype(int authtype) {
        this.authtype = authtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public int getMtime() {
        return mtime;
    }

    public void setMtime(int mtime) {
        this.mtime = mtime;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInventoryLink() {
        return inventoryLink;
    }

    public void setInventoryLink(int inventoryLink) {
        this.inventoryLink = inventoryLink;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public int getSnmpv3Authprotocol() {
        return snmpv3Authprotocol;
    }

    public void setSnmpv3Authprotocol(int snmpv3Authprotocol) {
        this.snmpv3Authprotocol = snmpv3Authprotocol;
    }

    public int getSnmpv3Privprotocol() {
        return snmpv3Privprotocol;
    }

    public void setSnmpv3Privprotocol(int snmpv3Privprotocol) {
        this.snmpv3Privprotocol = snmpv3Privprotocol;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSnmpv3Contextname() {
        return snmpv3Contextname;
    }

    public void setSnmpv3Contextname(String snmpv3Contextname) {
        this.snmpv3Contextname = snmpv3Contextname;
    }

    public int getEvaltype() {
        return evaltype;
    }

    public void setEvaltype(int evaltype) {
        this.evaltype = evaltype;
    }

    public String getJmxEndpoint() {
        return jmxEndpoint;
    }

    public void setJmxEndpoint(String jmxEndpoint) {
        this.jmxEndpoint = jmxEndpoint;
    }

//    @XmlTransient
//    public List<Functions> getFunctionsList() {
//        return functionsList;
//    }
//
//    public void setFunctionsList(List<Functions> functionsList) {
//        this.functionsList = functionsList;
//    }

    public Hosts getHostid() {
        return hostid;
    }

    public void setHostid(Hosts hostid) {
        this.hostid = hostid;
    }

//    @XmlTransient
//    public List<Items> getItemsList() {
//        return itemsList;
//    }
//
//    public void setItemsList(List<Items> itemsList) {
//        this.itemsList = itemsList;
//    }

    public Items getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Items templateid) {
        this.templateid = templateid;
    }

    public Interface getInterfaceid() {
        return interfaceid;
    }

    public void setInterfaceid(Interface interfaceid) {
        this.interfaceid = interfaceid;
    }

//    @XmlTransient
//    public List<Items> getItemsList1() {
//        return itemsList1;
//    }
//
//    public void setItemsList1(List<Items> itemsList1) {
//        this.itemsList1 = itemsList1;
//    }

    public Items getMasterItemid() {
        return masterItemid;
    }

    public void setMasterItemid(Items masterItemid) {
        this.masterItemid = masterItemid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Items[ itemid=" + itemid + " ]";
    }
    
}
