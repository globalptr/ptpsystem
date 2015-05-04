/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndDao.role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class RoleDao implements RoleDaoLocal {
    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;
    @Override
    public void addNewRole(RoleEntity role)
    {
        em.persist(role);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
