/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dylan.santosde
 */
@Entity
@Table(name = "services")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s")
    , @NamedQuery(name = "Services.findByIdServices", query = "SELECT s FROM Services s WHERE s.idServices = :idServices")
    , @NamedQuery(name = "Services.findByName", query = "SELECT s FROM Services s WHERE s.name = :name")
    , @NamedQuery(name = "Services.findByDescription", query = "SELECT s FROM Services s WHERE s.description = :description")
    , @NamedQuery(name = "Services.findByCredential", query = "SELECT s FROM Services s WHERE s.credential = :credential")})
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServices")
    private Integer idServices;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 244)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "credential")
    private String credential;
    @OneToMany(mappedBy = "idServicesSignals")
    private Collection<Signals> signalsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicesActions")
    private Collection<Actions> actionsCollection;

    public Services() {
    }

    public Services(Integer idServices) {
        this.idServices = idServices;
    }

    public Services(Integer idServices, String name, String credential) {
        this.idServices = idServices;
        this.name = name;
        this.credential = credential;
    }

    public Integer getIdServices() {
        return idServices;
    }

    public void setIdServices(Integer idServices) {
        this.idServices = idServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @XmlTransient
    public Collection<Signals> getSignalsCollection() {
        return signalsCollection;
    }

    public void setSignalsCollection(Collection<Signals> signalsCollection) {
        this.signalsCollection = signalsCollection;
    }

    @XmlTransient
    public Collection<Actions> getActionsCollection() {
        return actionsCollection;
    }

    public void setActionsCollection(Collection<Actions> actionsCollection) {
        this.actionsCollection = actionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServices != null ? idServices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.idServices == null && other.idServices != null) || (this.idServices != null && !this.idServices.equals(other.idServices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "connectto.Services[ idServices=" + idServices + " ]";
    }
    
}
