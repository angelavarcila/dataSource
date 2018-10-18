/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "interface")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interface.findAll", query = "SELECT i FROM Interface i"),
    @NamedQuery(name = "Interface.findByInterfaceid", query = "SELECT i FROM Interface i WHERE i.interfaceid = :interfaceid"),
    @NamedQuery(name = "Interface.findByMain", query = "SELECT i FROM Interface i WHERE i.main = :main"),
    @NamedQuery(name = "Interface.findByType", query = "SELECT i FROM Interface i WHERE i.type = :type"),
    @NamedQuery(name = "Interface.findByUseip", query = "SELECT i FROM Interface i WHERE i.useip = :useip"),
    @NamedQuery(name = "Interface.findByIp", query = "SELECT i FROM Interface i WHERE i.ip = :ip"),
    @NamedQuery(name = "Interface.findByDns", query = "SELECT i FROM Interface i WHERE i.dns = :dns"),
    @NamedQuery(name = "Interface.findByPort", query = "SELECT i FROM Interface i WHERE i.port = :port"),
    @NamedQuery(name = "Interface.findByBulk", query = "SELECT i FROM Interface i WHERE i.bulk = :bulk")})
public class Interface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "interfaceid")
    private Long interfaceid;
    @Basic(optional = false)
    @Column(name = "main")
    private int main;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @Column(name = "useip")
    private int useip;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "dns")
    private String dns;
    @Basic(optional = false)
    @Column(name = "port")
    private String port;
    @Basic(optional = false)
    @Column(name = "bulk")
    private int bulk;
    @JoinColumn(name = "hostid", referencedColumnName = "hostid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Hosts hostid;
    @OneToMany(mappedBy = "interfaceid", fetch = FetchType.EAGER)
    private List<Items> itemsList;

    public Interface() {
    }

    public Interface(Long interfaceid) {
        this.interfaceid = interfaceid;
    }

    public Interface(Long interfaceid, int main, int type, int useip, String ip, String dns, String port, int bulk) {
        this.interfaceid = interfaceid;
        this.main = main;
        this.type = type;
        this.useip = useip;
        this.ip = ip;
        this.dns = dns;
        this.port = port;
        this.bulk = bulk;
    }

    public Long getInterfaceid() {
        return interfaceid;
    }

    public void setInterfaceid(Long interfaceid) {
        this.interfaceid = interfaceid;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUseip() {
        return useip;
    }

    public void setUseip(int useip) {
        this.useip = useip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getBulk() {
        return bulk;
    }

    public void setBulk(int bulk) {
        this.bulk = bulk;
    }

    public Hosts getHostid() {
        return hostid;
    }

    public void setHostid(Hosts hostid) {
        this.hostid = hostid;
    }

    @XmlTransient
    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interfaceid != null ? interfaceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interface)) {
            return false;
        }
        Interface other = (Interface) object;
        if ((this.interfaceid == null && other.interfaceid != null) || (this.interfaceid != null && !this.interfaceid.equals(other.interfaceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Interface[ interfaceid=" + interfaceid + " ]";
    }
    
}
