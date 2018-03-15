/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dylan.santosde
 */
@Embeddable
public class UsersConnectionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsers")
    private int idUsers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConnections")
    private int idConnections;

    public UsersConnectionsPK() {
    }

    public UsersConnectionsPK(int idUsers, int idConnections) {
        this.idUsers = idUsers;
        this.idConnections = idConnections;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public int getIdConnections() {
        return idConnections;
    }

    public void setIdConnections(int idConnections) {
        this.idConnections = idConnections;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsers;
        hash += (int) idConnections;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersConnectionsPK)) {
            return false;
        }
        UsersConnectionsPK other = (UsersConnectionsPK) object;
        if (this.idUsers != other.idUsers) {
            return false;
        }
        if (this.idConnections != other.idConnections) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "connectto.UsersConnectionsPK[ idUsers=" + idUsers + ", idConnections=" + idConnections + " ]";
    }
    
}
