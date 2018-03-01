/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Planning;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class PlanningFacade extends AbstractFacade<Planning> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    @EJB
    PlanningItemFacade planningItemFacade;
    

    public int save(Planning planning) {
        planning.setId(generateId("Planning", "id"));
        create(planning);
        if (planning.getDateOnce() == null) {
            planningItemFacade.save(planning, planning.getPlanningItems());
        }        
        return 1;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanningFacade() {
        super(Planning.class);
    }
    
}
