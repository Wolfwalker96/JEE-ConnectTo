/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cedric.pahud
 */
@Entity
@Table(name = "actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a")
    , @NamedQuery(name = "Actions.findByIdActions", query = "SELECT a FROM Actions a WHERE a.idActions = :idActions")
    , @NamedQuery(name = "Actions.findByName", query = "SELECT a FROM Actions a WHERE a.name = :name")
    , @NamedQuery(name = "Actions.findByUrl", query = "SELECT a FROM Actions a WHERE a.url = :url")})
public class Actions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActions")
    private Integer idActions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "idServicesActions", referencedColumnName = "idServices")
    @ManyToOne(optional = false)
    private Services idServicesActions;

    public Actions() {
    }

    public Actions(Integer idActions) {
        this.idActions = idActions;
    }

    public Actions(Integer idActions, String name, String url) {
        this.idActions = idActions;
        this.name = name;
        this.url = url;
    }

    public Integer getIdActions() {
        return idActions;
    }

    public void setIdActions(Integer idActions) {
        this.idActions = idActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFullName(){
        return idServicesActions.getName()+ " > "+ name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Services getIdServicesActions() {
        return idServicesActions;
    }

    public void setIdServicesActions(Services idServicesActions) {
        this.idServicesActions = idServicesActions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActions != null ? idActions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.idActions == null && other.idActions != null) || (this.idActions != null && !this.idActions.equals(other.idActions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idServicesActions.getName()+" > "+name;
    }
    
}
