/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.facades;

import connectto.entities.Actions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dylan.santosde
 */
@Stateless
public class ActionsFacade extends AbstractFacade<Actions> {

    @PersistenceContext(unitName = "ConnectToPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActionsFacade() {
        super(Actions.class);
    }
    
}
