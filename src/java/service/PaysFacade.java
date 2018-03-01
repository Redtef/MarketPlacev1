/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Pays;
import bean.Secteur;
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
public class PaysFacade extends AbstractFacade<Pays> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    @EJB
    ServicePricingFacade servicePricingFacade;

    public BigDecimal getTvaBySecteur(Secteur secteur) {
       return secteur.getVille().getPays().getTva();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaysFacade() {
        super(Pays.class);
    }
    
}
