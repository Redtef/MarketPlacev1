/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Service;
import bean.ServicePricing;
import controler.util.SearchUtil;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class ServicePricingFacade extends AbstractFacade<ServicePricing> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

//    public ServicePricing findByDateApplicationAndService(Date dateApplication, Service service) {
//        return (ServicePricing) em.createQuery("SELECT item FROM ServicePricing item "
//                + "WHERE item.service.id="+service.getId()+"'").getSingleResult();
//    }
    public ServicePricing findByDateApplicationAndService(Date date, Service service) {
//        return (ServicePricing) em.createQuery("SELECT item FROM ServicePricing item "
//                + "WHERE item.dateApplication < '" + dateApplication + "' and item.service.id="+service.getId()+" ORDER BY item.dateApplication DESC").setMaxResults(1).getSingleResult();
       
        
        String query = "Select item FROM ServicePricing item WHERE 1=1";
        query += SearchUtil.addConstraintDate("item", "dateApplication", "<", date);
        query += "ORDER BY item.dateApplication DESC";
        return (ServicePricing) em.createQuery(query).setMaxResults(1).getSingleResult();
        
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicePricingFacade() {
        super(ServicePricing.class);
    }
    
}
