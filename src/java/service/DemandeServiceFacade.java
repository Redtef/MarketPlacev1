/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.ServicePricing;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class DemandeServiceFacade extends AbstractFacade<DemandeService> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    @EJB
    ServicePricingFacade servicePricingFacade;
    @EJB
    PaysFacade paysFacade;
    

    public int save(DemandeService demandeService) {
        //genererl id b generate max
       ServicePricing servicePricing= servicePricingFacade.findByDateApplicationAndService(demandeService.getDatedemande(), demandeService.getService());
       demandeService.setPrixTtc(servicePricing.getPrix().multiply(paysFacade.getTvaBySecteur(demandeService.getSecteur())));
       
       return 1;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeServiceFacade() {
        super(DemandeService.class);
    }

}
