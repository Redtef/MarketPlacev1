/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Planning;
import bean.PlanningItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class PlanningItemFacade extends AbstractFacade<PlanningItem> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    public void save(Planning planning, List<PlanningItem> planningItems) {
        // findByDateApplicationAndService
        for (PlanningItem planningItem : planningItems) {
            planningItem.setPlanning(planning);
            create(planningItem);
        }
       
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanningItemFacade() {
        super(PlanningItem.class);
    }
    
}
