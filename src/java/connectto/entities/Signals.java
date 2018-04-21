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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dylan.santosde
 */
@Entity
@Table(name = "signals")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signals.findAll", query = "SELECT s.name FROM Signals s")
    , @NamedQuery(name = "Signals.SignalAndService", query="SELECT CONCAT(s.name, s.idServicesSignals) FROM Signals s")
    , @NamedQuery(name = "Signals.findByIdSignals", query = "SELECT s FROM Signals s WHERE s.idSignals = :idSignals")
    , @NamedQuery(name = "Signals.findByName", query = "SELECT s FROM Signals s WHERE s.name = :name")})
public class Signals implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSignals")
    private Integer idSignals;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSignals")
    private Collection<Conections> conectionsCollection;
    @JoinColumn(name = "idServicesSignals", referencedColumnName = "idServices")
    @ManyToOne
    private Services idServicesSignals;

    public Signals() {
    }

    public Signals(Integer idSignals) {
        this.idSignals = idSignals;
    }

    public Integer getIdSignals() {
        return idSignals;
    }

    public void setIdSignals(Integer idSignals) {
        this.idSignals = idSignals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFullName(){
        return idServicesSignals.getName()+ " > "+ name;
    }

    @XmlTransient
    public Collection<Conections> getConectionsCollection() {
        return conectionsCollection;
    }

    public void setConectionsCollection(Collection<Conections> conectionsCollection) {
        this.conectionsCollection = conectionsCollection;
    }

    public Services getIdServicesSignals() {
        return idServicesSignals;
    }

    public void setIdServicesSignals(Services idServicesSignals) {
        this.idServicesSignals = idServicesSignals;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSignals != null ? idSignals.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Signals)) {
            return false;
        }
        Signals other = (Signals) object;
        if ((this.idSignals == null && other.idSignals != null) || (this.idSignals != null && !this.idSignals.equals(other.idSignals))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idServicesSignals.getName()+" > "+name;
    }
    
}
