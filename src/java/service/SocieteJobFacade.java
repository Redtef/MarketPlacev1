/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Societe;
import bean.SocieteJob;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class SocieteJobFacade extends AbstractFacade<SocieteJob> {

    @EJB
    SocieteFacade societeFacade;

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

//    public void save(List<SocieteJob> jobs,Societe societe) {
//        for (SocieteJob job : jobs) {
//            job.setId(generateId("SocieteJob", "id"));
//            job.setSociete(societe);
//            create(job);
//        }
//    }
    public void save(List<SocieteJob> jobs, Societe societe) {
        societeFacade.save(societe);
        jobs.stream().map((job) -> {
            job.setId(generateId("SocieteJob", "id"));
            return job;
        }).map((job) -> {
            job.setSociete(societe);
            return job;
        }).forEachOrdered((job) -> {
            create(job);
        });
    }

    public List<SocieteJob> findBySociete(Societe societe) {
        return em.createQuery("SELECT sj FROM SocieteJob sj WHERE sj.societe.id ='" + societe.getId() + "'").getResultList();
    }

    public void clone(SocieteJob societeJobSource, SocieteJob societeJobDestination) {
        societeJobDestination.setId(societeJobSource.getId());
        societeJobDestination.setSecteur(societeJobSource.getSecteur());
        societeJobDestination.setService(societeJobSource.getService());
        societeJobDestination.setSociete(societeJobSource.getSociete());

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

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
