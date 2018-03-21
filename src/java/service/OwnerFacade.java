/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Owner;
import controller.util.SearchUtil;
import controller.util.EmailUtil;
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

    public void save(Owner owner) {
        create(owner);
    }

    public int loginId(Owner owner) {

        Owner o = (Owner) em.createQuery("SELECT o FROM Owner o WHERE o.id='" + owner.getId() + "'").getSingleResult();
        if (o == null) {
            return -1;
        } else if (o.isBlocked()) {
            return -2;
        } else if (!o.getPassword().equals(owner.getPassword())) {
            return -3;
//        } else {
//            List<Device> device = em.createQuery("SELECT d FROM Device d WHERE d.manager.id='" + manager.getId() + "'").setMaxResults(1).getResultList();
//            if (device.isEmpty()) {
//                return 0;
        } else {
            return 1;
        }
    }

    public int loginEmail(Owner owner) {
        boolean valid = EmailUtil.emailValidate(owner.getEmail());
        if (!valid) {
            // validate if email is written properly
            return -1;
        } else {
            //email is valid , now check if blocked etc
            Owner o = findByCriteria(owner.getEmail());
            if (o == null) {
                return -2;
            } else if (o.isBlocked()) {
                return -3;
            } else if (!o.getPassword().equals(owner.getPassword())) {
                return -4;
//        } else {
//            List<Device> device = em.createQuery("SELECT d FROM Device d WHERE d.manager.id='" + manager.getId() + "'").setMaxResults(1).getResultList();
//            if (device.isEmpty()) {
//                return 0;
            } else {
                return 1;
            }
        }
    }

    public Owner findByCriteria(String email) {
        String query = "SELECT o FROM Owner o WHERE 1=1";
        query += SearchUtil.addConstraint("o", "email", "=", email);
        return (Owner) em.createQuery(query).getSingleResult();
    }

    public OwnerFacade() {
        super(Owner.class);
    }

}
