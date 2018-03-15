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
 * @author dylan.santosde
 */
@Entity
@Table(name = "conections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conections.findAll", query = "SELECT c FROM Conections c")
    , @NamedQuery(name = "Conections.findByIdConnections", query = "SELECT c FROM Conections c WHERE c.idConnections = :idConnections")})
public class Conections implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConnections")
    private Integer idConnections;
    @JoinColumn(name = "idActions", referencedColumnName = "idActions")
    @ManyToOne(optional = false)
    private Actions idActions;
    @JoinColumn(name = "idSignals", referencedColumnName = "idSignals")
    @ManyToOne(optional = false)
    private Signals idSignals;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conections")
    private Collection<UsersConnections> usersConnectionsCollection;

    public Conections() {
    }

    public Conections(Integer idConnections) {
        this.idConnections = idConnections;
    }

    public Integer getIdConnections() {
        return idConnections;
    }

    public void setIdConnections(Integer idConnections) {
        this.idConnections = idConnections;
    }

    public Actions getIdActions() {
        return idActions;
    }

    public void setIdActions(Actions idActions) {
        this.idActions = idActions;
    }

    public Signals getIdSignals() {
        return idSignals;
    }

    public void setIdSignals(Signals idSignals) {
        this.idSignals = idSignals;
    }

    @XmlTransient
    public Collection<UsersConnections> getUsersConnectionsCollection() {
        return usersConnectionsCollection;
    }

    public void setUsersConnectionsCollection(Collection<UsersConnections> usersConnectionsCollection) {
        this.usersConnectionsCollection = usersConnectionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConnections != null ? idConnections.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conections)) {
            return false;
        }
        Conections other = (Conections) object;
        if ((this.idConnections == null && other.idConnections != null) || (this.idConnections != null && !this.idConnections.equals(other.idConnections))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "connectto.Conections[ idConnections=" + idConnections + " ]";
    }
    
}
