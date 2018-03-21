/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Societe;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class SocieteFacade extends AbstractFacade<Societe> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;
    @EJB
    private OwnerFacade ownerFacade;

    public void save(Societe societe) {
        ownerFacade.save(societe.getOwner());
        societe.setId(generateId("Societe", "id"));
        create(societe);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocieteFacade() {
        super(Societe.class);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public OwnerFacade getOwnerFacade() {
        return ownerFacade;
    }

    public void setOwnerFacade(OwnerFacade ownerFacade) {
        this.ownerFacade = ownerFacade;
    }

}
