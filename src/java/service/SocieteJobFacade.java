/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Societe;
import bean.SocieteJob;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class SocieteJobFacade extends AbstractFacade<SocieteJob> {

    private Societe societe;
    
    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    public void save(List<SocieteJob> jobs,Societe societe) {
        for (SocieteJob job : jobs) {
            job.setId(generateId("SocieteJob", "id"));
            job.setSociete(societe);
            create(job);
        }
    }

    public List<SocieteJob> findBySociete(Societe societe) {
        return em.createQuery("SELECT sj FROM SocieteJob sj WHERE sj.societe.id ='" +societe.getId()+ "'").getResultList();
    }

    public void clone(SocieteJob societeJobSource, SocieteJob societeJobDestination) {
        societeJobSource.setId(societeJobDestination.getId());
        societeJobSource.setSecteur(societeJobDestination.getSecteur());
        societeJobSource.setService(societeJobDestination.getService());
        societeJobSource.setSociete(societeJobDestination.getSociete());

    }

    public SocieteJob clone(SocieteJob societeJob) {
        SocieteJob cloned = new SocieteJob();
        clone(societeJob, cloned);
        return cloned;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocieteJobFacade() {
        super(SocieteJob.class);
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    
}
