/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dylan.santosde
 */
@Entity
@Table(name = "users_connections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersConnections.findAll", query = "SELECT u FROM UsersConnections u")
    , @NamedQuery(name = "UsersConnections.findByIdUsers", query = "SELECT u FROM UsersConnections u WHERE u.usersConnectionsPK.idUsers = :idUsers")
    , @NamedQuery(name = "UsersConnections.findByIdConnections", query = "SELECT u FROM UsersConnections u WHERE u.usersConnectionsPK.idConnections = :idConnections")
    , @NamedQuery(name = "UsersConnections.findByPublic1", query = "SELECT u FROM UsersConnections u WHERE u.public1 = :public1")})
public class UsersConnections implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersConnectionsPK usersConnectionsPK;
    @Column(name = "public")
    private Boolean public1;
    @JoinColumn(name = "idConnections", referencedColumnName = "idConnections", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Conections conections;
    @JoinColumn(name = "idUsers", referencedColumnName = "idUsers", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UsersConnections() {
    }

    public UsersConnections(UsersConnectionsPK usersConnectionsPK) {
        this.usersConnectionsPK = usersConnectionsPK;
    }

    public UsersConnections(int idUsers, int idConnections) {
        this.usersConnectionsPK = new UsersConnectionsPK(idUsers, idConnections);
    }

    public UsersConnectionsPK getUsersConnectionsPK() {
        return usersConnectionsPK;
    }

    public void setUsersConnectionsPK(UsersConnectionsPK usersConnectionsPK) {
        this.usersConnectionsPK = usersConnectionsPK;
    }

    public Boolean getPublic1() {
        return public1;
    }

    public void setPublic1(Boolean public1) {
        this.public1 = public1;
    }

    public Conections getConections() {
        return conections;
    }

    public void setConections(Conections conections) {
        this.conections = conections;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersConnectionsPK != null ? usersConnectionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersConnections)) {
            return false;
        }
        UsersConnections other = (UsersConnections) object;
        if ((this.usersConnectionsPK == null && other.usersConnectionsPK != null) || (this.usersConnectionsPK != null && !this.usersConnectionsPK.equals(other.usersConnectionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "connectto.UsersConnections[ usersConnectionsPK=" + usersConnectionsPK + " ]";
    }
    
}
