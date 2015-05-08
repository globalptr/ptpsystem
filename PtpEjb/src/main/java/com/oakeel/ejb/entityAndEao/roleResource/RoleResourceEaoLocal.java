/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.roleResource;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface RoleResourceEaoLocal {
    public void addRoleResource(RoleResourceEntity roleResource);
    public void deleteRoleResource(RoleResourceEntity roleResource);
    public void updateRoleResource(RoleResourceEntity roleResource);
    public List<RoleResourceEntity> viewAllRoleResource();
}
