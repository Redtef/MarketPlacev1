/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.ServicePricing;
import java.math.BigDecimal;
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
    @EJB
    PlanningFacade planningFacade;

    public int save(DemandeService demandeService) {
        //genererl id b generate max
        demandeService.setId(generateId("DemandeService", "id"));
        ServicePricing servicePricing = servicePricingFacade.findByDateApplicationAndService(demandeService.getDatedemande(), demandeService.getService());
        calcPrixTtc(servicePricing, demandeService);
        planningFacade.save(demandeService.getPlanning());

        return 1;
    }

    private void calcPrixTtc(ServicePricing servicePricing, DemandeService demandeService) {
        BigDecimal prixTtc = servicePricing.getPrix().add(servicePricing.getPrix().multiply(paysFacade.getTvaBySecteur(demandeService.getSecteur())));
        demandeService.setPrixTtc(prixTtc);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeServiceFacade() {
        super(DemandeService.class);
    }

}
