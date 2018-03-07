/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.ServicePricing;
import java.math.BigDecimal;
import java.util.Date;
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
        
        
        Date date = new Date();
        demandeService.setDatedemande(date);//date actuelle li tdar fiha service
        
        
        
       
        ServicePricing servicePricing = servicePricingFacade.findByDateApplicationAndService(demandeService.getDatedemande(), demandeService.getService());
        demandeService.setPrixHt(servicePricing.getPrix());
        calcPrixTtc(servicePricing, demandeService);
        demandeService.setServicePricing(servicePricing);
        
        planningFacade.save(demandeService.getPlanning());

        create(demandeService);
        return 1;
    }

    private void calcPrixTtc(ServicePricing servicePricing, DemandeService demandeService) {
        BigDecimal tva = paysFacade.getTvaBySecteur(demandeService.getSecteur()).divide(new BigDecimal(100));
        BigDecimal prixTtc = servicePricing.getPrix().add(servicePricing.getPrix().multiply(tva));
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
