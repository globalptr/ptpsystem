/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.role;

import com.oakeel.ejb.entityAndEao.permission.PermissionEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface RoleEaoLocal {
    public List<RoleEntity> getAllRole();
    public void addNewRole(RoleEntity role);
    public void deleteRole(RoleEntity role);
    public void updateRole(RoleEntity role);
    public void deletePermission(RoleEntity role,PermissionEntity permission);
}
