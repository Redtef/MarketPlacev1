/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Owner;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class OwnerFacade extends AbstractFacade<Owner> {

    @PersistenceContext(unitName = "ServiceMarketv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void save(Owner owner){
        create(owner);
    }

//    
//    public int login(Manager manager) {
//
//        Manager m = (Manager) em.createQuery("SELECT m FROM Manager m WHERE m.id='" + manager.getId() + "'").getSingleResult();
//        if (m == null) {
//            return -1;
//        } else if (m.isBlocked()) {
//            return -2;
//        } else if (!m.getPassword().equals(manager.getPassword())) {
//            return -3;
//        } else {
//            List<Device> device = em.createQuery("SELECT d FROM Device d WHERE d.manager.id='" + manager.getId() + "'").setMaxResults(1).getResultList();
//            if (device.isEmpty()) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }
//    }
    public OwnerFacade() {
        super(Owner.class);
    }
    
}
