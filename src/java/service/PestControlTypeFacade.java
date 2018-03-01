/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.PestControlType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class PestControlTypeFacade extends AbstractFacade<PestControlType> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PestControlTypeFacade() {
        super(PestControlType.class);
    }
    
}
