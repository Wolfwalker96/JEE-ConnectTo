/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.facades;

import connectto.entities.Services;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author cedric.pahud
 */
@Stateless
public class ServicesFacade extends AbstractFacade<Services> {

    @PersistenceContext(unitName = "ConnectToPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicesFacade() {
        super(Services.class);
    }
    
    public Collection<Services> search(String name){
        return search(name, "", "");
    }
    
    public Collection<Services> search(String name, String description, String credential){
        return em.createNamedQuery("Services.search")
                    .setParameter("name", name)
                    .setParameter("description", description)
                    .setParameter("credential", credential)
                    .getResultList();
    }
    
}
