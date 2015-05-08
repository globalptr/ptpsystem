/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.user;

import com.oakeel.ejb.entityAndEao.organization.OrganizationEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface UserEaoLocal {
  
    public void addUser(UserEntity user);
    public Boolean  validateUserByName(String name,String password);
    public Boolean  validateUserByTelephone(String telephone,String password);
    public Boolean  validateUserByEmail(String email,String password);
    public List<UserEntity> getAllUser();
    public void updateUser(UserEntity user);
    public List<UserEntity> getUsersByOrganization(OrganizationEntity org);
    public  List<UserEntity> getUnclassedUser();
    public void deleteUser(UserEntity user);
    public void deleteRole(UserEntity user,RoleEntity role);
    public void addUserRole(UserEntity user,RoleEntity role);
    
}
