/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.permission;

import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface PermissionEaoLocal {
    public void addPermission(PermissionEntity roleResource);
    public void deletePermission(PermissionEntity permission);
    public void updatePermission(PermissionEntity permission);
    public List<PermissionEntity> getAllPermission();
}
