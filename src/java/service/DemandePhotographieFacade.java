/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandePhotographie;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class DemandePhotographieFacade extends AbstractFacade<DemandePhotographie> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    @EJB
    ClientFacade clientFacade;
    @EJB
    DemandeServiceFacade demandeServiceFacade;
    
    
    public void save(DemandePhotographie demandePhotographie){
        clientFacade.save(demandePhotographie.getDemandeService().getClient());
        demandeServiceFacade.save(demandePhotographie.getDemandeService());
        demandePhotographie.setId(generateId("DemandePhotographie", "id"));
        create(demandePhotographie);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandePhotographieFacade() {
        super(DemandePhotographie.class);
    }
    
}
